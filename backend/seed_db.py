from database import db
from utils.id_generator import generate_id


db.connect()

# get a new database


def get_collection(collection_name):
    return db.get_database()[collection_name]


# seed suppliers

# suppliers = [
#     {
#         "_id": generate_id(get_collection("SUPPLIERS"), "sup"),
#         "username": "natureharmony",
#         "password": "password",
#         "email": "natureharmony@gmail.com",
#         "address": "123 Table Mountain Drive, Cape Town, Western Cape, 8001",
#         "role": "SUPPLIER",
#         "supplier_name": "Natures Harmony",
#         "supplier_category": "Fashion and Apparel",
#         "supplier_description": "Natures Harmony is a company that specializes in eco-conscious and sustainable fashion. They offer a range of products made from recycled and environmentally friendly materials.",
#     },
#     {
#         "_id": generate_id(get_collection("SUPPLIERS"), "sup"),
#         "username": "techgenius",
#         "password": "password",
#         "email": "techgenius@gmail.com",
#         "address": "456 Stellenbosch Vineyard Street, Stellenbosch, Western Cape, 7599",
#         "role": "SUPPLIER",
#         "supplier_name": "Tech Genius",
#         "supplier_category": "Electronics and Technology",
#         "supplier_description": "TechGenius is a technology company known for producing high-quality electronic devices. They focus on creating innovative gadgets with top-notch performance and features.",
#     },
#     {
#         "_id": generate_id(get_collection("SUPPLIERS"), "sup"),
#         "username": "fitflex",
#         "password": "password",
#         "email": "789 Plettenberg Bay Road, Plettenberg Bay, Western Cape, 6600",
#         "address": "fitflex@gmail.com",
#         "role": "SUPPLIER",
#         "supplier_name": "Fit Flex",
#         "supplier_category": "Sports and Fitness",
#         "supplier_description": "FitFlex is a fitness equipment and accessories manufacturer. They design and produce fitness products that are durable, comfortable, and suitable for various workout routines.",
#     },
#     {
#         "_id": generate_id(get_collection("SUPPLIERS"), "sup"),
#         "username": "pawslux",
#         "password": "password",
#         "email": "pawslux@gmail.com",
#         "address": "321 Hermanus Whale Watching Avenue, Hermanus, Western Cape, 7200",
#         "role": "SUPPLIER",
#         "supplier_name": "Paws Lux",
#         "supplier_category": "Pet Supplies",
#         "supplier_description": "PawsLux is a luxury pet product company that caters to the needs of pampered pets. They offer a range of premium pet accessories and treats designed to provide comfort and style.",
#     },
#     {
#         "_id": generate_id(get_collection("SUPPLIERS"), "sup"),
#         "username": "eleganceera",
#         "password": "password",
#         "email": "eleganceera@gmail.com",
#         "address": "654 Langebaan Lagoon Lane, Langebaan, Western Cape, 7357",
#         "role": "SUPPLIER",
#         "supplier_name": "Elegance Era",
#         "supplier_category": "Home and Decor",
#         "supplier_description": "EleganceEra is a company specializing in elegant and upscale home decor products. They offer a variety of lighting fixtures and home accessories that add a touch of sophistication to any living space.",
#     },

# ]


# # add suppliers to database

# for supplier in suppliers:
#     get_collection("SUPPLIERS").insert_one(supplier)

# print("suppliers added to database")


# products = [
#     {
#     "_id": generate_id(get_collection("PRODUCTS"), "prod"),
#     "product_name": "Eco-conscious Denim Jacket",
#     "product_description": "Our eco-friendly denim jacket combines style and sustainability. Made from 100% recycled materials.",
#     "product_price": "2800",
#     "suppliers": ["natureharmony"]
#     },

#     {
#     "_id": generate_id(get_collection("PRODUCTS"), "prod"),
#     "product_name": " Organic Cotton Tee",
#     "product_description": "A soft, comfortable T-shirt made from organic cotton, perfect for everyday wear.",
#     "product_price": "1500",
#     "suppliers": ["natureharmony"]
#     },

#     {
#     "_id": generate_id(get_collection("PRODUCTS"), "prod"),
#     "product_name": "Sustainable Linen Trousers",
#     "product_description": "Lightweight trousers crafted from sustainable linen for a relaxed fit and feel.",
#     "product_price": "3200",
#     "suppliers": ["natureharmony"]
#     },


#     {
#     "_id": generate_id(get_collection("PRODUCTS"), "prod"),
#     "product_name": "NovaSmart Phone",
#     "product_description": "Our flagship smartphone offers an impressive display, top-tier camera, and speedy performance.",
#     "product_price": "8000",
#     "suppliers": ["techgenius"]
#     },

#     {
#     "_id": generate_id(get_collection("PRODUCTS"), "prod"),
#     "product_name": "NovaTab Tablet",
#     "product_description": "A high-resolution tablet for work, play, and everything in between.",
#     "product_price": "7000",
#     "suppliers": ["techgenius"]
#     },

#     {
#     "_id": generate_id(get_collection("PRODUCTS"), "prod"),
#     "product_name": "NovaBook Laptop",
#     "product_description": "Our lightweight laptop delivers superior performance for professionals on the go.",
#     "product_price": "9000",
#     "suppliers": ["techgenius"]
#     },

#     {
#     "_id": generate_id(get_collection("PRODUCTS"), "prod"),
#     "product_name": "ZephyrFit Yoga Mat",
#     "product_description": "Experience comfort and stability during your yoga sessions with our high-quality mat.",
#     "product_price": "1700",
#     "suppliers": ["fitflex"]
#     },

