<template>
    <div>
        <Navbar :username="username" />
        <!-- Other content -->
        <h1>👋 Welcome!</h1>

        <p>This is the brand portal, from here you can view and manage voucher requests, and mint new vouchers on the
            blockchain</p>

        <div class="buttons">
            <button @click="handleViewRequests">Manage Voucher Requests</button>
        </div>

    </div>
</template>
  
<script>
import Navbar from '../components/navbar.vue';
// vue3-router
import { useRouter } from 'vue-router'
// pinia
import { useUserStore } from '@/store/user'

export default {
    components: {
        Navbar
    },

    setup() {
        const router = useRouter()
        const userStore = useUserStore()

        const username = userStore.details.username

        const handleViewRequests = () => {
            router.push('/brand/requests');
        }

        return {
            username, handleViewRequests
        }
    },

};
</script>

<style scoped>

h1 {
  font-family: 'Poppins', sans-serif;
  font-size: 2em;
  color: #111010;
  margin: 0;
  padding: 20px;
  text-align: center;
}

p {
  font-family: 'Poppins', sans-serif;
  font-size: 1.2em;
  color: #111010;
  margin: 0;
  padding: 20px;
  text-align: center;
}

.buttons {
  display: flex;
  gap: 20px;
  justify-content: center;
  margin-top: 40px;
}

button {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1em;
  transition: background-color 0.3s;
  background-color: #007bff;
  color: white;
}

button:hover {
  background-color: #0069d9;
}

</style>