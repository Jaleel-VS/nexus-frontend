<template>
  <div class="login-page">
    <div class="login-wrapper">
      <h2>Sign In</h2>
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
      <button @click="handleLogin" class="sign-in-btn">
        <span v-if="loading" class="spinner"></span>
        <span v-else>Sign In</span>
      </button>

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
.login-page {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
  width: 100vw;
  font-family: 'Arial', sans-serif;
  background-color: #f7f7f7;
}

.login-wrapper {
  width: 30%;
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
  font-size: 2em;
  text-align: center;
  color: #111010;
}

.input-wrapper {
  display: flex;
  align-items: center;
  gap: 10px;
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

.sign-in-btn {
  background-color: #007bff;
  color: #fff;
  font-size: 1.2em;
  border-radius: 5px;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s;
}

input {
  flex: 1;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
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