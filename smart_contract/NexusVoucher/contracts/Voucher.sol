/*
 Voucher is a non-fungible token (ERC721) that represents a voucher that can be redeemed for a product.
 The Voucher contract is owned by the Nexus team and is used to mint vouchers.
 Brands can create vouchers by calling the mintVoucher function.
 Suppliers can redeem vouchers by calling the redeem function.
 The Voucher contract is also used by the Escrow contract to withdraw funds.
*/

// SPDX-License-Identifier: MIT
pragma solidity ^0.8.18;

import "@openzeppelin/contracts/token/ERC721/extensions/ERC721URIStorage.sol";
import "@openzeppelin/contracts/utils/Counters.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

contract Voucher is ERC721URIStorage, Ownable {
    using Counters for Counters.Counter;
    Counters.Counter private _voucherIds;

    struct VoucherData {
        string brandID;
        string influencerID;
        string supplierIDs;
        string productID;
        bool redeemed;
        uint256 expiryDate;
    }

    mapping(uint256 => VoucherData) private _vouchers;

    event VoucherMinted(uint256 indexed voucherId, address indexed to);
    event VoucherRedeemed(uint256 indexed voucherId, address indexed to);

    constructor() ERC721("NexusVoucher", "NXV") {}


    function mintVoucher(
        address recipient,
        string memory tokenURI,
        VoucherData memory data
    ) public onlyOwner returns (uint256) {
        _voucherIds.increment();

        uint256 newVoucherId = _voucherIds.current();
        _mint(recipient, newVoucherId);
        _setTokenURI(newVoucherId, tokenURI);

        _vouchers[newVoucherId] = data;

        emit VoucherMinted(newVoucherId, recipient);

        return newVoucherId;
    }

    function _isRedeemable(uint256 voucherId) internal view {
        require(_exists(voucherId), "Voucher does not exist");
        require(!_vouchers[voucherId].redeemed, "Voucher already redeemed");
        require(
            _vouchers[voucherId].expiryDate >= block.timestamp,
            "Voucher has expired"
        );
    }

    function redeem(uint256 voucherId, address supplierAddress) public {
        _isRedeemable(voucherId);

        _vouchers[voucherId].redeemed = true;

        emit VoucherRedeemed(voucherId, supplierAddress);
    }

    function getVoucher(
        uint256 voucherId
    ) public view returns (VoucherData memory) {
        return _vouchers[voucherId];
    }

    function helloWorld() public pure returns (string memory) {
        return "Hello World";
    }
}
