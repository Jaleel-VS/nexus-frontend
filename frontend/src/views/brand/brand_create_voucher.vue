<template>
    <Navbar :username="username" />

    <div class="create-voucher-page">
        <h1>Create new voucher</h1>

        <p> On this page you can mint a new voucher to influencer, and fund the voucher</p>

        <div class="create-voucher-wrapper">



            <h2> Voucher details </h2>

            <div v-if="loading" class="loading">
                <div class="spinner"></div>
            </div>


            <div v-else>
                <p>Influencer:</p>
                <p> {{ voucherRequestDetails.influencerName }} </p>

                <p>Influencer wallet address:</p>
                <p> {{ voucherRequestDetails.influencerWalletAddress }} </p>

                <p>Product:</p>
                <p> {{ voucherRequestDetails.productName }} </p>

                <p>Product amount:</p>
                <p> {{ voucherRequestDetails.productAmount }} </p>

                <p>Supplier:</p>
                <p> {{ voucherRequestDetails.suppliers[0] }} </p>

                <!-- select expiry date -->
                <p>Set Voucher Expiry date:</p>
                <!-- min tomorrow, max 14 days from now -->
                <input type="date" id="voucher-expiry-date" name="voucher-expiry-date" min="2023-08-16" max="2023-08-30">


                <p>Voucher Pin (Automatically generated)</p>
                <p> {{ voucherPin }} </p>

                <!-- connect to metamask -->
                <p>Connect to metamask</p>
                <button @click="connectMetamask">Connect</button>

                <!-- mint voucher -->
                <button @click="mintVoucher">Fund and Mint Voucher</button>

            </div>





        </div>
    </div>
</template>

<script>

import Navbar from '@/components/Navbar.vue'

import { useUserStore } from '@/store/user'
import { useVoucherRequestStore } from "@/store/voucher_request";

import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";

import { API_ENDPOINT } from '@/config/constants.js'

import axios from 'axios';

export default {
    name: 'CreateVoucher',
    components: {
        Navbar
    },
    setup() {

        const userStore = useUserStore()
        const voucherRequestStore = useVoucherRequestStore()

        const voucherRequestDetails = ref({})

        const voucherDetails = ref({})

        const voucherPin = ref('')

        const username = userStore.username;

        const router = useRouter()

        const loading = ref(false);


        onMounted(() => {
            getRequestDetails()

            // generated random 6 digit number for voucher pin, string
            voucherPin.value = Math.floor(100000 + Math.random() * 900000).toString()

        })

        // function for getting requestDetails from backend
        async function getRequestDetails() {
            // set influencer name, product name, product amount by calling the endpoints

            loading.value = true

            try {
                const response = await axios.get(`${API_ENDPOINT}/users/influencers/${voucherRequestStore.influencerId}`)

                voucherRequestDetails.influencerName = response.data.username
                voucherRequestDetails.influencerWalletAddress = voucherRequestDetails.walletAddress

                const response2 = await axios.get(`${API_ENDPOINT}/products/${voucherRequestStore.productId}`)

                voucherRequestDetails.productName = response2.data.productName
                voucherRequestDetails.productAmount = response2.data.productAmount

                const suppliers = response2.data.supplierIds

                voucherRequestDetails.suppliers = []

                suppliers.map(async (supplierId) => {
                    const response = await axios.get(`${API_ENDPOINT}/users/suppliers/${supplierId}`)
                    voucherRequestDetails.suppliers.push(response.data.supplierName)
                })

            } catch (error) {
                console.log(error)

            }

            loading.value = false

        }

        // function for minting voucher
        async function mintVoucher() {
            // mint voucher by calling the endpoint

            loading.value = true

            try {
                //             struct VoucherData {
                //     string brandID;
                //     string influencerID;
                //     string supplierID;
                //     string productID;
                //     uint256 expiryDate;
                // }

                // get expiry date
                const expiryDate = document.getElementById("voucher-expiry-date").value
                const future = Math.floor((new Date(expiryDate).getTime() - Date.now()) / 1000)

                const voucherData = {
                    brandID: userStore.userId,
                    influencerID: voucherRequestStore.influencerId,
                    supplierID: voucherRequestDetails.suppliers[0],
                    productID: voucherRequestStore.productId,
                    // integer representing the expiry date
                    expiryDate: Math.floor(Date.now() / 1000) + future
                }

                const response = await axios.post(`${API_ENDPOINT}/vouchers`, voucherData)

                if (response.status === 200) {
                    console.log("Voucher minted successfully")
                    router.push('/brand/dashboard')

                }

                console.log(response)

                // redirect to brand dashboard

            } catch (error) {
                console.log(error)
            }

            loading.value = false

        }


        return {
            username, voucherRequestDetails, voucherPin, loading
        }
    }
}

</script>

<style scoped>
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