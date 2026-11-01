package com.vehicle.vehicleregistrationsystem;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

public class BlockchainService {
    private final VehicleRegistry contract;

    public BlockchainService() throws Exception {
        // 1. Connect to Anvil
        Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));

        // 2. PASTE YOUR ANVIL PRIVATE KEY HERE (Keep the quotes!)
        String privateKey = "0xac0974bec39a17e36ba4a6b4d238ff944bacb478cbed5efcae784d7bf4f2ff80";
        Credentials credentials = Credentials.create(privateKey);

        // 3. PASTE YOUR CONTRACT ADDRESS HERE (Keep the quotes!)
        String contractAddress = "0x0165878A594ca255338adfa4d48449f69242Eb8F";

        // 4. Load the contract
        contract = VehicleRegistry.load(contractAddress, web3j, credentials, new DefaultGasProvider());


    }

    // Method to write to blockchain
    public void registerVehicle(String regNumber, String ownerName, String vin) throws Exception {
        contract.registerVehicle(regNumber, ownerName, vin).send();
    }

    // Method to read from blockchain
    public String[] getVehicle(String regNumber) throws Exception {
        var r = contract.getVehicle(regNumber).send();
        return new String[]{
                r.get(0).getValue().toString(), // Registration Number
                r.get(1).getValue().toString(), // Owner Name
                r.get(2).getValue().toString(), // VIN
                r.get(3).getValue().toString()  // Timestamp
        };
    }
}