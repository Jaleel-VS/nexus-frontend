<template>
  <div>
      <img src="proj_logo.png" />
  </div>

  <div class="login">

      <h1 class="text-center">Login Page</h1>

      <input type="text" v-model="username" placeholder="Username" />
      <input type="password" v-model="password" placeholder="Password" />
      <button @click="login">Login</button>

      <!-- TODO: Add Register Button -->
              
  </div>
</template>

<script>
import { ref } from 'vue'
import axios from 'axios' // You need to install axios via npm install axios
import { useUserStore } from '@/store/user'
import { useRouter } from 'vue-router'

export default {
    name: "Login",
    setup() {
        const username = ref('')
        const password = ref('')
        const router = useRouter()
        const userStore = useUserStore()

        const login = async () => {
            const response = await axios.post('http://localhost:8080/login', {
                username: username.value,
                password: password.value
            }, {
                headers: {
                    'accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            })

            if(response.data){
                console.log("Login Success")

                // Store the user details in the store
                userStore.setUserDetails(response.data)

                switch(response.data.role){
                    case 'SUPPLIER':
                        router.push('/SupplierPortal')
                        break
                    case 'INFLUENCER':
                        router.push('/influencer_portal')
                        break
                    case 'BRAND':
                        router.push('/brand_home')
                        break
                    default:
                        console.log("Invalid Role")
                }
            }else{
                console.log("Login Failed")
                // Implement code here for what should happen if login fails
            }
        }

        return { username, password, login }
    }
}
</script>
<style scoped>

img{
            position: absolute;
            top: 0;
            left: 50%;
            transform: translateX(-50%);
            width: 400px;
            height: auto;

}
  .login {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    text-align: center;
    height: 100vh;
    padding: 2em;
    box-sizing: border-box;
    font-family: Arial, sans-serif;
    color: #333;
  }
  
  .login h1 {
    font-size: 2.5em;
    margin-bottom: 25px;
  }
  
  
  .login button {
    background-color: #3498db;
    color: white;
    border: none;
    border-radius: 5px;
    padding: 1em 2em;
    font-size: 1em;
    text-decoration: none;
    cursor: pointer;
    transition: background-color 0.3s ease;
    margin-top: 1rem;
  }
  .r-button{
    background-color: #3498db;
    color: white;
    border: none;
    border-radius: 5px;
    padding: 1em 2em;
    font-size: 1em;
    text-decoration: none;
    cursor: pointer;
    transition: background-color 0.3s ease;
    margin-top: 1rem;
  }
  

  </style>