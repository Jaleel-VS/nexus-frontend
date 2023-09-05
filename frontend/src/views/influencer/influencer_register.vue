<template>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Link to your CSS file for styling -->

    <div class="registration-form">
        <h1>Register as an Influencer</h1>
        <form id="influencer-form" action="register.php" method="post"> <!-- Use your preferred server-side language for processing form data -->
            <label for="social-media-handle">Social Media Handle:</label>
            <input type="text" id="social-media-handle" name="social_media_handle" required>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
            <label for="surname">Surname:</label>
            <input type="text" id="surname" name="surname" required>
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <div class="button-container">
                <button id="connect-metamask" onclick="connectMetaMask()">Connect with MetaMask</button>
                <button type="submit">Register</button>
            </div>
        </form>
    </div>
</template>

<script>
function connectMetaMask() {
    if (typeof window.ethereum !== 'undefined') {
        // MetaMask is available
        ethereum
            .request({ method: 'eth_requestAccounts' })
            .then((accounts) => {
                const userAddress = accounts[0];
                // You can now use the user's Ethereum address in your registration process
                alert('Connected to MetaMask. Ethereum Address: ' + userAddress);
            })
            .catch((error) => {
                console.error(error);
                alert('MetaMask connection failed. Please check your MetaMask setup.');
            });
    } else {
        // MetaMask is not available
        alert('MetaMask is not installed. Please install MetaMask to continue.');
    }
}
</script>


<style scoped>
.registration-form {
    width: 300px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    text-align: center;
}

label {
    display: block;
    margin-bottom: 5px;
    text-align: left;
}

input[type="text"],
input[type="password"] {
    width: 100%;
    padding: 8px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 3px;
}

button {
    width: 100%;
    padding: 10px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 3px;
    cursor: pointer;
}

button:hover {
    background-color: #0056b3;
}

.button-container button {
  margin-bottom: 15px; 
}


</style>