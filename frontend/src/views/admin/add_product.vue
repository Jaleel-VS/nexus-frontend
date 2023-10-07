<template>
    <div class="main">
    <h1 v-if="userDetails">
      Welcome, {{ userDetails.username }}
    </h1>
    <h2>What product would you like to add?</h2>
    </div>
</template>
<script setup>
import { ref, onMounted } from "vue";
import { API_ENDPOINT } from "@/config/constants.js";
import { useUserStore } from "@/store/user";

const userDetails = ref(null);

onMounted(async () => {
  loading.value = true;

  const userStore = useUserStore();
  userDetails.value = userStore.details;

  console.log(userDetails.value);

  const response = await fetch(`${API_ENDPOINT}/users/brands`);
  const data = await response.json();
  brands.value = data;
  console.log(brands.value);

  loading.value = false;
});
</script>

<style scoped lang="scss">
@import url("https://fonts.googleapis.com/css2?family=Archivo:wght@100;400&family=DM+Serif+Display&family=Trocchi&family=Vesper+Libre&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Roboto&display=swap");
@import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
.main {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #001f3f;
}
h1 {
  font-size: 3rem;
  margin: 2rem 0;
  //  use Trocchi
  font-family: 'Poppins';
  color:#E040FB;
}

h2 {
  font-size: 2rem;
  margin: 1rem 0;
  // Trocchi
  font-family:'Poppins';
  color:#E040FB;
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