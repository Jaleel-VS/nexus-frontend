<template>
  <div class="main">
    <h1 v-if="userDetails">Welcome, {{ userDetails.username }}</h1>

    <h2>View and claim your vouchers 🐲</h2>

    <div v-if="loading" class="loading">
      <div class="spinner"></div>
    </div>

    <div v-else>
        <div v-if="voucherRequests && !selectedRequest" class="main-container">
        <v-card
          v-for="request in voucherRequests"
          class="mx-auto -card"
          min-width="200"
        >

        <v-img
        class="align-end text-white"
            height="200"
            :src="getBrandDetails(request).profilePictureUrl"
            cover
            >
        </v-img>
        <v-card-text>
            <div>ID: {{ request.id }}</div>
            <div>Date: {{ request.requestDate }}</div>
            <div>Status: {{ request.requestStatus }}</div>
          </v-card-text>

          <v-card-actions>
            <v-btn color="orange" @click="selectRequest(request)">
              Select
            </v-btn>
          </v-card-actions>
        </v-card>
        </div> 

        <div v-if="selectedRequest" class="approve-container">
        <h2>Viewing voucher request with id {{ selectedRequest.id }}</h2>

        <button @click="claimVoucher">Claim</button>
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
const loading = ref(false);
const selectedRequest = ref(null);
const brandDetails = ref([]);

onMounted(async () => {
  loading.value = true;

  const userStore = useUserStore();
  userDetails.value = userStore.details;

  // /api/voucher-requests/influencers/{influencerId}
  const response = await fetch(
    `${API_ENDPOINT}/voucher-requests/influencers/${userDetails.value.id}`
  );

  const data = await response.json();

  voucherRequests.value = data;

//   setBrandDetails

for (let i = 0; i < voucherRequests.value.length; i++) {
    const brandResponse = await fetch(
      `${API_ENDPOINT}/users/brands/${voucherRequests.value[i].brandId}`
    );

    console.log(brandResponse);

    if (brandResponse.status === 200) {
      const brandData = await brandResponse.json();
      brandDetails.value.push(brandData);
    }
  }

  


  loading.value = false;
});

const getBrandDetails = (request) => {

    for (let i = 0; i < brandDetails.value.length; i++) {
        if (brandDetails.value[i].id === request.brandId) {
            return brandDetails.value[i];
        }
    }
}

const selectRequest = (request) => {
  selectedRequest.value = request;
};

const deselectRequest = () => {
  selectedRequest.value = null;
};

const claimVoucher = () => {
  alert("Please supply the supplier with the voucherID to claim your product");
  deselectRequest();
}

</script>

<style>
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
  width: 100%;
  flex-wrap: wrap;
  gap: 2rem;
}

h1 {
  font-size: 3rem;
  margin: 2rem 0;
  font-family: "Trocchi", serif;
}

h2 {
  font-size: 2rem;
  margin: 1rem 0;
  font-family: "Trocchi", serif;
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

.approve-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: auto;
  flex-wrap: wrap;
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

</style>
