<template>
    <div>
    <h1 class="voucher-heading"> Voucher Lists</h1>
    <div class="InfluencerViewVoucher"> 
    
    <div>   <button v-if="!isMetamaskConnected" @click="connect">Connect wallet</button>   </div>
   
    <div class="voucher-container">
<table class="voucher-table">
    <thead>
        <tr>
            <th>VoucherID</th>
            <th>Product Name</th>
            <th>Expiry Date</th>
            <th>Supplier Name</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <tr v-for="voucher in vouchers" :key="voucher.VoucherID">
            <td>{{ voucher.VoucherID }}</td>
            <td>{{ voucher['Product Name'] }}</td>
            <td>{{ voucher['Expiry Date'] }}</td>
            <td>{{ voucher['Supplier Name'] }}</td>
            <td><router-link to="/reviewRequest">
    <button class="cta-button">View QR Code</button>
  </router-link></td>
        </tr>
    </tbody>
</table>
    </div>
</div>
    </div>
</template>

<script>
export default {
    name: "InfluencerVoucherMetamask",
  data() {
    return {
      web3: null,
      isMetamaskConnected: false,
      vouchers: [
            {

            }
            //... other voucher objects
        ]
    }
},
methods: {
    connect: function () {
    // this connects to the wallet
    if (window.ethereum) {
        // first we check if metamask is installed
        window.ethereum.request({ method: "eth_requestAccounts" })
          .then(() => {
            this.isMetamaskConnected = true; // If users successfully connected their wallet
          });
      }
    },
    review(voucher) {
        // Place your review action here
        console.log(`Reviewing voucher with ID ${voucher.VoucherID}`);
    }
}
}
</script>

<style scoped>

.cta-button {
background-color: #3498db;
color: white;
border: none;
border-radius: 5px;
padding: 1em 2em;
font-size: 1em;
text-decoration: none;
cursor: pointer;
transition: background-color 0.3s ease;
}
.voucher-heading {
  font-size: 2.5em;
  margin-bottom: 1em;
  display: flex;
  justify-content: center;
  text-align: center;
  height: 50%;

  
}
.voucher-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  height: 100vh;
  padding: 2em;
  box-sizing: border-box;
  font-family: Arial, sans-serif;
  color: #333;
}

.voucher-container h1 {
  font-size: 2.5em;
  margin-bottom: 1em;
}
.cta-button:hover {
background-color: #2980b9;
}

.voucher-table {
border-collapse: collapse;
width: 80%;
margin: 0 auto;
}

.voucher-table th,
.voucher-table td {
border: 1px solid #ccc;
padding: 8px;
}

.voucher-table th {
background-color: #f2f2f2;
}
.InfluencerViewVoucher {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    text-align: center;
    height: 100vh;
    padding: 2em;
    box-sizing: border-box;
    font-family: Arial, sans-serif;
    color: #333;
  }
  
  .InfluencerViewVoucher button {
    background-color: #3498db;
    color: white;
    border: none;
    border-radius: 5px;
    padding: 1em 2em;
    font-size: 1em;
    text-decoration: none;
    cursor: pointer;
    transition: background-color 0.3s ease;
    margin-top: 1rem;
  }


</style>