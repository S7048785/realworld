from pydantic import BaseModel, EmailStr


class UserRegister(BaseModel):
    email: EmailStr
    password: str


user = UserRegister(**{
    "email": "test@example.com",
    "password": "123456",
})

print(user.email)
print(user.email.split("@")[0])
