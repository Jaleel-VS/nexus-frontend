from models.base_user import BaseUserModel, BaseUserResponseModel

class BrandModel(BaseUserModel):
    brand_category: str
    brand_description: str
    associated_products: list

class BrandResponseModel(BaseUserResponseModel):
    brand_category: str
    brand_description: str
    products: list
