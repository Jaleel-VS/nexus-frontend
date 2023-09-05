<template>
    <div class="landing-page">
        <nav class="nav-bar">
            <div class="container">
            <ul>
                <li><a href="#"><b>About</b></a></li>
                <li><a href="#"><b>Security and Privacy</b></a></li>
                <li><a href="#"><b>Testimonials</b></a></li>
                <li><a href="#"><b>FAQ Section</b></a></li>
                <li><a href="#"><b>Contact</b></a></li>
            </ul>
            </div>
        </nav>
        
        <div class="content-wrapper">
            <div class="text-section">
                <h1>Nexus: <br> The three-way blockchain contract</h1>
                <p>
                    Seamless transactions, guaranteed trust, and automated smart contracts—all made possible with our innovative crypto voucher system. Dive into the future of trusted business, where every party's interest is safeguarded.
                </p>
                <div class="buttons">
                    <button @click="displayLoginPage" class="sign-in-btn">Sign In</button>
                    <button class="apply-btn">Apply</button>
                </div>
            </div>
            <div class="image-section">
                <img src="https://static.vecteezy.com/system/resources/previews/010/842/435/original/3d-illustration-ethereum-logo-png.png"
                    alt="Hero Image" />
            </div>
        </div>
        <header class="header">
            <h2>Why Nexus?</h2>
        </header>
        <div class="image-container">
            <div class="image-wrapper">
                <img src="../assets/LandingPage_catalogue.png" alt="Image 1">
                <b><p>Broad Catalogue</p></b>
                <p>Extensive merchant network gives you direct access to hundreds of premier global brands across multiple sectors.</p>
            </div>
            <div class="image-wrapper">
                <img src="../assets/LandingPage_trackandreport.png" alt="Image 2">
                <b><p>Tracking and Reporting</p></b>
                <p>Blockchain technology allows you to easily manage and track all your vouchers and progress in one place.</p>
            </div>
            <div class="image-wrapper">
                <img src="../assets/LandingPage_quickeasy.png" alt="Image 3">
                <b><p>Quick and Easy</p></b>
                <p>We would like to help you as fast as we can, in doing so signing up is very easy. </p>
            </div>
            
        </div>
        <header class="header">
            <h3>Let's get you started...</h3>
        </header>
        <section id="timeline">
  
            <div class="tl-item">
            
            <div class="tl-bg" style="background-image: url(https://placeimg.com/802/802/nature)"></div>
            
            <div class="tl-steps">
                <p class="f2 heading--sanSerif">1</p>
            </div>

            <div class="tl-content">
                <h1>Apply</h1>
                <p>Create your profile and connect your social platforms</p>
            </div>

            </div>

            <div class="tl-item">
            
            <div class="tl-bg" style="background-image: url(https://placeimg.com/802/802/nature)"></div>
            
            <div class="tl-steps">
                <p class="f2 heading--sanSerif">2</p>
            </div>

            <div class="tl-content">
                <h1 class="f3 text--accent ttu">Browse through all the brands</h1>
                <p>Browse through many brands and see which ones stand out to you.</p>
            </div>

            </div>

            <div class="tl-item">
            
            <div class="tl-bg" style="background-image: url(https://placeimg.com/803/803/nature)"></div>
            
            <div class="tl-steps">
                <p class="f2 heading--sanSerif">3</p>
            </div>

            <div class="tl-content">
                <h1 class="f3 text--accent ttu">Request a voucher</h1>
                <p>Enter the information required to request a voucher and wait for an approval by the brand.</p>
            </div>

            </div>

            <div class="tl-item">
            
            <div class="tl-bg" style="background-image: url(https://placeimg.com/800/800/nature)"></div>
            
            <div class="tl-steps">
                <p class="f2 heading--sanSerif">4</p>
            </div>

            <div class="tl-content">
                <h1 class="f3 text--accent ttu">Start promoting</h1>
                <p>Share and promote your brand new product across your social media platforms with your followers.</p>
            </div>
            </div>
            </section>
            <header class="header">
            <h3>Testimonials</h3>
            </header>
        <div class="testimonial-container">
        <div class="testimonial-card">
            <div class="text">Hello<i class="fas fa-quote-right quote"></i></div>
            <div class="footer">
            <div class="image"><img src="../assets/Testimonial_DP1.jpg" alt="Image 1"></div>
            <h3 class='person'>Paul</h3></div>
        </div>
        <div class="testimonial-card">
            <div class="text">Hello<i class="fas fa-quote-right quote"></i></div>
            <div class="footer">
            <div class="image"><img src="../assets/Testimonial_DP2.jpg" alt="Image 1"></div>
            <h3 class='person'>Elizabeth</h3></div>
        </div>
        <div class="testimonial-card">
            <div class="text">Hello<i class="fas fa-quote-right quote"></i></div>
            <div class="footer">
            <div class="image"><img src="../assets/Testimonial_DP3.jpg" alt="Image 1"></div>
            <h3 class='person'>Mary</h3></div>
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
.landing-page {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 100vw;
    font-family: 'Arial', sans-serif;
    background-color: #f7f7f7;
    padding-top: 20px;
}
.nav-bar {
    padding-top: 20px 0;
    margin-bottom: 50px;
}
.container {
    display: flex;
    flex-direction: column;
    align-items: center;
    max-width: 100%;
    text-align: center;
}

.nav-bar li {
    list-style: none;
    margin-right: 50px;
    display: inline-block;
}
.nav-bar a {
   
    text-decoration: none;
    color: #111010;
}

.content-wrapper {
    display: flex;
    width: 80%;
    max-width: 1200px;
    justify-content: space-between;
    flex-wrap: wrap;
    margin-bottom: 30px;
}

