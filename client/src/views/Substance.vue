<template>
  <v-container fluid v-if="substanceDetail.substance">
    <v-row class="ma-2">
      <div class="text-h4 mr-2">{{ substanceDetail.substance.preferredName }}</div>
      <div class="mx-2" v-if="t0Grade || t4Grade || call">
        <div v-if="t0Grade" class="mx-1 d-inline-flex align-center">
          <span class="text--secondary">T0: </span>
          <EditableChip
            :data="t0Grade"
            :use-tripod-colors="state.useTripodColors"
            :service="SubstanceGradeDataService"
            />
        </div>
        <div v-if="t4Grade" class="mx-1 d-inline-flex align-center">
          <span class="text--secondary">T4: </span>
          <EditableChip
          :data="t4Grade"
          :use-tripod-colors="state.useTripodColors"
          :service="SubstanceGradeDataService"
          />
        </div>
        <div v-if="call" class="mx-1 d-inline-flex align-center">
          <span class="text--secondary">Call: </span>
          <CallChip
          :call="call.call"
          :validated="call.validated"
          />
        </div>
      </div>
      <div v-if="substanceFlags" class="mx-2">
        <v-tooltip top v-for="flag in substanceFlags" :key="flag.flag.id">
          <template v-slot:activator="{ on, attrs }">
            <v-chip
              color="purple"
              :text-color="flag.validated ? 'white' : 'purple'"
              :outlined="!flag.validated"
              class="ma-1"
              v-bind="attrs"
              v-on="on"
            >
              {{ flag.flag.description }}
            </v-chip>
          </template>
          <span v-if="flag.validated">Validated flag</span>
          <span v-else>Automatic flag</span>
        </v-tooltip>
      </div>
      <v-spacer />
      <v-btn
        class="ma-1"
        dark
        color="primary"
        :to="'/substances/' + (substanceDetail.substance.id - 1)"
      >
        Previous Substance
      </v-btn>
      <v-btn
        class="ma-1"
        dark
        color="primary"
        :to="'/substances/' + (substanceDetail.substance.id + 1)"
      >
        Next Substance
      </v-btn>
    </v-row>
    <v-row>
      <v-col cols="2">
        <v-img
          :src="`${DASHBOARD_IMAGE_URL}/${substanceDetail.substance.dtxsid}`"
          :key="substanceDetail.substance.dtxsid"
          @error="state.missingImage = true"
        />
        <v-alert type="error" v-if="state.missingImage"
          >Image unavailable</v-alert
        >
      </v-col>

      <v-col cols="5" class="d-flex flex-column">
        <SubstanceInfoTable
          :substance="substanceDetail.substance"
          class="flex d-flex flex-column"
        />
      </v-col>

      <v-col cols="5" class="d-flex flex-column">
        <PropertyPredictionTable
          class="flex d-flex flex-column"
          v-if="substanceDetail.propertyPrediction"
          :property-prediction="substanceDetail.propertyPrediction"
        />
        <v-alert type="error" v-else>Property predictions unavailable</v-alert>
      </v-col>
    </v-row>

    <v-row>
      <v-col>
        <v-switch
          v-model="state.useTripodColors"
          :label="(state.useTripodColors ? 'Tripod' : 'Default') + ' colors'"
        ></v-switch>
        <v-expansion-panels 
          multiple>
          <SamplePanel 
            v-for="sampleDetail in substanceDetail.sampleDetails"
            :key="sampleDetail.sample.id"
            :sample="sampleDetail.sample"
            :experiments="sampleDetail.experiments"
            :use-tripod-colors="state.useTripodColors"
            />
        </v-expansion-panels>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import GradeDataService from "../services/GradeDataService";
import CallDataService from "../services/CallDataService";
import SubstanceDataService from "../services/SubstanceDataService";
import EditableChip from "../components/EditableChip";
import CallChip from "../components/CallChip";
import SubstanceInfoTable from "../components/SubstanceInfoTable";
import PropertyPredictionTable from "../components/PropertyPredictionTable";
import SamplePanel from "../components/SamplePanel";
import SubstanceGradeDataService from "../services/SubstanceGradeDataService"
import { DASHBOARD_IMAGE_URL } from "@/main";

export default {
  name: "substance-detail",

  components: {
    EditableChip,
    CallChip,
    SubstanceInfoTable,
    PropertyPredictionTable,
    SamplePanel
  },

  data() {
    return {
      substanceDetail: null,
      substanceFlags: [],
      t0Grade: null,
      t4Grade: null,
      call: null,

      grades: [],
      calls: [],

      state: {
        missingImage: false,
        useTripodColors: false,
      },
    };
  },

  computed: {
    DASHBOARD_IMAGE_URL() {
      return DASHBOARD_IMAGE_URL;
    },

    SubstanceGradeDataService() {
      return SubstanceGradeDataService;
    }
  },

  methods: {
    retrieveSubstanceDetail(id) {
      SubstanceDataService.getDetail(id)
        .then((response) => {
          this.substanceDetail = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          this.substanceDetail = null;
          console.log(e);
        });
    },

    retrieveSubstanceFlags(id) {
      SubstanceDataService.getFlags(id)
        .then((response) => {
          this.substanceFlags = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          this.substanceFlags = [];
          console.log(e);
        });
    },

    retrieveSubstanceGrades(id) {
      SubstanceDataService.getGrades(id)
        .then((response) => {
          this.t0Grade = null;
          this.t4Grade = null;
          response.data.forEach(grade => {
                if (grade.t0_t4) {
                    this.t4Grade = grade;
                } else {
                    this.t0Grade = grade;
                }
            })
          console.log(response.data);
        })
        .catch((e) => {
          this.t0Grade = null;
          this.t4Grade = null;
          console.log(e);
        });
    },

    retrieveSubstanceCall(id) {
      SubstanceDataService.getCall(id)
        .then((response) => {
          this.call = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          this.call = null;
          console.log(e);
        });
    },

    refresh(id) {
      this.state.missingImage = false;
      this.retrieveSubstanceDetail(id);
      this.retrieveSubstanceFlags(id);
      this.retrieveSubstanceGrades(id);
      this.retrieveSubstanceCall(id);
    },

    retrieveGradesAndCalls() {
      GradeDataService.getAll()
        .then((response) => {
          this.grades = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
      CallDataService.getAll()
        .then((response) => {
          this.calls = response.data;
          console.log(response.data);
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },

  watch: {
    "$route.params.id"() {
      this.refresh(this.$route.params.id);
    },
  },

  mounted() {
    this.retrieveGradesAndCalls();
    this.refresh(this.$route.params.id);
  },
};
</script>

<style>
.v-data-table-header th {
  white-space: nowrap;
}
</style>