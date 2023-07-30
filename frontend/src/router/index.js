import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import Login from '../views/Login.vue';
import Register from '../views/Register.vue';
import InfluencerPortal from '../views/InfluencerPortal.vue';
import SupplierPortal from '../views/SupplierPortal.vue';
import InfluencerPortal from '../views/InfluencerPortal.vue';
import Influencer_Request_Voucher from '../views/Influencer_Request_Voucher.vue';
import View_Vouchers from '../views/View_Vouchers.vue';
import SupplierVoucherMetamask from '../views/SupplierVoucherMetamask.vue';
import Popups from '../views/Popups.vue';
import Voucher_Popup from '../views/Voucher_Popup.vue'


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
  },

  
  {
    path: '/view_vouchers',
    name: 'view_vouchers',
    component: View_Vouchers
  },
  {
    path: '/request_voucher',
    name: 'request_voucher',
    component: Influencer_Request_Voucher
  },

  {
    path: '/supplier_vmm',
    name: 'supplier_vmm',
    component: SupplierVoucherMetamask
  },

  {
    path: '/voucher_pop',
    name: 'voucher_pop',
    component: Voucher_Popup
  },

  {
    path: '/popups',
    name: 'popups',
    component: Popups
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
});

export default router;
