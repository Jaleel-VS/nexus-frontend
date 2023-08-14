<template>
    <Navbar :username="username" />
    <h1>Voucher Requests</h1>

    <p> These are the voucher requests you've made. From here you can monitor your requests and see if they've been
        approved or not. An approved request can be claimed at the supplier</p>

    <div class="container">
        <div v-if="loading" class="loading">
            <div class="spinner"></div>
        </div>

        <div v-else class="table-wrapper">
            <table class="voucher-table">
                <thead>
                    <tr>
                        <th>Request ID</th>
                        <th>Request Date</th>
                        <th>Brand</th>
                        <th>Product</th>
                        <th>Request Status</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="request in voucherRequests" :key="request.id">
                        <td>{{ request.id }}</td>
                        <td>{{ request.requestDate }}</td>
                        <td>{{ request.brandName }}</td>
                        <td>{{ request.productName }}</td>
                        <td>{{ request.requestStatus }}</td>
                        <!-- claim voucher button if requestStatus is approved -->
                        <td v-if="request.requestStatus == 'APPROVED'">
                            <button @click="claimVoucher(request.id)">Claim Voucher</button>
                        </td>
                        <td v-else>
                           
                        </td>
                    </tr>
                </tbody>
            </table>
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

// axios
import axios from 'axios'

import { API_ENDPOINT } from '@/config/constants.js';

export default {
    components: {
        Navbar
    },

    setup() {
        const voucherRequests = ref([])
        const loading = ref(false)
        const userStore = useUserStore()
        const router = useRouter()

        const username = userStore.details.username

        // methods
        onMounted(
            async () => {
                fetchVoucherRequests()
            }
        )

        const fetchVoucherRequests = async () => {
            loading.value = true
            try {
                // /api/voucher-requests/influencers/{influencerId}
                const result = await axios.get(`${API_ENDPOINT}/voucher-requests/influencers/${userStore.details.id}`)
                voucherRequests.value = result.data

                // get brand names and product names
                voucherRequests.value.forEach(async (request) => {
                    const brandResult = await axios.get(`${API_ENDPOINT}/users/brands/${request.brandId}`)
                    const productResult = await axios.get(`${API_ENDPOINT}/products/${request.productId}`)
                    request.brandName = brandResult.data.brandName
                    request.productName = productResult.data.productName
                })

                // sort voucher by request status descending
                voucherRequests.value.sort((a, b) => {
                    if (a.requestStatus < b.requestStatus) {
                        return 1
                    }
                    if (a.requestStatus > b.requestStatus) {
                        return -1
                    }
                    return 0
                })
                loading.value = false
            } catch (err) {
                console.log(err)
                loading.value = false
            }
        }

        return {
            voucherRequests,
            loading,
            username
        }
    }
}

</script>

<style scoped>
h1 {
    font-family: 'Arial', sans-serif;
    font-size: 2em;
    color: #111010;
    margin: 0;
    padding: 20px;
    text-align: center;
}

p {
    font-family: 'Arial', sans-serif;
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


.table-wrapper {
    overflow-x: auto;
}

.voucher-table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 30px;
}

.voucher-table thead {
    background-color: #007BFF;
    color: white;
}

.voucher-table th,
.voucher-table td {
    padding: 10px;
    border: 1px solid #ccc;
    text-align: left;
}

button {
    background-color: #007BFF;
    color: white;
    padding: 5px 10px;
    border-radius: 5px;
    border: none;
    cursor: pointer;
}

button:hover {
    background-color: #0056b3;
}

</style>