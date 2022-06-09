<template>
  <v-container fluid fill-height>
    <v-row>
      <v-col>
        <div class="ma-2">
          <v-text-field
            v-model="state.search"
            append-icon="mdi-magnify"
            label="Search"
            single-line
            hide-details
          />
        </div>
        <v-data-table
          :headers="headers"
          :items="substances"
          :search="state.search">
          <template v-slot:item.detail="{ item }">
            <v-btn
              :to="'/substances/' + item.id"
              class="ma-2"
              dark
              small
              fab
              color="primary"
            >
              <v-icon>
                mdi-eye
              </v-icon>
            </v-btn>
          </template>
          <template v-slot:item.structure="{ item }">
            <v-img 
              max-height="150" max-width="150"
              :src="`${DASHBOARD_IMAGE_URL}/${item.dtxsid}`" />
          </template>
          <template v-slot:item.dtxsid="{ item }">
            <a 
              target="_blank"
              rel="noreferrer noopener"
              :href="`${DASHBOARD_DETAILS_URL}/${item.dtxsid}`"
            >{{ item.dtxsid }}</a><v-icon x-small class="ml-1">mdi-open-in-new</v-icon>
          </template>
          <template v-slot:item.pubchemCid="{ item }">
            <a 
              target="_blank"
              rel="noreferrer noopener"
              :href="`${PUBCHEM_CID_URL}/${item.pubchemCid}`"
            >{{ item.pubchemCid }}</a><v-icon x-small class="ml-1">mdi-open-in-new</v-icon>
          </template>
        </v-data-table>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import SubstanceDataService from "../services/SubstanceDataService";
import {
  DASHBOARD_DETAILS_URL,
  DASHBOARD_IMAGE_URL,
  PUBCHEM_CID_URL,
} from "@/main";

export default {
  name: "substances",

  data() {
    return {
      substances: [],
      headers: [
        { text: "", value: "detail", sortable: false, width: "1%" },
        { text: "", value: "structure", sortable: false, width: "1%" },
        { text: "DTXSID", value: "dtxsid", sortable: true, },
        { text: "Preferred Name", value: "preferredName", sortable: true, },
        { text: "CASRN", value: "casrn", sortable: true },
        { text: "Mol. Formula", value: "molFormula", sortable: true, },
        { text: "Mol. Weight", value: "molWeight", sortable: true, },
        { text: "PubChem CID", value: "pubchemCid", sortable: true, },
      ],

      state: {
        loading: false,
        search: "",
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
  },

  mounted() {
    this.retrieveSubstances();
  }
};
</script>
