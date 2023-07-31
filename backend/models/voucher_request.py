from enum import Enum
from pydantic import BaseModel
from typing import Optional
from datetime import datetime

class RequestStatus(str, Enum):
    REQUESTED = "REQUESTED"
    APPROVED = "APPROVED"
    DENIED = "DENIED"

class VoucherRequest(BaseModel):
    influencer_id: str
    brand_id: str
    supplier_id: str
    product_id: str
    request_date: datetime
    request_status: RequestStatus
    created_at: datetime