<template>
    <div>
    <img src="proj_logo.png" />
    </div>

<div class="Voucher verification">

    <h1 class="text-center">Verification</h1>

    <input type="text" v-model="VID" placeholder="Enter Vouche ID" />
   
    
    <button v-on:click="submit" >Submit</button>

    </div>
</template>

<script>
export default {
    name: "invalid voucher or deliver goods"
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
  .voucherverif {
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
  
  .voucherverif h1 {
    font-size: 2.5em;
    margin-bottom: 25px;
  }
  
  
  .voucherverif button {
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
  .t-button{
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