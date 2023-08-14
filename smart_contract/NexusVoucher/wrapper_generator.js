const fs = require('fs');
const path = require('path');

const contracts = [
    'Voucher',
    'Escrow',
    'ZARToken',
]

const exportDir = path.join(__dirname, 'export');

contracts.forEach(contractName => {
    const artifactPath = path.join(__dirname, 'artifacts', 'contracts', `${contractName}.sol`, `${contractName}.json`);
    const artifact = JSON.parse(fs.readFileSync(artifactPath, 'utf-8'));

    // Save ABI to a separate file
    fs.writeFileSync(path.join(exportDir, `${contractName}.abi`), JSON.stringify(artifact.abi, null, 2));

    // Save bytecode to a separate file
    fs.writeFileSync(path.join(exportDir, `${contractName}.bin`), artifact.bytecode);

    // Save abi as json to a separate file
    fs.writeFileSync(path.join(exportDir, `${contractName}.json`), JSON.stringify(artifact.abi, null, 2));

})

// const contractName = 'Voucher';  // Replace with your contract's name
// const artifactPath = path.join(__dirname, 'artifacts', 'contracts', `${contractName}.sol`, `${contractName}.json`);
// const artifact = JSON.parse(fs.readFileSync(artifactPath, 'utf-8'));

// // Save ABI to a separate file
// fs.writeFileSync(path.join(__dirname, `${contractName}.abi`), JSON.stringify(artifact.abi, null, 2));

// // Save bytecode to a separate file
// fs.writeFileSync(path.join(__dirname, `${contractName}.bin`), artifact.bytecode);
