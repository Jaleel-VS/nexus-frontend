<template>
  <div class="register-page">
    <div class="logo">
      <img src="../assets/project_logo.png" class="top-left" alt="logo" />
    </div>

    <h1>Sign up</h1>

    <div class="button">
      <router-link to="/login">
        <button class="sign-in-btn">Login</button>
      </router-link>
    </div>

    <div class="container">
      <div class="card">
        <div class="card-content">
          <h2>Influencer</h2>
          <div class="images">
            <img src="@/assets/influencer.png" />
          </div>
          <p>
            You've built your own following on social media, and now you're
            looking to track and monetize your influence. This is precisely the
            account you have been seeking.
          </p>
          <router-link to="/influencer/influencer_register">
            <button class="join-button">Register</button>
          </router-link>
        </div>
      </div>
      <div class="card">
        <div class="card-content">
          <h2>Brand Manager</h2>
          <div class="images">
            <img src="@/assets/brand.png" />
          </div>
          <p>
            Are you a brand manager looking to amplify your brand's reach? Join
            us to connect with influencers and create impactful campaigns that
            resonate with your target audience.
          </p>
          <!-- <router-link to="/brand/brand_register"> -->
            <button class="join-button disable-button">Register</button>
          <!-- </router-link> -->
        </div>
      </div>
      <div class="card">
        <div class="card-content">
          <h2>Supplier</h2>
          <div class="images">
            <img src="@/assets/supplier.png" />
          </div>
          <p>
            Are you a supplier interested in collaborating with brands and
            influencers? Join us to showcase your products and services, and be
            a vital part of innovative influencer campaigns.
          </p>
          <!-- <router-link to="/supplier/supplier_register"> -->
            <button class="join-button disable-button">Register</button>
          <!-- </router-link> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { useRouter } from "vue-router";

// import on mount
import { onMounted } from "vue";

// api
import { API_ENDPOINT, OTHER_CONST } from "@/config/constants.js";

// import axios
import axios from "axios";

export default {
  setup() {
    const router = useRouter();

    // call api on mount
    onMounted(() => {
      axios
        .get(API_ENDPOINT)
        .then((res) => {
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    });

    const displayLoginPage = () => {
      router.push("/login");
    };

    return { displayLoginPage };
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Poppins&display=swap");
.register-page {
  background-image: url(../assets/color-bars.svg);
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

.logo {
  text-align: center;
  position: relative;
}

.logo img {
  max-width: 400px; /* Adjust the max-width of the logo as needed */
  vertical-align: middle;
  display: inline-block;
}
h1 {
  font-size: 4em;
  color: #e040fb;
  text-align: center;
  animation: slideDown 1s ease-in-out forwards;
  font-family: "Poppins";
}

.buttons {
  display: flex;
  gap: 20px;
  justify-content: center;
  font-family: "Poppins";
}

button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1em;
  transition: background-color 0.3s;
  font-family: "Poppins";
}
.image {
  position: absolute;
  width: 40px;
  height: auto;
  top: 0;
  left: 100px;
}

.sign-in-btn {
  position: absolute;
  top: 50px; /* Adjust this value for desired vertical position */
  right: 50px; /* Adjust this value for desired horizontal position */
  padding: 10px 20px;
  background-color: #e040fb;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-family: "Poppins";
}
button:hover {
  opacity: 0.8;
}

.container {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
}

.card-content {
  display: flex;
  flex-direction: column;
  justify-content: center; /* Center vertically */
  align-items: center; /* Center horizontally */
  height: 100%;
  /* Ensure the content takes full height */
}

.card {
  background-color: rgb(255, 255, 255);
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
  padding: 20px;
  text-align: center;
  border-radius: 8px;
  width: 350px;
  height: auto;
  margin: 20px;
  margin-bottom: 400px;
  animation: slideUp 1s ease-in-out forwards;
  flex: 1;
  max-width: calc(33.33% - 20px);
}

@media (max-width: 768px) {
  .card {
    max-width: calc(50% - 20px);
  }
}

h2 {
  color: #001f3f;
  font-size: 24px;
  margin-bottom: 10px;
  font-family: "Poppins";
}

p {
  color: #001f3f;
  font-size: 16px;
  margin-bottom: 20px;
  font-family: "Roboto";
}

.join-button {
  background-color: #e040fb;
  color: #fff;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s;
  font-family: "Poppins";
}

.join-button:hover {
  opacity: 0.8;
}

.disable-button {
  background-color: grey;
  color: #fff;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  font-size: 16px;
  cursor: not-allowed;
  transition: background-color 0.3s;
  font-family: "Poppins";
}

.images {
  position: relative; /* Change to relative positioning */
  margin-top: 20px;
  width: 90px;
  height: auto;
  max-width: 100%;
}

@media (max-width: 768px) {
  .text-section,
  .content-wrapper {
    justify-content: center;
  }

  h1 {
    font-size: 2em;
  }

  .buttons {
    justify-content: center;
  }
}
@keyframes slideUp {
  0% {
    transform: translateY(100%);
    opacity: 0;
  }
  100% {
    transform: translateY(0);
    opacity: 1;
  }
}
@keyframes slideDown {
  0% {
    transform: translateY(-100%);
    opacity: 0;
  }
  100% {
    transform: translateY(0);
    opacity: 1;
  }
}
</style>
