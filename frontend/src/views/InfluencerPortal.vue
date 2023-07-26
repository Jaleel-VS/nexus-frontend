
<template>
  <div>
    <img src="proj_logo.png" />
  </div>

  <div class="InfluencerPortal">
    <h1 class="text-center">Influencer Portal</h1>

    <div class="button-container">
      <button v-on:click="Request_Voucher">Request Voucher</button>
      <button v-on:click="View_Voucher">View Voucher</button>
    </div>

  </div>
</template>

<script>
export default {
  name: "InfluencerPortal"
}
</script>

<style scoped>
/* Your existing styles */

img {
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

.button-container {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 20px; /* Adjust the top margin as desired */
}

.button-container button {
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 1em 2em;
  font-size: 1em;
  text-decoration: none;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin: 0 5px; /* Add margin between the buttons to create space */
}
</style>