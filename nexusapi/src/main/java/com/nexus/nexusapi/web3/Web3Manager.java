package com.nexus.nexusapi.web3;

import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import com.nexus.nexusapi.web3.contracts.Escrow;
import com.nexus.nexusapi.web3.contracts.Voucher;
import com.nexus.nexusapi.web3.contracts.ZARToken;
import com.nexus.nexusapi.web3.contracts.Voucher.VoucherData;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

// Token address: 0xBCb82c10C4e7F6d5c4bd7cD78FB664C65E1534c0
// Voucher address: 0x894a91e60CBeB2Dd1DC9A6299C6d161BcF08c669
// Escrow address: 0xB05d43B064E9a6d5Bcd59352f69A045c2445290A

@Service
public class Web3Manager {
    private final String ALCHEMY_API_URL = "https://eth-sepolia.g.alchemy.com/v2/rwHETGslhGFm8yUHbrOv3IvyHs2VRDGt";
    private final String OWNER_PRIVATE_KEY = "cd8939c98ffa0d4b475d6aeceddc40816bfa44bd3e400b72b2b0b5dfcd6f1886";

    private final Map<String, String> contractAddresses = new HashMap<>();

    private final String tokenURI = "https://ivory-coloured-lobster-148.mypinata.cloud/ipfs/QmYNZfoybMxiKqxhkMDde5w7ogD7w31fyYJMuW8F9ormWc";

    private Web3j web3j;
    private final Escrow escrowContract;
    private final Voucher voucherContract;
    private final ZARToken tokenContract;

    public Web3Manager() throws Exception {
        web3j = Web3j.build(new HttpService(ALCHEMY_API_URL));
        Credentials credentials = Credentials.create(OWNER_PRIVATE_KEY);

        contractAddresses.put("escrow", "0xB05d43B064E9a6d5Bcd59352f69A045c2445290A");
        contractAddresses.put("voucher", "0x894a91e60CBeB2Dd1DC9A6299C6d161BcF08c669");
        contractAddresses.put("token", "0xBCb82c10C4e7F6d5c4bd7cD78FB664C65E1534c0");

        escrowContract = Escrow.load(contractAddresses.get("escrow"), web3j, credentials, new DefaultGasProvider());
        voucherContract = Voucher.load(contractAddresses.get("voucher"), web3j, credentials, new DefaultGasProvider());
        tokenContract = ZARToken.load(contractAddresses.get("token"), web3j, credentials, new DefaultGasProvider());
    }

    public String mintVoucher(String influencerID, String brandID, String supplierID, String productID,
            String productDescription, Long expiryDateLong, String requestAddress) {
        // string brandID;
        // string influencerID;
        // string supplierID;
        // string productID;
        // string productDescription;
        // bool redeemed;
        // uint256 expiryDate;
        System.out.println("Minting voucher...");
        VoucherData voucherData = new VoucherData(
                brandID,
                influencerID,
                supplierID,
                productID,
                productDescription,
                false,
                BigInteger.valueOf(expiryDateLong));

        TransactionReceipt receipt = voucherContract.mintVoucher(requestAddress, tokenURI, voucherData).sendAsync()
                .join();

        System.out.println("Voucher minted!");

        return receipt.getTransactionHash();

    }

}
