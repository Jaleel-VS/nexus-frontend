'''
proposed endpoints:
- get all voucher requests
- get voucher request by id
'''


from fastapi import APIRouter, Depends, HTTPException, status, FastAPI
from database import db
from models.voucher_request import VoucherRequest
from utils.exceptions import NotFoundError, InternalServerError
from utils.id_generator import generate_id


# get voucher-request collection
voucher_requests = db.get_collection("voucher_requests")

# router definition
app = APIRouter()

# create a voucher request


@app.post("/voucher-requests")
async def create_voucher_request(voucher_request: VoucherRequest):
    try:
        voucher_request = voucher_request.model_dump()
        voucher_request["_id"] = generate_id(voucher_requests, "vrq")
        print(voucher_request)
        db.get_collection("voucher_requests").insert_one(voucher_request)
        return {"success": True, "message": "Voucher request created.", "data": voucher_request}
    except Exception as e:
        raise InternalServerError(str(e))

# get all voucher requests


@app.get("/voucher-requests")
async def get_all_voucher_requests():
    try:
        voucher_requests = db.get_collection("voucher_requests").find()
        if voucher_requests is None:
            raise NotFoundError("No voucher requests found.")
        
        voucher_requests = list(voucher_requests)

        return {"success": True, "message": "Voucher requests found.", "data": voucher_requests}
    except Exception as e:
        raise InternalServerError(str(e))

# get voucher request by id


@app.get("/voucher-requests/{voucher_request_id}")
async def get_voucher_request_by_id(voucher_request_id: str):
    try:
        voucher_request = db.get_collection("voucher_requests").find_one({
            "_id": voucher_request_id})
        if voucher_request is None:
            raise NotFoundError("Voucher request not found.")
        return {"success": True, "message": "Voucher request found.", "data": voucher_request}
    except Exception as e:
        raise InternalServerError(str(e))

# reject/approve voucher request
@app.put("/voucher-requests/{voucher_request_id}")
async def change_voucher_request_status(voucher_request_id: str, new_status: str):
    try:
        if new_status not in ["REQUESTED", "APPROVED", "DENIED"]:
            raise HTTPException(status_code=status.HTTP_400_BAD_REQUEST, detail="Invalid status.")
        
        # fetch voucher request
        voucher_request = db.get_collection("voucher_requests").find_one({"_id": voucher_request_id})
        if not voucher_request:
            raise NotFoundError("Voucher request not found.")
        
        # update status
        voucher_request["request_status"] = new_status

        # update db
        db.get_collection("voucher_requests").update_one({"_id": voucher_request_id}, {"$set": voucher_request})
        return {"success": True, "message": f"Voucher request status updated to {new_status}."}
    except Exception as e:
        raise InternalServerError(str(e))