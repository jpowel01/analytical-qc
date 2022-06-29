<template>
  <v-app>
    <v-navigation-drawer app v-model="state.drawer" dark color="secondary">
      <v-card light class="ma-2">
        <v-card-title class="pb-0">Go to record</v-card-title>
        <v-card-text>
          <v-select
              v-model="search.type"
              :items="search.items"
              hide-details
            />
            <v-text-field
              v-model="search.query"
              append-icon="mdi-arrow-right"
              hide-details
              label="Go"
              @keyup.enter="go"
              @click:append="go"
            />
        </v-card-text>
      </v-card>
      <v-card light class="ma-2">
        <v-card-title class="pb-0">Go to list</v-card-title>
        <v-card-text>
          <v-select
              v-model="list.id"
              :items="list.items"
              item-text="name"
              item-value="id"
              hide-details
            />
        </v-card-text>
      </v-card>
      <v-card light class="ma-2">
        <v-card-text class="text-center"><v-btn to="/substances" color="primary"> All substances </v-btn></v-card-text>
      </v-card>
    </v-navigation-drawer>

    <v-app-bar app color="primary" dark>
      <v-app-bar-nav-icon @click.stop="state.drawer = !state.drawer" />
      <v-row align="center">
        <v-col class="d-inline-flex col-auto mr-auto">
          <div class="text-h6">Analytical QC</div>
        </v-col>
        <v-col class="d-inline-flex col-auto">
          
        </v-col>
      </v-row>
    </v-app-bar>

    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
import ListDataService from "./services/ListDataService";

export default {
  name: "App",

  data() {
    return {
      search: {
        items: [
          { text: "DTXSID", value: "dtxsid" },
          { text: "CASRN", value: "casrn" },
          { text: "Tox21 ID", value: "tox21-id" },
          { text: "NCGC ID", value: "ncgc-id" },
          { text: "Bottle Barcode", value: "bottle-barcode" },
        ],
        type: "dtxsid",
        query: "",
      },

      list: {
        items: [],
        selected: {},
      },

      state: {
        drawer: null,
      }
    };
  },

  methods: {
    go() {
      this.$router.push(`/substances/${this.search.type}/${this.search.query}`);
      this.search.query = "";
    },
  },

  watch: {
    "list.id"() {
      if (this.list.id) {
        this.$router.push(`/substances/list/${this.list.id}`);
      }
    }
  },

  mounted() {
    ListDataService.getAll()
      .then((response) => {
        this.list.items = response.data;
      })
  },
};
</script>
