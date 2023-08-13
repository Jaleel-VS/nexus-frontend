const { expect } = require("chai");

describe("Nexus System", function () {
    let zarToken, voucher, escrow, owner, brand, influencer, supplier;

    beforeEach(async function () {
        [owner, brand, influencer, supplier] = await ethers.getSigners();

        const ZARToken = await ethers.getContractFactory("ZARToken");
        zarToken = await ZARToken.deploy();
        await zarToken.deployed();

        const Voucher = await ethers.getContractFactory("Voucher");
        voucher = await Voucher.deploy();
        await voucher.deployed();

        const Escrow = await ethers.getContractFactory("Escrow");
        escrow = await Escrow.deploy(voucher.address, zarToken.address);
        await escrow.deployed();

        await voucher.setEscrowContract(escrow.address);
    });

    describe("ZARToken", function () {
        it("Should mint initial supply to owner", async function () {
            expect(await zarToken.balanceOf(owner.address)).to.equal(ethers.utils.parseEther("1000000"));
        });
    });

    describe("Voucher", function () {
        it("Should mint voucher", async function () {
            const voucherData = {
                brandID: "BRAND1",
                influencerID: "INFLUENCER1",
                supplierID: "SUPPLIER1",
                productID: "PRODUCT1",
                productDescription: "Sample Product",
                redeemed: false,
                expiryDate: Math.floor(Date.now() / 1000) + 60 * 60 * 24 // 1 day from now
            };

            await voucher.mintVoucher(influencer.address, "tokenURI", voucherData);
            expect(await voucher.ownerOf(1)).to.equal(influencer.address);
        });

        it("Should not allow redeeming an already redeemed voucher", async function () {
            const voucherData = {
                brandID: "BRAND1",
                influencerID: "INFLUENCER1",
                supplierID: "SUPPLIER1",
                productID: "PRODUCT1",
                productDescription: "Sample Product",
                redeemed: false,
                expiryDate: Math.floor(Date.now() / 1000) + 60 * 60 * 24 // 1 day from now
            };

            const amount = ethers.utils.parseEther("100");

            // Transfer some ZAR tokens to brand for testing
            await zarToken.transfer(brand.address, amount);

            // Brand approves the transfer to escrow
            await zarToken.connect(brand).approve(escrow.address, amount);

            await voucher.mintVoucher(influencer.address, "tokenURI", voucherData);

            // Deposit into escrow
            await escrow.connect(brand).deposit(1, amount);

            await voucher.redeem(1, supplier.address);

            await expect(voucher.redeem(1, supplier.address)).to.be.revertedWith("Voucher already redeemed");
        });

        it("Should not allow redeeming an expired voucher", async function () {
            const voucherData = {
                brandID: "BRAND1",
                influencerID: "INFLUENCER1",
                supplierID: "SUPPLIER1",
                productID: "PRODUCT1",
                productDescription: "Sample Product",
                redeemed: false,
                expiryDate: Math.floor(Date.now() / 1000) - 60 // 1 minute ago
            };

            await voucher.mintVoucher(influencer.address, "tokenURI", voucherData);

            await expect(voucher.redeem(1, supplier.address)).to.be.revertedWith("Voucher has expired");
        });
    });

    describe("Escrow", function () {
        it("Should deposit ZAR tokens into escrow", async function () {
            const amount = ethers.utils.parseEther("100");

            // Transfer some ZAR tokens to brand for testing
            await zarToken.transfer(brand.address, amount);

            // Brand approves the transfer to escrow
            await zarToken.connect(brand).approve(escrow.address, amount);

            // Deposit into escrow
            await escrow.connect(brand).deposit(1, amount);

            expect(await zarToken.balanceOf(escrow.address)).to.equal(amount);
        });

        it("Should allow withdrawal by the Voucher contract", async function () {
            const amount = ethers.utils.parseEther("100");
        
            // Transfer some ZAR tokens to brand for testing
            await zarToken.transfer(brand.address, amount);
        
            // Brand approves the transfer to escrow
            await zarToken.connect(brand).approve(escrow.address, amount);
        
            const voucherData = {
                brandID: "BRAND1",
                influencerID: "INFLUENCER1",
                supplierID: "SUPPLIER1",
                productID: "PRODUCT1",
                productDescription: "Sample Product",
                redeemed: false,
                expiryDate: Math.floor(Date.now() / 1000) + 60 * 60 * 24 // 1 day from now
            };
        
            // Mint a voucher
            await voucher.mintVoucher(influencer.address, "tokenURI", voucherData);
        
            // Deposit into escrow
            await escrow.connect(brand).deposit(1, amount);
        
            // Redeem the voucher
            await voucher.redeem(1, supplier.address);
        
            expect(await zarToken.balanceOf(supplier.address)).to.equal(amount);
        });
        

        it("Should not allow withdrawal by anyone other than the Voucher contract", async function () {
            const amount = ethers.utils.parseEther("100");

            // Transfer some ZAR tokens to brand for testing
            await zarToken.transfer(brand.address, amount);

            // Brand approves the transfer to escrow
            await zarToken.connect(brand).approve(escrow.address, amount);

            // Deposit into escrow
            await escrow.connect(brand).deposit(1, amount);

            await expect(escrow.withdraw(1, supplier.address)).to.be.revertedWith("Only the Voucher contract can withdraw");
        });

        it("Should allow refund after expiry", async function () {
            const amount = ethers.utils.parseEther("100");

            // Transfer some ZAR tokens to brand for testing
            await zarToken.transfer(brand.address, amount);

            // Brand approves the transfer to escrow
            await zarToken.connect(brand).approve(escrow.address, amount);

            // Deposit into escrow
            await escrow.connect(brand).deposit(1, amount);

            // Simulate time passing beyond expiry
            await ethers.provider.send("evm_increaseTime", [60 * 60 * 25]); // 25 hours
            await ethers.provider.send("evm_mine");

            await escrow.connect(brand).refund(1);

            expect(await zarToken.balanceOf(brand.address)).to.equal(amount);
        });
    });
});
