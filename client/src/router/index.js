import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: Home,
  },
  {
    path: "/substances",
    name: "substance-list",
    component: () => import("../views/SubstanceList.vue"),
  },
  {
    path: "/substances/:type/:query",
    name: "substance",
    component: () => import("../views/Substance.vue"),
  },
  {
    path: "/error",
    name: "error",
    component: () => import("../views/Error.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
