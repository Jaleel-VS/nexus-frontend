import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/landing.vue';
import Login from '../views/login.vue';
import Application from "../views/application.vue";
// Brand Routes
import brandDashboard from '../views/brand/brand_dashboard.vue';
import brandRequests from '../views/brand/brand_requests.vue';
import brandCreateVoucher from '../views/brand/brand_create_voucher.vue';
// import brandDash from '../views/brand/brand_dash2.vue';
import brand_register from '../views/brand/brand_register.vue';
import brand_viewvoucher from '../views/brand/brand_viewvoucher.vue';
// Supplier Routes
import supplierDashboard from '../views/supplier/supplier_dashboard.vue';
import supplier_register from '../views/supplier/supplier_register.vue';
import supplier_redeem from '../views/supplier/supplier_redeem.vue';
import supplier_metrics from '../views/supplier/supplier_metrics.vue';
// Influencer Routes
import influencerDashboard from '../views/influencer/influencer_dashboard.vue';
import requestVoucher from '../views/influencer/request_voucher.vue';
import influencerRequests from '../views/influencer/influencer_requests.vue';
import influencer_register from '../views/influencer/influencer_register.vue';

// inf dashboard
import influencerDash from '../views/influencer/influencer_dash2.vue';
//brand dash test
import popUp from '../views/pages/popup.vue'


const routes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },

  {
   path: '/popUp',
  name: 'popUp',
  component: popUp
  }, 

  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/application',
    name: 'application',
    component: Application
  },
  {
    path: '/brand/dashboard',
    name: 'brandDashboard',
    component: brandDashboard
  },
  
  {
    path: '/supplier/supplier_register',
    name: 'supplier_register',
    component: supplier_register
  },
  {
    path: '/supplier/supplier_metrics',
    name: 'supplier_metrics',
    component: supplier_metrics
  },
  // {
  //   path: '/supplier/supplier_viewvoucher',
  //   name: 'supplier_viewvoucher',
  //   component: supplier_viewvoucher
  // },
  {
    path: '/supplier/supplier_redeem',
    name: 'supplier_redeem',
    component: supplier_redeem
  },
  {
    path: '/brand/brand_register',
    name: 'brand_register',
    component: brand_register
  },
  {
    path: '/brand/brand_viewvoucher',
    name: 'brand_viewvoucher',
    component: brand_viewvoucher
  },
  {
    path: '/influencer/influencer_register',
    name: 'influencer_register',
    component: influencer_register
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
  {
    path: '/brand/create-voucher',
    name: 'brandCreateVoucher',
    component: brandCreateVoucher
  },

// testing ⬇
  
  // {
  // path: '/brandT',
  // name: 'brandT',
  // component: brandT,
  // children: [
  // { path: '', name: 'dhome', component: () => import('../views/pages/Red.vue') },
  // { path: '/yellow', component: () => import('../views/pages/Yellow.vue') },
  // { path: '/blue', component: () => import('../views/pages/Blue.vue') },
  // { path: '/red', component: () => import('../views/pages/Yellow.vue')}
  // ],
  // },

  {
    path: '/influencerDash',
    name: 'influencerDash',
    component: influencerDash,
    children: [
      { path: '', name: 'requestVouchers', component: () => import('../views/influencer/influencer_requests.vue')},
      { path: '/yellow', component: () => import('../views/pages/Yellow.vue') },
      { path: '/blue', component: () => import('../views/pages/Blue.vue') },
      { path: '/red', component: () => import('../views/pages/Red.vue')}
    ],
  },
  // {
  //   path: '/brandDash',
  //   name: 'brandDash',
  //   component: brandDash,
  //   children: [
  //     { path: '', name: 'brand_requests', component: () => import('../views/brand/brand_requests2.vue')},
  //     { path: '/yellow', component: () => import('../views/pages/Yellow.vue') },
  //     { path: '/blue', component: () => import('../views/pages/Blue.vue') },
  //     { path: '/red', component: () => import('../views/pages/Red.vue')}
  //   ],
  // },

  
  // {
  //   path: '/supplier/supplier_dashboard',
  //   name: 'supplier_dashboard',
  //   component: supplier_dashboard,
  //   children: [
  //     { path: '', name: 'Redeem Voucher', component: () => import('../views/supplier/supplier_redeem.vue')},
  //     { path: '', name: 'Performance metrics', component: () => import('../views/supplier/supplier_metrics.vue')},
  //     { path: '', name: 'Change Password', component: () => import('../views/change_password.vue') },
  //     { path: '', name: 'Contact', component: () => import('../views/contact.vue')},
  //     { path: '', name: 'View Vouchers', component: () => import('../views/supplier/supplier_viewvoucher.vue')}
  //   ],
  // },



];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
});

export default router;