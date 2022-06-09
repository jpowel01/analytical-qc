import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../components/Home.vue";

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
    component: () => import("../components/SubstanceList.vue"),
  },
  {
    path: "/substances/:id",
    name: "substance-details",
    component: () => import("../components/Substance.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
