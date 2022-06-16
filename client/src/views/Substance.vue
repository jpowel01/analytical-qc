<template>
  <v-container fluid v-if="substanceDetail.substance">
    <v-row class="ma-2" align="center">
      <div class="text-h4 mr-2">
        {{ substanceDetail.substance.preferredName }}
      </div>
      <div
        class="mx-2"
        v-if="
          editable.mappedGradeT0 ||
          editable.mappedGradeT4 ||
          editable.mappedCall ||
          editable.substanceFlags ||
          editable.annotation
        "
      >
        <div v-if="editable.annotation" class="mx-1 d-inline-flex align-center">
          <AnnotationChip
            :annotation="editable.annotation.annotation"
          />
        </div>
        <div v-if="editable.mappedGradeT0" class="mx-1 d-inline-flex align-center">
          <EditableChip
            :data="editable.mappedGradeT0"
            type="grade"
            title="T0"
            :use-tripod-colors="state.useTripodColors"
          />
        </div>
        <div v-if="editable.mappedGradeT4" class="mx-1 d-inline-flex align-center">
          <EditableChip
            :data="editable.mappedGradeT4"
            type="grade"
            title="T4"
            :use-tripod-colors="state.useTripodColors"
          />
        </div>
        <div v-if="editable.mappedCall" class="mx-1 d-inline-flex align-center">
          <EditableChip
            :data="editable.mappedCall"
            type="call"
            title="Call"
            :use-tripod-colors="state.useTripodColors"
          />
        </div>
        <div v-if="editable.substanceFlags" class="mx-1 d-inline-flex align-center">
          <EditableChip
            v-for="sf in editable.substanceFlags"
            :key="sf.id"
            :data="sf"
            type="flag"
            :use-tripod-colors="state.useTripodColors"
          />
        </div>
      </div>
      <v-spacer />
      <EditDialog
        :grades="grades"
        :calls="calls"
        :editable="editable"
        @edited="saveEdited"
        />
      <v-btn
        class="ma-1"
        dark
        color="primary"
        :to="'/substances/id/' + (substanceDetail.substance.id - 1)"
      >
        Previous
      </v-btn>
      <v-btn
        class="ma-1"
        dark
        color="primary"
        :to="'/substances/id/' + (substanceDetail.substance.id + 1)"
      >
        Next
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
import AnnotationChip from "../components/AnnotationChip";
import EditDialog from "../components/EditDialog";
import SubstanceInfoTable from "../components/SubstanceInfoTable";
import PropertyPredictionTable from "../components/PropertyPredictionTable";
import SamplePanel from "../components/SamplePanel";
import SubstanceGradeDataService from "../services/SubstanceGradeDataService";
import SubstanceCallDataService from "../services/SubstanceCallDataService";
import SubstanceFlagDataService from "../services/SubstanceFlagDataService";
import SubstanceAnnotationDataService from "../services/SubstanceAnnotationDataService";
import { DASHBOARD_IMAGE_URL } from "@/main";

