<template>
  <div id="app">
  <v-app>
        <epa-header></epa-header>

    <!-- <a href="https://comptox.epa.gov/dashboard/">CompTox Chemicals Dashboard</a>
    <a href="https://comptox.epa.gov/genra/">Generalized Read-Across (GenRA)</a>
    <a href="https://comptox.epa.gov/dashboard/chemical/pubmed-abstract-sifter/">Literature Abstract Sifter</a>
    <a href="https://www.epa.gov/chemical-research/cheminformatics">Cheminformatics PoC Modules</a>
    <a href="http://v2626umcth819.rtord.epa.gov:9414/#/">Chemical Transformations Database</a>
    <a href="http://v2626umcth819.rtord.epa.gov:5173/">Spectral and Methods Database</a>
    <a href="http://v2626umcth819.rtord.epa.gov:9004/molmass_web.py">Molecular Mass Calculator</a> -->


    <!-- <v-app-bar class="flex-grow-0" color="primary" dark id="bar">
      <v-app-bar-nav-icon @click.stop="state.drawer = !state.drawer" />
      <v-toolbar-title>Analytical QC Viewer</v-toolbar-title>
    </v-app-bar> -->

  
  <v-container fluid id ="router-content">
    <v-row no-gutters>
      <v-col cols="2">
      <!-- <v-navigation-drawer v-if="state.drawer === true" v-model="state.drawer" dark color="secondary"> -->
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
      <!-- <v-card light class="ma-2">
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
        </v-card> -->
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
      <!-- </v-navigation-drawer> -->

      <v-card light class="ma-2">
        <v-card-title class="pb-0">Additional Apps</v-card-title>
        <v-card-text>
            <v-select v-model="defaultSelected" :items="body" item-text="name" item-value="name" hide-details>

            <template v-slot:item="{ item }">
              <template>
                <v-list-item-content>
                  <v-list-item :href="item.link" target="_blank">{{ item.name }}</v-list-item>
                  <v-list-item-subtitle>{{
                    item.description
                  }}</v-list-item-subtitle>
                </v-list-item-content>
              </template>
            </template>
          </v-select>
        </v-card-text>
      </v-card>
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
    EpaFooter,
    
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
        show: false

      },

      list: {
        items: [],
        selected: {},
      },

      state: {
        drawer: true,
      },
      defaultSelected: {
        name: "John",
        last: "Doe"
      },

      selected: '',
      body: [
        {
          name: "CompTox Chemicals Dashboard",
          last: "one",
          link: "https://comptox.epa.gov/dashboard/",
          description: "Suite of databases and web applications providing public access to chemical data"
        },
        {
          name: "Generalized Read-Across (GenRA)",
          last: "Dylan",
          link: "https://comptox.epa.gov/genra/",
          description: "Application to make reproducible read-across predictions of toxicity outcomes"
        },
        {
          name: "Literature Abstract Sifter",
          last: "Wen",
          link: "https://comptox.epa.gov/dashboard/chemical/pubmed-abstract-sifter/",
          description: "Find journal articles from PubMed related to a substance, domain, or phenomenon"
        },
         {
          name: "Cheminformatics PoC Modules",
          last: "Wen2",
          link: "https://www.epa.gov/chemical-research/cheminformatics",
          description: "Information on chemical structure, properties, fate and transport"

        }, {
          name: "Chemical Transformations Database",
          last: "Wen3",
          link: "http://v2626umcth819.rtord.epa.gov:9414/#/",
          description: "Curated compilation of observed organic chemical transformation pathways reported in literature"

        }, {
          name: "Spectral and Methods Database",
          last: "Wen4",
          link: "http://v2626umcth819.rtord.epa.gov:5173/",
          description: "Browse collection of mass spectra, monograph, and analytical methods"

        }, {
          name: "Molecular Mass Calculator",
          last: "Wen5",
          link: "http://v2626umcth819.rtord.epa.gov:9004/molmass_web.py",
          description: "Calculate Mass Composition of a substance"
        },
      ]
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
    },

    "selected"() {
      if (this.selected) {
        window.location = this.body.link;
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