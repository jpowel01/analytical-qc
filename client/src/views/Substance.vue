<template>
  <v-container fluid v-if="detail && detail.substance">
    <v-row class="ma-2" align="center">
      <div class="text-h4 mr-2">
        {{ detail.substance.preferredName }}
      </div>
      <div
        class="mx-2"
        v-if="annotation"
      >
        <div v-if="annotation && annotation.annotation" class="mx-1 d-inline-flex align-center">
          <AnnotationChip :annotation="annotation.annotation" />
        </div>
        <div
          v-if="annotation.t0Grade"
          class="mx-1 d-inline-flex align-center"
        >
          <GradeCallChip
            :data="annotation.t0Grade"
            :validated="annotation.validated"
            title="T0"
            :use-tripod-colors="state.useTripodColors"
          />
        </div>
        <div
          v-if="annotation.t4Grade"
          class="mx-1 d-inline-flex align-center"
        >
          <GradeCallChip
            :data="annotation.t4Grade"
            :validated="annotation.validated"
            title="T4"
            :use-tripod-colors="state.useTripodColors"
          />
        </div>
        <div v-if="annotation.call" class="mx-1 d-inline-flex align-center">
          <GradeCallChip
            :data="annotation.call"
            :validated="annotation.validated"
            title="Call"
            :use-tripod-colors="state.useTripodColors"
          />
        </div>
        <div
          v-if="substanceStructureFlags"
          class="mx-1 d-inline-flex align-center"
        >
          <StructureFlagChip
            v-for="sf in substanceStructureFlags"
            :key="sf.id"
            :substance-structure-flag="sf"
          />
        </div>
      </div>
      <v-spacer />
      <EditDialog
        :grades="grades"
        :calls="calls"
        :structure-flags="structureFlags"
        :annotation="annotation"
        :substance-structure-flags="substanceStructureFlags"
        show-structure-flags="true"
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
          :src="`${DASHBOARD_IMAGE_URL}/${detail.substance.dtxsid}`"
          :key="detail.substance.dtxsid"
          @error="state.missingImage = true"
        />
        <v-alert type="error" v-if="state.missingImage"
          >Image unavailable</v-alert
        >
      </v-col>

      <v-col cols="5" class="d-flex flex-column">
        <SubstanceInfoTable
          :substance="detail.substance"
          class="flex d-flex flex-column"
        />
      </v-col>

      <v-col cols="5" class="d-flex flex-column">
        <PropertyPredictionTable
          class="flex d-flex flex-column"
          v-if="detail.propertyPrediction || detail.amenabilityPrediction"
          :property-prediction="detail.propertyPrediction"
          :amenability-prediction="detail.amenabilityPrediction"
        />
        <v-alert type="error" v-else>Substance predictions unavailable</v-alert>
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
              (state.showSpectrusFiles ? 'Showing' : 'Hiding') + ' Spectrus files'
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
            v-for="sampleDetail in detail.sampleDetails"
            :key="sampleDetail.sample.id"
            :sample="sampleDetail.sample"
            :experiments="sampleDetail.experiments"
            :use-tripod-colors="state.useTripodColors"
            :show-spectrus-files="state.showSpectrusFiles"
            :grades="grades"
            :calls="calls"
          />
          <v-expansion-panel v-if="detail.substanceFiles.length > 0">
            <v-expansion-panel-header class="text-h6">
              <v-row align="center">
                <div class="ma-2">
                  Additional files
                </div>
              </v-row>
            </v-expansion-panel-header>
            <v-expansion-panel-content>
              <v-list>
                <v-list-item v-for="file in detail.substanceFiles" :key="file.id">
                  <a
                    target="_blank"
                    rel="noreferrer noopener"
                    :href="`${CONTENT_SERVER_URL}/${file.fileName}`"
                    >{{ file.fileName }}</a
                  ><v-icon x-small class="ml-1">mdi-open-in-new</v-icon>
                  &nbsp;({{ file.note }})
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
import StructureFlagDataService from "../services/StructureFlagDataService";
import SubstanceDataService from "../services/SubstanceDataService";
import GradeCallChip from "../components/GradeCallChip";
import StructureFlagChip from "../components/StructureFlagChip";
import AnnotationChip from "../components/AnnotationChip";
import EditDialog from "../components/EditDialog";
import SubstanceInfoTable from "../components/SubstanceInfoTable";
import PropertyPredictionTable from "../components/PropertyPredictionTable";
import SamplePanel from "../components/SamplePanel";
import SubstanceStructureFlagDataService from "../services/SubstanceStructureFlagDataService";
import SubstanceAnnotationDataService from "../services/SubstanceAnnotationDataService";
import { DASHBOARD_IMAGE_URL, CONTENT_SERVER_URL } from "@/store";

