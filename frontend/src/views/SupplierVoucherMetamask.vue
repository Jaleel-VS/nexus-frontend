<template>
    <div>
    <img src="proj_logo.png" />
    </div>

<div class="SupplierVoucher">

    <button v-on:click="connectMetamask">Connect Metamask</button>

    <button v-on:click="scanVoucher">Scan Voucher</button>
       
    </div>
</template>

<script>
import Web3 from "web3";

export default {
    name: "SupplierVoucherMetamask",
    data() {
    return {
      web3: null,
      isMetamaskConnected: false,
    };
  },
    
  methods: {
    async connectMetamask() {
      if (typeof window.ethereum !== "undefined") {
        this.web3 = new Web3(window.ethereum);
        try {
          
          await window.ethereum.enable();
          this.isMetamaskConnected = true;
          console.log("Metamask connected!");
        } catch (error) {
          console.error("Metamask connection error:", error);
        }
      } else {
        console.error("Metamask is not installed.");
      }
    },
},
};
    
</script>
<style scoped>

img{
            position: absolute;
            top: 0;
            left: 50%;
            transform: translateX(-50%);
            width: 400px;
            height: auto;

}
  .SupplierVoucher {
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
  
  .SupplierVoucher button {
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