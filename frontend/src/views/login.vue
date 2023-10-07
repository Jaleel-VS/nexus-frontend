<template>
  <div class="login-page">
    <!--<div class="login-background-image">
      <img src="@/assets/blockchain.jpg" alt="Background Image" />
    </div>-->
    <div class="login-wrapper">
      <h2>Login</h2>
      <div v-if="error" class="error-message">
        <i class="fas fa-exclamation-circle"></i> {{ error }}
      </div>
      <div class="input-wrapper">
        <i class="fas fa-user"></i>
        <input v-model="username" type="text" placeholder="Username" />
      </div>
      <div class="input-wrapper">
        <i class="fas fa-lock"></i>
        <input v-model="password" type="password" placeholder="Password" />
      </div>
      <!-- Add Remember Me checkbox and Forgot Password link here -->
      <div class="remember-forgot">
        <div class="remember-me">
          <input type="checkbox" id="remember_me" />
          <label for="remember_me">Remember me</label>
        </div>
        <div class="forget-pw">
          <a href="#">Forgot password?</a>
        </div>
      </div>
      
      <button @click="handleLogin" class="login-btn">
        <span v-if="loading" class="spinner"></span>
        <span v-else>Login</span>
      </button>
      
      <div class="apply-section">
        <label>Don't have an account?</label><ul>
        <li><router-link to="/application"><h3>Apply</h3></router-link></li>
      </ul>
        
      </div>

    </div>
  </div>


  
</template>


<script>
import { ref } from 'vue';
import axios from 'axios' // You need to install axios via npm install axios
import { useUserStore } from '@/store/user'
import { useRouter } from 'vue-router'

import { API_ENDPOINT, OTHER_CONST } from '@/config/constants.js';

export default {
  setup() {
    const username = ref('');
    const password = ref('');
    const error = ref('');
    const loading = ref(false);
    const router = useRouter()
    const userStore = useUserStore()

    const handleLogin = async () => {
      loading.value = true;
      await axios.post(`${API_ENDPOINT}/users/login`, {
        username: username.value,
        password: password.value
      }, {
        headers: {
          'accept': 'application/json',
          'Content-Type': 'application/json'
        }
      }).then((result) => {
        console.log(result);
        loading.value = false; // Reset the loader once the request is successful

        const role = result.data.userType;

        userStore.setUserDetails(result.data);

        console.log("User details:")
        console.log(userStore.details);

        switch ((role.toLowerCase())) {
          case 'supplier':
            router.push('/supplier/dashboard');
            break;
          case 'influencer':
            router.push('/influencer/dashboard');
            break;
          case 'brand':
            router.push('/brand/dashboard');
            break;
          default:
            router.push('/login');
            break;
        }
        console.log(role);
      }).catch((err) => {
        error.value = "Username or password is incorrect";
        loading.value = false; // Reset the loader if there's an error
        console.log(err);
      });
    }

    return { username, password, error, handleLogin, loading };

  }
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
.centered-login {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
}
/*.login-background-image {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 0; 
  overflow: hidden;
}*/


/*.login-background-image img {
  width: 100%;
  height: 300px;
  object-fit: cover;
  object-position: center;
}*/
.login-page {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
  width: 100vw;
  font-family: 'Poppins', sans-serif;
  background-color: #001f3f;
  

}
.login-wrapper {
  width: 30%;
  max-width: 400px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  gap: 20px;
  opacity: 2;
  background-color: #f7f7f7;
  position: relative;
  z-index: 1; /* Ensure the login form is above the background image */
  
}

h2 {
  font-size: 2em;
  text-align: center;
  color: #E040FB;
  font-family: 'Poppins';
}

.input-wrapper {
  display: flex;
  align-items: center;
  gap: 10px;
  color:#001f3f; 
}

.remember-forgot {
  display: flex;
  align-items: center;
  gap: 20px; /* Add spacing between Remember Me and Forgot Password */
  color: black;
}

.remember-me {
  display: flex;
  align-items: center;
}

.forget-pw a {
  color: #0b2c5c;
  text-decoration: none;
}

/* Add space between "Remember Me" and "Forgot Password" */
.forget-pw {
  margin-left: auto;
}




.apply-section {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 10px; /* Add top margin for spacing */
  color: black;
  gap: 10px; 
}
.apply-section a {
  color: #0b2c5c;
  text-decoration: none;
}

.spinner {
    border: 6px solid #f3f3f3;
    /* Light grey */
    border-top: 6px solid #E040FB;
    /* Blue */
    border-radius: 50%;
    width: 10px;
    height: 10px;
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

.login-btn {
  background-color: #E040FB;
  color: #fff;
  font-size: 1.2em;
  border-radius: 5px;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s;
  justify-content: center;
  display: flex;
}
button:hover {
  opacity: 0.8;
  }

input {
  flex: 1;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  color: #0b2c5c;
}

.error-message {
  color: red;
  text-align: center;
  display: flex;
  align-items: center;
  gap: 10px;
}

/* Responsive Styles */
@media (max-width: 768px) {
  .login-wrapper {
    width: 90%;
  }

  h2 {
    font-size: 1.5em;
  }
}




</style>