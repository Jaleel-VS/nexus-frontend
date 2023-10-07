<template>
  <div>
    <Navbar :username="username" />
    <h1>Request Voucher</h1>

    <p>Please select a brand to work with, their product and the metamask account you'd like to receive the voucher in. To
      find out how to install metamask, click <a href="">here</a></p>

    <div class="container">
      <div v-if="loading" class="loading">
        <div class="spinner"></div>
      </div>

      <div v-else>
        <select v-model="selectedBrand" @change="fetchProducts">
          <option disabled value="">Please select a brand</option>
          <option v-for="brand in brands" :key="brand.id" :value="brand">
            {{ brand.brandName }}
          </option>
        </select>

        <select v-model="selectedProduct" v-if="products && products.length">
          <option disabled value="">Please select a product</option>
          <option v-for="product in products" :key="product.id" :value="product">
            {{ product.productName }} <!-- You may need to adjust this based on your product object structure -->
          </option>
        </select>

        <!-- Connect to MetaMask -->
        <button v-if="selectedBrand && selectedProduct && !publicAddress" @click="connectMetamask">➕ Connect to
          MetaMask</button>

        <!-- Switch MetaMask Account -->
        <button v-if="publicAddress" @click="switchMetamaskAccount">🔃 Switch MetaMask Account</button>


        <div v-if="publicAddress" style="padding-bottom: 30px; color: black;">
          <p>Requesting with public wallet address: {{ publicAddress }}</p>
        </div>


        <button @click="submit" :disabled="!publicAddress">Submit</button>
      </div>
    </div>
  </div>
</template>

<!-- vue 3 -->
<script>
import Navbar from '../components/navbar.vue';
// vue3-router
import { useRouter } from 'vue-router'

// pinia
import { useUserStore } from '@/store/user'

import { ref, onMounted, watch } from 'vue'
import axios from 'axios'

import { API_ENDPOINT } from '@/config/constants.js';

export default {
  components: {
    Navbar
  },

  setup() {
    const brands = ref([])
    const products = ref([])
    const selectedBrand = ref("")
    const selectedProduct = ref("")
    const loading = ref(false)
    const publicAddress = ref(null)

    const router = useRouter()
    const userStore = useUserStore()

    let username = ""

    // methods
    onMounted(() => {
      if (!userStore.details) {
        alert("Please login first")
        router.push('/login')
      } else {
        username = userStore.details.username
        fetchBrands()
      }
    })


    const fetchBrands = async () => {
      loading.value = true
      try {
        const result = await axios.get(`${API_ENDPOINT}/users/brands`)
        brands.value = result.data
        loading.value = false
      } catch (err) {
        console.log(err)
        loading.value = false
      }
    }

    const fetchProducts = async () => {
      console.log(`Selected brand: ${selectedBrand.value.id}`)
      loading.value = true
      products.value = [] // Clear previously loaded products
      try {
        // call api to get products, 
        // /api/products/brand/{brandId}

        const result = await axios.get(`${API_ENDPOINT}/products/brand/${selectedBrand.value.id}`)

        products.value = result.data

      } catch (error) {
        console.error(error)
      } finally {
        loading.value = false
      }
    }



    const connectMetamask = async () => {
      if (window.ethereum) {
        try {
          const accounts = await window.ethereum.request({ method: 'eth_requestAccounts' })
          publicAddress.value = accounts[0]
        } catch (error) {
          console.error(error)
        }
      } else {
        alert("Please install metamask")
      }
    }

    const switchMetamaskAccount = async () => {
      if (window.ethereum) {
        try {
          await window.ethereum.request({ method: 'wallet_requestPermissions', params: [{ eth_accounts: {} }] });

          // Now, request connection which will prompt user
          const accounts = await window.ethereum.request({ method: 'eth_requestAccounts' });
          publicAddress.value = accounts[0];
        } catch (error) {
          console.error(error);
        }
      } else {
        alert("Please install metamask");
      }
    }

    const submit = async () => {
      loading.value = true
      try {
        const voucherRequest = {
          influencerId: userStore.details.id,
          brandId: selectedBrand.value.id,
          productId: selectedProduct.value.id,
          walletAddress: publicAddress.value
        }

        const result = await axios.post(`${API_ENDPOINT}/voucher-requests`, voucherRequest)

        if (result.data) {
          alert("Voucher Request Sent!")
          router.push('/influencer/portal')
        }


        router.push('/influencer/dashboard')

      } catch (err) {
        console.log(err)
      } finally {
        loading.value = false
      }
    }

    return {
      username, brands, products, selectedBrand, selectedProduct, loading, publicAddress, fetchProducts, fetchBrands, connectMetamask, submit, switchMetamaskAccount
    }
  },

};

</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Roboto&display=swap");
@import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
h1 {
  font-family: 'Poppins', sans-serif;
  font-size: 2em;
  color: #111010;
  margin: 0;
  padding: 20px;
  text-align: center;
}

p {
  font-family: 'Poppins', sans-serif;
  font-size: 1.2em;
  color: #111010;
  margin: 0;
  padding: 20px;
  text-align: center;
}

.container {
  display: flex;
  flex-direction: column;
  /* justify-content: center; */
  align-items: center;
  height: 100vh;
  padding: 0 30px;
  box-sizing: border-box;
}

h1 {
  text-align: center;
  color: #333;
  margin-bottom: 50px;
}

img {
  /* width: 100px; */
  /* height: 100px; */
  margin-bottom: 20px;
}

select,
button {
  width: 100%;
  height: 40px;
  padding: 10px;
  margin-bottom: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button:disabled {
  background-color: #ccc;
}

button {
  background-color: #007BFF;
  color: white;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

/* css spinner */
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