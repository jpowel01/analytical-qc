<template>
  <v-container fluid v-if="substanceDetail.substance">
    <v-row class="mx-2 my-2">
      <div class="text-h4">{{ substanceDetail.substance.preferredName }}</div>
      <div v-if="substanceFlags" class="mx-2">
        <v-chip 
          v-for="flag in substanceFlags"
          :key="flag.flag.id"
          color="red"
          :text-color = "flag.validated ? 'white' : 'red'"
          :outlined="!flag.validated"
          class="ma-1">
          {{ flag.flag.description }}
        </v-chip>
      </div>
      <v-spacer />
      <v-btn
        class="ma-1"
        dark
        color="primary"
        :to="'/substances/' + (substanceDetail.substance.id - 1)">
        Previous Substance
      </v-btn>
      <v-btn
        class="ma-1"
        dark
        color="primary"
        :to="'/substances/' + (substanceDetail.substance.id + 1)">
        Next Substance
      </v-btn>
    </v-row>
    <v-row>
      <v-col cols="2">
        <v-img 
          :src="`${DASHBOARD_IMAGE_URL}/${substanceDetail.substance.dtxsid}`"
          :key="substanceDetail.substance.dtxsid"
          @error="state.missingImage = true" />
        <v-alert type="error" v-if="state.missingImage">Image unavailable</v-alert>
      </v-col>

      <v-col cols="5" class="d-flex flex-column">
        <v-card class="flex d-flex flex-column">
          <v-card-title>Substance Information</v-card-title>
          <v-simple-table>
            <tbody>
              <tr>
                <th scope="row">DTXSID</th>
                <td>
                  <a 
                    target="_blank"
                    rel="noreferrer noopener"
                    :href="`${DASHBOARD_DETAILS_URL}/${substanceDetail.substance.dtxsid}`"
                  >{{ substanceDetail.substance.dtxsid }}</a><v-icon x-small class="ml-1">mdi-open-in-new</v-icon>
                </td>
              </tr>
              <tr>
                <th scope="row">CASRN</th>
                <td>{{ substanceDetail.substance.casrn }}</td>
              </tr>
              <tr>
                <th scope="row">Mol. Formula</th>
                <td>{{ substanceDetail.substance.molFormula }}</td>
              </tr>
              <tr>
                <th scope="row">Mol. Weight</th>
                <td>{{ substanceDetail.substance.molWeight }}</td>
              </tr>
              <tr>
                <th scope="row">PubChem CID</th>
                <td>
                  <div v-if="substanceDetail.substance.pubchemCid">
                    <a 
                      target="_blank"
                      rel="noreferrer noopener"
                      :href="`${PUBCHEM_CID_URL}/${substanceDetail.substance.pubchemCid}`"
                    >{{ substanceDetail.substance.pubchemCid }}</a><v-icon x-small class="ml-1">mdi-open-in-new</v-icon>
                  </div>
                </td>
              </tr>
            </tbody>
          </v-simple-table>
        </v-card>
      </v-col>

      <v-col cols="5" class="d-flex flex-column">
        <v-card class="flex d-flex flex-column" v-if="substanceDetail.propertyPrediction">
          <v-card-title>OPERA Predicted Properties</v-card-title>
          <v-simple-table>
            <tbody>
              <tr>
                <th scope="row">Melting Point (C)</th>
                <td>{{ substanceDetail.propertyPrediction.mp.toFixed(0) }}</td>
              </tr>
              <tr>
                <th scope="row">Boiling Point (C)</th>
                <td>{{ substanceDetail.propertyPrediction.bp.toFixed(0) }}</td>
              </tr>
              <tr>
                <th scope="row">Vapor Pressure (mmHg)</th>
                <td v-if="substanceDetail.propertyPrediction.vp < 0.1">{{ substanceDetail.propertyPrediction.vp.toExponential(2) }}</td>
                <td v-else>{{ substanceDetail.propertyPrediction.vp.toFixed(2) }}</td>
              </tr>
              <tr>
                <th scope="row">Octanol-Water Partition Coefficient (logP)</th>
                <td>{{ substanceDetail.propertyPrediction.logP.toPrecision(2) }}</td>
              </tr>
            </tbody>
          </v-simple-table>
        </v-card>
        <v-alert type="error" v-else>Property predictions unavailable</v-alert>
      </v-col>
    </v-row>

    <v-row>
      <v-col>
        <v-expansion-panels multiple>
          <v-expansion-panel v-for="sample in substanceDetail.sampleDetails" :key="sample.sample.id">
            <v-expansion-panel-header class="text-h6">
              <span v-if=sample.sample.tox21Id>Tox21_{{ sample.sample.tox21Id }}</span>
              <span v-else-if=sample.sample.ncgcId>{{ sample.sample.ncgcId }}</span>
              <span v-else-if=sample.sample.bottleBarcode>{{ sample.sample.bottleBarcode }}</span>
              <span v-else>{{ substanceDetail.substance.dtxsid }} (no sample information)</span>
            </v-expansion-panel-header>
            <v-expansion-panel-content>
              <v-data-table
                :headers="experimentHeaders"
                :items="sample.experiments"
                disable-pagination
                hide-default-footer
                sort-by="timepoint">
              </v-data-table>
              <div class="text-caption text--secondary my-2">
                <span class="mx-1" v-if=sample.sample.tox21Id><strong>Tox21 ID:</strong> Tox21_{{ sample.sample.tox21Id }}</span>
                <span class="mx-1" v-if=sample.sample.ncgcId><strong>NCGC ID:</strong> {{ sample.sample.ncgcId }}</span>
                <span class="mx-1" v-if=sample.sample.bottleBarcode><strong>EPA Bottle Barcode:</strong> {{ sample.sample.bottleBarcode }}</span>
                <span class="mx-1" v-if=sample.sample.pubchemSid><strong>PubChem SID:</strong> <a 
                    target="_blank"
                    rel="noreferrer noopener"
                    :href="`${PUBCHEM_SID_URL}/${sample.sample.pubchemSid}`"
                  >{{ sample.sample.pubchemSid }}</a><v-icon x-small class="ml-1">mdi-open-in-new</v-icon>
                </span>
              </div>
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import SubstanceDataService from "../services/SubstanceDataService";
import SubstanceFlagDataService from "../services/SubstanceFlagDataService";
import {
  DASHBOARD_DETAILS_URL,
  DASHBOARD_IMAGE_URL,
  PUBCHEM_CID_URL,
  PUBCHEM_SID_URL,
} from "@/main";

