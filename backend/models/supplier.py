from models.base_user import BaseUserModel, BaseUserResponseModel


class SupplierModel(BaseUserModel):
    supplier_name: str
    supplier_category: str
    supplier_description: str

class SupplierResponseModel(BaseUserResponseModel):
    supplier_name: str
    supplier_category: str
    supplier_description: str