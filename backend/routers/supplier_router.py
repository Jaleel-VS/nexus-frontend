'''
Proposed routes for supplier endpoints
- get all suppliers
- get supplier by id
- get supplier by username
- create a supplier
'''

from fastapi import APIRouter, Depends, HTTPException, status, FastAPI
from database import db
from models.supplier import SupplierModel
from utils.exceptions import NotFoundError, InternalServerError

app = APIRouter()


# create a supplier
@app.post("/suppliers")
async def create_supplier(supplier: SupplierModel):
    try:
        db.get_collection("suppliers").insert_one(supplier)
        return {"success": True, "message": "Supplier created.", "data": supplier}
    except Exception as e:
        raise InternalServerError(str(e))
    
# get all suppliers
@app.get("/suppliers")
async def get_all_suppliers():
    try:
        suppliers = db.get_collection("suppliers").find()
        if suppliers is None:
            raise NotFoundError("No suppliers found.")
        return {"success": True, "message": "Suppliers found.", "data": suppliers}
    except Exception as e:
        raise InternalServerError(str(e))
    
# get supplier by id
@app.get("/suppliers/{supplier_id}")
async def get_supplier_by_id(supplier_id: str):
    try:
        supplier = db.get_collection("suppliers").find_one({"_id": supplier_id})
        if supplier is None:
            raise NotFoundError("Supplier not found.")
        return {"success": True, "message": "Supplier found.", "data": supplier}
    except Exception as e:
        raise InternalServerError(str(e))
    
# get supplier by username
@app.get("/suppliers/{username}")
async def get_supplier_by_username(username: str):
    try:
        supplier = db.get_collection("suppliers").find_one({"username": username})
        if supplier is None:
            raise NotFoundError("Supplier not found.")
        return {"success": True, "message": "Supplier found.", "data": supplier}
    except Exception as e:
        raise InternalServerError(str(e))