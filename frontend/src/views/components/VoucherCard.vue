<template>
    <div class="flip-card" @click="flipCard">
      <div class="flip-card-inner" :style="{ transform: isFlipped ? 'rotateY(180deg)' : 'rotateY(0deg)' }">
        <div class="flip-card-front">
          <h3 class="voucher">Voucher ID:<br>Voucher Status:</h3>
        </div>
        <div class="flip-card-back">
          <img src="proj_logo.png" alt="Logo" class="logo">
          <div class="brand-header">
            <label for="brand">(Brand Name)</label>
          </div>
          <div class="label-header">
            <h4 class="voucherdetails">Voucher Details:</h4>
          </div>
  
          <div class="label-and-textbox">
            <div class="label">
              <label for="label1">Supplier ID:</label>
            </div>
            <div class="textbox">
              <p> {{ voucherdetails.supplierId }}</p>
            </div>
          </div>
  
          <div class="label-and-textbox">
            <div class="label">
              <label for="label2">Product:</label>
            </div>
            <div class="textbox">
              <p> {{ voucherdetails.product }}</p>
            </div>
          </div>
  
          <div class="label-and-textbox">
            <div class="label">
              <label for="label3">Expiry Date:</label>
            </div>
            <div class="textbox">
              <p> {{ voucherdetails.expiryDate }}</p>
            </div>
          </div>
  
          <div class="label-and-textbox">
            <div class="label">
              <label for="label4">Amount:</label>
            </div>
            <div class="textbox">
              <p> {{ voucherdetails.amount }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
      import { shallowRef } from 'vue';
        const isFlipped =  shallowRef(false);
        const props = defineProps({
          voucherdetails: Array, 
        })
        
      function flipCard() {
        isFlipped.value = !isFlipped.value;
      }
  </script>
  
  <style scoped>
/* .flip-card styles */
.flip-card {
  background-color: transparent;
  width: 280px;
  height: 300px;
  border-radius: 10px;
  box-shadow: 0 8px 30px rgba(0, 0, 0, 0.3);
  perspective: 1000px;
  display: flex; 
}

/* .flip-card-inner styles */
.flip-card-inner {
  position: relative;
  width: 100%;
  height: 100%;
  text-align: center;
  transition: transform 0.8s;
  transform-style: preserve-3d;
}

/* .flip-card-front and .flip-card-back styles */
.flip-card-front,
.flip-card-back {
  position: absolute;
  width: 100%;
  height: 100%;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
}

/* .flip-card-front styles */
.flip-card-front {
  background-image: url(../../assets/VoucherCardBackground.png);
  background-size: cover;
  color: #f9f9f9;
}

/* .voucher styles */
.voucher {
  position: absolute;
  top: 75%;
  left: 5%;
  color: #f9f9f9;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-size: 0.8rem;
  text-align: left;
  display: flex;
}

/* .flip-card-back styles */
.flip-card-back {
  background-color: grey;
  transform: rotateY(180deg);
}

/* .logo styles */
.logo {
  max-width: 100px;
  margin-top: 1px;
  align-items: center;
}

/* .brand styles */
.brand {
  position: relative;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-size: 0.8rem;
  text-align: center;
}

/* .voucherdetails styles */
.voucherdetails {
  position: relative;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-size: 0.8rem;
  margin-top: 5px;
  text-align: center;
}

/* .label-and-textbox styles */
.label-and-textbox {
  display: flex;
  margin-top: 5px;
  margin-bottom: 8px;
  align-items: center;
}
/* .label styles */
.label {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-size: 0.8rem;
  margin-left: 20px;
  margin-right: 15px;
  white-space: nowrap;
}
/* .textbox styles */
.textbox {
  display: inline-block;
  font-size: 0.8rem;
  text-align: center;
  max-width: 100%;
  width: 100%;
}
</style>

  