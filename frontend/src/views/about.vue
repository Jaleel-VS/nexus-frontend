<template>
<div class="about-page">
    <div class="wrapper">
    <div class="logo">
            <img src="../assets/project_logo.png" alt="logo">       
        </div>
        <h1>About us</h1>
        <p>At Nexus, we are on a mission to transform the way influencers, brand managers, and suppliers collaborate in the digital age. 
            Our platform is designed to connect and empower these key players, fostering innovative partnerships and driving growth in 
            the influencer marketing ecosystem.</p>
    <h2>Our Story</h2>
    <p>Nexus began as a passion project, born from the desire to make influencer marketing accessible and efficient. 
        It all started when Impact.com saw the untapped potential in connecting influencers, brand managers, and suppliers seamlessly. 
        Through tireless dedication and a shared vision, Nexus emerged as a powerful solution to the challenges faced by the influencer 
        marketing community.</p>
        <h2>Our Values</h2>
        <p>At Nexus, our core values guide every decision we make. Integrity, transparency, and inclusivity are at the heart of what we do. 
            We believe in the power of meaningful collaborations and are committed to fostering an environment where every member of our 
            community can thrive.</p>
            <h2>Meet our team</h2>
            <div class="team">
            <img src="../assets/team_pic.jpeg" alt="Team picture">       
        </div>
        <p>Nexus Team: Thaakirah Majiet, Savanna Alexander, JD van Staden and Thaakir Mayet</p>
            <h2>Join us on this journey</h2>
            <p>Join us in shaping the future of influencer marketing. Whether you're an influencer, brand manager, or supplier, 
                Nexus is here to empower your collaborations. Sign up today and become a part of our vibrant community.</p>
                <div class="container">
                <router-link to="/application">
                        <button class="apply-btn">Join Now</button>
                    </router-link>
                </div>
    </div>
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
* {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
  font-family: "Roboto", sans-serif;
}
.about-page {
    background-color: #001f3f;
}
.wrapper {
  max-width: 75%;
  margin: auto;
}
.logo {
  text-align: center;
  margin-top: 1rem; /* Adjust the margin as needed */
}
.logo img {
  max-width: 400px; /* Adjust the max-width of the logo as needed */
  vertical-align: middle;
  display: inline-block;
}
.team {
  text-align: center;
  margin-top: 1rem; /* Adjust the margin as needed */
}
.team img {
  max-width: 400px; /* Adjust the max-width of the logo as needed */
  vertical-align: middle;
  display: inline-block;
}
.wrapper > p {
    margin: 1.5rem 0;
  text-align: center;
  font-size: 20px;
  color: white;
  font-family: 'Lora', sans-serif;
}
.wrapper > h1 {
  margin: 1.5rem 0;
  text-align: center;
  font-size: 45px;
  color: #E040FB;
  font-family: 'Poppins';
}
.wrapper > h1 {
  letter-spacing: 3px;
}
.wrapper > h2 {
  margin: 1.5rem 0;
  text-align: center;
  font-size: 30px;
  color: #E040FB;
  font-family: 'Roboto', serif;
}
.wrapper > h2 {
  letter-spacing: 3px;
}
.container {
  display: flex;
  justify-content: center; /* Center horizontally */
  align-items: center; /* Center vertically */
  height: 70px;
  
}
.apply-btn {
    padding: 10px 20px;
  background-color: #E040FB;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 30px;
  font-family: 'Roboto', serif;
  margin-bottom: 50px;
  
}
.apply-btn:hover {
    opacity: 0.8;
}
</style>