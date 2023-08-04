from pydantic import BaseModel, EmailStr


class VoucherModel(BaseModel):
    code: str
    discount: int
    expiration_date: str
    is_used: bool
    user_id: str
    created_at: str
    updated_at: str