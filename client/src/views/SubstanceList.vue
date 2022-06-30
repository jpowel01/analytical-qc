<template>
  <v-container fluid>
    <v-row class="ma-2">
      <div class="text-h4">{{ state.title }}</div>
      <v-spacer />
      <v-text-field
        v-if="!$route.params.id"
        label="Search"
        append-icon="mdi-magnify"
        v-model="state.search"
        solo
        clearable
        hide-details
      />
    </v-row>
    <v-row>
      <v-col>
        <v-data-table
          :headers="headers"
          :items="substances"
          :options.sync="state.options"
          :server-items-length="totalSubstances"
          :loading="state.loading"
        >
          <template v-slot:item.id="{ value }">
            <v-btn
              :to="`/substances/id/${value}`"
              class="ma-2"
              dark
              small
              fab
              color="primary"
            >
              <v-icon> mdi-eye </v-icon>
            </v-btn>
          </template>
          <template v-slot:item.structure="{ item }">
            <v-img
              max-height="150"
              max-width="150"
              :src="`${DASHBOARD_IMAGE_URL}/${item.dtxsid}`"
            />
          </template>
          <template v-slot:item.dtxsid="{ value }">
            <a
              target="_blank"
              rel="noreferrer noopener"
              :href="`${DASHBOARD_DETAILS_URL}/${value}`"
              >{{ value }}</a
            ><v-icon x-small class="ml-1">mdi-open-in-new</v-icon>
          </template>
          <template v-slot:item.pubchemCid="{ value }">
            <div v-if="value">
                <a
                  target="_blank"
                  rel="noreferrer noopener"
                  :href="`${PUBCHEM_CID_URL}/${value}`"
                  >{{ value }}</a
                ><v-icon x-small class="ml-1">mdi-open-in-new</v-icon>
              </div>
          </template>
        </v-data-table>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import SubstanceDataService from "../services/SubstanceDataService";
import ListDataService from "../services/ListDataService"
import {
  DASHBOARD_DETAILS_URL,
  DASHBOARD_IMAGE_URL,
  PUBCHEM_CID_URL,
} from "@/store";

export default {
  data() {
    return {
      substances: [],
      totalSubstances: 0,

      headers: [
        { text: "", value: "id", sortable: false, width: "1%" },
        { text: "", value: "structure", sortable: false, width: "1%" },
        { text: "DTXSID", value: "dtxsid", sortable: false },
        { text: "Preferred Name", value: "preferredName", sortable: false },
        { text: "CASRN", value: "casrn", sortable: false },
        { text: "Mol. Formula", value: "molFormula", sortable: false },
        { text: "Mol. Weight", value: "molWeight", sortable: false },
        { text: "PubChem CID", value: "pubchemCid", sortable: false },
      ],

      state: {
        loading: false,
        options: {},
        search: "",
        title: "Substances",
      },
    };
  },

  watch: {
    "state.options": {
      handler() {
        this.setSubstances();
      },
      deep: true,
    },
    "state.search"() {
      this.state.options.page = 1;
      this.setSubstances();
    },
    "$route.params": {
      handler() {
        this.state.options.page = 1;
        this.setSubstances();
      },
      deep: true,
    },
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
    setSubstances() {
      this.state.loading = true;
      if (!this.$route.params.id) {
        this.state.title = "Substances";
        SubstanceDataService.searchPaged(
          this.state.search,
          this.state.options.page - 1,
          this.state.options.itemsPerPage
        )
          .then((response) => {
            this.substances = response.data.content;
            this.totalSubstances = response.data.totalElements;
            console.log(response.data);
            this.state.loading = false;
          })
          .catch((e) => {
            console.log(e);
            this.state.loading = false;
          });
        } else {
          ListDataService.getDetail(this.$route.params.id)
            .then((response) => {
              const substanceIds = response.data.substanceIds;
              this.state.title = response.data.list.name;
              SubstanceDataService.listPaged(
                substanceIds,
                this.state.options.page - 1,
                this.state.options.itemsPerPage
              )
                .then((response) => {
                  this.substances = response.data.content;
                  this.totalSubstances = response.data.totalElements;
                  console.log(response.data);
                  this.state.loading = false;
                })
                .catch((e) => {
                  console.log(e);
                  this.state.loading = false;
                });
            })
        }
    }
  },
};
</script>
