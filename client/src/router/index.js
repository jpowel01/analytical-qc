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
    name: "substances",
    component: () => import("../views/SubstanceList.vue"),
  },
  {
    path: "/substances/:type/:query",
    name: "substance-detail",
    component: () => import("../views/Substance.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
