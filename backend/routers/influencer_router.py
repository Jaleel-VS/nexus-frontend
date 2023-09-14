from fastapi import APIRouter, Depends, HTTPException, status, FastAPI
from database import db
from utils.exceptions import NotFoundError, InternalServerError


# router definition
app = APIRouter()


# get all influencers


@app.get("/influencers")
async def get_all_influencers():
    try:
        influencers = db.get_collection("influencers").find()
        if influencers is None:
            raise NotFoundError("No influencers found.")
        return {"success": True, "message": "Influencers found.", "data": influencers}
    except Exception as e:
        raise InternalServerError(str(e))


# get influencer by id
@app.get("/influencers/{influencer_id}")
async def get_influencer_by_id(influencer_id: str):
    try:
        influencer = db.get_collection(
            "influencers").find_one({"_id": influencer_id})
        if influencer is None:
            raise NotFoundError("Influencer not found.")
        return {"success": True, "message": "Influencer found.", "data": influencer}
    except Exception as e:
        raise InternalServerError(str(e))

# get influencer by username


@app.get("/influencers/{username}")
async def get_influencer_by_username(username: str):
    try:
        influencer = db.get_collection(
            "influencers").find_one({"username": username})
        if influencer is None:
            raise NotFoundError("Influencer not found.")
        return {"success": True, "message": "Influencer found.", "data": influencer}
    except Exception as e:
        raise InternalServerError(str(e))


# get all voucher requests
@app.get("/influencers/{influencer_id}/voucher-requests")
async def get_all_voucher_requests(influencer_id: str):
    try:
        voucher_requests = db.get_collection(
            "voucher_requests").find({"influencer_id": influencer_id})
        if voucher_requests is None:
            raise NotFoundError("No voucher requests found.")
        return {"success": True, "message": "Voucher requests found.", "data": voucher_requests}
    except Exception as e:
        raise InternalServerError(str(e))

# get all vouchers


@app.get("/influencers/{influencer_id}/vouchers")
async def get_all_vouchers(influencer_id: str):
    try:
        vouchers = db.get_collection("vouchers").find(
            {"influencer_id": influencer_id})
        if vouchers is None:
            raise NotFoundError("No vouchers found.")
        return {"success": True, "message": "Vouchers found.", "data": vouchers}
    except Exception as e:
        raise InternalServerError(str(e))
