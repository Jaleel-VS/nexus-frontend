from models.base_user import BaseUserModel

class BrandModel(BaseUserModel):
    brand_category: str
    brand_description: str
    associated_products: list

