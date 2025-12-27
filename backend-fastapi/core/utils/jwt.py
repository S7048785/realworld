from datetime import timedelta, datetime
from typing import Optional

from jose import jwt, JWTError
import os

SECRET_KEY = os.getenv("SECRET_KEY")
ALGORITHM = os.getenv("ALGORITHM")

def create_access_token(data: dict, expires_delta: Optional[timedelta] = None) -> str:
    """
    生成JWT Token

    Args:
        data (dict): 要编码到token中的数据字典
        expires_delta (Optional[timedelta], optional): token过期时间间隔，如果为None则默认30分钟

    Returns:
        str: 编码后的JWT token字符串
    """
    to_encode = data.copy()
    if expires_delta:
        expire = datetime.utcnow() + expires_delta
    else:
        expire = datetime.utcnow() + timedelta(minutes=30)
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
        dict: 包含用户名的字典，格式为{"username": username}
    """
    try:
        payload = jwt.decode(token, SECRET_KEY, algorithms=[ALGORITHM])
        # 从Token中提取用户名（sub=subject）
        username: str = payload.get("sub")
        if username is None:
            raise credentials_exception
    except JWTError:
        raise credentials_exception
    return {"username": username}

