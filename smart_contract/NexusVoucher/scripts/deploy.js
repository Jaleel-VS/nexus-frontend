// scripts/deploy.js
const { ethers } = require("hardhat");

async function main() {
  const contractOwner = await ethers.getSigners();

  console.log(
      "Deploying contracts with the account:",
      contractOwner.address
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
}

main()
  .then(() => process.exit(0))
  .catch((error) => {
      console.error(error);
      process.exit(1);
  });
