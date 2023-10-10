



<template>
  <div class="main">
    <div class="logo">
      <img src="../assets/project_logo.png" alt="logo">
    </div>

    <div class="contact-page">


      <div class="contact-wrapper">
        <h2>Contact Us</h2>

        <div class="input-wrapper">
          <i class="fas fa-user"></i>
          <input v-model="FullName" type="text" placeholder="Full Name" />
        </div>
        <div class="input-wrapper">
          <i class="fas fa-envelope"></i>
          <input v-model="Email" type="email" placeholder="Email" />
        </div>
        <div class="input-wrapper">
          <i class="fas fa-phone"></i>
          <input v-model="PhoneNumber" type="phonenumber" placeholder="Phone Number" />
        </div>
        <div class="input-wrapper">
          <i class="fas fa-comment"></i>
          <input v-model="Message" type="message" placeholder="Your Message" />
        </div>
        <button @click="handleContact" class="contact-btn">
          <span v-if="loading" class="spinner"></span>
          <span v-else>Submit</span>
        </button>
      </div>
    </div>

    <div class="contactinfo-page">
      <div class="contact-info-section">
        <h2>Contact Details</h2>
        <div class="input-wrapper">
          <i class="fas fa-user"></i>
          <input type="text" value="Nexus Administrator" readonly />
        </div>
        <div class="input-wrapper">
          <i class="fas fa-phone"></i>
          <input type="text" value="027-456-7890" readonly />
        </div>
        <div class="input-wrapper">
          <i class="fas fa-fax"></i>
          <input type="text" value="441225800801" readonly />
        </div>
        <div class="input-wrapper">
          <i class="fas fa-envelope"></i>
          <input type="text" value="Blckchaincontract@nexus.com" readonly />
        </div>
        <div class="input-wrapper">
          <i class="fas fa-clock"></i>
          <input type="text" value="Working Hours: Mon-Fri, 9 AM - 5 PM" readonly />
        </div>
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


</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');

.main {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-image: url(../assets/color-bars.svg);
  background-size: cover;
  background-position: center;
  backdrop-filter: blur(0px);
  background-color: #001f3f;
}

.main::before {
    content: "";
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 1, 63, 0.85); /* Adjust the opacity as needed */
    z-index: -1; /* Ensure the semi-transparent pane is behind other elements */
}

.contact-info-section {
  width: 80%;
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
  z-index: 1;
  /* Adjust the spacing from the contact form */
  font-family: Poppins;
}

.contact-info-section h2 {
  font-size: 2em;
  text-align: center;
  color: rgb(13, 13, 72);
  font-family: Poppins;
  color: #e040fb;
}


.contact-info-section .input-wrapper {
  display: flex;
  align-items: center;
  gap: 10px;
  color: rgb(13, 13, 72);
  font-family: Poppins;

}

.contact-info-section input {
  flex: 1;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  color: #0b2c5c;
  font-family: Poppins;
}

.contact-info-section i {
  font-size: 1.2em;
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

.logo {
  text-align: center;

}

.logo img {
  max-width: 400px;
  vertical-align: middle;
  display: inline-block;
  margin-bottom: 20px;
  /* Add space between logo and form */
}

.contactinfo-page {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
  width: 100vw;
  font-family: Poppins;


}

.contact-page {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
  width: 100vw;
  font-family: Poppins;

}

.contact-wrapper {
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
  z-index: 1;
  /* Ensure the login form is above the background image */
  font-family: Poppins;
}

h2 {
  font-size: 2em;
  text-align: center;
  color: #e040fb;
  font-family: Poppins;
}

.input-wrapper {
  display: flex;
  align-items: center;
  gap: 10px;
  color: #0b2c5c;
  font-family: Poppins;
}









.contact-btn {
  background-color: #e040fb;
  color: #fff;
  font-size: 1.2em;
  border-radius: 5px;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s;
  justify-content: center;
  display: flex;
  font-family: Poppins;
}

input {
  flex: 1;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  color: #0b2c5c;
  font-family: Poppins;
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
          