
<template>
    <div>
    <img src="proj_logo.png" />
    </div>
<!-- 
<div class="Influencer Portal">

    <h1 class="text-center">Influencer Portal</h1>

    <button v-on:click="Request voucher" >Request Voucher</button>

    <button v-on:click="View Voucher" >View Voucher</button>

    <router-link to="/Influencer request voucher">
        <button class="x-button">Request Voucher</button>
      </router-link>
           
    </div> -->
</template>

<script>
export default {
    name: "Influencer Portal"
}
</script>
<style scoped>

img{
            position: absolute;
            top: 0;
            left: 50%;
            transform: translateX(-50%);
            width: 400px;
            height: auto;

}
  .InfluencerPortal {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    text-align: center;
    height: 100vh;
    padding: 2em;
    box-sizing: border-box;
    font-family: Arial, sans-serif;
    color: #333;
  }
  
  .InfluencerPortal h1 {
    font-size: 2.5em;
    margin-bottom: 25px;
  }
  
  
  .InfluencerPortal button {
    background-color: #3498db;
    color: white;
    border: none;
    border-radius: 5px;
    padding: 1em 2em;
    font-size: 1em;
    text-decoration: none;
    cursor: pointer;
    transition: background-color 0.3s ease;
    margin-top: 1rem;
  }
  .x-button{
    background-color: #3498db;
    color: white;
    border: none;
    border-radius: 5px;
    padding: 1em 2em;
    font-size: 1em;
    text-decoration: none;
    cursor: pointer;
    transition: background-color 0.3s ease;
    margin-top: 1rem;
  }
  

  </style>