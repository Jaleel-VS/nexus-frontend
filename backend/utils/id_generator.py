from pymongo.errors import DuplicateKeyError
import uuid

def generate_id(collection, prefix):
    while True:
        new_id = f"{prefix}_{str(uuid.uuid4())[:6]}"
        # Try to insert a document with the new_id
        try:
            collection.insert_one({"_id": new_id, "temp": True})
            # If successful, delete the temp document and return the new_id
            collection.delete_one({"_id": new_id})
            return new_id
        except DuplicateKeyError:
            # If a DuplicateKeyError is raised, the loop will simply try again with a new ID
            continue