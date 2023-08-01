from models.base_user import BaseUserModel, BaseUserResponseModel

class BrandModel(BaseUserModel):
    brand_name: str
    brand_category: str
    brand_description: str
    products: list

class BrandResponseModel(BaseUserResponseModel):
    brand_name: str
    brand_category: str
    brand_description: str
    products: list
