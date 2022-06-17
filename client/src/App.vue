<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <v-row align="center">
        <v-col class="d-inline-flex col-auto">
          <v-icon>mdi-checkbox-multiple-marked</v-icon>
        </v-col>
        <v-col class="d-inline-flex col-auto mr-auto">
          <div class="text-h6">Analytical QC</div>
        </v-col>
        <v-col class="d-inline-flex col-2">
          <v-select
            v-model="search.type"
            light
            :items="search.items"
            hide-details
            solo
            dense
          />
        </v-col>
        <v-col class="d-inline-flex col-2">
          <v-text-field solo
          v-model="search.query"
          light
          append-icon="mdi-arrow-right"
          hide-details
          label="Go"
          dense
          @keyup.enter="go"
          @click:append="go"
        />
        </v-col>
        <v-col class="d-inline-flex col-auto">
          <v-btn to="/substances" text> All </v-btn>
        </v-col>
      </v-row>
    </v-app-bar>

    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
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
          { text: "Bottle Barcode", value: "bottle-barcode" }
        ],
        type: "dtxsid",
        query: "",
      }
    }
  },

  methods: {
    go() {
      this.$router.push(`/substances/${this.search.type}/${this.search.query}`);
      this.search.query = "";
    }
  }
};
</script>
