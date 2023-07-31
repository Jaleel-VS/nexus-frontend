from models.base_user import BaseUserModel, BaseUserResponseModel

class InfluencerModel(BaseUserModel):
    instrargam_handle: str

class InfluencerResponseModel(BaseUserResponseModel):
    instagram_handle: str