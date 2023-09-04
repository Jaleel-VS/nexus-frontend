<template>
    <div class="landing-page">
        <div class="content-wrapper">
            <div class="text-section">
                <h1>Nexus: <br> The three-way blockchain contract 📃</h1>
                <p>
                    Seamless transactions, guaranteed trust, and automated smart contracts—all made possible with our innovative crypto voucher system. Dive into the future of trusted business, where every party's interest is safeguarded.
                </p>
                <div class="buttons">
                    <button @click="displayLoginPage" class="sign-in-btn">Sign In</button>
                    <button class="apply-btn">Apply</button>
                </div>
            </div>
            <div class="image-section">
                <img src="https://static.vecteezy.com/system/resources/previews/010/842/435/original/3d-illustration-ethereum-logo-png.png"
                    alt="Hero Image" />
            </div>
        </div>
    </div>
</template>
  
<script>
import { useRouter } from 'vue-router'

// import on mount
import { onMounted } from 'vue'

// api
import { API_ENDPOINT, OTHER_CONST } from '@/config/constants.js';

// import axios
import axios from 'axios'


export default {
    setup() {
        const router = useRouter();

        // call api on mount
        onMounted(() => {
            axios.get(API_ENDPOINT)
                .then(res => {
                    console.log(res.data)
                })
                .catch(err => {
                    console.log(err)
                })
        })



        const displayLoginPage = () => {
            router.push('/login')
        }

        return { displayLoginPage }
    }
};
</script>

  
<style scoped>
.landing-page {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100vh;
    width: 100vw;
    font-family: 'Arial', sans-serif;
    background-color: #f7f7f7;
}

.content-wrapper {
    display: flex;
    width: 80%;
    max-width: 1200px;
    justify-content: space-between;
    flex-wrap: wrap;
}

.text-section {
    width: 50%;
    display: flex;
    flex-direction: column;
    gap: 20px;
}

h1 {
    font-size: 3.5em;
    color: #111010;
    margin-bottom: 20px;
}

p {
    color: #302e2e;
    line-height: 1.5;
    font-size: 1.2em;
}

.buttons {
    display: flex;
    gap: 20px;
}

button {
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 1em;
    transition: background-color 0.3s;
}

.sign-in-btn {
    background-color: #007bff;
    color: white;
}

.apply-btn {
    background-color: #ccc;
    color: #333;
}

button:hover {
    opacity: 0.8;
}

.image-section img {
    width: 30rem;
    height: 30rem;
    /* height: auto; */
    display: block;
}


@media (max-width: 768px) {

    .text-section,
    .image-section {
        width: 100%;
        text-align: center;
        /* display: none; */
    }

    .content-wrapper {
        justify-content: center;
    }

    h1 {
        font-size: 2em;
    }

    .buttons {
        justify-content: center;
    }
}
</style>
  