export default {
  name: "substance-detail",

  data() {
    return {
      substanceDetail: null,
      substanceFlags: [],
      experimentHeaders: [
        { text: "File Date", value: "file.fileDate", sortable: true, },
        { text: "Experiment Date", value: "experimentDate", sortable: true, },
        { text: "File Name", value: "file.fileName", sortable: true, },
        { text: "Study", value: "study", sortable: true, },
        { text: "Timepoint", value: "timepoint", sortable: true, },
        { text: "Batch", value: "batch", sortable: true, },
        { text: "Well", value: "well", sortable: true, },
        { text: "Grade", value: "grade", sortable: true, },
        { text: "Purity", value: "purity", sortable: true, },
        { text: "Pass/Fail", value: "passFail", sortable: true, },
        { text: "Comment", value: "comment", sortable: true, },
      ],

      state: {
        loading: false,
        missingImage: false,
      }
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
    PUBCHEM_SID_URL() {
      return PUBCHEM_SID_URL;
    },
  },

  methods: {
    retrieveSubstanceDetail(id) {
      this.state.loading = true;
      SubstanceDataService.getDetail(id)
        .then((response) => {
          this.substanceDetail = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          this.substanceDetail = null;
          console.log(e);
        });
      this.state.loading = false;
    },

    retrieveSubstanceFlags(id) {
      this.state.loading = true;
      SubstanceFlagDataService.getBySubstanceId(id)
        .then((response) => {
          this.substanceFlags = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          this.substanceFlags = [];
          console.log(e);
        });

      this.state.loading = false;
    },

    refresh(id) {
      this.state.missingImage = false;
      this.retrieveSubstanceDetail(id);
      this.retrieveSubstanceFlags(id);
    }
  },

  watch: {
    '$route.params.id'() {
      this.refresh(this.$route.params.id);
    }
  },

  mounted() {
    this.refresh(this.$route.params.id);
  },
};
</script>

<style>
.v-data-table-header th {
  white-space: nowrap;
}
</style>