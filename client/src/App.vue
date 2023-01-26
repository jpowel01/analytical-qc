<template>
  <div id="app">
  <v-app>
        <epa-header></epa-header>


    <v-app-bar class="flex-grow-0" color="primary" dark id="bar">
      <v-app-bar-nav-icon @click.stop="state.drawer = !state.drawer" />
      <v-toolbar-title>Analytical QC Viewer</v-toolbar-title>
    </v-app-bar>

  
  <v-container fluid id ="router-content">
    <v-row no-gutters>
      <v-col cols="2">
      <v-navigation-drawer v-if="state.drawer === true" v-model="state.drawer" dark color="secondary">
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
              :items="visibleLists"
              item-text="name"
              item-value="id"
              hide-details
            >
            <template v-slot:item="{ item }">
              <template>
                <v-list-item-content>
                  <v-list-item-title>{{ item.name }}</v-list-item-title>
                  <v-list-item-subtitle>{{
                    item.description
                  }}</v-list-item-subtitle>
                </v-list-item-content>
              </template>
            </template>
          </v-select>
        </v-card-text>
      </v-card>
      <v-card light class="ma-2">
        <v-card-title>
          <v-row>
            <v-col class="mr-auto col-auto">
              View all
            </v-col>
            <v-col class="col-auto">
              <v-btn icon to="/substances" color="primary">
                <v-icon>
                  mdi-arrow-right
                </v-icon>
              </v-btn>
            </v-col>
          </v-row>
        </v-card-title>
      </v-card>
      <v-card light class="ma-2">
        <v-card-title>
          <v-row>
            <v-col class="mr-auto col-auto">
              Manage
            </v-col>
            <v-col class="col-auto">
              <v-btn icon to= "/manage" color="primary">
                <v-icon>
                  mdi-arrow-right
                </v-icon>
              </v-btn>
              </v-col>
            </v-row>
          </v-card-title>
        </v-card>
        <v-card light class="ma-2">
        <v-card-title>
          <v-row>
            <v-col class="mr-auto col-auto">
              Batch Search
            </v-col>
            <v-col class="col-auto">
              <v-btn icon to="/batch-search" color="primary">
                <v-icon>
                  mdi-arrow-right
                </v-icon>
              </v-btn>
              </v-col>
            </v-row>
          </v-card-title>
        </v-card>

      </v-navigation-drawer>
    </v-col>

    <v-col cols="9">
            <v-main>
        <router-view @update="fetchLists" />
            </v-main>
      </v-col>

    </v-row>
    </v-container>

  </v-app>

      <epa-footer></epa-footer>

  </div>
</template>

<script>
import ListDataService from "./services/ListDataService";
import EpaHeader from "./components/EpaHeader.vue";
import EpaFooter from "./components/EpaFooter.vue";

export default {
  name: "App",
  components: {
    EpaHeader,
    EpaFooter
  },
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
        drawer: true,
      }
    };
  },

  computed: {
    visibleLists() {
      return this.list.items.filter((ele) => !ele.hidden);
    }
  },

  methods: {
    go() {
      this.$router.push(`/substances/${this.search.type}/${this.search.query}`);
      this.search.query = "";
    },

    fetchLists() {
      ListDataService.getAll()
      .then((response) => {
        this.list.items = response.data;
      })
    }
  },

  watch: {
    "list.id"() {
      if (this.list.id) {
        this.$router.push(`/substances/lists/${this.list.id}`);
      }
    }
  },

  mounted() {
    this.fetchLists();
  },
};
</script>

<style scoped>

  v-app-bar.bar{
    position: fixed;
    height: 0.5rem !important;
  }
</style>