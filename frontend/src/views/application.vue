<template>
    <div class="application-page">
        
        <div class="image" style="width: 300px; height: auto; position: absolute; top: 0; left: 0;">
    <img src="proj_logo.png" style="width: 100%; height: 100%;" />
</div>

            <div>
                <h1>Sign up as an influencer, brand manager or supplier</h1>
            </div>
            <div class="button">
            <router-link to="/login">
                <button class="sign-in-btn">Login</button>
            </router-link>
        </div>
        <body>
        <div class="container">
            <div class="card">
            <h2>Influencer</h2>
            <img src="@/assets/influencer.png" />
            <p>You've built your own following on social, and now you're looking to track and monetize your influence. This is the account you want.</p>
            <router-link to="/InfluencerRegister">
                <button class="join-button">Join Now</button>
            </router-link>
        </div>
        <div class="card">
            <h2>Brand Manager</h2>
            <img src="@/assets/brand.png" />
            <p>Are you a brand manager looking to amplify your brand's reach? Join us to connect with influencers and create impactful campaigns that resonate with your target audience.</p>
            <router-link to="/BrandRegister">
                <button class="join-button">Join Now</button>
            </router-link>
            
        </div>
        <div class="card">
            <h2>Supplier</h2>
            <img src="@/assets/supplier.png" />
            <p>Are you a supplier interested in collaborating with brands and influencers? Join us to showcase your products and services, and be a vital part of innovative influencer campaigns.</p>
            <router-link to="/supplier/supplier_register">
                <button class="join-button">Join Now</button>
            </router-link>
        </div>
        </div>
        </body>
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
h1 {
    font-size: 2em;
    color: #111010;
    margin-top: 150px;
    align-items: center;
    margin-left: 450px;
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
.image {
    position: absolute;
    background-color: #fff;
    width: 50px;
    height: auto;
    top: 0;
    left: 100px;
    
}

.sign-in-btn {
    position: absolute;
    top: 20px; /* Adjust this value for desired vertical position */
    right: 20px; /* Adjust this value for desired horizontal position */
    padding: 10px 20px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}
button:hover {
    opacity: 0.8;
}

body {
    font-family: Arial, sans-serif;
    background-color: #f0f0f0;
    margin: 0;
    padding: 0;
}

.container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}


.card {
    background-color: rgb(255, 255, 255);
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
    padding: 20px;
    text-align: center;
    border-radius: 8px;
    width: 300px;
    margin: 20px;
    margin-bottom: 400px;
}

h2 {
    color: #333;
    font-size: 24px;
    margin-bottom: 10px;
}

p {
    color: #666;
    font-size: 16px;
    margin-bottom: 20px;
}

.join-button {
    background-color: #007bff;
    color: #fff;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
    transition: background-color 0.3s;
}

.join-button :hover {
    background-color: #0056b3;
}

.img{
    position: absolute;
    top: 0;
    left: 100px;
    transform: translateX(-50%);
    width: 100px;
    height: auto;
  } 


@media (max-width: 768px) {

    .text-section,
   

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


