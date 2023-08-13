require("@nomicfoundation/hardhat-toolbox");

const ALCHEMY_URL = "https://eth-sepolia.g.alchemy.com/v2/rwHETGslhGFm8yUHbrOv3IvyHs2VRDGt";
const METAMASK_URL = "cd8939c98ffa0d4b475d6aeceddc40816bfa44bd3e400b72b2b0b5dfcd6f1886"

/** @type import('hardhat/config').HardhatUserConfig */
module.exports = {
  solidity: "0.8.19",
  networks: {
    sepolia: {
      url: ALCHEMY_URL,
      accounts: [`0x${METAMASK_URL}`]
    },
  },
  
};
