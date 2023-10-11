package com.nexus.nexusapi.web3;

import org.springframework.stereotype.Service;
import org.web3j.abi.datatypes.Bool;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Numeric;

import com.nexus.nexusapi.web3.contracts.Voucher;
import com.nexus.nexusapi.web3.contracts.Voucher.VoucherData;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@Service
public class Web3Manager {
    private final String ALCHEMY_API_URL = "https://eth-sepolia.g.alchemy.com/v2/rwHETGslhGFm8yUHbrOv3IvyHs2VRDGt";
    private final String OWNER_PRIVATE_KEY = "cd8939c98ffa0d4b475d6aeceddc40816bfa44bd3e400b72b2b0b5dfcd6f1886";
    private final String IPFS_PREFIX = "https://ivory-coloured-lobster-148.mypinata.cloud/ipfs/";
    private final String IMG_URL = "https://ivory-coloured-lobster-148.mypinata.cloud/ipfs/QmRRRVTsAV3N3mvkXvAYe6cGCUgw6pTyC8mpvG6bbdKx3t";

    private final Map<String, String> contractAddresses = new HashMap<>();

    private Web3j web3j;
    private final Voucher voucherContract;

    public Web3Manager() throws Exception {
        web3j = Web3j.build(new HttpService(ALCHEMY_API_URL));
        Credentials credentials = Credentials.create(OWNER_PRIVATE_KEY);

        contractAddresses.put("voucher", "0x4EfCaBa6842489958E9b09feaeD3d9a77499455D");

        voucherContract = Voucher.load(contractAddresses.get("voucher"), web3j, credentials, new DefaultGasProvider());
    }

    public ArrayList<String> mintVoucher(String influencerID, String brandID, String supplierIds, String productID, Integer price, 
     Long expiryDateLong, String requestAddress) {

        


        System.out.println("Minting voucher...");
        VoucherData voucherData = new VoucherData(
            brandID,
            influencerID,
            supplierIds,
            productID,
            BigInteger.valueOf(price),
            false,
            BigInteger.valueOf(expiryDateLong)
        );

        StringBuilder description = new StringBuilder();

        description.append("Brand ID: ").append(brandID).append("; ");
        description.append("Influencer ID: ").append(influencerID).append("; ");
        description.append("Supplier IDs: ").append(supplierIds).append("; ");
        description.append("Product ID: ").append(productID).append("; ");
        description.append("Price: ").append(price).append("; ");
        description.append("Redeemed: ").append(false).append("; ");
        description.append("Expiry Date: ").append(expiryDateLong).append("; ");
        
        LinkedHashMap<String, String> jsonContent = new LinkedHashMap<>();
        jsonContent.put("name", "Nexus Voucher");
        jsonContent.put("image", IMG_URL);
        jsonContent.put("description", description.toString());

        PinataUploader pinataUploader = new PinataUploader(jsonContent);

        System.out.println("Uploading to IPFS...");

        String tokenURI = IPFS_PREFIX + pinataUploader.request();    

        System.out.println("Uploaded to IPFS!");
                

        TransactionReceipt receipt = voucherContract.mintVoucher(requestAddress, tokenURI, voucherData).sendAsync()
                .join();

        System.out.println("Voucher minted!");

   


        ArrayList<String> idAndHash = new ArrayList<>();

        idAndHash.add(receipt.getTransactionHash());

        String voucherId = receipt.getLogs().get(2).getTopics().get(1);
        int intValue = Numeric.decodeQuantity(voucherId).intValue();

        voucherId = Integer.toString(intValue);

        System.out.println("Voucher ID: " + voucherId);

        idAndHash.add(voucherId);


        return idAndHash;

    }

    public String redeemVoucher(BigInteger voucherId, String supplierAddress, String brandID, String influencerID, String productID, Integer price, Long expiryDateLong) {
        System.out.println("Redeeming voucher...");

        String tokenURI = getTokenURI(true, brandID, influencerID, supplierAddress, productID, price, expiryDateLong);

        TransactionReceipt receipt = voucherContract.redeem(voucherId, "0xB6033c36754396957d3a2F307e9536074cd2d695", tokenURI).sendAsync().join();

        System.out.println("Voucher redeemed!");

        return  receipt.getTransactionHash();
    }

    private String getTokenURI(Boolean redeemed, String brandID, String influencerID, String supplierId, String productID, Integer price, Long expiryDateLong) {

        StringBuilder description = new StringBuilder();
    
        description.append("Brand ID: ").append(brandID).append("; ");
        description.append("Influencer ID: ").append(influencerID).append("; ");
        description.append("Supplier ID: ").append(supplierId).append("; ");
        description.append("Product ID: ").append(productID).append("; ");
        description.append("Price: ").append(price).append("; ");
        description.append("Redeemed: ").append(redeemed).append("; ");
        description.append("Expiry Date: ").append(expiryDateLong).append("; ");
    
        // Debug statements to print the values
        System.out.println("Description: " + description.toString());
        System.out.println("Price: " + price);
        System.out.println("Expiry Date: " + expiryDateLong);
    
        LinkedHashMap<String, String> jsonContent = new LinkedHashMap<>();
        jsonContent.put("name", "Nexus Voucher");
        jsonContent.put("description", description.toString());
        jsonContent.put("image", IMG_URL);
    
        // Debug statement to print JSON content
        System.out.println("JSON Content: " + jsonContent);
    
        PinataUploader pinataUploader = new PinataUploader(jsonContent);
    
        String tokenURI = IPFS_PREFIX + pinataUploader.request();
    
        // Debug statement to print final token URI
        System.out.println("Token URI: " + tokenURI);
    
        return tokenURI;
    }
    
    public String getVoucherData(BigInteger voucherId) {
        try {
            VoucherData voucherData = voucherContract.getVoucher(voucherId).sendAsync().join();

            String brandID = voucherData.brandID;
            String influencerID = voucherData.influencerID;
            String supplierIds = voucherData.supplierIDs;
            String productID = voucherData.productID;
            Integer price = voucherData.price.intValue();
            Boolean redeemed = voucherData.redeemed;
            Long expiryDateLong = voucherData.expiryDate.longValue();

            return "{\"brandID\": \"" + brandID + "\", \"influencerID\": \"" + influencerID + "\", \"supplierIds\": \"" + supplierIds + "\", \"productID\": \"" + productID + "\", \"price\": \"" + price + "\", \"redeemed\": \"" + redeemed + "\", \"expiryDate\": \"" + expiryDateLong + "\"}";

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            Web3Manager web3Manager = new Web3Manager();

            // mint voucher
            ArrayList<String> idAndHash = web3Manager.mintVoucher("0x1", "0x2", "0x3", "0x4", 100, 1697692010L, "0x5");


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
