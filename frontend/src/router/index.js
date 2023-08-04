import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import Login from '../views/Login.vue';
import Register from '../views/Register.vue';
import InfluencerPortal from '../views/InfluencerPortal.vue';
import SupplierPortal from '../views/SupplierPortal.vue';
<<<<<<< HEAD
import Influencer_Request_Voucher from '../views/Influencer_Request_Voucher.vue';
import View_Vouchers from '../views/View_Vouchers.vue';
import SupplierVoucherMetamask from '../views/SupplierVoucherMetamask.vue'
=======
// import Influencer_Request_Voucher from '../views/Influencer_Request_Voucher.vue';
import View_Vouchers from '../views/View_Vouchers.vue';
import SupplierVoucherMetamask from '../views/SupplierVoucherMetamask.vue';
import Popups from '../views/Popups.vue';
import Voucher_Popup from '../views/Voucher_Popup.vue';
import Brand_Dashboard from '../views/brandDashboard.vue';
// Influencer routes
import voucherRequest from '../views/influencer_views/request_voucher.vue'
>>>>>>> 971458d9684e910812c8df7157fd67ac21f29733

// Brand routes
import openRequests from '../views/brand_views/requestsList.vue'

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
<<<<<<< HEAD
  },

  {
    path: '/influencer_request_voucher',
    name: 'influencer_request_voucher',
    component: Influencer_Request_Voucher
=======
>>>>>>> 971458d9684e910812c8df7157fd67ac21f29733
  },

  
  {
    path: '/view_vouchers',
    name: 'view_vouchers',
    component: View_Vouchers
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

  {
    path: '/brand_home',
    name: 'brand_home',
    component: Brand_Dashboard
  },
  {
    path: '/voucherRequest',
    name: 'voucherRequest',
    component: voucherRequest
  },

  {
    path: '/openRequests',
    name: 'openRequests',
    component: openRequests
  },


];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
});

export default router;
