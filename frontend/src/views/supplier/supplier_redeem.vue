<template>
  <div class="redeem_page">
    <div v-if="loading" class="loading">
      <div class="spinner"></div>
    </div>

    <div v-else class="main-container">
      <button
        v-if="!showQRCodeScanner && !showVoucherPinInput"
        class="Redeem-voucher"
        @click="redeemWithQRCode"
      >
        Redeem with QR Code
      </button>
      <button
        v-if="!showQRCodeScanner && !showVoucherPinInput"
        class="Redeem-voucher"
        @click="redeemWithVoucherPin"
      >
        Redeem with Voucher Pin
      </button>

      <div v-if="showQRCodeScanner">
        <h1>Scan QR Code</h1>

        <!-- Render content for QR Code scanner here -->
        <qrcode-stream
          :paused="paused"
          @detect="onDetect"
          @error="onError"
          style="width: 300px; height: 300px"
        >
          <!-- Messages based on QR validation -->
          <div v-if="validationSuccess" class="validation-success">
            Voucher detected. Redeeming...
          </div>
          <div v-if="validationFailure" class="validation-failure">
            Invalid voucher QR code!
          </div>
          <div v-if="validationPending" class="validation-pending">
            Processing voucher...
          </div>
        </qrcode-stream>

        <!-- close button -->
        <button @click="resetViews">Close</button>
      </div>

      <div v-if="showVoucherPinInput">
        <!-- Render content for voucher pin input here -->
        <h1>Enter Voucher Pin</h1>
        <input type="text" placeholder="Enter Voucher Pin" />
        <button @click="redeemVoucher">Redeem</button>
        <br />
        <button @click="resetViews">Close</button>
      </div>
    </div>
  </div>
</template>
<script setup>
import { useRouter } from "vue-router";

import { QrcodeStream } from "vue-qrcode-reader";

import { ref, onMounted, computed } from "vue";

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

const isValid = ref(undefined);
const paused = ref(false);
const result = ref(null);

const validationPending = computed(() => isValid.value === undefined && paused.value);
const validationSuccess = computed(() => isValid.value === true);
const validationFailure = computed(() => isValid.value === false);

const onError = (error) => {
  console.error(error);
};

const resetValidationState = () => {
  isValid.value = undefined;
};

const onDetect = async ([firstDetectedCode]) => {
  result.value = firstDetectedCode.rawValue;
  paused.value = true;

  // If you want to validate the QR code content, you can use a condition similar to below:
  // For now, we're assuming any scanned QR is a valid voucherQRCodeString, but you can add conditions
  isValid.value = true; // Assume true for all QR codes. Modify as needed.
  
  if (isValid.value) {
    await redeemWithQRCodeString(result.value); // Use the QR code string for redemption
  }

  paused.value = false;
};

const redeemWithQRCodeString = async (voucherQRCodeString) => {
  loading.value = true;

  const supplierId = userDetails.value.id;
  
  fetch(
    `${API_ENDPOINT}/vouchers/redeem/${voucherQRCodeString}/supplier/${supplierId}`
  )
  .then(response => response.json())
  .then(data => {
    if (data.success) {
      alert("Voucher redeemed successfully. Please hand over the item to the customer.");
    } else {
      alert(data.message || "Voucher redemption failed");
    }
    loading.value = false;
    resetValidationState();
    resetViews(); // Reset views to hide the QR scanner
  })
  .catch(error => {
    console.error("Error redeeming voucher:", error);
    alert("An error occurred while redeeming the voucher. Please try again.");
    loading.value = false;
  });
};



const redeemVoucher = () => {
  loading.value = true;

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
      alert(
        "Voucher redeemed successfully. Please hand over the item to the customer."
      );
    } else {
      alert("Voucher redemption failed");
    }
  });
  loading.value = false;
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Roboto&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");

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
  font-family: "Poppins";
  color: #e040fb;
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
  background-color: #e040fb;
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

/* QR Code validation styles */
.validation-success,
.validation-failure,
.validation-pending {
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: rgba(255, 255, 255, 0.8);
  padding: 10px;
  text-align: center;
  font-weight: bold;
  font-size: 1.4rem;
  color: black;
  display: flex;
  flex-flow: column nowrap;
  justify-content: center;
  font-family: "Poppins"; /* Using Poppins font for consistency */
}

.validation-success {
  color: green;
}

.validation-failure {
  color: red;
}

.validation-pending {
  color: #e040fb; /* Using your purple theme color for the pending message */
}
</style>
