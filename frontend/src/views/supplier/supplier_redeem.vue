<template>
    <div class="image" style="width: 300px; height: auto; position: absolute; top: 0; left: 0;">
    <img src="/proj_logo.png" style="width: 100%; height: 100%;" />
</div>
    <h1>Redeem Voucher</h1>
    <div class="button">
            <router-link to="/login">
                <button class="sign-in-btn">Logout</button>
            </router-link>
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
<style scope>
h1 {
    font-size: 2em;
    color: #111010;
    align-items: center;
    margin-left: 45%;
    margin-top: 90px;
}
.image {
    position: absolute;
    width: 50px;
    height: auto;
    top: 0;
    left: 100px;
    
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
</style>