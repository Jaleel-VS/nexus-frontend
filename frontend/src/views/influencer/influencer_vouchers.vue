<template>
  <div class="main">
    <h1 v-if="userDetails">Welcome, {{ userDetails.username }}</h1>

    <h2>View and claim your vouchers 🐲</h2>

    <div v-if="loading" class="loading">
      <div class="spinner"></div>
    </div>

    <div v-else>
        <div v-if="voucherRequests && !selectedRequest" class="main-container">
        <!-- <transition name="fade"> -->
        <!-- <v-card
          v-for="request in voucherRequests"
          class="mx-auto -card"
          min-width="200"
        > -->
        </div>

    </div>
  </div>
</template>

<script>
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

  


  loading.value = false;
});

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
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
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
</style>
