<template>
    <div class="register-page">
        <div class="login-background-image">
      <img src="@/assets/blockchain.jpg" alt="Background Image" />
    </div>
      <div class="registration-card">
        <h1>Register as a Supplier</h1>
        <form @submit.prevent="register">
            <div class="name-fields">
          <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" v-model="formData.name" required>
          </div>
          <div class="form-group">
            <label for="surname">Surname:</label>
            <input type="text" id="surname" v-model="formData.surname" required>
          </div>
        </div>
    
          <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" v-model="formData.email" required>
          </div>
          <div class="form-group">
            <label for="username">Username:</label>
            <input type="username" id="username" v-model="formData.username" required>
          </div>
          <div class="password-fields">
          <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" v-model="formData.password" required>
            <span id="password-message"></span>
          </div>
          <div class="form-group">
            <label for="confirm-password">Confirm Password:</label>
            <input type="password" id="confirm-password" v-model="formData.confirmPassword" required>
            <span id="confirm-password-message"></span>
          </div>
        </div>
          <div class="button-container">
            <button>Connect to MetaMask</button>
            <div class="register-buttons">
            <router-link to ="/login">
                <button>Register</button>
            </router-link>
            <router-link to ="/application">
                <button>Cancel</button>
            </router-link>
        </div>
          </div>
        </form>
      </div>
    </div>
  </template>
  
  <script>
  
  export default {
    data() {
      return {
        formData: {
          name: '',
          surname: '',
          email: '',
          username: '',
          password: '',
          confirmPassword: ''
        }
      };
    },
    methods: {
      register() {
        // Implement registration logic here
        // Access form data using this.formData
      },
      connectMetaMask() {
        // Implement MetaMask connection logic here
      },
      cancelRegistration() {
        // Implement cancel registration logic here
      }
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
    height: 100vh;
    background-color: white;
  }
  
  .registration-card {
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
  input[type="username"] {
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
  flex-direction: column;
    align-items: center; 
    gap: 10px;
  }
  
  button:last-child {
    margin-right: 0;
  }
  
  button:hover {
    background-color: #0056b3;
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
  