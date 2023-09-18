<template>
    <Navbar :username="username" />

    <div class="create-voucher-page">
        <h1>Create new voucher</h1>

        <p> On this page you can mint a new voucher to influencer, and fund the voucher</p>

        <div class="create-voucher-wrapper">

            <p class="result-box" v-if="resultMessage" v-html="resultMessage"></p>

            <div v-else>

            <h2> Voucher details </h2>

            <div v-if="loading" class="loading">
                <div class="spinner"></div>
            </div>


            <div v-else class="details-wrapper">
                <h3>Influencer:</h3>
                <p> {{ voucherRequestDetails.influencerName }} </p>

                <h3>Influencer wallet address:</h3>
                <p> {{ voucherRequestDetails.influencerWalletAddress }} </p>

                <h3>Product:</h3>
                <p> {{ voucherRequestDetails.productName }} </p>

                <h3>Product amount:</h3>
                <p> {{ voucherRequestDetails.productAmount }} </p>

                <h3>Supplier:</h3>
                <p v-if="voucherRequestDetails.suppliers && voucherRequestDetails.suppliers.length > 0">
                    {{ voucherRequestDetails.suppliers[0] }}
                </p>

                <!-- select expiry date -->
                <h3>Days until expiry</h3>
                <!-- input type slider, number -->
                <input type="number" id="voucher-expiry-date" name="voucher-expiry-date" min="1" max="30" value="14">


                <h3>Voucher QR Code (Automatically generated)</h3>
                <!-- show qr code -->

                <!-- connect to metamask -->
                <h3>Connect to metamask</h3>
                <button @click="connectMetamask">Connect</button>

                <!-- mint voucher -->
                <h3>Mint voucher</h3>
                <button @click="mintVoucher">Mint Voucher</button>

                <!-- <p v-html="resultMessage"></p> -->


            </div>
        </div>





        </div>
    </div>
</template>

<script>

import Navbar from '../components/navbar.vue';

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

        const voucherPin = ref('')

        const username = userStore.details.username;

        const loading = ref(false);

        const publicAddress = ref('')

        const resultMessage = ref('')


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
                let infID = voucherRequestStore.details.influencerId
                let prodID = voucherRequestStore.details.productId

                const endpoint = `${API_ENDPOINT}/users/influencers/${infID}`
                const response = await axios.get(endpoint)

                console.log(response)

                voucherRequestDetails.value.influencerName = response.data.username;
                voucherRequestDetails.value.influencerWalletAddress = voucherRequestStore.details.walletAddress;

                const response2 = await axios.get(`${API_ENDPOINT}/products/${prodID}`)

                console.log(response2)

                voucherRequestDetails.value.productId = response2.data.productId
                voucherRequestDetails.value.productName = response2.data.productName
                voucherRequestDetails.value.productAmount = response2.data.productPrice

                const suppliers = response2.data.supplierIds

                voucherRequestDetails.value.suppliers = []
                voucherRequestDetails.value.supplierIds = suppliers


                console.log(suppliers)

                suppliers.map(async (supplierId) => {
                    const response = await axios.get(`${API_ENDPOINT}/users/suppliers/${supplierId}`)
                    voucherRequestDetails.value.suppliers.push(response.data.supplierName)
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

                


                const voucherData = {
                    supplierID: voucherRequestDetails.value.supplierIds[0],
                    voucherRequestID: voucherRequestStore.details.id,
                    expiryDate:  Math.floor(Date.now() / 1000) + parseInt(document.getElementById("voucher-expiry-date").value) * 24 * 60 * 60,
                }

                console.log(voucherData)

                const response = await axios.post(`${API_ENDPOINT}/vouchers`, voucherData)



                if (response.status === 200) {
                    console.log("Voucher minted successfully")
                    resultMessage.value = "Voucher minted successfully and sent to influencers wallet ✨. <br>View the blockchain transaction here ⛓: <a target=\"_blank\" href='https://sepolia.etherscan.io/tx/" + response.data.blockchainURL + "'>" + "etherscan" + "</a>"
                    // alert("Voucher minted successfully!")
                    // router.push('/brand/dashboard')
                }

                console.log(response)

            } catch (error) {
                console.log(error)
            }

            loading.value = false

        }

        // function for connecting metamask
        async function connectMetamask() {
            // connect metamask by calling the endpoint

            loading.value = true

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

            loading.value = false

        }


        return {
            username, voucherRequestDetails, voucherPin, loading, mintVoucher, connectMetamask, getRequestDetails, resultMessage
        }
    }
}

</script>

<style scoped>
.create-voucher-page {
    margin-top: 20px;
    display: flex;
    align-items: center;
    /* justify-content: center; */
    height: 100vh;
    width: 100vw;
    font-family: 'Arial', sans-serif;
    background-color: #f7f7f7;
    flex-direction: column;
}

.details-wrapper {
    /* width: 30%; */
    max-width: 400px;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    background-color: #fff;
    display: flex;
    flex-direction: column;
    gap: 20px;
}

h2 {
    margin-top: 20px;
    text-align: center;
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

.result-box {
    padding: 10px;
    margin-top: 15px;
    border-radius: 5px;
    border: 1px solid #ccc;
    background-color: #fff;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
</style>