#     {
#     "_id": generate_id(get_collection("PRODUCTS"), "prod"),
#     "product_name": "ZephyrFit Kettlebells",
#     "product_description": "Enhance your strength training with our durable and ergonomic kettlebells.",
#     "product_price": "2500",
#     "suppliers": ["fitflex"]
#     },

#     {
#     "_id": generate_id(get_collection("PRODUCTS"), "prod"),
#     "product_name": "ZephyrFit Resistance Bands",
#     "product_description": "Achieve a full-body workout with our set of resistance bands, suitable for all fitness levels.",
#     "product_price": "1000",
#     "suppliers": ["fitflex"]
#     },


#     {
#     "_id": generate_id(get_collection("PRODUCTS"), "prod"),
#     "product_name": "Luxurious Pet Bed",
#     "product_description": "Give your pet the comfort they deserve with our ultra-soft, luxurious pet bed.",
#     "product_price": "4500",
#     "suppliers": ["pawslux"]
#     },

#     {
#     "_id": generate_id(get_collection("PRODUCTS"), "prod"),
#     "product_name": "Gourmet Pet Treats",
#     "product_description": "Our gourmet pet treats are made from premium ingredients that your pet will love.Our gourmet pet treats are made from premium ingredients that your pet will love.",
#     "product_price": "1200",
#     "suppliers": ["pawslux"]
#     },

#     {
#     "_id": generate_id(get_collection("PRODUCTS"), "prod"),
#     "product_name": "Designer Pet Collar",
#     "product_description": "Keep your pet looking stylish with our designer pet collar.",
#     "product_price": "2000",
#     "suppliers": ["pawslux"]
#     },


#     {
#     "_id": generate_id(get_collection("PRODUCTS"), "prod"),
#     "product_name": "Crystal Chandelier",
#     "product_description": "Elevate your home's decor with our stunning crystal chandelier.",
#     "product_price": "9500",
#     "suppliers": ["eleganceera"]
#     },

#     {
#     "_id": generate_id(get_collection("PRODUCTS"), "prod"),
#     "product_name": "Industrial Floor Lamp",
#     "product_description": "Our industrial floor lamp provides stylish and functional lighting for any room.",
#     "product_price": "4500",
#     "suppliers": ["eleganceera"]
#     },

#     {
#     "_id": generate_id(get_collection("PRODUCTS"), "prod"),
#     "product_name": "Vintage Table Lamp",
#     "product_description": "Add a touch of vintage charm to your space with our table lamp.",
#     "product_price": "4000",
#     "suppliers": ["eleganceera"]

#     }
#     ]

# # add products to database

# for product in products:
#     get_collection("PRODUCTS").insert_one(product)

brands = [
    {
        "_id": generate_id(get_collection("BRANDS"), "bra"),
        "username": "EcoBloom",
        "brand_description": "Crafting sustainable, fashionable clothing for the conscious consumer.",
        "brand_category": "Clothing",
        "brand_name": "Eco Bloom",
        "password": "password",
        "email": "ecobloom@gmail.com",
        "address": "1234 Main St, San Francisco, CA 94122",
        "role": "BRAND",
        "products": ["prod_bb935e", "prod_30f482", "prod_44ae80"]

    },
    {
        "_id": generate_id(get_collection("BRANDS"), "bra"),

        "username": "Technova",
        "brand_description": "Innovating the future with cutting-edge electronics and gadgets.",
        "brand_category": "Electronics",
        "brand_name": "Technova",
        "password": "password",
        "email": "technova@gmail.com",
        "address": "35 Fifth Ave, Rondebosch, Cape Town 7800",
        "role": "BRAND",
        "products": ["prod_2ab3bf", "prod_7c46b7", "prod_5aa689"]
    },
    {
        "_id": generate_id(get_collection("BRANDS"), "bra"),

        "username": "Zephyrfit",
        "brand_description": "Bringing you high-quality fitness equipment for your at-home workouts.",
        "brand_category": "Fitness Equipment",
        "brand_name": "Zephyrfit",
        "password": "password",
        "email": "zephyrfit@gmail.com",
        "address": "4 Close St, Claremont, Cape Town 7800",
        "role": "BRAND",
        "products": ["prod_9b8dc9", "prod_294459", "prod_b3c486"]
    },
    {
        "_id": generate_id(get_collection("BRANDS"), "bra"),

        "username": "Petpamper",
        "brand_description": "Elevating your pet's lifestyle with luxury pet products",
        "brand_category": "Pet Supplies",
        "brand_name": "Petpamper",
        "password": "password",
        "email": "petpamper@gmail.com",
        "address": "105 Main St, Kenilworth, Cape Town 7780",
        "role": "BRAND",
        "products": ["prod_2cd322", "prod_610452", "prod_f78874"]
    },
    {
        "_id": generate_id(get_collection("BRANDS"), "bra"),

        "username": "Luxelumina",
        "brand_description": "Illuminating your home with designer lighting fixtures.",
        "brand_category": "Home Decor",
        "brand_name": "Luxelumina",
        "password": "password",
        "email": "luxelumina@gmail.com",
        "address": "103 Long St, Cape Town 7800",
        "role": "BRAND",
        "products": ["prod_5fef52", "prod_22d0e5", "prod_f11893"]
    }
]

# add brands to database

for brand in brands:
    get_collection("BRANDS").insert_one(brand)


