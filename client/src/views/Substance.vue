<template>
  <v-container fluid v-if="substanceDetail && substanceDetail.substance">
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
          <AnnotationChip :annotation="editable.annotation.annotation" />
        </div>
        <div
          v-if="editable.mappedGradeT0"
          class="mx-1 d-inline-flex align-center"
        >
          <EditableChip
            :data="editable.mappedGradeT0"
            type="grade"
            title="T0"
            :use-tripod-colors="state.useTripodColors"
          />
        </div>
        <div
          v-if="editable.mappedGradeT4"
          class="mx-1 d-inline-flex align-center"
        >
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
        <div
          v-if="editable.substanceFlags"
          class="mx-1 d-inline-flex align-center"
        >
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
        @edited="save"
      />
      <v-btn
        class="ma-1"
        dark
        color="primary"
        :to="'/substances/id/' + state.previous"
      >
        Previous
      </v-btn>
      <v-btn class="ma-1" dark color="primary" :to="'/substances/id/' + state.next">
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
            :show-ungraded-experiments="state.showUngradedExperiments"
            :grades="grades"
            :calls="calls"
          />
          <v-expansion-panel v-if="substanceDetail.substanceFiles.length > 0">
            <v-expansion-panel-header class="text-h6">
              <v-row align="center">
                <div class="ma-2">
                  Additional files
                </div>
              </v-row>
            </v-expansion-panel-header>
            <v-expansion-panel-content>
              <v-list>
                <v-list-item v-for="file in substanceDetail.substanceFiles" :key="file.id">
                  <a
                    target="_blank"
                    rel="noreferrer noopener"
                    :href="`${CONTENT_SERVER_URL}/${file.fileName}`"
                    >{{ file.fileName }}</a
                  ><v-icon x-small class="ml-1">mdi-open-in-new</v-icon>
                </v-list-item>
              </v-list>
            </v-expansion-panel-content>
          </v-expansion-panel>
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
import store from "../store";
import { DASHBOARD_IMAGE_URL, CONTENT_SERVER_URL } from "@/store";

export default {
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
        showUngradedExperiments: true,
        next: null,
        previous: null,
      },

      store
    };
  },

  computed: {
    DASHBOARD_IMAGE_URL() {
      return DASHBOARD_IMAGE_URL;
    },

    CONTENT_SERVER_URL() {
      return CONTENT_SERVER_URL;
    },
  },

  methods: {
    getSubstanceDetail(query, type) {
      return SubstanceDataService.getDetailAlternate(query, type);
    },

    setSubstanceFlags(id) {
      this.editable.substanceFlags = [];
      SubstanceFlagDataService.getBySubstanceId(id)
        .then((response) => {
          this.editable.substanceFlags = response.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    setSubstanceGrades(id) {
      this.editable.mappedGradeT0 = null;
      this.editable.mappedGradeT4 = null;
      SubstanceGradeDataService.getBySubstanceId(id)
        .then((response) => {
          response.data.forEach((resp) => {
            if (resp.t0_t4) {
              this.editable.mappedGradeT4 = resp;
            } else {
              this.editable.mappedGradeT0 = resp;
            }
          });
        })
        .catch((err) => {
          console.log(err);
        });
    },

    setSubstanceCall(id) {
      this.editable.mappedCall = null;
      SubstanceCallDataService.getBySubstanceId(id)
        .then((response) => {
          this.editable.mappedCall = response.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    setSubstanceAnnotation(id) {
      this.editable.annotation = null;
      SubstanceAnnotationDataService.getBySubstanceId(id)
        .then((response) => {
          this.editable.annotation = response.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    setSubstanceData(query, type) {
      this.state.missingImage = false;
      this.substanceDetail = null;
      this.getSubstanceDetail(query, type)
        .then((response) => {
          this.substanceDetail = response.data;
          this.setNavigation();
          this.setEditable(this.substanceDetail.substance.id);
        })
        .catch(() => {
          this.$router.push("/error");
        });
    },

    setEditable(id) {
      this.setSubstanceFlags(id);
      this.setSubstanceGrades(id);
      this.setSubstanceCall(id);
      this.setSubstanceAnnotation(id);
    },

    setGradesAndCalls() {
      GradeDataService.getAll()
        .then((response) => {
          this.grades = response.data;
        })
        .catch((err) => {
          console.log(err);
        });
      CallDataService.getAll()
        .then((response) => {
          this.calls = response.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    save(edited) {
      this.saveEdited(edited)
        .then(() => {
          this.setEditable(this.substanceDetail.substance.id);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    saveEdited(edited) {
      const p0 = this.saveGrade(
        this.editable.mappedGradeT0,
        edited.mappedGradeT0
      );
      const p1 = this.saveGrade(
        this.editable.mappedGradeT4,
        edited.mappedGradeT4
      );
      const p2 = this.saveCall(this.editable.mappedCall, edited.mappedCall);
      const p3 = this.saveAnnotation(
        this.editable.annotation,
        edited.annotation
      );
      return Promise.all([p0, p1, p2, p3]);
    },

    saveGrade(editableMappedGrade, editedMappedGrade) {
      let savedMappedGrade = editableMappedGrade;
      if (!editableMappedGrade && editedMappedGrade.grade.name) {
        editedMappedGrade.substance = this.substanceDetail.substance;
        savedMappedGrade = SubstanceGradeDataService.post(editedMappedGrade);
      } else if (editableMappedGrade) {
        if (!editedMappedGrade.grade.name) {
          savedMappedGrade = SubstanceGradeDataService.delete(
            editableMappedGrade.id
          );
        } else if (
          editableMappedGrade.grade.name !== editedMappedGrade.grade.name ||
          editableMappedGrade.validated !== editedMappedGrade.validated
        ) {
          savedMappedGrade = SubstanceGradeDataService.put(
            editableMappedGrade.id,
            editedMappedGrade
          );
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
          savedMappedCall = SubstanceCallDataService.delete(
            editableMappedCall.id
          );
        } else if (
          editableMappedCall.call.name !== editedMappedCall.call.name ||
          editableMappedCall.validated !== editedMappedCall.validated
        ) {
          savedMappedCall = SubstanceCallDataService.put(
            editableMappedCall.id,
            editedMappedCall
          );
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
        savedAnnotation = SubstanceAnnotationDataService.put(
          editableAnnotation.id,
          editedAnnotation
        );
      } else if (editableAnnotation) {
        savedAnnotation = SubstanceAnnotationDataService.delete(
          editableAnnotation.id
        );
      }

      return Promise.resolve(savedAnnotation);
    },

    setNavigation() {
      if (this.store.listSelected()) {
          this.state.next = this.store.next();
          this.state.previous = this.store.previous();
        } else {
          this.state.next = this.substanceDetail.substance.id + 1;
          this.state.previous = this.substanceDetail.substance.id - 1;
        }
    }
  },

  watch: {
    "$route.params"() {
      this.setSubstanceData(this.$route.params.query, this.$route.params.type);
    },

    "state.showSpectrusFiles"() {
      if (this.state.showSpectrusFiles) {
        this.state.showUngradedExperiments = false;
      }
    },

    "state.showUngradedExperiments"() {
      if (this.state.showUngradedExperiments) {
        this.state.showSpectrusFiles = false;
      }
    }
  },

  mounted() {
    this.setGradesAndCalls();
    this.setSubstanceData(this.$route.params.query, this.$route.params.type);
    this.setNavigation();
  },
};
</script>

<style>
.v-data-table-header th {
  white-space: nowrap;
}
</style>
