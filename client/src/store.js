import Vue from "vue";
import ListDataService from "./services/ListDataService";

export const SERVER_URL = process.env.VUE_APP_SERVER_URL;
export const CONTENT_SERVER_URL = process.env.VUE_APP_CONTENT_SERVER_URL;
export const DASHBOARD_DETAILS_URL =
  "https://comptox.epa.gov/dashboard/chemical/details";
export const DASHBOARD_IMAGE_URL =
  "https://comptox.epa.gov/dashboard-api/ccdapp1/chemical-files/image/by-dtxsid";
export const PUBCHEM_CID_URL = "https://pubchem.ncbi.nlm.nih.gov/compound";
export const PUBCHEM_SID_URL = "https://pubchem.ncbi.nlm.nih.gov/substance";

const listStore = Vue.observable({
  listDetail: {
    list: {
      id: null,
      name: "",
      description: "",
    },
    substanceIds: [],
  },
  currentIndex: -1,
});

export default {
  listSelected() {
    if (listStore.listDetail && listStore.listDetail.list && listStore.listDetail.list.id) {
      return true;
    } else {
      return false;
    }
  },

  listSubstanceIds() {
    return listStore.listDetail.substanceIds;
  },

  next() {
    if (listStore.currentIndex == listStore.listDetail.substanceIds.length - 1) {
      listStore.currentIndex = 0;
    } else {
      listStore.currentIndex = listStore.currentIndex + 1;
    }
    return listStore.listDetail.substanceIds[listStore.currentIndex];
  },

  previous() {
    if (listStore.currentIndex == 0) {
      listStore.currentIndex = listStore.listDetail.substanceIds.length - 1;
    } else {
      listStore.currentIndex = listStore.currentIndex - 1;
    }
    return listStore.listDetail.substanceIds[listStore.currentIndex];
  },

  async reset(listId) {
    return ListDataService.getDetail(listId)
      .then((response) => {
        listStore.listDetail = response.data;
        listStore.currentIndex = 0;
      })
      .catch(() => {
        this.clear();
      })
  },

  clear() {
    listStore.listDetail = null;
  },
}