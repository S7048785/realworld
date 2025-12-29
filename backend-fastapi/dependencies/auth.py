from fastapi import Depends, HTTPException, status, Request
from fastapi.security import HTTPBearer, HTTPAuthorizationCredentials

from core.utils.jwt import verify_token

security = HTTPBearer()

async def get_current_user(request: Request ):
    # 从请求头获取Authorization值
    auth_header = request.headers.get("Authorization")
    if not auth_header:
        credentials_exception = HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Authorization header missing",
            headers={"WWW-Authenticate": "Bearer"},
        )
        raise credentials_exception
    # 直接使用Authorization头的值作为token
    token = auth_header
    credentials_exception = HTTPException(
        status_code=status.HTTP_401_UNAUTHORIZED,
        detail="Could not validate credentials",
        headers={"WWW-Authenticate": "Bearer"},
    )
    return verify_token(token, credentials_exception)
