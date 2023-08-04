<template>
    <div class="container">

        <img src="../../../proj_logo.png" />


        <h1>Open Voucher Requests</h1>

        <div v-if="loading" class="loading">
            <div class="spinner"></div>
            <p>Updating Latest Data...</p>
        </div>


        <div v-else class="table-wrapper">
            <table class="voucher-table">
                <thead>
                    <tr>
                        <th>Request ID</th>
                        <th>Date Requested</th>
                        <th>Influencer Name</th>
                        <th>Influencer Handle</th>
                        <th>Product ID</th>
                        <th>Product Name</th>
                        <th>Approve</th>
                        <th>Deny</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="voucherRequest in voucherRequests" :key="voucherRequest._id" :class="{ 'disabled-row': voucherRequest.disabled }">
                        <td>{{ voucherRequest._id }}</td>
                        <td>{{ voucherRequest.requestDate }}</td>
                        <td>{{ voucherRequest.influencerName }}</td>
                        <td>{{ voucherRequest.influencerHandle }}</td>
                        <td>{{ voucherRequest.productId }}</td>
                        <td>{{ voucherRequest.productName }}</td>
                        <td>
            <button :disabled="voucherRequest.disabled" @click="updateStatus(voucherRequest._id, 'APPROVED')">Approve</button>
        </td>
        <td>
            <button :disabled="voucherRequest.disabled" @click="updateStatus(voucherRequest._id, 'DENIED')">Deny</button>
        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
import { onMounted, ref } from 'vue';
import axios from 'axios';

export default {
    setup() {
        const voucherRequests = ref([]);
        const loading = ref(true);  // Add this line

        const formatDate = (dateStr) => {
            const date = new Date(dateStr);
            return `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, '0')}-${String(date.getDate()).padStart(2, '0')}`;
        }

        const updateStatus = async (id, status) => {
      await axios.put(`http://localhost:8080/voucher-requests/${id}?new_status=${status}`);

      const voucher = voucherRequests.value.find(voucher => voucher._id === id);
      voucher.status = status;
      voucher.disabled = true;
    }

        const fetchData = async () => {
            const voucherRequestsData = await axios.get('http://localhost:8080/voucher-requests');

            for (let request of voucherRequestsData.data.data) {
                const influencerData = await axios.get(`http://localhost:8080/influencers/${request.influencer_id}`);
                console.log(influencerData);

                const productData = await axios.get(`http://localhost:8080/products/${request.product_id}`);

                voucherRequests.value.push({
                    _id: request._id,
                    requestDate: formatDate(request.request_date),
                    influencerName: `${influencerData.data.data.first_name} ${influencerData.data.data.last_name}`, // Assuming this is the property name
                    influencerHandle: influencerData.data.data.instagram_handle, // Assuming this is the property name
                    productId: productData.data.data._id, // Assuming this is the property name
                    productName: productData.data.data.product_name // Assuming this is the property name
                });
            }
        }

        onMounted(() => {
      loading.value = true;  // Add this line
      fetchData().then(() => loading.value = false);  // Add this line
    });

        return {
            voucherRequests,
            loading,
            updateStatus  // Add this line
        };
    },
};
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
    padding: 0 30px;
    box-sizing: border-box;
    color: black;
}

h1 {
    text-align: center;
    color: #333;
    margin-bottom: 20px;
}

img {
    margin-bottom: 20px;
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

/* Add these styles */
.loading {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 60vh;
    text-align: center;
    flex-direction: column;
}

.spinner {
    border: 16px solid #f3f3f3;
    border-top: 16px solid #3498db;
    border-radius: 50%;
    width: 120px;
    height: 120px;
    animation: spin 2s linear infinite;
    margin-bottom: 20px;
}

@keyframes spin {
    0% {
        transform: rotate(0deg);
    }
    100% {
        transform: rotate(360deg);
    }
}

.disabled-row {
    color: #999;
    text-decoration: line-through;
}

.disabled-row button {
    cursor: not-allowed;
    opacity: 0.5;
}

</style>
