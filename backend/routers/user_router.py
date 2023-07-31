'''
Proposed routes for user endpoints
- get all users
- get user by id
- get user by username
- get user by email
'''

from typing import Union
from fastapi import APIRouter, Depends, HTTPException, status, FastAPI
from models.base_user import UserRole
from models.base_user import BaseUserLoginModel, BaseUserResponseModel
from models.brand import BrandResponseModel
from models.influencer import InfluencerResponseModel
from models.supplier import SupplierResponseModel
from database import db


# router definition
app = APIRouter()


# get user from db
async def get_user(username: str):
    for collection in ["influencers", "suppliers", "brands"]:
        user = db.get_collection(collection).find_one({"username": username})
        if user is not None:
            return user
    return None

# login
@app.post("/login", response_model=Union[BrandResponseModel, InfluencerResponseModel, SupplierResponseModel])
async def login(user: BaseUserLoginModel):
    try:
        this_user = await get_user(user.username)

        if this_user is None:
            raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="User not found")
        
        if this_user['password'] != user.password:
            raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail="Incorrect password")
        
        if this_user['role'] == UserRole.BRAND:
            return BrandResponseModel(**this_user)
        elif this_user['role'] == UserRole.INFLUENCER:
            return InfluencerResponseModel(**this_user)
        elif this_user['role'] == UserRole.SUPPLIER:
            return SupplierResponseModel(**this_user)
    
    except Exception as e:
        raise HTTPException(status_code=status.HTTP_500_INTERNAL_SERVER_ERROR, detail=str(e))






# # get all users
# @app.get("/users")
# async def get_all_users():
#     # TODO: implement
#     pass

# # get user by id
# @app.get("/users/{user_id}")
# async def get_user_by_id(user_id: int):
#     # TODO: implement
#     pass

# # get user by username
# @app.get("/users/{username}")
# async def get_user_by_username(username: str):
#     # TODO: implement
#     pass

# # get user by email
# @app.get("/users/{email}")
# async def get_user_by_email(email: str):
#     # TODO: implement
#     pass