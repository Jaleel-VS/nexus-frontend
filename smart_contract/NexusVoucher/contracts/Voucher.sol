pragma solidity ^0.8.18;

import "@openzeppelin/contracts/token/ERC721/extensions/ERC721URIStorage.sol";
import "@openzeppelin/contracts/utils/Counters.sol";
import "@openzeppelin/contracts/access/Ownable.sol";
import "./Escrow.sol";

contract Voucher is ERC721URIStorage, Ownable {
    using Counters for Counters.Counter;
    Counters.Counter private _voucherIds;

    struct VoucherData {
        string brandID;
        string influencerID;
        string supplierID;
        string productID;
        string productDescription;
        bool redeemed;
        uint256 expiryDate;
    }

    mapping(uint256 => VoucherData) private _vouchers;

    event VoucherMinted(uint256 indexed voucherId, address indexed to);
    event VoucherRedeemed(uint256 indexed voucherId, address indexed to);

    Escrow private escrowContract;

    constructor() ERC721("NexusVoucher", "NXV") {
    }

    function setEscrowContract(address _escrowContract) public onlyOwner {
        escrowContract = Escrow(_escrowContract);
    }

    function mintVoucher(address recipient, string memory tokenURI, VoucherData memory data)
        public
        onlyOwner
        returns (uint256)
    {
        _voucherIds.increment();

        uint256 newVoucherId = _voucherIds.current();
        _mint(recipient, newVoucherId);
        _setTokenURI(newVoucherId, tokenURI);

        _vouchers[newVoucherId] = data;

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

    function getVoucher(uint256 voucherId) public view returns (VoucherData memory) {
        return _vouchers[voucherId];
    }

    function helloWorld() public pure returns (string memory) {
        return "Hello World";
    }
}
