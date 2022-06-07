<template>
  <v-container>
    <v-row>
      <v-col>
        <v-data-table
          :headers="headers"
          :items="substances"
          :loading="state.loading"
        >
            <template v-slot:header.structure />
            <template v-slot:item.structure="{ item }">
                <v-img :src="`${DASHBOARD_IMAGE_URL}/${item.dtxsid}`" max-height="150" max-width="150" />
            </template>
            <template v-slot:item.dtxsid="{ item }">
                <a :href="`${DASHBOARD_DETAILS_URL}/${item.dtxsid}`">{{ item.dtxsid }}</a>
            </template>
            <template v-slot:item.pubchemCid="{ item }">
                <a :href="`${PUBCHEM_CID_URL}/${item.pubchemCid}`">{{ item.pubchemCid }}</a>
            </template>
        </v-data-table>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import SubstanceDataService from "../services/SubstanceDataService";
import {DASHBOARD_DETAILS_URL, DASHBOARD_IMAGE_URL, PUBCHEM_CID_URL} from "@/main";

export default {
  name: "substances",

  data() {
    return {
      substances: [],
      headers: [
        { text: "Structure", value: "structure", sortable: false, width: "1%" },
        { text: "DTXSID", value: "dtxsid", sortable: false },
        { text: "Preferred Name", value: "preferredName", sortable: false },
        { text: "CASRN", value: "casrn", sortable: false },
        { text: "Molecular Formula", value: "molFormula", sortable: false },
        { text: "Molecular Weight", value: "molWeight", sortable: false },
        { text: "PubChem CID", value: "pubchemCid", sortable: false },
      ],
      state: {
        loading: false,
      },
    };
  },

  computed: {
    DASHBOARD_DETAILS_URL() {
      return DASHBOARD_DETAILS_URL;
    },
    DASHBOARD_IMAGE_URL() {
      return DASHBOARD_IMAGE_URL;
    },
    PUBCHEM_CID_URL() {
      return PUBCHEM_CID_URL;
    },
  },

  methods: {
    retrieveSubstances() {
      this.state.loading = true;
      SubstanceDataService.getAll()
        .then((response) => {
          this.substances = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
      this.state.loading = false;
    },

    refreshList() {
      this.retrieveSubstances();
    },
  },

  mounted() {
    this.retrieveSubstances();
  },
};
</script>
