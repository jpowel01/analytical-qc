import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import SubstanceList from "../views/SubstanceList.vue";
import Substance from "../views/Substance.vue";

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
    component: SubstanceList,
  },
  {
    path: "/manage", 
    name: "manage",
    component: () => import("../views/Manage.vue"),
  },
  {
    path: "/substances/lists/:id", 
    name: "substance-list",
    component: SubstanceList,
  },
  
  {
    path: "/substances/:type/:query",
    name: "substance",
    component: Substance,
  },
  {
    path: "/error",
    name: "error",
    component: () => import("../views/Error.vue"),
  },
  {
    path: "/batch-search",
    name: "batch-search",
    component: () => import("../views/BatchSearch.vue")
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
