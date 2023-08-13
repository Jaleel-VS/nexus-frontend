package com.nexus.nexusapi.web3;

import org.web3j.codegen.SolidityFunctionWrapperGenerator;

public class ContractGenerator {

    public static void main(String[] args) throws Exception {
        String[] contracts = new String[] {
            "Escrow",
            "Voucher",
            "ZARToken",
        };

        // Assuming you're running this from the project root
        String projectRoot = System.getProperty("user.dir");

        for (String contract : contracts) {
            String binFile = projectRoot + "/src/main/java/com/nexus/nexusapi/web3/contracts/" + contract + ".bin";
            String abiFile = projectRoot + "/src/main/java/com/nexus/nexusapi/web3/contracts/" + contract + ".abi";
            
            // Output directory set to the root of your Java source code
            String outputDirectory = projectRoot + "/src/main/java";
            
            String packageName = "com.nexus.nexusapi.web3.contracts.generated";

            SolidityFunctionWrapperGenerator.main(new String[]{
                "-b", binFile,
                "-a", abiFile,
                "-o", outputDirectory,
                "-p", packageName
            });
        }
    }
}
