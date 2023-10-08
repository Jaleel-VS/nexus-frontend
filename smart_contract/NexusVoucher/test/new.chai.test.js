const { expect } = require("chai");

describe("Voucher Contract", function () {
  let Voucher;
  let voucher;
  let owner;
  let brand;
  let supplier;
  let addr1;
  let addr2;

  beforeEach(async function () {
    [owner, brand, supplier, addr1, addr2] = await ethers.getSigners();

    Voucher = await ethers.getContractFactory("Voucher");
    voucher = await Voucher.deploy();
    await voucher.deployed();
  });

  it("Should mint a voucher and get voucher details with 1-day expiry", async function () {
    const voucherData = {
      brandID: "Brand123",
      influencerID: "Influencer456",
      supplierIDs: "Supplier789",
      productID: "Product101",
      price: 100,
      redeemed: false,
      expiryDate: Math.floor(Date.now() / 1000) + 60 * 60 * 24, // 1 day from now
    };

    await voucher.connect(owner).mintVoucher(addr1.address, "tokenURI", voucherData);

    const retrievedVoucherData = await voucher.getVoucher(1);
    expect(retrievedVoucherData.brandID).to.equal(voucherData.brandID);
    expect(retrievedVoucherData.price).to.equal(voucherData.price);
    expect(retrievedVoucherData.expiryDate).to.equal(voucherData.expiryDate);
  });

  it("Should not allow to redeem an expired voucher", async function () {
    const voucherData = {
      brandID: "Brand123",
      influencerID: "Influencer456",
      supplierIDs: "Supplier789",
      productID: "Product101",
      price: 100,
      redeemed: false,
      expiryDate: Math.floor(Date.now() / 1000) - 60, // 1 minute ago
    };

    await voucher.connect(owner).mintVoucher(addr1.address, "tokenURI", voucherData);

    await expect(voucher.connect(owner).redeem(1, addr2.address, "newTokenURI")).to.be.revertedWith(
      "Voucher has expired"
    );
  });

  it("Should redeem a voucher", async function () {
    const voucherData = {
      brandID: "Brand123",
      influencerID: "Influencer456",
      supplierIDs: "Supplier789",
      productID: "Product101",
      price: 100,
      redeemed: false,
      expiryDate: Math.floor(Date.now() / 1000) + 60 * 60 * 24, // 1 day from now
    };

    await voucher.connect(owner).mintVoucher(addr1.address, "tokenURI", voucherData);
    await voucher.connect(owner).redeem(1, addr2.address, "newTokenURI");

    const retrievedVoucherData = await voucher.getVoucher(1);
    expect(retrievedVoucherData.redeemed).to.equal(true);
  });

  it("Should not allow to redeem an already redeemed voucher", async function () {
    const voucherData = {
      brandID: "Brand123",
      influencerID: "Influencer456",
      supplierIDs: "Supplier789",
      productID: "Product101",
      price: 100,
      redeemed: false,
      expiryDate: Math.floor(Date.now() / 1000) + 60 * 60 * 24, // 1 day from now
    };

    await voucher.connect(owner).mintVoucher(addr1.address, "tokenURI", voucherData);
    await voucher.connect(owner).redeem(1, addr2.address, "newTokenURI");

    await expect(voucher.connect(supplier).redeem(1, addr2.address, "newTokenURI")).to.be.revertedWith(
      "Voucher already redeemed"
    );
  });
});
