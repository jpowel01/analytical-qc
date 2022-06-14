<template>
  <v-container fluid v-if="substanceDetail.substance">
    <v-row class="ma-2" align="center">
      <div class="text-h4 mr-2">
        {{ substanceDetail.substance.preferredName }}
      </div>
      <div class="mx-2" v-if="substanceGradeT0 || substanceGradeT4 || substanceCall || substanceFlags">
        <div v-if="substanceGradeT0" class="mx-1 d-inline-flex align-center">
          <EditableChip
            :data="substanceGradeT0"
            type="grade"
            title="T0"
            :use-tripod-colors="state.useTripodColors"
            @validated="onValidate(substanceGradeT0, SubstanceGradeDataService)"
            @deleted="onDelete(substanceGradeT0, SubstanceGradeDataService)"
          />
        </div>
        <div v-if="substanceGradeT4" class="mx-1 d-inline-flex align-center">
          <EditableChip
            :data="substanceGradeT4"
            type="grade"
            title="T4"
            :use-tripod-colors="state.useTripodColors"
            @validated="onValidate(substanceGradeT4, SubstanceGradeDataService)"
            @deleted="onDelete(substanceGradeT4, SubstanceGradeDataService)"
          />
        </div>
        <div v-if="substanceCall" class="mx-1 d-inline-flex align-center">
          <EditableChip
            :data="substanceCall"
            type="call"
            title="Call"
            :use-tripod-colors="state.useTripodColors"
            @validated="onValidate(substanceCall, SubstanceCallDataService)"
            @deleted="onDelete(substanceCall, SubstanceCallDataService)"
          />
        </div>
        <div v-if="substanceFlags" class="mx-1 d-inline-flex align-center">
          <EditableChip
            v-for="sf in substanceFlags"
            :key="sf.id"
            :data="sf"
            type="flag"
            :use-tripod-colors="state.useTripodColors"
            @validated="onValidate(sf, SubstanceFlagDataService)"
            @deleted="onDelete(sf, SubstanceFlagDataService)"
          />
        </div>
        <EditDialog
          :grades="grades"
          :calls="calls"
          :existingT0="t0"
          :existingT4="t4"
          :existing-call="call"
          :grade-service="SubstanceGradeDataService"
          :call-service="SubstanceCallDataService"
        />
      </div>
      <v-spacer />
      <v-btn
        class="ma-1"
        dark
        color="primary"
        :to="'/substances/id/' + (substanceDetail.substance.id - 1)"
      >
        Previous Substance
      </v-btn>
      <v-btn
        class="ma-1"
        dark
        color="primary"
        :to="'/substances/id/' + (substanceDetail.substance.id + 1)"
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
        <div class="d-inline-flex align-center mx-2">
          <v-switch
            v-model="state.useTripodColors"
            :label="(state.useTripodColors ? 'Tripod' : 'Default') + ' colors'"
            hide-details
          />
        </div>
        <div class="d-inline-flex align-center mx-2">
          <v-switch
            v-model="state.showSpectrusFiles"
            :label="
              (state.showSpectrusFiles ? 'Show' : 'Hide') + ' Spectrus files'
            "
            hide-details
          />
        </div>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-expansion-panels multiple>
          <SamplePanel
            v-for="sampleDetail in substanceDetail.sampleDetails"
            :key="sampleDetail.sample.id"
            :sample="sampleDetail.sample"
            :experiments="sampleDetail.experiments"
            :use-tripod-colors="state.useTripodColors"
            :show-spectrus-files="state.showSpectrusFiles"
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
import EditDialog from "../components/EditDialog";
import SubstanceInfoTable from "../components/SubstanceInfoTable";
import PropertyPredictionTable from "../components/PropertyPredictionTable";
import SamplePanel from "../components/SamplePanel";
import SubstanceGradeDataService from "../services/SubstanceGradeDataService";
import SubstanceCallDataService from "../services/SubstanceCallDataService";
import SubstanceFlagDataService from "../services/SubstanceFlagDataService";
import { DASHBOARD_IMAGE_URL } from "@/main";

export default {
  name: "substance-detail",

  components: {
    EditableChip,
    SubstanceInfoTable,
    PropertyPredictionTable,
    SamplePanel,
    EditDialog,
  },

  data() {
    return {
      substanceDetail: null,
      substanceFlags: [],
      substanceGradeT0: null,
      substanceGradeT4: null,
      substanceCall: null,

      grades: [],
      calls: [],

      state: {
        missingImage: false,
        useTripodColors: false,
        showSpectrusFiles: false,
      },
    };
  },

  computed: {
    DASHBOARD_IMAGE_URL() {
      return DASHBOARD_IMAGE_URL;
    },

    SubstanceGradeDataService() {
      return SubstanceGradeDataService;
    },

    SubstanceCallDataService() {
      return SubstanceCallDataService;
    },

    SubstanceFlagDataService() {
      return SubstanceFlagDataService;
    },
  },

  methods: {
    async onValidate(data, service) {
      data.validated = true;
      data = await service.put(data.id, data);
    },

    async onDelete(data, service) {
      data = await service.delete(data.id);
      this.refreshEditable(this.substanceDetail.substance.id)
    },

    retrieveSubstanceDetail(query, type) {
      return SubstanceDataService.getDetailAlternate(query, type);
    },

    async retrieveSubstanceFlags(id) {
      this.substanceFlags = [];
      let response = await SubstanceDataService.getFlags(id);
      if (response) {
        this.substanceFlags = response.data;
      }
    },

    async retrieveSubstanceGrades(id) {
      this.substanceGradeT0 = null;
      this.substanceGradeT4 = null;
      let response = await SubstanceDataService.getGrades(id);
      if (response) {
        response.data.forEach((resp) => {
          if (resp.t0_t4) {
            this.substanceGradeT4 = resp;
          } else {
            this.substanceGradeT0 = resp;
          }
        });
      }
    },

    async retrieveSubstanceCall(id) {
      this.substanceCall = null;
      let response = await SubstanceDataService.getCall(id);
      if (response) {
        this.substanceCall = response.data;
      }
    },

    async refresh(query, type) {
      this.state.missingImage = false;
      this.substanceDetail = null;
      let response = await this.retrieveSubstanceDetail(query, type);
      if (response) {
        this.substanceDetail = response.data;
        this.refreshEditable(this.substanceDetail.substance.id);
      }
    },

    refreshEditable(id) {
      this.retrieveSubstanceFlags(id);
      this.retrieveSubstanceGrades(id);
      this.retrieveSubstanceCall(id);
    },

    retrieveGradesAndCalls() {
      GradeDataService.getAll()
        .then((response) => {
          this.grades = response.data;
          console.log(response.data);
        });
      CallDataService.getAll()
        .then((response) => {
          this.calls = response.data;
          console.log(response.data);
        });
    },
  },

  watch: {
    "$route.params"() {
      this.refresh(this.$route.params.query, this.$route.params.type);
    }
  },

  mounted() {
    this.retrieveGradesAndCalls();
    this.refresh(this.$route.params.query, this.$route.params.type);
  },
};
</script>

<style>
.v-data-table-header th {
  white-space: nowrap;
}
</style>
