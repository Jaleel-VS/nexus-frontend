from models.base_user import BaseUserModel, BaseUserResponseModel

class InfluencerModel(BaseUserModel):
    first_name: str
    last_name: str
    instagram_handle: str

class InfluencerResponseModel(BaseUserResponseModel):
    first_name: str
    last_name: str
    instagram_handle: str