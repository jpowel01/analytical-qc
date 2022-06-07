import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import vuetify from "./plugins/vuetify";

Vue.config.productionTip = false;

export const SERVER_URL =
  "http://localhost:8080/api";
export const DASHBOARD_DETAILS_URL =
  "https://comptox.epa.gov/dashboard/chemical/details/";
export const DASHBOARD_IMAGE_URL =
  "https://comptox.epa.gov/dashboard-api/ccdapp1/chemical-files/image/by-dtxsid";
export const PUBCHEM_CID_URL =
  "https://pubchem.ncbi.nlm.nih.gov/compound";
export const PUBCHEM_SID_URL =
  "https://pubchem.ncbi.nlm.nih.gov/substance";

new Vue({
  router,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
