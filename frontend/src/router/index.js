import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import Login from '../views/Login.vue';
import Register from '../views/Register.vue';
import InfluencerPortal from '../views/InfluencerPortal.vue';
import SupplierPortal from '../views/SupplierPortal.vue';
import SupplierVoucherMetamask from '../views/SupplierVoucherMetamask.vue'


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
    path: '/SupplierPortal',
    name: 'SupplierPortal',
    component: SupplierPortal
  },
  {
    path: '/SupplierVoucherMetamask',
    name: 'SupplierVoucherMetamask',
    component: SupplierVoucherMetamask
  }
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
});

export default router;
