/*
Nexus voucher that intergrates
with an escrow contract that stores
erc20 tokens until the voucher is redeemed

Contract features:
- Minting of vouchers
- Redeeming of vouchers
- Transfer of erc20 tokens to escrow contract
- Transfer of erc20 tokens from escrow contract

*/

pragma solidity ^0.8.18;

import "@openzeppelin/contracts/token/ERC721/extensions/ERC721URIStorage.sol";
import "@openzeppelin/contracts/utils/Counters.sol";
import "@openzeppelin/contracts/access/Ownable.sol";
import "./Escrow.sol";

contract Voucher is ERC721URIStorage, Ownable {
    using Counters for Counters.Counter;
    Counters.Counter private _voucherIds;

    // voucher metadata
    struct VoucherData {
        string brandID;
        string influencerID;
        string supplierID;
        string productID;
        string productDescription;
        bool redeemed;
        uint256 expiryDate;
    }

    // voucher id => voucher metadata
    mapping(uint256 => VoucherData) private _vouchers;

    // events
    event VoucherMinted(uint256 indexed voucherId, address indexed to);
    event VoucherRedeemed(uint256 indexed voucherId, address indexed to);

    Escrow private escrowContract;

    constructor() ERC721("NexusVoucher", "NXV") {
    }

    function setEscrowContract(address _escrowContract) public onlyOwner {
        escrowContract = Escrow(_escrowContract);
    }

     // add owner only modifier to mintVoucher
    function mintVoucher(address recipient, string memory tokenURI, VoucherData memory data, uint256 amount)
        public
        onlyOwner
        returns (uint256)
    {
        _voucherIds.increment();

        uint256 newVoucherId = _voucherIds.current();
        _mint(recipient, newVoucherId);
        _setTokenURI(newVoucherId, tokenURI);

        _vouchers[newVoucherId] = data;

        // deposit the funds into the escrow contract
        escrowContract.deposit(newVoucherId, amount);

        return newVoucherId;
    }


    function _isRedeemable(uint256 voucherId) internal view {
        require(_exists(voucherId), "Voucher does not exist");
        require(!_vouchers[voucherId].redeemed, "Voucher already redeemed");
        require(_vouchers[voucherId].expiryDate >= block.timestamp, "Voucher has expired");
    }


    function redeem(uint256 voucherId, address supplierAddress) public {
        _isRedeemable(voucherId);
        require(ownerOf(voucherId) == msg.sender, "Only the owner can redeem the voucher");

        _vouchers[voucherId].redeemed = true;

        escrowContract.withdraw(voucherId, payable(supplierAddress));

        emit VoucherRedeemed(voucherId, supplierAddress);

        _burn(voucherId);
    }

    // Get voucher data
    function getVoucher(uint256 voucherId) public view returns (VoucherData memory) {
        return _vouchers[voucherId];
    }

    // hello world
    function helloWorld() public pure returns (string memory) {
        return "Hello World";
    }



}