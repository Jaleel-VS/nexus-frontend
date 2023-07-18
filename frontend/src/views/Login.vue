<template>
<img class="logo" src="../assets/logo.svg" />
<h1> Login page </h1>
<div class="login">

    <input type="text" v-model="username" placeholder="Username" />
    <input type="password" v-model="password" placeholder="Password" />
    <button v-on:click="login" >Login</button>
           
    </div>
</template>

<script>
export default {
    name: "Login"
}
</script>