// SPDX-License-Identifier: MIT
pragma solidity ^0.8.18;

import "@openzeppelin/contracts/token/ERC20/IERC20.sol";
import "@openzeppelin/contracts/access/Ownable.sol";
import "./Voucher.sol";
import "./ZARToken.sol";

contract Escrow is Ownable {
    struct Payment {
        address depositor;
        uint256 amount;
        uint256 expiryDate;
    }

    mapping(uint256 => Payment) public payments;

    Voucher public voucherContract;
    ZARToken public zarToken;

    // events
    event Deposited(uint256 indexed voucherId, address indexed depositor, uint256 amount);
    event Withdrawn(uint256 indexed voucherId, address indexed to, uint256 amount);

    constructor(address _voucherContract, address _tokenAddress) {
        voucherContract = Voucher(_voucherContract);
        zarToken = ZARToken(_tokenAddress);
    }

    function deposit(uint256 voucherId, uint256 amount) external {
        require(msg.sender == address(voucherContract), "Only the Voucher contract can deposit");
        require(amount > 0, "Amount should be greater than 0");

        Voucher.VoucherData memory voucherData = voucherContract.getVoucher(voucherId);

        payments[voucherId] = Payment({
            depositor: msg.sender,
            amount: amount,
            expiryDate: voucherData.expiryDate
        });

        zarToken.transferFrom(msg.sender, address(this), amount);

        emit Deposited(voucherId, msg.sender, amount);
    }

    function withdraw(uint256 voucherId, address to) external {
        require(msg.sender == address(voucherContract), "Only the Voucher contract can withdraw");
        require(payments[voucherId].amount > 0, "No funds to withdraw");

        uint256 amount = payments[voucherId].amount;

        payments[voucherId].amount = 0; // Reset the payment amount to prevent reentrancy

        zarToken.transfer(to, amount);

        emit Withdrawn(voucherId, to, amount);
    }

    function refund(uint256 voucherId) external {
        require(payments[voucherId].depositor == msg.sender, "Only the depositor can refund");
        require(payments[voucherId].expiryDate <= block.timestamp, "Cannot refund before expiry");
        require(payments[voucherId].amount > 0, "No funds to refund");

        uint256 amount = payments[voucherId].amount;

        payments[voucherId].amount = 0; // Reset the payment amount to prevent reentrancy

        zarToken.transfer(msg.sender, amount);

        emit Withdrawn(voucherId, msg.sender, amount);
    }
}
