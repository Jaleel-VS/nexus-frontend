import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/landing.vue';
import Login from '../views/login.vue';
// Brand Routes
import brandDashboard from '../views/brand/brand_dashboard.vue';
import brandRequests from '../views/brand/brand_requests.vue';

// Supplier Routes
import supplierDashboard from '../views/supplier/supplier_dashboard.vue';
// Influencer Routes
import influencerDashboard from '../views/influencer/influencer_dashboard.vue';
import requestVoucher from '../views/influencer/request_voucher.vue';
import influencerRequests from '../views/influencer/influencer_requests.vue';

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
    path: '/brand/dashboard',
    name: 'brandDashboard',
    component: brandDashboard
  }
  ,
  {
    path: '/supplier/dashboard',
    name: 'supplierDashboard',
    component: supplierDashboard
  },
  {
    path: '/influencer/dashboard',
    name: 'influencerDashboard',
    component: influencerDashboard
  },
  {
    path: '/influencer/request-voucher',
    name: 'requestVoucher',
    component: requestVoucher
  },
  {
    path: '/influencer/requests',
    name: 'influencerRequests',
    component: influencerRequests
  },
  {
    path: '/brand/requests',
    name: 'brandRequests',
    component: brandRequests
  },



];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
});

export default router;
