/*
 * Seed the database with some initial data
 * Product
 * 
 * _id

*/

package com.nexus.nexusapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nexus.nexusapi.model.Brand;
import com.nexus.nexusapi.model.Influencer;
import com.nexus.nexusapi.model.Product;
import com.nexus.nexusapi.model.Supplier;
import com.nexus.nexusapi.repository.BrandRepository;
import com.nexus.nexusapi.repository.InfluencerRepository;
import com.nexus.nexusapi.repository.ProductRepository;
import com.nexus.nexusapi.repository.SupplierRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;
    // Create a list of products, string
    String[] productNames = {
            "Eco-conscious Denim Jacket",
            "Organic Cotton Tee",
            "Sustainable Linen Trousers",
            "NovaSmart Phone",
            "NovaTab Tablet",
            "NovaBook Laptop",
            "ZephyrFit Yoga Mat",
            "ZephyrFit Kettlebells",
            "ZephyrFit Resistance Bands",
            "Luxurious Pet Bed",
            "Gourmet Pet Treats",
            "Designer Pet Collar",
            "Crystal Chandelier",
            "Industrial Floor Lamp",
            "Vintage Table Lamp"
    };

    String[] productDescriptions = {
            "Our eco-friendly denim jacket combines style and sustainability. Made …",
            "A soft, comfortable T-shirt made from organic cotton, perfect for ever…",
            "Lightweight trousers crafted from sustainable linen for a relaxed fit …",
            "Our flagship smartphone offers an impressive display, top-tier camera,…",
            "A high-resolution tablet for work, play, and everything in between.",
            "Our lightweight laptop delivers superior performance for professionals…",
            "Experience comfort and stability during your yoga sessions with our hi…",
            "Enhance your strength training with our durable and ergonomic kettlebe…",
            "Achieve a full-body workout with our set of resistance bands, suitable…",
            "Give your pet the comfort they deserve with our ultra-soft, luxurious …",
            "Our gourmet pet treats are made from premium ingredients that your pet…",
            "Keep your pet looking stylish with our designer pet collar.",
            "Elevate your home's decor with our stunning crystal chandelier.",
            "Our industrial floor lamp provides stylish and functional lighting for…",
            "Add a touch of vintage charm to your space with our table lamp."
    };

    String[] productPrices = {
            "2800",
            "1500",
            "3200",
            "8000",
            "7000",
            "9000",
            "1700",
            "2500",
            "1000",
            "4500",
            "1200",
            "2000",
            "9500",
            "4500",
            "4000"
    };

    @Autowired
    private InfluencerRepository influencerRepository;

    // Create a list of influencers, string
    String[] influencerUsernames = { "jakebaldino", "janine_ellis" };
    String[] influencerFirstNames = { "Jake", "Janine" };
    String[] influencerLastNames = { "Baldino", "Ellis" };
    String[] influencerPasswords = { "password", "password" };
    String[] influencerAddresses = {
            "1234 Main St, Cape Town, 8000",
            "893 Main St, Cape Town, 8000"
    };
    String[] influencerInstagramHandles = { "@jakebaldino", "@janine_ellis" };

    @Autowired
    private SupplierRepository supplierRepository;
    // Create a list of suppliers, string
    String[] supplierUsernames = { "NatureHarmony", "TechGenius", "FitFlex", "PawsLux", "EleganceEra" };
    String[] supplierDescriptions = {
            "Natures Harmony is a company that specializes in eco-conscious and sustainable fashion.",
            "TechGenius is a technology company known for producing high-quality electronics and gadgets.",
            "FitFlex is a fitness equipment and accessories manufacturer. They design and manufacture high-quality fitness equipment.",
            "PawsLux is a luxury pet product company that caters to the needs of pampered pets.",
            "EleganceEra is a company specializing in elegant and upscale home decor."
    };
    String[] supplierCategories = { "Fashion and Apparel", "Electronics and Technology", "Sports and Fitness",
            "Pet Supplies", "Home and Decor" };
    String[] supplierNames = { "Natures Harmony", "Tech Genius", "Fit Flex", "Paws Lux", "Elegance Era" };
    String[] supplierPasswords = { "password", "password", "password", "password", "password" };
    String[] supplierAddresses = {
            "123 Table Mountain Drive, Cape Town, Western Cape, 8001",
            "456 Stellenbosch Vineyard Street, Stellenbosch, Western Cape, 7599",
            "789 Plettenberg Bay Road, Plettenberg Bay, Western Cape, 6600",
            "321 Hermanus Whale Watching Avenue, Hermanus, Western Cape, 7200",
            "654 Langebaan Lagoon Lane, Langebaan, Western Cape, 7357"
    };

    @Autowired
    private BrandRepository brandRepository;
    // Create a list of brands, string
    String[] brandUsernames = { "EcoBloom", "Technova", "Zephyrfit", "Petpamper", "Luxelumina" };
    String[] brandDescriptions = {
            "Crafting sustainable, fashionable clothing for the conscious consumer.",
            "Innovating the future with cutting-edge electronics and gadgets.",
            "Bringing you high-quality fitness equipment for your at-home workouts.",
            "Elevating your pet's lifestyle with luxury pet products",
            "Illuminating your home with designer lighting fixtures."
    };
    String[] brandCategories = { "Clothing", "Electronics", "Fitness Equipment", "Pet Supplies", "Home Decor" };
    String[] brandNames = { "Eco Bloom", "Technova", "Zephyrfit", "Petpamper", "Luxelumina" };
    String[] brandPasswords = { "password", "password", "password", "password", "password" };
    String[] brandAddresses = {
            "1234 Main St, San Francisco, CA 94122",
            "35 Fifth Ave, Rondebosch, Cape Town 7800",
            "4 Close St, Claremont, Cape Town 7800",
            "105 Main St, Kenilworth, Cape Town 7780",
            "103 Long St, Cape Town 7800"
    };

    @Override
    public void run(String... args) throws Exception {
        if (brandRepository.count() == 0 && supplierRepository.count() == 0 && influencerRepository.count() == 0 && productRepository.count() == 0) {
            Supplier[] suppliers = new Supplier[supplierUsernames.length];
            for (int i = 0; i < 5; i++) {
                Brand brand = new Brand();
                brand.setUsername(brandUsernames[i]);
                brand.setBrandDescription(brandDescriptions[i]);
                brand.setBrandCategory(brandCategories[i]);
                brand.setBrandName(brandNames[i]);
                brand.setPassword(brandPasswords[i]);
                brand.setEmail(brandUsernames[i] + "@gmail.com");
                brand.setAddress(brandAddresses[i]);
                brandRepository.save(brand);

                Supplier supplier = new Supplier();
                supplier.setUsername(supplierUsernames[i]);
                supplier.setSupplierDescription(supplierDescriptions[i]);
                supplier.setSupplierCategory(supplierCategories[i]);
                supplier.setSupplierName(supplierNames[i]);
                supplier.setPassword(supplierPasswords[i]);
                supplier.setEmail(supplierUsernames[i] + "@gmail.com");
                supplier.setAddress(supplierAddresses[i]);
                suppliers[i] = supplier;
                supplierRepository.save(supplier);
            }

            for (int i = 0; i < 2; i++) {
                Influencer influencer = new Influencer();
                influencer.setUsername(influencerUsernames[i]);
                influencer.setFirstName(influencerFirstNames[i]);
                influencer.setLastName(influencerLastNames[i]);
                influencer.setPassword(influencerPasswords[i]);
                influencer.setEmail(influencerUsernames[i] + "@gmail.com");
                influencer.setAddress(influencerAddresses[i]);
                influencer.setInstagramHandle(influencerInstagramHandles[i]);
                influencerRepository.save(influencer);
            }

            for (int i = 0; i < 15; i++) {
                Product product = new Product();
                product.setProductName(productNames[i]);
                product.setProductDescription(productDescriptions[i]);
                product.setProductPrice(Double.parseDouble(productPrices[i]));

                // Assigning suppliers based on the product index
                if (i < 3) {
                    product.getSuppliers().add(suppliers[0]); // natureharmony
                } else if (i >= 3 && i < 6) {
                    product.getSuppliers().add(suppliers[1]); // techgenius
                } else if (i >= 6 && i < 9) {
                    product.getSuppliers().add(suppliers[2]); // fitflex
                } else if (i >= 9 && i < 12) {
                    product.getSuppliers().add(suppliers[3]); // pawslux
                } else {
                    product.getSuppliers().add(suppliers[4]); // eleganceera
                }

                productRepository.save(product);
            }

        } else {
            System.out.println("Database already seeded.");
        }

    }

}
