<template>
    <div>
      <h1>Review Request</h1>
      <p>Request ID: {{ requestId }}</p>
      <p>Request Details: {{ requestDetails }}</p>

      <router-link to="/acceptVoucher">
        <button class="accept-button">Accept</button>
      </router-link>

      <router-link to="/denyVoucher">
        <button class="deny-button">Deny</button>
      </router-link>
      
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        requestId: null,
        requestDetails: "Sample request details...", // Replace with actual request details
      };
    },
    created() {
      // Get the request ID from the route params
      this.requestId = this.$route.params.id;
    },
    methods: {
      acceptRequest() {
        // Handle accepting the request, e.g., update the request status in the backend
        // You can also show a confirmation message to the user after the action
        console.log("Accepted request:", this.requestId);
      },
      denyRequest() {
        // Handle denying the request, e.g., update the request status in the backend
        // You can also show a confirmation message to the user after the action
        console.log("Denied request:", this.requestId);
      },
    },
  };
  </script>
  
  <style scoped>
  .reviewRequest {
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

  .reviewRequest h1 {
    font-size: 2.5em;
    margin-bottom: 1em;
  }

  .accept-button {
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
  .deny-button{
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

   .cta-button {
    background-color: #3498db;
    color: white;
    border: none;
    border-radius: 5px;
    padding: 1em 2em;
    font-size: 1em;
    text-decoration: none;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  .cta-button:hover {
    background-color: #2980b9;
  }
  </style>
  