export default {
  components: {
    GradeCallChip,
    StructureFlagChip,
    SubstanceInfoTable,
    PropertyPredictionTable,
    SamplePanel,
    EditDialog,
    AnnotationChip,
  },

  data() {
    return {
      detail: null,
      annotation: {},
      substanceStructureFlags: [],

      grades: [],
      calls: [],
      structureFlags: [],

      state: {
        missingImage: false,
        useTripodColors: false,
        showSpectrusFiles: false,
        next: null,
        previous: null,
      },
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
    getDetail(query, type) {
      return SubstanceDataService.getDetailAlternate(query, type);
    },

    setSubstanceStructureFlags(id) {
      this.substanceStructureFlags = [];
      SubstanceStructureFlagDataService.getBySubstanceId(id)
        .then((response) => {
          this.substanceStructureFlags = response.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    setSubstanceAnnotation(id) {
      this.annotation = null;
      SubstanceAnnotationDataService.getBySubstanceId(id)
        .then((response) => {
          this.annotation = response.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    setSubstanceData(query, type) {
      this.state.missingImage = false;
      this.detail = null;
      this.getDetail(query, type)
        .then((response) => {
          this.detail = response.data;
          this.setNavigation();
          this.setEditable(this.detail.substance.id);
        })
        .catch(() => {
          this.$router.push("/error");
        });
    },

    setEditable(id) {
      this.setSubstanceStructureFlags(id);
      this.setSubstanceAnnotation(id);
    },

    initGradesCallsStructureFlags() {
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
      StructureFlagDataService.getAll()
        .then((response) => {
          this.structureFlags = response.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    save(edited) {
      this.saveEdited(edited)
        .then(() => {
          this.setEditable(this.detail.substance.id);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    saveEdited(edited) {
      const p1 = this.saveAnnotation(
        edited.annotation
      );
      const p2 = this.saveStructureFlags(
        edited.substanceStructureFlags,
        edited.annotation.validated
      );
      return Promise.all([p1, p2]);
    },

    saveAnnotation(editedAnnotation) {
      let savedAnnotation = this.annotation;
      if (!this.annotation && editedAnnotation) {
        editedAnnotation.substance = this.detail.substance;
        savedAnnotation = SubstanceAnnotationDataService.post(editedAnnotation);
      } else if (editedAnnotation) {
        savedAnnotation = SubstanceAnnotationDataService.put(
          this.annotation.id,
          editedAnnotation
        );
      }

      return Promise.resolve(savedAnnotation);
    },

    saveStructureFlags(editedStructureFlags, validated) {
      let p1 = [];
      this.substanceStructureFlags.forEach((sf) => {
        p1.push(SubstanceStructureFlagDataService.delete(sf.id));
      })

      let p2 = [];
      Promise.all(p1)
        .then(() => {
          editedStructureFlags.forEach((sf) => {
            sf.substance = this.detail.substance;
            sf.validated = validated;
            p2.push(SubstanceStructureFlagDataService.post(sf));
          })
        })

      return Promise.all(p2);
    },

    setNavigation() {
      this.state.next = this.detail.substance.id + 1;
      this.state.previous = this.detail.substance.id - 1;
    }
  },

  watch: {
    "$route.params"() {
      this.setSubstanceData(this.$route.params.query, this.$route.params.type);
    },
  },

  mounted() {
    this.initGradesCallsStructureFlags();
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
