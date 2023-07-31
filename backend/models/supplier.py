from models.base_user import BaseUserModel


class SupplierModel(BaseUserModel):
    supplier_name: str
    supplier_category: str
    supplier_description: str