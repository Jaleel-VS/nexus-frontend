import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import Login from '../views/Login.vue';
import Register from '../views/Register.vue';
import InfluencerPortal from '../views/InfluencerPortal.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/register',
    name: 'register',
    component: Register
  },

  {
    path: '/influencer_portal',
    name: 'influencer_portal',
    component: InfluencerPortal
  },

  {
    path: '/influencer_request_voucher',
    name: 'influencer_request_voucher',
    component: Influencer_Request_Voucher
  },
  {
    path: '/SupplierPortal',
    name: 'SupplierPortal',
    component: SupplierPortal
  },

];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
});

export default router;
