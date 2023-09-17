<template>
    <div>
    <img src="proj_logo.png" />
    </div>

<div class="register">

    <h1 class="text-center">Registration</h1>

    <input type="text" v-model="username" placeholder="Username" />
    <input type="password" v-model="password" placeholder="Password" />
    <button v-on:click="login" >Register</button>
           
    </div>
</template>

<script>
export default {
    name: "Register"
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
  .register {
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
  
  .register h1 {
    font-size: 2.5em;
    margin-bottom: 25px;
  }
  
  
  .register button {
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