'''
Proposed routes for brand endpoints
- get all brands
- create a brand
- get voucher requests (get all voucher requests made to a brand)
- reject/approve voucher request
- get all vouchers
'''

from fastapi import APIRouter, Depends, HTTPException, status, FastAPI
from models.brand import BrandModel
from database import db
from utils.exceptions import NotFoundError, InternalServerError


# router definition
app = APIRouter()

# get all brands
@app.get("/brands")
async def get_all_brands():
    try:
        brands = db.get_collection("brands").find()
        if not brands:
            raise NotFoundError("No brands found.")
        
        return {"success": False, "message": "Brands found", "data": brands}

    except Exception as e:
        raise InternalServerError(str(e))

    

# create a brand
@app.post("/brands")
async def create_brand(brand: BrandModel):
    try:
        # check if brand already exists, username
        brand_exists = db.get_collection("brands").find_one({"username": brand.username})
        if brand_exists:
            raise HTTPException(status_code=status.HTTP_400_BAD_REQUEST, detail="Brand already exists.")
        db.get_collection("brands").insert_one(brand)
        return {"success": True, "message": "Brand created.", "data": brand}
    except Exception as e:
        raise InternalServerError(str(e))
    

# get voucher requests (get all voucher requests made to a brand)
@app.get("/brands/{brand_id}/voucher-requests")
async def get_voucher_requests(brand_id: int):
    try:
        voucher_requests = db.get_collection("voucher_requests").find({"brand_id": brand_id})
        if not voucher_requests:
            raise NotFoundError("No voucher requests found.")
        
        return {"success": False, "message": "Voucher requests found", "data": voucher_requests}
    except Exception as e:
        raise InternalServerError(str(e))



# get all vouchers
@app.get("/brands/{brand_id}/vouchers")
async def get_all_vouchers(brand_id: int):
    try:
        vouchers = db.get_collection("vouchers").find({"brand_id": brand_id})
        if not vouchers:
            raise NotFoundError("No vouchers found.")
        
        return {"success": False, "message": "Vouchers found", "data": vouchers}
    except Exception as e:
        raise InternalServerError(str(e))