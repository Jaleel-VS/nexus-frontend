// SPDX-License-Identifier: MIT
pragma solidity ^0.8.18;

import { ERC20} from "@openzeppelin/contracts/token/ERC20/ERC20.sol";

contract ZARToken is ERC20 {
    string constant tokenName = "ZARToken";
    string constant tokenSymbol = "ZAR";
    uint8 constant tokenDecimals = 18;
    uint256 constant initialSupply = 1_000_000 * (10 ** uint256(tokenDecimals)); // 1 million tokens

    constructor() ERC20(tokenName, tokenSymbol){
        _mint(msg.sender, initialSupply);
    }

    

    
}
