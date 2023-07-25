<template>
    <div>
    <img src="proj_logo.png" />
    </div>

<div class="Influencer request voucher">

    <h1 class="text-center">Voucher Details</h1>

    <input type="text" v-model="Name" placeholder="Name" />
    <input type="text" v-model="Username" placeholder="Username" />
    <input type="text" v-model="Brand" placeholder="Brand" />
    <input type="text" v-model="SocialMed" placeholder="Social Media Handle" />
    <input type="text" v-model="Quantity" placeholder="Quantity" />
    
    <button v-on:click="submit" >Submit</button>

    </div>
</template>

<script>
export default {
    name: "Go to brand"
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
  .Inflreq {
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
  
  .Inflreq h1 {
    font-size: 2.5em;
    margin-bottom: 25px;
  }
  
  
  .Inflreq button {
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
  .y-button{
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