from datetime import timedelta, datetime
from typing import Optional

from jose import jwt, JWTError
import os
from dotenv import load_dotenv

load_dotenv()
SECRET_KEY = os.getenv("SECRET_KEY")
ALGORITHM = os.getenv("ALGORITHM")
ACCESS_TOKEN_EXPIRE_MINUTES = int(os.getenv("ACCESS_TOKEN_EXPIRE_MINUTES", 30))

def create_access_token(user_id: int, expires_delta: Optional[timedelta] = None) -> str:
    """
    生成JWT Token

    Args:
        user_id (int): 用户ID
        expires_delta (Optional[timedelta], optional): token过期时间间隔，如果为None则使用配置的默认值

    Returns:
        str: 编码后的JWT token字符串
    """
    to_encode = {"sub": str(user_id)}
    if expires_delta:
        expire = datetime.utcnow() + expires_delta
    else:
        expire = datetime.utcnow() + timedelta(minutes=ACCESS_TOKEN_EXPIRE_MINUTES)
    to_encode.update({"exp": expire})
    encoded_jwt = jwt.encode(to_encode, SECRET_KEY, algorithm=ALGORITHM)
    return encoded_jwt


def verify_token(token: str, credentials_exception) -> dict:
    """
    验证Token有效性

    参数:
        token (str): 需要验证的JWT令牌
        credentials_exception: 凭证异常对象，当验证失败时抛出

    返回值:
        dict: 包含用户名的字典，格式为{"user_id": user_id}
    """
    try:
        print(token)
        payload = jwt.decode(token, SECRET_KEY, algorithms=[ALGORITHM])
        # 从Token中提取用户名（sub=subject）
        user_id: str = payload.get("sub")
        if user_id is None:
            raise credentials_exception
    except JWTError:
        raise credentials_exception
    return {"user_id": user_id}