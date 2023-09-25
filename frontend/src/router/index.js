import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/landing.vue";
import Login from "../views/login.vue";
import Application from "../views/application.vue";
import Faq from "../views/faq.vue";
// Brand Routes

// import brandDash from '../views/brand/brand_dash2.vue';
import brand_register from "../views/brand/brand_register.vue";
// import brand_viewvoucher from "../views/brand/brand_viewvoucher.vue";
// Supplier Routes
import supplierDashboard from "../views/supplier/supplier_dashboard.vue";
import supplier_register from "../views/supplier/supplier_register.vue";
import supplier_redeem from "../views/supplier/supplier_redeem.vue";
import supplier_metrics from "../views/supplier/supplier_metrics.vue";
// Influencer Routes
import influencerDashboard from "../views/influencer/influencer_dashboard.vue";
import requestVoucher from "../views/influencer/request_voucher.vue";
import influencerRequests from "../views/influencer/influencer_requests.vue";
import influencer_register from "../views/influencer/influencer_register.vue";

// inf dashboard
import influencerDash from "../views/influencer/influencer_dash2.vue";
//sup dash test
import brandT from "../views/brand/brand_dash_test.vue";
import supplierT from "../views/supplier/supplier_dashboard.vue";

const routes = [
  {
    path: "/",
    name: "home",
    component: Home,
  },

  {
    path: "/login",
    name: "login",
    component: Login,
  },
  {
    path: "/application",
    name: "application",
    component: Application,
  },
  {
    path: "/faq",
    name: "faq",
    component: Faq,
  },
  {
    path: "/supplier/supplier_register",
    name: "supplier_register",
    component: supplier_register,
  },
  {
    path: "/supplier/supplier_metrics",
    name: "supplier_metrics",
    component: supplier_metrics,
  },
  // {
  //   path: '/supplier/supplier_viewvoucher',
  //   name: 'supplier_viewvoucher',
  //   component: supplier_viewvoucher
  // },
  {
    path: "/supplier/supplier_redeem",
    name: "supplier_redeem",
    component: supplier_redeem,
  },
  {
    path: "/brand/brand_register",
    name: "brand_register",
    component: brand_register,
  },
  {
    path: "/influencer/influencer_register",
    name: "influencer_register",
    component: influencer_register,
  },
  {
    path: "/influencer/dashboard",
    name: "influencerDashboard",
    component: influencerDashboard,
  },
  {
    path: "/influencer/request-voucher",
    name: "requestVoucher",
    component: requestVoucher,
  },
  {
    path: "/influencer/requests",
    name: "influencerRequests",
    component: influencerRequests,
  },

  // testing ⬇

  {
    path: "/brandT",
    name: "brandT", //
    component: brandT,
    children: [
      {
        path: "",
        name: "viewVoucherRequests",
        component: () => import("../views/brand/brand_requests.vue"), 
      },
      // { path: "/yellow", component: () => import("../views/pages/Yellow.vue") },
      // { path: "/blue", component: () => import("../views/pages/Blue.vue") },
      // { path: "/red", component: () => import("../views/pages/Yellow.vue") },
    ],
  },
  {
    path: "/supplierT",
    name: "supplierT", 
    component: supplierT,
    children: [
      {
        path: "",
        name: "viewSupplierRedeem",
        component: () => import("../views/supplier/supplier_redeem.vue"), 
      },
      
    ],
  },

  {
    path: "/influencerDash",
    name: "influencerDash",
    component: influencerDash,
    children: [
      {
        path: "",
        name: "requestVouchers",
        component: () => import("../views/influencer/influencer_requests.vue"),
      },
      { path: "/yellow", component: () => import("../views/influencer/influencer_vouchers.vue") },
      { path: "/blue", component: () => import("../views/pages/Blue.vue") },
      { path: "/red", component: () => import("../views/pages/Red.vue") },
    ],
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;
