<template>
    <Navbar :username="username" />
    <h1>Voucher Requests</h1>

    <p> These are the voucher requests influencers have made. From here you can approve or deny them. Upon approval you'll
        be able to mint the voucher and fund the amount for the requested product.</p>

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
                        <th>Influencer</th>
                        <th>Product</th>
                        <th>Request Status</th>
                        <th>Approve</th>
                        <th>Deny</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="request in voucherRequests" :key="request.id">
                        <td>{{ request.id }}</td>
                        <td>{{ request.requestDate }}</td>
                        <td><a href="" target="_blank">@{{ request.influencerName }}</a></td>
                        <td>{{ request.productName }}</td>
                        <td>{{ request.requestStatus }}</td>
                        <td>
                            <button @click="approveRequest(request.id)">Approve</button>
                        </td>
                        <td>
                            <button @click="denyRequest(request.id)">Deny</button>
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
import { useVoucherRequestStore } from '@/store/voucher_request'

import { ref, onMounted, watch } from 'vue'

// axios
import axios from 'axios'


import { API_ENDPOINT } from '@/config/constants.js';

export default {
    components: {
        Navbar
    },

    setup() {
        const router = useRouter()
        const userStore = useUserStore()

        const username = userStore.details.username

        const loading = ref(true)
        const voucherRequests = ref([])

        const getVoucherRequests = async () => {
            try {
                // http://localhost:8080/api/voucher-requests/status/REQUESTED/brands/9
                const response = await axios.get(`${API_ENDPOINT}/voucher-requests/status/REQUESTED/brands/${userStore.details.id}`)
                voucherRequests.value = response.data

                // get influencer name and product name
                for (let i = 0; i < voucherRequests.value.length; i++) {
                    const influencerId = voucherRequests.value[i].influencerId
                    const productId = voucherRequests.value[i].productId

                    const influencerResponse = await axios.get(`${API_ENDPOINT}/users/influencers/${influencerId}`)
                    const productResponse = await axios.get(`${API_ENDPOINT}/products/${productId}`)

                    voucherRequests.value[i].influencerName = influencerResponse.data.username
                    voucherRequests.value[i].productName = productResponse.data.productName
                }
                loading.value = false
            } catch (error) {
                console.log(error)
            }
        }

        onMounted(getVoucherRequests)

        const approveRequest = async (requestId) => {
            try {
                const thisVoucherRequest = voucherRequests.value.find(request => request.id === requestId)


                // approve request using endpoint
                // put /{requestId}/approve
                const response = await axios.put(`${API_ENDPOINT}/voucher-requests/${requestId}/approve`)

                // if 200
                if (response.status === 200) {
                    // set voucher request details in store
                    useVoucherRequestStore().setVoucherRequestDetails(thisVoucherRequest)
                    console.log(useVoucherRequestStore().details)
                    // redirect to create voucher page
                    router.push('/brand/create-voucher')
                } else {
                    alert('Something went wrong')
                    console.log(response)
                }

            } catch (error) {
                console.log(error)
            }
        }

        const denyRequest = async (requestId) => {
            try {
                // deny request using endpoint
                // /{requestId}/deny
                const response = await axios.put(`${API_ENDPOINT}/voucher-requests/${requestId}/deny`)

                // if 200
                if (response.status === 200) {
                    // remove request from voucherRequests
                    const index = voucherRequests.value.findIndex(request => request.id === requestId)
                    voucherRequests.value.splice(index, 1)


                } else {
                    alert('Something went wrong')
                    console.log(response)
                }
            } catch (error) {
                console.log(error)
            }
        }

        return {
            username, loading, voucherRequests, approveRequest, denyRequest
        }
    },

};

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