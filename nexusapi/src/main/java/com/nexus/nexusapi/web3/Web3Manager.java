// package com.nexus.nexusapi.web3;

// import org.springframework.stereotype.Service;
// import org.web3j.crypto.Credentials;
// import org.web3j.protocol.Web3j;
// import org.web3j.protocol.core.methods.response.TransactionReceipt;
// import org.web3j.protocol.http.HttpService;
// import org.web3j.tx.gas.DefaultGasProvider;

// import java.math.BigInteger;

// @Service
// public class Web3Manager {

//     private static final String ALCHEMY_API_URL = "https://eth-goerli.alchemyapi.io/v2/YOUR_ALCHEMY_API_KEY";
//     private static final String CONTRACT_ADDRESS = "YOUR_CONTRACT_ADDRESS_ON_GOERLI";
//     private static final String PRIVATE_KEY = "YOUR_PRIVATE_KEY"; // Never hard-code this in a real application!

//     private final Web3j web3;
//     private final YourContract contract;

//     public Web3Manager() {
//         // Initialize Web3j service
//         this.web3 = Web3j.build(new HttpService(ALCHEMY_API_URL));

//         // Load the contract
//         Credentials credentials = Credentials.create(PRIVATE_KEY);
//         this.contract = YourContract.load(CONTRACT_ADDRESS, web3, credentials, DefaultGasProvider.GAS_PRICE, DefaultGasProvider.GAS_LIMIT);
//     }

//     public String getVoucher(BigInteger voucherId) throws Exception {
//         // Call the getVoucher method from the contract
//         TransactionReceipt receipt = contract.getVoucher(voucherId).send();

//         // For this example, let's assume the getVoucher method returns a string. Adjust accordingly.
//         return receipt.getOutput(); 
//     }

//     // Add more methods to interact with other contract functions as needed
// }
