<template>
  <div class="redeem_page">
  <div class="main-container">
    <h1>Redeem Voucher</h1>
    <input type="text" id="fname" name="fname" />
    <button class="Redeem-voucher" @click="redeemVoucher">Redeem voucher</button> 
  </div>
</div>
</template>
<script>
import { useRouter } from "vue-router";

import { QrcodeStream } from 'vue-qrcode-reader';



// import on mount
import { onMounted } from "vue";

// api
import { API_ENDPOINT, OTHER_CONST } from "@/config/constants.js";


export default {
  setup() {

    const redeemVoucher = () => {
      alert("Voucher succesfully redeemed! Please give client the product.");
    };

    return {  redeemVoucher };
  },
};
</script>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Roboto&display=swap");
@import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
.redeem_page {
  background-image: url(../../assets/color-bars.svg);
  background-size: cover;
  background-position: center;
  backdrop-filter: blur(0px);
  background-color: #001f3f;
}

.redeem_page::before {
    content: "";
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 1, 63, 0.85); /* Adjust the opacity as needed */
    z-index: -1; /* Ensure the semi-transparent pane is behind other elements */
}

h1 {
  font-size: 3rem;
  margin: 2rem 0;
  font-family: 'Poppins';
  color: #E040FB;
}

.main-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  flex-wrap: wrap;
  gap: 2rem;
  
}

button {
  background-color: #E040FB;
  color: #fff;
  font-size: 1.2em;
  border-radius: 5px;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s;
  justify-content: center;
  display: flex;
}
button:hover{
  opacity: 0.8;
}

input {
  flex: 1;
  /* padding: 10px; */
  border: 1px solid #ccc;
  border-radius: 5px;
  color: #0b2c5c;
}
</style>
