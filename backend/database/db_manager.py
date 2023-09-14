import os
import logging
from urllib.parse import quote_plus
from pymongo import MongoClient, ReturnDocument
from pymongo.database import Database
from pymongo.errors import ConnectionFailure
from dotenv import load_dotenv
from typing import Optional

load_dotenv()

MONGO_PASSWORD = os.getenv('MONGO_PASSWORD')
MONGO_DBNAME = os.getenv('MONGO_DBNAME')
MONGO_URL = ""

# Set up a basic logging configuration
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

if MONGO_PASSWORD and MONGO_DBNAME:
    MONGO_URL = f"mongodb+srv://jaleel_admin:{quote_plus(MONGO_PASSWORD)}@serverlessinstance0.ksq2r.mongodb.net/?retryWrites=true&w=majority"

class MongoManager:
    def __init__(self) -> None:
        self.mongo_client = None
        self.db = None

    def connect(self):
        if not self.mongo_client:
            try:
                self.mongo_client = MongoClient(MONGO_URL)
                self.mongo_client.admin.command('ismaster')
                logger.info("Connection established.")
            except ConnectionFailure as e:
                logger.error(f"Unable to connect to database: {str(e)}")
                return

            if self.mongo_client and MONGO_DBNAME:
                self.db = self.mongo_client[MONGO_DBNAME]

    def disconnect(self):
        if self.mongo_client:
            self.mongo_client.close()
            self.mongo_client = None
            self.db = None
            logger.info("Connection closed.")
        else:
            logger.warning("Attempted to close a non-existing connection.")

    def get_database(self):
        if self.db is None:
            self.connect()
        return self.db
    
    def get_collection(self, collection_name: str):
        collection_name = collection_name.upper()
        if self.db is None:
            self.connect()
        return self.db[collection_name]