<template>
  <div class="redeem_page">
    <div v-if="loading" class="loading">
      <div class="spinner"></div>
    </div>

    <div v-else class="main-container">
      <button v-if="!showQRCodeScanner && !showVoucherPinInput" class="Redeem-voucher" @click="redeemWithQRCode">Redeem
        with QR Code</button>
      <button v-if="!showQRCodeScanner && !showVoucherPinInput" class="Redeem-voucher"
        @click="redeemWithVoucherPin">Redeem with Voucher Pin</button>

      <div v-if="showQRCodeScanner">
        <!-- Render content for QR Code scanner here -->
        <qrcode-stream @decode="onDecode" style="width: 300px; height: 300px;"></qrcode-stream>
        <h1>Scan QR Code</h1>
        <!-- close button -->
        <button @click="resetViews">Close</button>
      </div>

      <div v-if="showVoucherPinInput">
        <!-- Render content for voucher pin input here -->
        <h1>Enter Voucher Pin</h1>
        <input type="text" placeholder="Enter Voucher Pin" />
        <button @click="redeemVoucher">Redeem</button>
        <br>
        <button @click="resetViews">Close</button>
      </div>
    </div>
  </div>
</template>
<script setup>
import { useRouter } from "vue-router";

import { QrcodeStream } from 'vue-qrcode-reader';

import { ref, onMounted } from "vue";



// api
import { API_ENDPOINT } from "@/config/constants.js";

// store
import { useUserStore } from "@/store/user";

// details
const userDetails = ref(null);


onMounted(async () => {
  const userStore = useUserStore();
  userDetails.value = userStore.details;

  console.log(userDetails.value);
});



const showQRCodeScanner = ref(false);
const showVoucherPinInput = ref(false);

const loading = ref(false);


const redeemWithQRCode = () => {
  showQRCodeScanner.value = true;
  showVoucherPinInput.value = false;
  // Add logic for QR Code redemption here
};

const redeemWithVoucherPin = () => {
  showQRCodeScanner.value = false;
  showVoucherPinInput.value = true;
  // Add logic for Voucher Pin redemption here

  // redeemVoucher();
};

const resetViews = () => {
  showQRCodeScanner.value = false;
  showVoucherPinInput.value = false;
};




const redeemVoucher = () => {
  loading.value = true;
  // endpoint: /api/vouchers/redeem/{voucherQRCodeString}/supplier/{supplierId})
  // method: GET

  const supplierId = userDetails.value.id;
  // parse input and alert if empty
  const voucherPin = document.querySelector("input").value;
  if (voucherPin === "") {
    alert("Please enter a voucher pin");
    loading.value = false;
    return;
  }

  // fetch
  fetch(
    `${API_ENDPOINT}/vouchers/redeem/${voucherPin}/supplier/${supplierId}`
  ).then((response) => {
    console.log(response);
    if (response.status === 200) {
      alert("Voucher redeemed successfully. Please hand over the item to the customer.");
    } else {
      alert("Voucher redemption failed");
    }
  });
  loading.value = false;


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
  background-color: rgba(0, 1, 63, 0.85);
  z-index: -1;
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
  flex-wrap: wrap;
  gap: 2rem;
  padding: 100px 100px;

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

button:hover {
  opacity: 0.8;
}

input {
  flex: 1;
  /* padding: 10px; */
  border: 1px solid #ccc;
  border-radius: 5px;
  color: white;
}


.loading {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 60vh;
}

.spinner {
  border: 16px solid #f3f3f3;
  /* Light grey */
  border-top: 16px solid #3498db;
  /* Blue */
  border-radius: 50%;
  width: 120px;
  height: 120px;
  animation: spin 2s linear infinite;
}

@keyframes spin {
  0% {
    transform: rotate(0deg);
  }

  100% {
    transform: rotate(360deg);
  }
}
</style>
