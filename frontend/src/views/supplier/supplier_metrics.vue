<template>
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

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Roboto&display=swap");
@import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
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
    background-color: #E040FB;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}
button:hover {
    opacity: 0.8;
}
</style>