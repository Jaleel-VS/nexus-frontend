<template>
    <div>
        <Navbar :username="username" />
        <!-- Other content -->
        <h1>👋 Welcome!</h1>

<p>This is the influencer portal, from here you can make request voucher, view pending requests, and redeem vouchers issued to you!.</p>

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

        return {
            username
        }
    },

};
</script>