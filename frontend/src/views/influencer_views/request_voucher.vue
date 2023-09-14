<template>
    <div class="container">
        <img src="../../../proj_logo.png" />


        <h1>Request Voucher</h1>


        <div v-if="loading" class="loading">
            <div class="spinner"></div>
        </div>

        <div v-else>
            <select v-model="selectedBrand" @change="fetchProducts">
                <option disabled value="">Please select a brand</option>
                <option v-for="brand in brands" :key="brand._id" :value="brand._id">
                    {{ brand.brand_name }}
                </option>
            </select>

            <select v-model="selectedProduct" v-if="products && products.length">
                <option disabled value="">Please select a product</option>
                <option v-for="product in products" :key="product._id" :value="product._id">
                    {{ product.product_name }} <!-- You may need to adjust this based on your product object structure -->
                </option>
            </select>

            <!-- connect to metamask -->
            <button v-if="selectedBrand && selectedProduct" @click="connectMetamask">Connect to MetaMask</button>

            <div v-if="publicAddress" style="padding-bottom: 30px; color: black;">
                <p>Requesting with public wallet address: {{ publicAddress }}</p>
            </div>


            <button @click="submit" :disabled="!publicAddress">Submit</button>
        </div>
    </div>
</template>

<script>
import { ref, onMounted, watch } from 'vue'
import axios from 'axios'
import { useUserStore } from '@/store/user'
import { useRouter } from 'vue-router'


export default {
    name: "RequestVoucher",
    setup() {
        const brands = ref([])
        const products = ref([])
        const selectedBrand = ref("")
        const selectedProduct = ref("")
        const loading = ref(false)
        const userStore = useUserStore()
        const publicAddress = ref(null)
        const router = useRouter()

        onMounted(async () => {
            console.log(userStore.details)
            loading.value = true
            try {

                const response = await axios.get('http://localhost:8080/brands') // Adjust URL as needed
                brands.value = response.data.data
            } catch (error) {
                console.error(error)
            } finally {
                loading.value = false
            }
        })

        const fetchProducts = async () => {
            loading.value = true
            products.value = [] // Clear previously loaded products
            try {
                const selectedBrandObj = brands.value.find(brand => brand._id === selectedBrand.value)
                for (let product of selectedBrandObj.products) {
                    const response = await axios.get(`http://localhost:8080/products/${product}`) // Adjust URL as needed
                    products.value.push(response.data.data)
                }
            } catch (error) {
                console.error(error)
            } finally {
                loading.value = false
            }
        }

        // Fetch products whenever selectedBrand changes
        watch(selectedBrand, fetchProducts)

        const connectMetamask = async () => {
            if (window.ethereum) {
                try {
                    const accounts = await window.ethereum.request({ method: 'eth_requestAccounts' })
                    publicAddress.value = accounts[0]
                } catch (error) {
                    console.error(error)
                }
            } else {
                alert("Please install MetaMask to use this feature")
            }
        }

        const submit = async () => {
            loading.value = true
            try {
                const voucherRequest = {
                    influencer_id: userStore.details.id, // Assuming this is the correct id
                    brand_id: selectedBrand.value,
                    product_id: selectedProduct.value,
                    request_date: new Date().toISOString(),
                    request_status: 'REQUESTED',
                    wallet_address: publicAddress.value
                }
                const response = await axios.post('http://localhost:8080/voucher-requests', voucherRequest) // Adjust URL as needed
                // You can handle the response here, maybe showing a success message to the user

                if (response.data) {
                    alert("Voucher Request Sent!")
                    router.push('/influencer_portal')
                }

                console.log(voucherRequest)


            } catch (error) {
                console.error(error)
                // You should handle this error, maybe showing an error message to the user
            } finally {
                loading.value = false
            }
        }

        return { brands, products, selectedBrand, selectedProduct, loading, fetchProducts, connectMetamask, submit, publicAddress }
    }
}
</script>


<style scoped>
body {
    background-color: #f3f3f3;
    font-family: Arial, sans-serif;
}

.container {
    display: flex;
    flex-direction: column;
    justify-content: center;
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
