package com.nexus.nexusapi.web3.contracts;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.8.7.
 */
@SuppressWarnings("rawtypes")
public class Escrow extends Contract {
    public static final String BINARY = "0x60806040523480156200001157600080fd5b506040516200183638038062001836833981810160405281019062000037919062000217565b620000576200004b620000e160201b60201c565b620000e960201b60201c565b81600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080600360006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050506200025e565b600033905090565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000620001df82620001b2565b9050919050565b620001f181620001d2565b8114620001fd57600080fd5b50565b6000815190506200021181620001e6565b92915050565b60008060408385031215620002315762000230620001ad565b5b6000620002418582860162000200565b9250506020620002548582860162000200565b9150509250929050565b6115c8806200026e6000396000f3fe608060405234801561001057600080fd5b50600436106100925760003560e01c80638da5cb5b116100665780638da5cb5b1461010b578063c9851a6c14610129578063dc0f957e14610147578063e2bbb15814610165578063f2fde38b1461018157610092565b8062f714ce14610097578063278ecde1146100b3578063715018a6146100cf57806387d81789146100d9575b600080fd5b6100b160048036038101906100ac9190610bec565b61019d565b005b6100cd60048036038101906100c89190610c2c565b6103b2565b005b6100d76105da565b005b6100f360048036038101906100ee9190610c2c565b6105ee565b60405161010293929190610c77565b60405180910390f35b610113610638565b6040516101209190610cae565b60405180910390f35b610131610661565b60405161013e9190610d28565b60405180910390f35b61014f610687565b60405161015c9190610d64565b60405180910390f35b61017f600480360381019061017a9190610d7f565b6106ad565b005b61019b60048036038101906101969190610dbf565b610977565b005b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161461022d576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161022490610e6f565b60405180910390fd5b6000600160008481526020019081526020016000206001015411610286576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161027d90610edb565b60405180910390fd5b60006001600084815260200190815260200160002060010154905060006001600085815260200190815260200160002060010181905550600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a9059cbb83836040518363ffffffff1660e01b815260040161031a929190610efb565b6020604051808303816000875af1158015610339573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061035d9190610f5c565b508173ffffffffffffffffffffffffffffffffffffffff16837fcf7d23a3cbe4e8b36ff82fd1b05b1b17373dc7804b4ebbd6e2356716ef202372836040516103a59190610f89565b60405180910390a3505050565b3373ffffffffffffffffffffffffffffffffffffffff166001600083815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614610456576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161044d90610ff0565b60405180910390fd5b60006001600083815260200190815260200160002060010154116104af576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016104a69061105c565b60405180910390fd5b60006001600083815260200190815260200160002060010154905060006001600084815260200190815260200160002060010181905550600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663a9059cbb33836040518363ffffffff1660e01b8152600401610543929190610efb565b6020604051808303816000875af1158015610562573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105869190610f5c565b503373ffffffffffffffffffffffffffffffffffffffff16827f7ca5472b7ea78c2c0141c5a12ee6d170cf4ce8ed06be3d22c8252ddfc7a6a2c4836040516105ce9190610f89565b60405180910390a35050565b6105e26109fa565b6105ec6000610a78565b565b60016020528060005260406000206000915090508060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16908060010154908060020154905083565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905090565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600081116106f0576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016106e7906110c8565b60405180910390fd5b6000600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16630cfa2fd8846040518263ffffffff1660e01b815260040161074d9190610f89565b600060405180830381865afa15801561076a573d6000803e3d6000fd5b505050506040513d6000823e3d601f19601f8201168201806040525081019061079391906113a8565b90508060a00151156107da576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016107d19061143d565b60405180910390fd5b60405180606001604052803373ffffffffffffffffffffffffffffffffffffffff1681526020018381526020018260c001518152506001600085815260200190815260200160002060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506020820151816001015560408201518160020155905050600360009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166323b872dd3330856040518463ffffffff1660e01b81526004016108df9392919061145d565b6020604051808303816000875af11580156108fe573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906109229190610f5c565b503373ffffffffffffffffffffffffffffffffffffffff16837f1599c0fcf897af5babc2bfcf707f5dc050f841b044d97c3251ecec35b9abf80b8460405161096a9190610f89565b60405180910390a3505050565b61097f6109fa565b600073ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff16036109ee576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016109e590611506565b60405180910390fd5b6109f781610a78565b50565b610a02610b3c565b73ffffffffffffffffffffffffffffffffffffffff16610a20610638565b73ffffffffffffffffffffffffffffffffffffffff1614610a76576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610a6d90611572565b60405180910390fd5b565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050816000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055508173ffffffffffffffffffffffffffffffffffffffff168173ffffffffffffffffffffffffffffffffffffffff167f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e060405160405180910390a35050565b600033905090565b6000604051905090565b600080fd5b600080fd5b6000819050919050565b610b6b81610b58565b8114610b7657600080fd5b50565b600081359050610b8881610b62565b92915050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000610bb982610b8e565b9050919050565b610bc981610bae565b8114610bd457600080fd5b50565b600081359050610be681610bc0565b92915050565b60008060408385031215610c0357610c02610b4e565b5b6000610c1185828601610b79565b9250506020610c2285828601610bd7565b9150509250929050565b600060208284031215610c4257610c41610b4e565b5b6000610c5084828501610b79565b91505092915050565b610c6281610bae565b82525050565b610c7181610b58565b82525050565b6000606082019050610c8c6000830186610c59565b610c996020830185610c68565b610ca66040830184610c68565b949350505050565b6000602082019050610cc36000830184610c59565b92915050565b6000819050919050565b6000610cee610ce9610ce484610b8e565b610cc9565b610b8e565b9050919050565b6000610d0082610cd3565b9050919050565b6000610d1282610cf5565b9050919050565b610d2281610d07565b82525050565b6000602082019050610d3d6000830184610d19565b92915050565b6000610d4e82610cf5565b9050919050565b610d5e81610d43565b82525050565b6000602082019050610d796000830184610d55565b92915050565b60008060408385031215610d9657610d95610b4e565b5b6000610da485828601610b79565b9250506020610db585828601610b79565b9150509250929050565b600060208284031215610dd557610dd4610b4e565b5b6000610de384828501610bd7565b91505092915050565b600082825260208201905092915050565b7f4f6e6c792074686520566f756368657220636f6e74726163742063616e20776960008201527f7468647261770000000000000000000000000000000000000000000000000000602082015250565b6000610e59602683610dec565b9150610e6482610dfd565b604082019050919050565b60006020820190508181036000830152610e8881610e4c565b9050919050565b7f4e6f2066756e647320746f207769746864726177000000000000000000000000600082015250565b6000610ec5601483610dec565b9150610ed082610e8f565b602082019050919050565b60006020820190508181036000830152610ef481610eb8565b9050919050565b6000604082019050610f106000830185610c59565b610f1d6020830184610c68565b9392505050565b60008115159050919050565b610f3981610f24565b8114610f4457600080fd5b50565b600081519050610f5681610f30565b92915050565b600060208284031215610f7257610f71610b4e565b5b6000610f8084828501610f47565b91505092915050565b6000602082019050610f9e6000830184610c68565b92915050565b7f4f6e6c7920746865206465706f7369746f722063616e20726566756e64000000600082015250565b6000610fda601d83610dec565b9150610fe582610fa4565b602082019050919050565b6000602082019050818103600083015261100981610fcd565b9050919050565b7f4e6f2066756e647320746f20726566756e640000000000000000000000000000600082015250565b6000611046601283610dec565b915061105182611010565b602082019050919050565b6000602082019050818103600083015261107581611039565b9050919050565b7f416d6f756e742073686f756c642062652067726561746572207468616e203000600082015250565b60006110b2601f83610dec565b91506110bd8261107c565b602082019050919050565b600060208201905081810360008301526110e1816110a5565b9050919050565b600080fd5b6000601f19601f8301169050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052604160045260246000fd5b611136826110ed565b810181811067ffffffffffffffff82111715611155576111546110fe565b5b80604052505050565b6000611168610b44565b9050611174828261112d565b919050565b600080fd5b600080fd5b600080fd5b600067ffffffffffffffff8211156111a3576111a26110fe565b5b6111ac826110ed565b9050602081019050919050565b60005b838110156111d75780820151818401526020810190506111bc565b60008484015250505050565b60006111f66111f184611188565b61115e565b90508281526020810184848401111561121257611211611183565b5b61121d8482856111b9565b509392505050565b600082601f83011261123a5761123961117e565b5b815161124a8482602086016111e3565b91505092915050565b60008151905061126281610b62565b92915050565b600060e0828403121561127e5761127d6110e8565b5b61128860e061115e565b9050600082015167ffffffffffffffff8111156112a8576112a7611179565b5b6112b484828501611225565b600083015250602082015167ffffffffffffffff8111156112d8576112d7611179565b5b6112e484828501611225565b602083015250604082015167ffffffffffffffff81111561130857611307611179565b5b61131484828501611225565b604083015250606082015167ffffffffffffffff81111561133857611337611179565b5b61134484828501611225565b606083015250608082015167ffffffffffffffff81111561136857611367611179565b5b61137484828501611225565b60808301525060a061138884828501610f47565b60a08301525060c061139c84828501611253565b60c08301525092915050565b6000602082840312156113be576113bd610b4e565b5b600082015167ffffffffffffffff8111156113dc576113db610b53565b5b6113e884828501611268565b91505092915050565b7f566f756368657220616c72656164792072656465656d65640000000000000000600082015250565b6000611427601883610dec565b9150611432826113f1565b602082019050919050565b600060208201905081810360008301526114568161141a565b9050919050565b60006060820190506114726000830186610c59565b61147f6020830185610c59565b61148c6040830184610c68565b949350505050565b7f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160008201527f6464726573730000000000000000000000000000000000000000000000000000602082015250565b60006114f0602683610dec565b91506114fb82611494565b604082019050919050565b6000602082019050818103600083015261151f816114e3565b9050919050565b7f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572600082015250565b600061155c602083610dec565b915061156782611526565b602082019050919050565b6000602082019050818103600083015261158b8161154f565b905091905056fea2646970667358221220acdd0b2a795cfa453dbfd2c2767f631f2af137c8997121269dbe76d14e09a95f64736f6c63430008130033";

