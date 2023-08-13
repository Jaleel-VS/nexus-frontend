const { expect } = require("chai");
const { ethers } = require("hardhat");

describe("Voucher and Escrow System", function () {
    let zarToken, escrow, voucher, owner, addr1, addr2;
    const voucherData = {
        brandID: "brand123",
        influencerID: "influencer123",
        supplierID: "supplier123",
        productID: "product123",
        productDescription: "A cool product",
        redeemed: false,
        expiryDate: Math.floor(Date.now() / 1000) + 60 * 60 * 24 // 1 day from now
    };

    beforeEach(async function () {
        // Deploy ZARToken
        const ZARToken = await ethers.getContractFactory("ZARToken");
        zarToken = await ZARToken.deploy();
        await zarToken.deployed();

        // Deploy Voucher with Escrow address
        const Voucher = await ethers.getContractFactory("Voucher");
        voucher = await Voucher.deploy();
        await voucher.deployed();

        // Deploy Escrow
        const Escrow = await ethers.getContractFactory("Escrow");
        escrow = await Escrow.deploy(voucher.address, zarToken.address);
        await escrow.deployed();

        voucher.setEscrowContract(escrow.address);
        

        [owner, addr1, addr2] = await ethers.getSigners();
    });

    describe("ZARToken", function () {
        it("Should mint initial supply to owner", async function () {
            const balance = await zarToken.balanceOf(owner.address);
            expect(balance.toString()).to.equal(ethers.utils.parseEther("1000000").toString());
        });
    });

    describe("Voucher", function () {
        it("Should mint a voucher", async function () {

            await voucher.mintVoucher(addr1.address, "ipfs://tokenURI", voucherData);
            const voucherInfo = await voucher.getVoucher(1);
            expect(voucherInfo.brandID).to.equal("brand123");
        });
    });

    describe("Escrow", function () {
        it("Should allow deposit for a voucher", async function () {

            await voucher.mintVoucher(addr1.address, "ipfs://tokenURI", voucherData);

            // Transfer ZAR tokens to addr1
            await zarToken.transfer(addr1.address, ethers.utils.parseEther("1000"));

            // Approve Escrow to spend ZAR tokens on behalf of addr1
            await zarToken.connect(addr1).approve(escrow.address, ethers.utils.parseEther("500"));

            // Deposit to escrow
            await escrow.connect(addr1).deposit(1, ethers.utils.parseEther("500"));

            const payment = await escrow.payments(1);
            expect(payment.amount.toString()).to.equal(ethers.utils.parseEther("500").toString());
        });
    });

    // ... Add more tests for redeem, refund, and other functionalities.
    describe("Redemption", function () {
        it("Should allow the owner of a voucher to redeem it", async function () {

    
            await voucher.mintVoucher(addr1.address, "ipfs://tokenURI", voucherData);
    
            // Transfer ZAR tokens to addr1 and deposit to escrow
            await zarToken.transfer(addr1.address, ethers.utils.parseEther("1000"));
            await zarToken.connect(addr1).approve(escrow.address, ethers.utils.parseEther("500"));
            await escrow.connect(addr1).deposit(1, ethers.utils.parseEther("500"));

            console.log("Escrow balance: ", await zarToken.balanceOf(escrow.address));
            console.log("Supplier balance: ", await zarToken.balanceOf(addr2.address));
    
            await voucher.connect(addr1).redeem(1, addr2.address);
            console.log("Escrow balance: ", await zarToken.balanceOf(escrow.address));
            console.log("Supplier balance: ", await zarToken.balanceOf(addr2.address));
    
            const voucherInfo = await voucher.getVoucher(1);
            expect(voucherInfo.redeemed).to.equal(true);
        });
    });
    
    describe("Refund", function () {
        it("Should allow the depositor to refund after expiry", async function () {
            
            const newVoucherData = {
                brandID: "brand123",
                influencerID: "influencer123",
                supplierID: "supplier123",
                productID: "product123",
                productDescription: "A cool product",
                redeemed: false,
                expiryDate: Math.floor(Date.now() / 1000) + 5 // 5 seconds from now
            };
    
            await voucher.mintVoucher(addr1.address, "ipfs://tokenURI", newVoucherData);

            // Transfer ZAR tokens to addr1 and deposit to escrow
            await zarToken.transfer(addr1.address, ethers.utils.parseEther("1000"));
            await zarToken.connect(addr1).approve(escrow.address, ethers.utils.parseEther("500"));
            await escrow.connect(addr1).deposit(1, ethers.utils.parseEther("500"));
    
            // Wait for 6 seconds to ensure the voucher has expired
            await new Promise(resolve => setTimeout(resolve, 6000));
    
            await escrow.connect(addr1).refund(1);
    
            const payment = await escrow.payments(1);
            expect(payment.amount.toString()).to.equal("0");
        });
    
        it("Should not allow non-depositors to refund", async function () {
            const newVoucherData = {
                brandID: "brand123",
                influencerID: "influencer123",
                supplierID: "supplier123",
                productID: "product123",
                productDescription: "A cool product",
                redeemed: false,
                expiryDate: Math.floor(Date.now() / 1000) + 5 // 1 minute from now
            };
    
            await voucher.mintVoucher(addr1.address, "ipfs://tokenURI", newVoucherData);
    
            // Transfer ZAR tokens to addr1 and deposit to escrow
            await zarToken.transfer(addr1.address, ethers.utils.parseEther("1000"));
            await zarToken.connect(addr1).approve(escrow.address, ethers.utils.parseEther("500"));
            await escrow.connect(addr1).deposit(1, ethers.utils.parseEther("500"));
    
            // Wait for 2 minutes to ensure the voucher has expired
            await new Promise(resolve => setTimeout(resolve, 6));
    
            await expect(escrow.connect(addr2).refund(1)).to.be.revertedWith("Only the depositor can refund");
        });
    });
    
    describe("Deposit", function () {
        it("Should not allow deposits for redeemed vouchers", async function () {
    
            await voucher.mintVoucher(addr1.address, "ipfs://tokenURI", voucherData);
    
            // Transfer ZAR tokens to addr1 and deposit to escrow
            await zarToken.transfer(addr1.address, ethers.utils.parseEther("1000"));
            await zarToken.connect(addr1).approve(escrow.address, ethers.utils.parseEther("500"));
            await escrow.connect(addr1).deposit(1, ethers.utils.parseEther("500"));
    
            await voucher.connect(addr1).redeem(1, addr2.address);
    
            await expect(escrow.connect(addr1).deposit(1, ethers.utils.parseEther("500"))).to.be.revertedWith("Voucher already redeemed");
        });
    });

    describe("Ownership", function () {
        it("Should mint 5 vouchers and test if addr1 is the owner of all 5", async function () {
            await voucher.mintVoucher(addr1.address, "ipfs://tokenURI", voucherData);
            await voucher.mintVoucher(addr1.address, "ipfs://tokenURI", voucherData);
            await voucher.mintVoucher(addr1.address, "ipfs://tokenURI", voucherData);
            await voucher.mintVoucher(addr1.address, "ipfs://tokenURI", voucherData);
            await voucher.mintVoucher(addr1.address, "ipfs://tokenURI", voucherData);

            const balance = await voucher.balanceOf(addr1.address);
            expect(balance.toString()).to.equal("5");
        });
    });

    describe("Voucher Retrieval", function () {
        it("Should return the correct voucher data", async function () {
            const voucherData = {
                brandID: "brand123",
                influencerID: "influencer123",
                supplierID: "supplier123",
                productID: "product123",
                productDescription: "A cool product",
                redeemed: false,
                expiryDate: Math.floor(Date.now() / 1000) + 60 * 60 * 24 // 1 day from now
            };
    
            // Mint a voucher
            await voucher.mintVoucher(addr1.address, "ipfs://tokenURI", voucherData);
    
            // Retrieve the voucher data
            const retrievedVoucher = await voucher.getVoucher(1);
    
            // Compare the retrieved data with the expected data
            expect(retrievedVoucher.brandID).to.equal(voucherData.brandID);
            expect(retrievedVoucher.influencerID).to.equal(voucherData.influencerID);
            expect(retrievedVoucher.supplierID).to.equal(voucherData.supplierID);
            expect(retrievedVoucher.productID).to.equal(voucherData.productID);
            expect(retrievedVoucher.productDescription).to.equal(voucherData.productDescription);
            expect(retrievedVoucher.redeemed).to.equal(voucherData.redeemed);
            expect(retrievedVoucher.expiryDate.toString()).to.equal(voucherData.expiryDate.toString());
        });
    });




    describe("Emits", function () {
        it("Should emit the VoucherMinted event", async function () {
              
            // Mint a voucher and get the transaction object
            const tx = await voucher.mintVoucher(addr1.address, "ipfs://tokenURI", voucherData);
    
            // Wait for the transaction to be mined
            const receipt = await tx.wait();
    
            // Find the VoucherMinted event in the receipt
            const event = receipt.events?.find(e => e.event === "VoucherMinted");
            console.log(event);
    
            // Assert that the event was emitted with the expected values
            expect(event).to.not.be.undefined;
            expect(event.args[0].toString()).to.equal("1");  // newVoucherId
            expect(event.args[1]).to.equal(addr1.address);  // recipient
        });
    });
    

    
});
