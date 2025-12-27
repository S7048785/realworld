# schemas/validators.py
from pydantic import BaseModel, validator
from typing import List
import json


class StringArrayValidator(BaseModel):
    """字符串数组验证器"""
    data: List[str]

    @validator('data', each_item=True)
    def validate_item_is_string(cls, v):
        if not isinstance(v, str):
            raise ValueError('所有数组元素必须是字符串')
        return v

    @classmethod
    def from_json_string(cls, json_str: str):
        parsed_data = json.loads(json_str)
        return cls(data=parsed_data)
