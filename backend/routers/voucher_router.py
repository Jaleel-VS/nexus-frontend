'''
Proposed routes for voucher endpoints
- get all vouchers
- get voucher by id
'''

from fastapi import APIRouter, Depends, HTTPException, status, FastAPI
from database import db
from models.voucher import VoucherModel
from utils.exceptions import NotFoundError, InternalServerError

# router definition
app = APIRouter()



# create a voucher
@app.post("/vouchers")
async def create_voucher(voucher: VoucherModel):
    try:
        db.get_collection("vouchers").insert_one(voucher)
        return {"success": True, "message": "Voucher created.", "data": voucher}
    except Exception as e:
        raise InternalServerError(str(e))


# get all voucher requests
@app.get("/vouchers")
async def get_all_vouchers():
    try:
        vouchers = db.get_collection("vouchers").find()
        if vouchers is None:
            raise NotFoundError("No vouchers found.")
        return {"success": True, "message": "Vouchers found.", "data": vouchers}
    except Exception as e:
        raise InternalServerError(str(e))


# get voucher request by id
@app.get("/vouchers/{voucher_id}")
async def get_voucher_by_id(voucher_id: int):
    try:
        voucher = db.get_collection("vouchers").find_one({"_id": voucher_id})
        if voucher is None:
            raise NotFoundError("Voucher not found.")
        return {"success": True, "message": "Voucher found.", "data": voucher}
    except Exception as e:
        raise InternalServerError(str(e))
    
# change voucher status
@app.put("/vouchers/{voucher_id}")
async def change_voucher_status(voucher_id: int, status: str):
    try:
        voucher = db.get_collection("vouchers").find_one({"_id": voucher_id})
        if voucher is None:
            raise NotFoundError("Voucher not found.")
        db.get_collection("vouchers").update_one({"_id": voucher_id}, {"$set": {"status": status}})
        return {"success": True, "message": "Voucher status updated.", "data": voucher}
    except Exception as e:
        raise InternalServerError(str(e))
