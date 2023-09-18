// scripts/deploy.js
const { ethers } = require("hardhat");

const TOKEN_ADDR = "0xBCb82c10C4e7F6d5c4bd7cD78FB664C65E1534c0";

async function main() {
  const contractOwner = await ethers.getSigners();

  console.log(
      "Deploying contracts with the account:",
      contractOwner[0].address
  );


    // Deploy voucher contract
  const Voucher = await ethers.getContractFactory("Voucher");
  const voucher = await Voucher.deploy();
  await voucher.deployed();
  console.log("Voucher address:", voucher.address);

  // Deploy escrow contract
  // const Escrow = await ethers.getContractFactory("Escrow");
  // const escrow = await Escrow.deploy(voucher.address, TOKEN_ADDR);
  // await escrow.deployed();
  // console.log("Escrow address:", escrow.address);

  // await voucher.setEscrowContract(escrow.address);
}

main()
  .then(() => process.exit(0))
  .catch((error) => {
      console.error(error);
      process.exit(1);
  });
