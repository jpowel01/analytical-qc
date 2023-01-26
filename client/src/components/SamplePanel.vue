<template>
  <div class="FullTemplate">
  <v-expansion-panel expand>
    <v-expansion-panel-header class="text-h6">
      <v-row align="center">
        <div :class="titleClass" v-if="sample.tox21Id">
          Tox21_{{ sample.tox21Id }}
        </div>
        <div :class="titleClass" v-else-if="sample.ncgcId">
          {{ sample.ncgcId }}
        </div>
        <div :class="titleClass" v-else-if="sample.bottleBarcode">
          {{ sample.bottleBarcode }}
        </div>
        <div :class="titleClass" v-else>No sample number</div>
        <v-spacer />
        <div class="mx-2">
          <EditDialog
            :grades="grades"
            :calls="calls"
            :annotation="annotation"
            @edited="save"
          />
          <div class="ma-0 d-inline-flex" v-if="annotation">
            <div
              v-if="annotation.annotation"
              class="mx-1 d-inline-flex align-center"
            >
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
                :use-tripod-colors="useTripodColors"
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
                :use-tripod-colors="useTripodColors"
              />
            </div>
            <div v-if="annotation.call" class="mx-1 d-inline-flex align-center">
              <GradeCallChip
                :data="annotation.call"
                :validated="annotation.validated"
                title="Call"
              />
            </div>
          </div>
        </div>
        <div class="mx-2" v-if="sample.withdrawn">
          <ExperimentGradeChip
            :use-tripod-colors="useTripodColors"
            :grade="{ name: 'W', description: 'Sample Withdrawn' }"
          />
        </div>
      </v-row>
    </v-expansion-panel-header>
    <v-expansion-panel-content>
      <v-data-table
        :headers="experimentHeaders"
        :items="experiments"
        disable-pagination
        hide-default-footer
        sort-by="experiment.timepoint"
        :search="showSpectrusFiles ? '' : '.pdf'"
      >
        <template v-slot:[`item.experiment.id`]="{ item }">
          <ExperimentAnnotationChip :experiment="item.experiment" />
        </template>
 
        <template v-slot:[`item.experiment.study`]="{ value }">
          <span :class="changeStudyColor(value)">

            {{ value }}
          </span>
        </template>
        <!-- CR 11/15/22: need brackets on the object for vuetify tables or linting error -->
        <!-- https://stackoverflow.com/questions/73170422/vue-js-how-to-use-template-v-slot-on-props -->
        <template v-slot:[`item.experiment.experimentDate`]="{ value }">
          {{ value | formatDate }}
        </template>

        <template v-slot:[`item.experiment.file.fileDate`]="{ value }">
          {{ value | formatDate }}
        </template>

        <template v-slot:[`item.experiment.file.fileName`]="{ value }">
          <span v-if="value">
            <a
              target="_blank"
              rel="noreferrer noopener"
              :href="`${CONTENT_SERVER_URL}/${value}`"
              >{{ value }}</a
            ><v-icon x-small class="ml-1">mdi-open-in-new</v-icon>
          </span>
        </template>
        <!-- need ` after brackets if it's the item -->
        <!-- see: https://vuetifyjs.com/en/components/data-tables/#expandable-rows -->
        <template v-slot:[`item.experiment.grade`]="{item}">
          <ExperimentGradeChip
            v-for="grade in item.grades"
            :key="grade.id"
            :grade="grade"
            :use-tripod-colors="useTripodColors"
          />
        </template>
      </v-data-table>
      <div class="text-caption text--secondary my-2">
        <span class="mx-1" v-if="sample.tox21Id"
          ><strong>Tox21 ID:</strong> Tox21_{{ sample.tox21Id }} ({{
            library
          }})</span
        >
        <span class="mx-1" v-if="sample.ncgcId"
          ><strong>NCGC ID:</strong> {{ sample.ncgcId }}</span
        >
        <span class="mx-1" v-if="sample.bottleBarcode"
          ><strong>EPA Bottle Barcode:</strong> {{ sample.bottleBarcode }}</span
        >
        <span class="mx-1" v-if="sample.pubchemSid"
          ><strong>PubChem SID: </strong>
          <a
            target="_blank"
            rel="noreferrer noopener"
            :href="`${PUBCHEM_SID_URL}/${sample.pubchemSid}`"
            >{{ sample.pubchemSid }}</a
          ><v-icon x-small class="ml-1">mdi-open-in-new</v-icon>
        </span>
      </div>
    </v-expansion-panel-content>
  </v-expansion-panel>
  </div>
</template>



<script>
import ExperimentGradeChip from "./ExperimentGradeChip";
import GradeCallChip from "./GradeCallChip";
import AnnotationChip from "./AnnotationChip";
import EditDialog from "./EditDialog";
import SampleAnnotationDataService from "../services/SampleAnnotationDataService";
import ExperimentAnnotationChip from "../components/ExperimentAnnotationChip";
import { PUBCHEM_SID_URL, CONTENT_SERVER_URL } from "@/store";

export default {
  props: [
    "sample",
    "experiments",
    "useTripodColors",
    "showSpectrusFiles",
    "grades",
    "calls",
  ],

  components: {
    EditDialog,
    ExperimentGradeChip,
    GradeCallChip,
    AnnotationChip,
    ExperimentAnnotationChip,
  },

  computed: {
    PUBCHEM_SID_URL() {
      return PUBCHEM_SID_URL;
    },

    CONTENT_SERVER_URL() {
      return CONTENT_SERVER_URL;
    },

    titleClass() {
      return this.sample.withdrawn
        ? "ma-2 text-decoration-line-through"
        : "ma-2";
    },

    library() {
      const digit = Number(String(this.sample.tox21Id).charAt(0));
      switch (digit) {
        case 1:
          return "NCATS";
        case 2:
          return "NTP";
        case 3:
          return "EPA";
        case 4:
          return "replicate";
      }
      return null;
    },

    experimentHeaders() {
      return [
        {
          text: "",
          value: "experiment.id",
          sortable: false,
        },
        {
          text: "File Date",
          value: "experiment.file.fileDate",
          sortable: true,
        },
        {
          text: "Experiment Date",
          value: "experiment.experimentDate",
          sortable: true,
        },
        {
          text: "File Name",
          value: "experiment.file.fileName",
          sortable: true,
        },
        { text: "Study", value: "experiment.study", sortable: true },
        { text: "Timepoint", value: "experiment.timepoint", sortable: true },
        { text: "Batch", value: "experiment.batch", sortable: true },
        { text: "Well", value: "experiment.well", sortable: true },
        { text: "Grade", value: "experiment.grade", sortable: true },
        { text: "Purity", value: "experiment.purity", sortable: true },
        { text: "Pass/Fail", value: "experiment.passFail", sortable: true },
        { text: "Comment", value: "experiment.comment", sortable: true },
      ];
    },
  },

  data() {
    return {
      annotation: {}
    };
  },

  methods: {
    setSampleAnnotation(id) {
      this.annotation = null;
      SampleAnnotationDataService.getBySampleId(id)
        .then((response) => {
          this.annotation = response.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    changeStudyColor(study) {
      console.log("study is: ", study);
      try {
        if (study === "NIST GCMS") {
          return "red--text";
        } else if (study === "") {
          return "orange--text"
        
        } else if (study === "OPANS LCMS") {
          return "green--text";
        } else {
          return "blue--text";
        }
      } catch (error) {
        return "black--text";
      }
    },

    save(edited) {
      this.saveAnnotation(edited.annotation)
        .then(() => {
          this.setSampleAnnotation(this.sample.id);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    filterOutSpectrusEvotec (value, search, item) {
        return value != null &&
          search != null &&
          typeof value === 'string' &&
          value.toString().toLocaleUpperCase().indexOf(search) !== -1

      },


    saveAnnotation(editedAnnotation) {
      let savedAnnotation = this.annotation;
      if (!this.annotation && editedAnnotation) {
        editedAnnotation.sample = this.sample;
        savedAnnotation = SampleAnnotationDataService.post(editedAnnotation);
      } else if (editedAnnotation) {
        savedAnnotation = SampleAnnotationDataService.put(
          this.annotation.id,
          editedAnnotation
        );
      }

      return Promise.resolve(savedAnnotation);
    },
  },


  mounted() {
    this.setSampleAnnotation(this.sample.id);
    console.log("this.experiments:", this.experiments);
  }
}
</script>
