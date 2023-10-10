<template>
  <div class="register-page">
    <div class="registration-card">
      <h1>Register as an Influencer</h1>
      <form @submit.prevent="register">
        <div class="name-fields">
          <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" v-model="formData.name" required />
          </div>
          <div class="form-group">
            <label for="surname">Surname:</label>
            <input type="text" id="surname" v-model="formData.surname" required />
          </div>
        </div>
        <div class="form-group">
          <label for="socialmedia">Social Media Handle:</label>
          <input type="text" id="socialmedia" v-model="formData.socialmedia" required />
        </div>
        <div class="form-group">
          <label for="profilepicture">Profile Picture:</label>
          <input type="text" id="profilepicture" v-model="formData.profilepicture" required />
        </div>
        <div class="form-group">
          <label for="email">Email:</label>
          <input type="email" id="email" v-model="formData.email" required />
        </div>
        <div class="form-group">
          <label for="username">Username:</label>
          <input type="text" id="username" v-model="formData.username" required />
        </div>
        <div class="password-fields">
          <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" v-model="formData.password" required />
            <span id="password-message"></span>
          </div>
        </div>
        <div class="button-container">
          <button @click="connectMetamask" type="button">Connect to MetaMask</button>
          <div v-if="publicAddress" style="padding-bottom: 30px; color: black;">
            <p>Registering with public wallet address: {{ publicAddress }}</p>
          </div>
          <div class="register-buttons">
            <button type="submit">Register</button>
            <router-link to="/application">
              <button type="button">Cancel</button>
            </router-link>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";

const formData = ref({
  name: "",
  surname: "",
  socialmedia: "",
  profilepicture: "",
  email: "",
  username: "",
  password: "",
  confirmPassword: "",
});

const publicAddress = ref("");

const register = () => {
  // Implement registration logic here
  // Access form data using formData.value
};

const connectMetamask = async () => {
  if (window.ethereum) {
    try {
      const accounts = await window.ethereum.request({ method: "eth_requestAccounts" });
      publicAddress.value = accounts[0];
    } catch (error) {
      console.error(error);
    }
  } else {
    alert("Please install MetaMask to use this feature");

    // You can redirect to the MetaMask installation page here
    const url = "https://metamask.io/download/";
    window.open(url, "_blank");
  }
};


</script>
  
  <style scoped>
  @import url("https://fonts.googleapis.com/css2?family=Roboto&display=swap");
@import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
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
  .register-page {
    display: flex;
    justify-content: center;
    align-items: center;
  
    background-image: url(../../assets/color-bars.svg);
  background-size: cover;
  background-position: center;
  backdrop-filter: blur(0px);
  background-color: #001f3f;
  }

  .register-page::before {
  content: "";
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 1, 63, 0.85); /* Adjust the opacity as needed */
  z-index: -1; /* Ensure the semi-transparent pane is behind other elements */
}
  
  .registration-card {
    margin-top: 40px;
    margin-bottom: 40px;
    background-color: #fff;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
    padding: 20px;
    border-radius: 5px;
    text-align: center;
    width: 400px;
    z-index: 1; 
  }
  
  h1 {
    font-size: 24px;
    margin-bottom: 20px;
    color: #E040FB;
    font-family: 'Poppins';
  }
  
  .form-group {
    text-align: left;
  margin-bottom: 10px;
  }
  
  label {
    display: block;
    margin-bottom: 5px;
    color: #0b2c5c;
  }
  
  input[type="text"],
  input[type="password"],
  input[type="email"],
  input[type="username"],
  input[type="socialmedia"] {
    width: 100%;
    padding: 8px;
    border: 1px solid #0b2c5c;
    border-radius: 3px;
    margin-bottom: 10px;
    color: #333;
  }
  
  .button-container {
    display: flex;
    margin-top: 20px;
    gap: 10px;
    flex-direction: column;
    align-items: center;
    
  }
  .register-buttons {
    display: flex;
  flex-direction: column;
  align-items: center; 
  gap: 10px;
  }
  
  button {
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
  flex-direction: column;
    align-items: center; 
    gap: 10px;
  }
  
  button:last-child {
    margin-right: 0;
  }
  
  button:hover {
  opacity: 0.8;
  }
  /* Adjust the width and margins for the name and surname fields */
.name-fields {
    display: flex;
    justify-content: space-between;
    margin-bottom: 10px;
}

.name-fields .form-group {
    flex: 1;
    margin-right: 10px;
}


.password-fields {
    display: flex;
    justify-content: space-between;
    margin-bottom: 10px;
}

.password-fields .form {
    flex: 1;
    margin-right: 10px;
}

  </style>
  