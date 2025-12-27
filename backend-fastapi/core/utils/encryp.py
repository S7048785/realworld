# 初始化密码哈希上下文
from passlib.context import CryptContext

pwd_context = CryptContext(schemes=["bcrypt"], deprecated="auto")

def get_password_hash(password: str) -> str:
    """将密码转为哈希值（存储）"""
    return pwd_context.hash(password)

def verify_password(plain_password: str, hashed_password: str) -> bool:
    """验证密码是否匹配哈希值"""
    return pwd_context.verify(plain_password, hashed_password)