    public static final String FUNC_DEPOSIT = "deposit";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PAYMENTS = "payments";

    public static final String FUNC_REFUND = "refund";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_VOUCHERCONTRACT = "voucherContract";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final String FUNC_ZARTOKEN = "zarToken";

    public static final Event DEPOSITED_EVENT = new Event("Deposited", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event REFUNDED_EVENT = new Event("Refunded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event WITHDRAWN_EVENT = new Event("Withdrawn", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected Escrow(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Escrow(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Escrow(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Escrow(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<DepositedEventResponse> getDepositedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DEPOSITED_EVENT, transactionReceipt);
        ArrayList<DepositedEventResponse> responses = new ArrayList<DepositedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DepositedEventResponse typedResponse = new DepositedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.voucherId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.depositor = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DepositedEventResponse> depositedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, DepositedEventResponse>() {
            @Override
            public DepositedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(DEPOSITED_EVENT, log);
                DepositedEventResponse typedResponse = new DepositedEventResponse();
                typedResponse.log = log;
                typedResponse.voucherId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.depositor = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DepositedEventResponse> depositedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DEPOSITED_EVENT));
        return depositedEventFlowable(filter);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public List<RefundedEventResponse> getRefundedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REFUNDED_EVENT, transactionReceipt);
        ArrayList<RefundedEventResponse> responses = new ArrayList<RefundedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RefundedEventResponse typedResponse = new RefundedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.voucherId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RefundedEventResponse> refundedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RefundedEventResponse>() {
            @Override
            public RefundedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REFUNDED_EVENT, log);
                RefundedEventResponse typedResponse = new RefundedEventResponse();
                typedResponse.log = log;
                typedResponse.voucherId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RefundedEventResponse> refundedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REFUNDED_EVENT));
        return refundedEventFlowable(filter);
    }

    public List<WithdrawnEventResponse> getWithdrawnEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(WITHDRAWN_EVENT, transactionReceipt);
        ArrayList<WithdrawnEventResponse> responses = new ArrayList<WithdrawnEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            WithdrawnEventResponse typedResponse = new WithdrawnEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.voucherId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<WithdrawnEventResponse> withdrawnEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, WithdrawnEventResponse>() {
            @Override
            public WithdrawnEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(WITHDRAWN_EVENT, log);
                WithdrawnEventResponse typedResponse = new WithdrawnEventResponse();
                typedResponse.log = log;
                typedResponse.voucherId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<WithdrawnEventResponse> withdrawnEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(WITHDRAWN_EVENT));
        return withdrawnEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> deposit(BigInteger voucherId, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DEPOSIT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(voucherId), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Tuple3<String, BigInteger, BigInteger>> payments(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PAYMENTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<String, BigInteger, BigInteger>>(function,
                new Callable<Tuple3<String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> refund(BigInteger voucherId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REFUND, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(voucherId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> voucherContract() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_VOUCHERCONTRACT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> withdraw(BigInteger voucherId, String to) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(voucherId), 
                new org.web3j.abi.datatypes.Address(160, to)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> zarToken() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ZARTOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static Escrow load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Escrow(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Escrow load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Escrow(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Escrow load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Escrow(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Escrow load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Escrow(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Escrow> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _voucherContract, String _tokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _voucherContract), 
                new org.web3j.abi.datatypes.Address(160, _tokenAddress)));
        return deployRemoteCall(Escrow.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<Escrow> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _voucherContract, String _tokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _voucherContract), 
                new org.web3j.abi.datatypes.Address(160, _tokenAddress)));
        return deployRemoteCall(Escrow.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Escrow> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _voucherContract, String _tokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _voucherContract), 
                new org.web3j.abi.datatypes.Address(160, _tokenAddress)));
        return deployRemoteCall(Escrow.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<Escrow> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _voucherContract, String _tokenAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _voucherContract), 
                new org.web3j.abi.datatypes.Address(160, _tokenAddress)));
        return deployRemoteCall(Escrow.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class DepositedEventResponse extends BaseEventResponse {
        public BigInteger voucherId;

        public String depositor;

        public BigInteger amount;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class RefundedEventResponse extends BaseEventResponse {
        public BigInteger voucherId;

        public String to;

        public BigInteger amount;
    }

    public static class WithdrawnEventResponse extends BaseEventResponse {
        public BigInteger voucherId;

        public String to;

        public BigInteger amount;
    }
}