export default {
  name: "substance-detail",

  components: {
    EditableChip,
    SubstanceInfoTable,
    PropertyPredictionTable,
    SamplePanel,
    EditDialog,
    AnnotationChip,
  },

  data() {
    return {
      substanceDetail: null,
      editable: {
        substanceFlags: [],
        mappedGradeT0: null,
        mappedGradeT4: null,
        mappedCall: null,
        annotation: null,
      },

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
  },

  methods: {
    retrieveSubstanceDetail(query, type) {
      return SubstanceDataService.getDetailAlternate(query, type);
    },

    async retrieveSubstanceFlags(id) {
      this.editable.substanceFlags = [];
      let response = await SubstanceFlagDataService.getBySubstanceId(id);
      if (response) {
        this.editable.substanceFlags = response.data;
      }
    },

    async retrieveSubstanceGrades(id) {
      this.editable.mappedGradeT0 = null;
      this.editable.mappedGradeT4 = null;
      let response = await SubstanceGradeDataService.getBySubstanceId(id);
      if (response) {
        response.data.forEach((resp) => {
          if (resp.t0_t4) {
            this.editable.mappedGradeT4 = resp;
          } else {
            this.editable.mappedGradeT0 = resp;
          }
        });
      }
    },

    async retrieveSubstanceCall(id) {
      this.editable.mappedCall = null;
      let response = await SubstanceCallDataService.getBySubstanceId(id);
      if (response) {
        this.editable.mappedCall = response.data;
      }
    },

    async retrieveSubstanceAnnotation(id) {
      this.editable.annotation = null;
      let response = await SubstanceAnnotationDataService.getBySubstanceId(id);
      if (response) {
        this.editable.annotation = response.data;
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
      this.retrieveSubstanceAnnotation(id);
    },

    retrieveGradesAndCalls() {
      GradeDataService.getAll().then((response) => {
        this.grades = response.data;
      });
      CallDataService.getAll().then((response) => {
        this.calls = response.data;
      });
    },

    async saveEdited(edited) {
      this.saveEditable(edited).then((values) => {
        this.editable.mappedGradeT0 = values[0];
        this.editable.mappedGradeT4 = values[1];
        this.editable.mappedCall = values[2];
        this.editable.annotation = values[3];
        this.refreshEditable(this.substanceDetail.substance.id);
      }
      )
    },

    async saveEditable(edited) {
      const p1 = this.saveGrade(this.editable.mappedGradeT0, edited.mappedGradeT0);
      const p2 = this.saveGrade(this.editable.mappedGradeT4, edited.mappedGradeT4);
      const p3 = this.saveCall(this.editable.mappedCall, edited.mappedCall);
      const p4 = this.saveAnnotation(this.editable.annotation, edited.annotation);
      return Promise.all([p1, p2, p3, p4]);
    },

    saveGrade(editableMappedGrade, editedMappedGrade) {
      let savedMappedGrade = editableMappedGrade;
      if (!editableMappedGrade && editedMappedGrade.grade.name) {
        editedMappedGrade.substance = this.substanceDetail.substance;
        savedMappedGrade = SubstanceGradeDataService.post(editedMappedGrade);
      } else if (editableMappedGrade) {
        if (!editedMappedGrade.grade.name) {
          savedMappedGrade = SubstanceGradeDataService.delete(editableMappedGrade.id);
        } else if (editableMappedGrade.grade.name !== editedMappedGrade.grade.name || (editableMappedGrade.validated !== editedMappedGrade.validated)) {
          savedMappedGrade = SubstanceGradeDataService.put(editableMappedGrade.id, editedMappedGrade);
        }
      }

      return Promise.resolve(savedMappedGrade);
    },

    saveCall(editableMappedCall, editedMappedCall) {
      let savedMappedCall = editableMappedCall;
      if (!editableMappedCall && editedMappedCall.call.name) {
        editedMappedCall.substance = this.substanceDetail.substance;
        savedMappedCall = SubstanceCallDataService.post(editedMappedCall);
      } else if (editableMappedCall) {
        if (!editedMappedCall.call.name) {
          savedMappedCall = SubstanceCallDataService.delete(editableMappedCall.id);
        } else if ((editableMappedCall.call.name !== editedMappedCall.call.name) || (editableMappedCall.validated !== editedMappedCall.validated)) {
          savedMappedCall = SubstanceCallDataService.put(editableMappedCall.id, editedMappedCall);
        }
      }

      return Promise.resolve(savedMappedCall);
    },

    saveAnnotation(editableAnnotation, editedAnnotation) {
      let savedAnnotation = editableAnnotation;
      if (!editableAnnotation && editedAnnotation.annotation) {
        editedAnnotation.substance = this.substanceDetail.substance;
        savedAnnotation = SubstanceAnnotationDataService.post(editedAnnotation);
      } else if (editableAnnotation && editedAnnotation.annotation) {
        savedAnnotation = SubstanceAnnotationDataService.put(editableAnnotation.id, editedAnnotation);
      } else if (editableAnnotation) {
        savedAnnotation = SubstanceAnnotationDataService.delete(editableAnnotation.id);
      }

      return Promise.resolve(savedAnnotation);
    }
  },

  watch: {
    "$route.params"() {
      this.refresh(this.$route.params.query, this.$route.params.type);
    },
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
