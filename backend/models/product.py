from typing import List, Optional
from pydantic import BaseModel, Field
from utils.id_generator import generate_id


class ProductModel(BaseModel):
    product_name: str
    product_description: str
    product_price: int
    suppliers: List[str]

class ProductResponseModel(BaseModel):
    id: str
    product_name: str
    product_description: str
    product_price: int
    suppliers: List[str]