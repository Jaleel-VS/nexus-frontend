
<template>
  <div>
    <img src="proj_logo.png" />
  </div>

  <div class="InfluencerPortal">
    <h1 class="text-center">Influencer Portal</h1>
    <h2>Welcome, {{ userDetails.first_name }} {{ userDetails.last_name }}</h2>

    <button class="cta-button" @click="requestVoucher">Request Voucher</button>
    <button class="cta-button" @click="viewVouchers">View Vouchers</button>
    <button class="logout-button" @click="logout">Log out</button>
  </div>
</template>
  
<script>
import { useUserStore } from '@/store/user'
import { useRouter } from 'vue-router'

export default {
  name: "InfluencerPortal",
  setup() {
    const userStore = useUserStore()
    const router = useRouter()

    const requestVoucher = () => {
      router.push('/voucherRequest')
    }

    const viewVouchers = () => {
      router.push('/view_voucher')
    }

    const logout = () => {
      // you should also clear user data from the store
      // userStore.clearUserData() // Uncomment this line once you implement the clearUserData action in your store
      router.push('/')
    }

    return { userDetails: userStore.details, requestVoucher, viewVouchers, logout }
  }
}
</script>


<style scoped>
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
  margin-bottom: 1em;
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
  margin-top: 20px;
}

.cta-button:hover {
  background-color: #2980b9;
}

.logout-button {
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 1em 2em;
  font-size: 1em;
  text-decoration: none;
  cursor: pointer;
  transition: background-color 0.3s ease;
  position: absolute;
  top: 10px;
  right: 10px;
}

.logout-button:hover {
  background-color: #2980b9;
}


</style>