from fastapi import APIRouter, Depends, HTTPException, status, FastAPI
from models.product import ProductModel, ProductResponseModel
from database import db
from utils.exceptions import NotFoundError, InternalServerError


# router definition
app = APIRouter()

# get all
@app.get("/products")
def get_all_products():
    try:
        products = db.get_collection("products").find()
        if not products:
            raise NotFoundError("No products found.")
        
        products = list(products)
        
        return {"success": True, "message": "Products found", "data": products}

    except Exception as e:
        raise InternalServerError(str(e))




# get by id
@app.get("/products/{product_id}")
def get_product_by_id(product_id: str):
    try:
        product = db.get_collection("products").find_one({"_id": product_id})
        if not product:
            raise NotFoundError("Product not found.")
        
        return {"success": False, "message": "Product found", "data": product}

    except Exception as e:
        raise InternalServerError(str(e))


