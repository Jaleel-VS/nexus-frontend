'''
Proposed routes for user endpoints
- get all users
- get user by id
- get user by username
- get user by email
'''

from fastapi import APIRouter, Depends, HTTPException, status, FastAPI


# router definition
app = APIRouter()



# get all users
@app.get("/users")
async def get_all_users():
    # TODO: implement
    pass

# get user by id
@app.get("/users/{user_id}")
async def get_user_by_id(user_id: int):
    # TODO: implement
    pass

# get user by username
@app.get("/users/{username}")
async def get_user_by_username(username: str):
    # TODO: implement
    pass

# get user by email
@app.get("/users/{email}")
async def get_user_by_email(email: str):
    # TODO: implement
    pass