.text-section {
    width: 50%;
    display: flex;
    flex-direction: column;
    gap: 20px;
}

h1 {
    font-size: 3.5em;
    color: #111010;
    margin-bottom: 20px;
}

p {
    color: #302e2e;
    line-height: 1.5;
    font-size: 1.2em;
}

.buttons {
    display: flex;
    gap: 20px;
}

button {
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 1em;
    transition: background-color 0.3s;
}

.sign-in-btn {
    background-color: #007bff;
    color: white;
}

.apply-btn {
    background-color: #ccc;
    color: #333;
}

button:hover {
    opacity: 0.8;
}

.image-section img {
    width: 30rem;
    height: 30rem;
    /* height: auto; */
    display: block;
}


@media (max-width: 768px) {

    .text-section,
    .image-section {
        width: 100%;
        text-align: center;
        /* display: none; */
    }

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
.header h2 {
    max-width: 100%;
    box-sizing: border-box;
    padding: 5px;
    margin-bottom: 20px;
    text-align: center;
    font-size: 40px;
}
.image-container {
    display: flex;
    justify-content: space-between; 
    align-items: flex-start;
    flex-wrap: wrap; 
    margin-top: 5px; 
}

.image-wrapper {
    text-align: center;
    width: 30%;
    height: 30%; 
    margin-bottom: 20px;
}

.image-wrapper img {
    width: 12rem; 
    height: 8rem;
}

.image-wrapper p {
    margin: 5px; 
    font-size: 14px; 
}
.header h3 {
    max-width: 100%;
    box-sizing: border-box;
    padding: 5px;
    margin-top: 20px;
    margin-bottom: 20px;
    text-align: center;
    font-size: 40px;
}
#timeline {
    display: flex;
  }
  
  #timeline:hover .tl-item {
    width: 23.3333%;
  }
  
  .tl-item {
    transform: translate3d(0, 0, 0);
    position: relative;
    width: 25%;
    height: 100vh;
    min-height: 600px;
    color: #f9f9fa;
    overflow: hidden;
    transition: width 0.5s ease;
  }
  
  .tl-item:before,
  .tl-item:after {
    transform: translate3d(0, 0, 0);
    content: '';
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
  }
  
  .tl-item:after {
    background: rgb(254, 255, 255);
    opacity: 1;
    transition: opacity 0.5s ease;
  }
  
  .tl-item:before {
    background: linear-gradient(to bottom, rgba(125, 187, 238, 0) 0%, rgb(18, 96, 141) 75%);
    z-index: 1;
    opacity: 0;
    transform: translate3d(0, 0, 0) translateY(50%);
    transition: opacity 0.5s ease, transform 0.5s ease;
  }
  
  .tl-item:hover {
    width: 30% !important;
  }
  
  .tl-item:hover:after {
    opacity: 0;
  }
  
  .tl-item:hover:before {
    opacity: 1;
    transform: translate3d(0, 0, 0) translateY(0);
    transition: opacity 1s ease, transform 1s ease 0.25s;
  }
  
  .tl-item:hover .tl-content {
    opacity: 1;
    transform: translateY(0);
    transition: all 0.75s ease 0.5s;
  }
  
  .tl-item:hover .tl-bg {
    filter: grayscale(0);
  }
  
  .tl-content {
    transform: translate3d(0, 0, 0) translateY(25px);
    position: relative;
    z-index: 1;
    text-align: center;
    margin: 0 1.618em;
    top: 55%;
    opacity: 0;
  }
  
  .tl-content h1 {
    font: sans-serif;
    text-transform: uppercase;
    color: #050505;
    font-size: 1.44rem;
    font-weight: normal;
  }
  .tl-content p {
    font: sans-serif;
    color: #f9f9fa;
    font-size: 1.10rem;
    font-weight: normal;
  }
  
  .tl-steps {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translateX(-50%) translateY(-50%);
    z-index: 1;
  }
  
  .tl-steps p {
    font: sans-serif;
    font-size: 1.728rem;
    line-height: 0;
    color: #050505;
  }
  
  .tl-bg {
    transform: translate3d(0, 0, 0);
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    background-size: cover;
    background-position: center center;
    transition: filter 0.5s ease;
    filter: grayscale(100%);
  }
  .testimonial-container{
    margin-top: 20px;
    height: auto;
    display: flex;
    justify-content: space-between; 
    align-items: flex-start;
    flex-wrap: wrap; 
  }
  .testimonial-card{
    height: 300px;
    width: 200px;
    background-color: white;
    border-radius: 10px;
    box-shadow: 0 8px 30px rgba(0,0,0,.3);
    display: flex;
    flex-flow: column;
    justify-content:space-between;
    margin-right: 35px;
  }
  .text{
    padding: 2.2em;
    line-height: 1.7em;
    position: relative;
    font-size: 12px;
  }
  .image{
    height: 75px;
    width: 75px;
    border-radius: 50%;
    border: 5px solid white;
    position: absolute;
    top: -50%;
    left: 50%;
    transform: translateX(-50%);
    display:flex;
    justify-content:center;
    overflow: hidden;
  }
  .image img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    border-radius: 50%;
  }
  .footer{
    background: linear-gradient(90deg, rgba(125, 187, 238, 0) 0%, rgb(18, 96, 141) 75%);
    height: 90px;
    border-radius: 0 0 10px 10px;
    position:relative; 
  }
  .quote{
    font-size: 180%;
    float: right;
    opacity: .1;
    transform: rotate(10deg) translate(-10px, -40px);
    color:#4D3FA3;
  }
  .person{
    color:white;
    position: absolute;
    top: 60%;
    left: 50%;
    transform: translate(-50%, -100%);
    font-size: 0.7rem;
    font-weight: 600;
    text-align: center;
  }
</style>
  