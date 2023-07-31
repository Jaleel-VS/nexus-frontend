from fastapi import FastAPI
from routers import (brand_router as brand, 
                     supplier_router as supplier, 
                     influencer_router as influencer, 
                     product_router as products, 
                     voucher_request_router as voucher_requests,
                     voucher_router as voucher,)
from fastapi.middleware.cors import CORSMiddleware
import uvicorn
import socket
from database import db_manager as db
from utils.exceptions import NotFoundError, InternalServerError
from fastapi import HTTPException, status
from fastapi.responses import JSONResponse





app = FastAPI()

# establish connection to the db
@app.on_event("startup")
def startup():
    db.connect()


@app.exception_handler(NotFoundError)
async def not_found_exception_handler(request, exc):
    return JSONResponse(status_code=status.HTTP_404_NOT_FOUND, content={"success": False, "message": exc.detail})

@app.exception_handler(InternalServerError)
async def internal_server_error_handler(request, exc):
    return JSONResponse(status_code=status.HTTP_500_INTERNAL_SERVER_ERROR, content={"success": False, "message": exc.detail})




# Configure CORS
origins = [
    "http://localhost",
    "http://localhost:8080",
    "http://localhost:4100",
    "http://127.0.0.1:5500",
    # Add more origins as needed
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["GET", "POST", "PUT", "DELETE"],
    allow_headers=["*"],
)




routers = [brand, supplier, influencer, products, voucher_requests, voucher]

for router in routers:
    app.include_router(router.app)

def find_open_port(start_port):
    port = start_port
    while True:
        try:
            with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
                s.bind(('localhost', port))
                return port
        except OSError:
            port += 1

port = find_open_port(8000)

if __name__ == "__main__":
    uvicorn.run(app, host="localhost", port=port)