<template>
  <div class="main">
    <h1 v-if="userDetails">Welcome, {{ userDetails.username }}</h1>
    <h2>Review Voucher Requests</h2>

    <div v-if="loading" class="loading">
      <div class="spinner"></div>
    </div>

    <div v-else>

      <div v-if="latestMint">
        <p v-html="latestMint"></p>
      </div>

      <div v-if="voucherRequests && !selectedRequest" class="main-container">
        <!-- <transition name="fade"> -->
        <v-card
          v-for="request in voucherRequests"
          class="mx-auto -card"
          min-width="200"
        >
          <v-img
            class="align-end text-white"
            height="300"
            :src="getInfluencerDetails(request).profilePictureUrl"
            cover
          >
          </v-img>
          <v-card-subtitle class="pt-4">
            <span class="headline">
              {{
                `${getInfluencerDetails(request).firstName} ${
                  getInfluencerDetails(request).lastName
                }`
              }}</span
            >
          </v-card-subtitle>
          <v-card-text>
            <div>ID: {{ request.id }}</div>
            <div>Date: {{ request.requestDate }}</div>
            <div>Product: {{ getProductDetails(request).productName }}</div>
          </v-card-text>

          <v-card-actions>
            <v-btn color="orange" @click="selectRequest(request)">
              Select
            </v-btn>
          </v-card-actions>
        </v-card>
        <!-- </transition> -->
      </div>

      <div v-if="selectedRequest" class="approve-container">
        <h2>Review voucher request with id {{ selectedRequest.id }}</h2>

        <div class="details-container">
          <h2>Influencer:</h2>
          <h3>
            {{
              `${getInfluencerDetails(selectedRequest).firstName} ${
                getInfluencerDetails(selectedRequest).lastName
              }`
            }}
          </h3>

          <h2>Product:</h2>
          <h3>
            {{ getProductDetails(selectedRequest).productName }}
          </h3>

          <h2>Request Date:</h2>
          <h3>{{ selectedRequest.requestDate }}</h3>

          <h2>Days until expiry:</h2>
          <input type="number" name="expiry" id="expiry"  min="7" max="21" placeholder="7">
        </div>
        <div class="buttons">
          <button @click="approveRequest">Approve</button>
          <button @click="denyRequest">Deny</button>

          
        </div>

        <p>Approving mints a new voucher on the blockchain</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { API_ENDPOINT } from "@/config/constants.js";
import { useUserStore } from "@/store/user";

const userDetails = ref(null);
const voucherRequests = ref([]);
const influencerDetails = ref([]);
const productDetails = ref([]);
const loading = ref(false);
const selectedRequest = ref(null);
const latestMint = ref(null);

onMounted(async () => {
  loading.value = true;

  const userStore = useUserStore();
  userDetails.value = userStore.details;

  // /api/voucher-requests/status/{status}/brands/{brandId}
  const response = await fetch(
    `${API_ENDPOINT}/voucher-requests/status/REQUESTED/brands/${userDetails.value.id}`
  );

  if (response.status === 200) {
    const data = await response.json();
    voucherRequests.value = data;

    for (let i = 0; i < voucherRequests.value.length; i++) {
      const influencerResponse = await fetch(
        `${API_ENDPOINT}/users/influencers/${voucherRequests.value[i].influencerId}`
      );

      if (influencerResponse.status === 200) {
        const influencerData = await influencerResponse.json();
        influencerDetails.value.push(influencerData);
      }
    }

    for (let i = 0; i < voucherRequests.value.length; i++) {
      const productResponse = await fetch(
        `${API_ENDPOINT}/products/${voucherRequests.value[i].productId}`
      );

      if (productResponse.status === 200) {
        const productData = await productResponse.json();
        productDetails.value.push(productData);
      }
    }
  }
  loading.value = false;
});

const getInfluencerDetails = (request) => {
  // find influencer details where influencerId = requestID.influencerId
  for (let i = 0; i < influencerDetails.value.length; i++) {
    if (influencerDetails.value[i].id === request.influencerId) {
      return influencerDetails.value[i];
    }
  }

  return null;
};

const getProductDetails = (request) => {
  for (let i = 0; i < productDetails.value.length; i++) {
    if (productDetails.value[i].id === request.productId) {
      return productDetails.value[i];
    }
  }

  return null;
};

const selectRequest = (request) => {
  selectedRequest.value = request;
};

const deselectRequest = () => {
  selectedRequest.value = null;
};

const approveRequest = async() => {

  loading.value = true;
  /*

  body:
  {
  "voucherRequestID": "string",
  "expiryDate": 0
}

endpoint: /api/vouchers

  */

  const days = document.getElementById("expiry").value;

  const currentDate = new Date();

  const futureDate = new Date(currentDate);
  futureDate.setDate(currentDate.getDate() + parseInt(days));

  const expiryDate = futureDate.getTime();

  const body = {
    voucherRequestID: selectedRequest.value.id,
    expiryDate: expiryDate,
  };

  const response = await fetch(`${API_ENDPOINT}/vouchers`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(body),
  });

  if (response.status === 200) {
    const data = await response.json();
    latestMint.value = "Voucher minted successfully and sent to influencers wallet ✨. <br>View the blockchain transaction here ⛓: <a target=\"_blank\" href='https://sepolia.etherscan.io/tx/" + data.blockchainURL + "'>" + "etherscan" + "</a>"
    console.log(data);
  }

  loading.value = false;

  alert("Voucher approved and minted on the blockchain");

  latestMint.value =

  deselectRequest();



  
};

const denyRequest = async() => {

  loading.value = true;

  try {
    const response = await fetch(
      `${API_ENDPOINT}/voucher-requests/${selectedRequest.value.id}/status/deny`
    );

    if (response.status === 200) {
      const data = await response.json();
      console.log(data);
    }
  } catch (error) {
    console.log(error);
  }

  loading.value = false;


  
};
</script>

<style scoped lang="scss">
@import url("https://fonts.googleapis.com/css2?family=Archivo:wght@100;400&family=DM+Serif+Display&family=Trocchi&family=Vesper+Libre&display=swap");

.main {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.main-container {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  width: auto;
  flex-wrap: wrap;
  gap: 5em;
}

.approve-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: auto;
  flex-wrap: wrap;
  // gap: 5em;
}

h1 {
  font-size: 3rem;
  margin: 2rem 0;
  //  use Trocchi
  font-family: "Trocchi", serif;
}

h2 {
  font-size: 2rem;
  margin: 1rem 0;
  // Trocchi
  font-family: "Trocchi", serif;
  
}

h3 {
  font-size: 1.5rem;
  margin: 1rem 0;
  // Trocchi
  font-family: "Trocchi", serif;
  // color #e4532b
  color: #e4532b;
}

p {
  font-size: 1rem;
  margin: 1rem 0;
  // Trocchi
  font-family: "Trocchi", serif;
  color: #0b2c5c;
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

// spinner and loading stuff

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

input {
  flex: 1;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  color: #0b2c5c;
}

.buttons {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 100%;
  gap: 2em;
  margin-top: 2em;
}
</style>
