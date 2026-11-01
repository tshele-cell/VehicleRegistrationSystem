package com.vehicle.vehicleregistrationsystem; // Change to your actual package name

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VehicleRegistry extends Contract {

    protected VehicleRegistry(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider gasProvider) {
        super("", contractAddress, web3j, credentials, gasProvider);
    }

    protected VehicleRegistry(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider gasProvider) {
        super("", contractAddress, web3j, transactionManager, gasProvider);
    }

    public static VehicleRegistry load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider gasProvider) {
        return new VehicleRegistry(contractAddress, web3j, credentials, gasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> registerVehicle(String regNumber, String ownerName, String vin) {
        final Function function = new Function(
                "registerVehicle",
                Arrays.asList(new Utf8String(regNumber), new Utf8String(ownerName), new Utf8String(vin)),
                Collections.emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List<Type>> getVehicle(String regNumber) {
        final Function function = new Function(
                "getVehicle",
                Collections.singletonList(new Utf8String(regNumber)),
                Arrays.asList(
                        new TypeReference<Utf8String>() {},
                        new TypeReference<Utf8String>() {},
                        new TypeReference<Utf8String>() {},
                        new TypeReference<Uint256>() {}
                ));
        return executeRemoteCallMultipleValueReturn(function);
    }

    public RemoteFunctionCall<BigInteger> getVehicleCount() {
        final Function function = new Function(
                "getVehicleCount",
                Collections.emptyList(),
                Collections.singletonList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }
}