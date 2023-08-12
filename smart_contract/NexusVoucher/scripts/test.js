// transfer tokens from one account to another
const { ethers } = require("hardhat");

async function main() {
    const contractOwner = await ethers.getSigners();

    console.log(
        "Deploying contracts with the account:",
        contractOwner[0].address
    );

    // Deploy token contract
    const Token = await ethers.getContractFactory("ZARToken");
    const token = await Token.deploy();
    await token.deployed();
    console.log("Token address:", token.address);

    // Deploy voucher contract
    const Voucher = await ethers.getContractFactory("Voucher");
    const voucher = await Voucher.deploy();
    await voucher.deployed();
    console.log("Voucher address:", voucher.address);

    // Deploy escrow contract
    const Escrow = await ethers.getContractFactory("Escrow");
    const escrow = await Escrow.deploy(voucher.address, token.address);
    await escrow.deployed();
    console.log("Escrow address:", escrow.address);

    await voucher.setEscrowContract(escrow.address);

    // Transfer tokens from one account to another
    const [sender, receiver] = await ethers.getSigners();
    const amount = 1000;
    await token.transfer(receiver.address, amount);
    console.log(`Transferred ${amount} tokens from ${sender.address} to ${receiver.address}`);

   
    // metada
    // struct VoucherData {
    //     string brandID;
    //     string influencerID;
    //     string supplierID;
    //     string productID;
    //     string productDescription;
    //     bool redeemed;
    //     uint256 expiryDate;
    // }
    const sevenDaysFromNow = Math.floor(Date.now() / 1000) + 604800;
    const ExampleVoucher = {
        brandID: "Nike",
        influencerID: "James Gain",
        supplierID: "Walmart",
        productID: "Nike Air Max",
        productDescription: "Nike Air Max 90",
        redeemed: false,
        expiryDate: sevenDaysFromNow
    }

    const productCost = 1000;

    // mint voucher
    // function mintVoucher(address recipient, string memory tokenURI, VoucherData memory data, uint256 amount)
    const tokenURI = "https://gateway.pinata.cloud/ipfs/QmQUMLqaQEr2UXYtNkwkYz9vPewWBsrLccqGyS4aBVcqBe"

    token.increaseAllowance(voucher.address, productCost);
    
    const tx = await voucher.mintVoucher(
        receiver.address,
        tokenURI,
        ExampleVoucher,
        productCost 
    );

    // wait for the transaction to be mined
    await tx.wait();
    console.log("Minted voucher to", receiver.address);  


}

main()
    .then(() => process.exit(0))
    .catch((error) => {
        console.error(error);
        process.exit(1);
    }
);
