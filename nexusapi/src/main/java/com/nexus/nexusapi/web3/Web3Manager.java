package com.nexus.nexusapi.web3;

import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import com.nexus.nexusapi.web3.contracts.Voucher;
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
    private final PinataUploader pinataUploader = new PinataUploader();

    private final Map<String, String> contractAddresses = new HashMap<>();

    private final String tokenURI = "https://ivory-coloured-lobster-148.mypinata.cloud/ipfs/QmYNZfoybMxiKqxhkMDde5w7ogD7w31fyYJMuW8F9ormWc";

    private Web3j web3j;
    private final Voucher voucherContract;

    public Web3Manager() throws Exception {
        web3j = Web3j.build(new HttpService(ALCHEMY_API_URL));
        Credentials credentials = Credentials.create(OWNER_PRIVATE_KEY);

        contractAddresses.put("voucher", "0x5FbDB2315678afecb367f032d93F642f64180aa3");

        voucherContract = Voucher.load(contractAddresses.get("voucher"), web3j, credentials, new DefaultGasProvider());
    }

    public String mintVoucher(String influencerID, String brandID, String supplierIds, String productID,
     Long expiryDateLong, String requestAddress) {
        System.out.println("Minting voucher...");
        VoucherData voucherData = new VoucherData(
                brandID,
                influencerID,
                supplierIds,
                productID,
                false,
                BigInteger.valueOf(expiryDateLong));

        TransactionReceipt receipt = voucherContract.mintVoucher(requestAddress, tokenURI, voucherData).sendAsync()
                .join();

        System.out.println("Voucher minted!");


        return receipt.getTransactionHash();

    }

    public String redeemVoucher(BigInteger voucherId, String supplierAddress) {
        System.out.println("Redeeming voucher...");

        TransactionReceipt receipt = voucherContract.redeem(voucherId, supplierAddress).sendAsync().join();

        System.out.println("Voucher redeemed!");

        return  receipt.getTransactionHash();
    }

}
