require("@nomicfoundation/hardhat-toolbox");

const ALCHEMY_URL = "https://eth-sepolia.g.alchemy.com/v2/q0Kxqaro3WgwaI1HavhYn7p7ANrIBtxR";
const METAMASK_URL = "9b94f07022a8e93b6b4c3ef6ac950bdf013d0cfbaa056e70363a7bdb66621af8"

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
