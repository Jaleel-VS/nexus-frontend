<template>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Link to your CSS file for styling -->

    <div class="image" style="width: 300px; height: auto; position: absolute; top: 0; left: 0;">
    <img src="/proj_logo.png" style="width: 100%; height: 100%;" />
</div>

    <div class="registration-form">
        <h1>Register as a Supplier</h1>
        <form id="influencer-form" action="register.php" method="post"> <!-- Use your preferred server-side language for processing form data -->
            <img src="@/assets/supplier.png" />
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
            <label for="surname">Surname:</label>
            <input type="text" id="surname" name="surname" required>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <span id="password-message"></span>
            <label for="confirm-password">Confirm Password:</label>
            <input type="password" id="confirm-password" name="confirm-password" required>
            <span id="confirm-password-message"></span>
            <div class="button-container">
                <button id="connect-metamask" onclick="connectMetaMask()">Connect with MetaMask</button>
                <button type="submit">Register</button>
                <router-link to="/application">
                <button class="join-button">Cancel</button>
            </router-link>
            </div>
        </form>
    </div>
</template>

<script>
function connectMetaMask() {
    if (typeof window.ethereum !== 'undefined') {
        // MetaMask is available
        ethereum
            .request({ method: 'eth_requestAccounts' })
            .then((accounts) => {
                const userAddress = accounts[0];
                // You can now use the user's Ethereum address in your registration process
                alert('Connected to MetaMask. Ethereum Address: ' + userAddress);
            })
            .catch((error) => {
                console.error(error);
                alert('MetaMask connection failed. Please check your MetaMask setup.');
            });
    } else {
        // MetaMask is not available
        alert('MetaMask is not installed. Please install MetaMask to continue.');
    }
}
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
const passwordInput = document.getElementById('password');
  const confirmPasswordInput = document.getElementById('confirm-password');
  const passwordMessage = document.getElementById('password-message');
  const confirmPasswordMessage = document.getElementById('confirm-password-message');

  function validatePassword() {
    const password = passwordInput.value;
    const confirmPassword = confirmPasswordInput.value;

    // Password rules (you can customize these as needed)
    const minLength = 8;
    const containsUpperCase = /[A-Z]/.test(password);
    const containsLowerCase = /[a-z]/.test(password);
    const containsNumber = /[0-9]/.test(password);
    const containsSpecialChar = /[!@#$%^&*()_+[\]{};':"\\|,.<>?]/.test(password);

    // Validate and display messages
    let errorMessage = '';

    if (password.length < minLength) {
      errorMessage += `Password must be at least ${minLength} characters long.<br>`;
    }

    if (!containsUpperCase) {
      errorMessage += 'Password must contain at least one uppercase letter.<br>';
    }

    if (!containsLowerCase) {
      errorMessage += 'Password must contain at least one lowercase letter.<br>';
    }

    if (!containsNumber) {
      errorMessage += 'Password must contain at least one number.<br>';
    }

    if (!containsSpecialChar) {
      errorMessage += 'Password must contain at least one special character (!@#$%^&*()_+[]{};\'":\\|,.<>?).<br>';
    }

    passwordMessage.innerHTML = errorMessage;

    // Check if passwords match
    if (password === confirmPassword) {
      confirmPasswordMessage.innerHTML = '';
    } else {
      confirmPasswordMessage.innerHTML = 'Passwords do not match.';
    }
  }
</script>


<style scoped>
h1 {
    font-size: 2em;
    color: #111010;
    align-items: center;
    
}
.image {
    position: absolute;
    background-color: #fff;
    width: 50px;
    height: auto;
    top: 0;
    left: 100px;
    
}
.registration-form {
    width: 400px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    text-align: center;
    margin-top: 50px;
}

label {
    display: block;
    margin-bottom: 5px;
    text-align: left;
}

input[type="text"],
input[type="password"] {
    width: 100%;
    padding: 8px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 3px;
}

button {
    width: 100%;
    padding: 10px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 3px;
    cursor: pointer;
}

button:hover {
    background-color: #0056b3;
}

.button-container button {
  margin-bottom: 15px; 
}

.img{
    position: absolute;
    top: 0;
    left: 100px;
    transform: translateX(-50%);
    width: 100px;
    height: auto;
  }
</style>