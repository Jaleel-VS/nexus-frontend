<template>
  <div class="main-container">
    <h1>Redeem Voucher</h1>
    <input type="text" id="fname" name="fname" />
    <button class="Redeem-voucher" @click="redeemVoucher">Redeem voucher</button> 
  </div>
</template>
<script>
import { useRouter } from "vue-router";

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
h1 {
  font-size: 3rem;
  margin: 2rem 0;
  font-family: "Trocchi", serif;
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
  background-color: #0b2c5c;
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

input {
  flex: 1;
  /* padding: 10px; */
  border: 1px solid #ccc;
  border-radius: 5px;
  color: #0b2c5c;
}
</style>
