<template>
  <div class="main">
    <h1 v-if="userDetails">
      Welcome, {{ userDetails.username }}
    </h1>
    <h2>What brand would you like to work with?</h2>

    <div v-if="loading" class="loading">
      <div class="spinner"></div>
    </div>

    <div v-else class="main-container">
      <transition name="fade">
        <div v-if="!selectedBrand" class="brand-cards">
          <v-card
            v-for="brand in brands"
            class="mx-auto brand-card"
            min-width="200"
          >
            <v-img
              class="align-end text-white"
              height="200"
              :src="brand.profilePictureUrl"
              cover
            >
            </v-img>
            <v-card-subtitle class="pt-4">
              <span class="headline">{{ brand.brandName }}</span>
            </v-card-subtitle>
            <v-card-text>
              <div>{{ brand.brandCategory }}</div>
            </v-card-text>
            <v-card-actions>
              <v-btn color="orange" @click="selectBrand(brand)"> Select </v-btn>
            </v-card-actions>
          </v-card>
        </div>
      </transition>

      <!-- Display the selected card and undo button -->
      <transition name="fade">
        <div v-if="selectedBrand">
          <v-card class="mx-auto brand-card" min-width="200" width="200">
            <v-img
              class="align-end text-white"
              height="200"
              :src="selectedBrand.profilePictureUrl"
              cover
            >
            </v-img>
            <v-card-subtitle class="pt-4">
              <span class="headline">{{ selectedBrand.brandName }}</span>
            </v-card-subtitle>
            <v-card-text>
              <div>{{ selectedBrand.brandCategory }}</div>
            </v-card-text>
            <v-card-actions>
              <v-btn color="red" @click="deselectBrand"> Undo </v-btn>
            </v-card-actions>
          </v-card>
        </div>
      </transition>

      <h2 v-if="selectedBrand">What product would you like to promote?</h2>

      <div v-if="selectedBrand && !selectedProduct" class="product-cards">
        <v-card
          v-for="product in products"
          :key="product.id"
          class="mx-auto product-card"
          min-width="250"
        >
          <v-img
            class="align-end text-white"
            height="200"
            :src="product.productImage"
            cover
          >
          </v-img>
          <v-card-subtitle class="pt-4">
            <span class="headline">{{ product.productName }}</span>
          </v-card-subtitle>
          <!--        <v-card-text>-->
          <!--          <div>Whitehaven Beach</div>-->
          <!--          <div>Whitsunday Island, Whitsunday Islands</div>-->
          <!--        </v-card-text>-->
          <v-card-actions>
            <v-btn color="orange" @click="selectProduct(product)">
              Select
            </v-btn>
          </v-card-actions>
        </v-card>
      </div>

      <transition name="fade">
        <div v-if="selectedProduct && selectedBrand">
          <v-card class="mx-auto product-card" min-width="200" width="200">
            <v-img
              class="align-end text-white"
              height="200"
              :src="selectedProduct.productImage"
              cover
            >
            </v-img>
            <v-card-subtitle class="pt-4">
              <span class="headline">{{ selectedProduct.productName }}</span>
            </v-card-subtitle>
            <!--        <v-card-text>-->
            <!--          <div>Whitehaven Beach</div>-->
            <!--          <div>Whitsunday Island, Whitsunday Islands</div>-->
            <!--        </v-card-text>-->
            <v-card-actions>
              <v-btn color="red" @click="deselectProduct"> Undo </v-btn>
            </v-card-actions>
          </v-card>
        </div>
      </transition>

      <!--    display request button if selected producted and selected brand-->

      <v-btn
        v-if="selectedProduct && selectedBrand"
        color="orange"
        @click="request"
        class="request-btn"
      >
        Request
      </v-btn>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { API_ENDPOINT } from "@/config/constants.js";
import { useUserStore } from "@/store/user";

const brands = ref([]);
const products = ref([]);
const loading = ref(false);
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

// /api/products/brand/{brandId}

const getProducts = async (brandId) => {
  loading.value = true;

  const response = await fetch(`${API_ENDPOINT}/products/brand/${brandId}`);
  const data = await response.json();
  products.value = data;
  console.log(products.value);

  loading.value = false;
};

const selectedBrand = ref(null);
const selectedProduct = ref(null);

function selectBrand(brand) {
  selectedBrand.value = brand;

  getProducts(brand.id);
}

function deselectBrand() {
  selectedBrand.value = null;
  selectedProduct.value = null;
}

function selectProduct(product) {
  selectedProduct.value = product;
  console.log(selectedProduct.value);
}

function deselectProduct() {
  selectedProduct.value = null;
}

const request = async () => {
  loading.value = true;
  try {
    const voucherRequest = {
      influencerId: userDetails.value.id,
      brandId: selectedBrand.value.id,
      productId: selectedProduct.value.id,
    };

    const response = await fetch(`${API_ENDPOINT}/voucher-requests`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(voucherRequest),
    });

    console.log(response);

    if (response.status === 200) {
      alert("Voucher Request Sent!");
      // refresh the page
      deselectProduct();
      deselectBrand();
    }

    console.log(voucherRequest);
  } catch (error) {
    console.error(error);
    // You should handle this error, maybe showing an error message to the user
  } finally {
    loading.value = false;
  }
};
</script>

<style scoped lang="scss">
@import url("https://fonts.googleapis.com/css2?family=Archivo:wght@100;400&family=DM+Serif+Display&family=Trocchi&family=Vesper+Libre&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Roboto&display=swap");
@import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
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
  //  use Trocchi
  font-family: "Trocchi", serif;
}

h2 {
  font-size: 2rem;
  margin: 1rem 0;
  // Trocchi
  font-family: "Trocchi", serif;
}

.brand-cards {
  display: flex;
  flex-direction: row;
  // align-items: center;
  // justify-content: center;
  align-items: baseline;
  justify-content: flex-start;
  gap: 5em;
  flex-wrap: wrap;
  width: auto;
}

// add light shadow to cards
.brand-card {
  box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.2);
}

.product-cards {
  display: flex;
  flex-direction: row;
  // align-items: center;
  // justify-content: center;
  align-items: baseline;
  justify-content: flex-start;
  gap: 5em;
  flex-wrap: wrap;
  width: auto;
  margin-bottom: 20px;
}

.product-card {
  box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.2);
}

// glow on hover
.brand-card:hover {
  box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.4);
}

.product-card:hover {
  box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.4);
}

.request-btn {
  margin-top: 20px;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.25s;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
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
