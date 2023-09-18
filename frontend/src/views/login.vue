<template>



  <div class="login-page">
    <div class="login-background-image">
      <img src="@/assets/blockchain.jpg" alt="Background Image" />
    </div>
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
      <!-- <div class="remember-forgot">
        <div class="remember-me">
          <input type="checkbox" id="remember_me" />
          <label for="remember_me">Remember me</label>
        </div>
        <div class="forget-pw">
          <a href="#">Forgot password?</a>
        </div>
      </div> -->
      
      <button @click="handleLogin" class="login-btn">
        <span v-if="loading" class="spinner"></span>
        <span v-else>Login</span>
      </button>
      
      <div class="apply-section">
        <label>Don't have an account?</label>
        <a href="#">Apply</a>
      </div>

    </div>
  </div>
<div class="footer">
  <div class="footer-links">
    <a href="#">Terms of Service</a>
    <a href="#">Privacy Notice</a>
    <a href="#">Terms and Conditions</a>
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
            router.push('/influencerDash');
            break;
          case 'brand':
            router.push('/brandT');
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
.centered-login {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
}
.login-background-image {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 0; /* Place the background image behind the login form */
  overflow: hidden;
}

.login-background-image img {
  width: 100%;
  height: 300px;
  object-fit: cover;
  object-position: center;
}
.login-page {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
  width: 100vw;
  font-family: 'Arial', sans-serif;
  background-color: white;
  

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
  color: black;
}

.input-wrapper {
  display: flex;
  align-items: center;
  gap: 10px;
  color:#0b2c5c; 
}
.remember-forgot {
  display: flex;
  justify-content: center; /* Center content horizontally */
  gap: 10px; /* Add spacing between Remember Me and Forgot Password */
  color: black;
}

.remember-me {
  display: flex;
  align-items: center;
  margin-right: 30px;
}

.forget-pw a {
  color:  #0b2c5c;
  text-decoration: none;
  margin-left: 20px;
}

.apply-section {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 10px; /* Add top margin for spacing */
  color: black;
}
.apply-section a {
  color: #0b2c5c;
  text-decoration: none;
  margin-left: 5px; /* Add left margin for spacing */
}

.spinner {
    border: 6px solid #f3f3f3;
    /* Light grey */
    border-top: 6px solid #3498db;
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
  background-color: #0b2c5c;
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

.footer {
  text-align: center;
  margin-top: 20px;
}

.footer-links {
  margin-bottom: 10px;
}

.footer-links a {
  margin: 0 10px;
  text-decoration: none;
  color: #0b2c5c;
}

/* Style for the links on hover */
.footer-links a:hover {
  text-decoration: underline;
}
</style>