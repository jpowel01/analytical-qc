<template>
  <v-expansion-panel>
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
            :editable="editable"
            @edited="save"
          />
          <div
            v-if="editable.annotation"
            class="mx-1 d-inline-flex align-center"
          >
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
              :use-tripod-colors="useTripodColors"
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
              :use-tripod-colors="useTripodColors"
            />
          </div>
          <div
            v-if="editable.mappedCall"
            class="mx-1 d-inline-flex align-center"
          >
            <EditableChip
              :data="editable.mappedCall"
              type="call"
              title="Call"
            />
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
      >
        <template v-slot:item.experiment.id="{ item }">
          <ExperimentAnnotationChip :experiment="item.experiment" />
        </template>
        
        <template v-slot:item.experiment.experimentDate="{ value }">
          {{ value | formatDate }}
        </template>

        <template v-slot:item.experiment.file.fileDate="{ value }">
          {{ value | formatDate }}
        </template>

        <template v-slot:item.experiment.file.fileName="{ value }">
          <span v-if="value">
            <a
              target="_blank"
              rel="noreferrer noopener"
              :href="`${CONTENT_SERVER_URL}/${value}`"
              >{{ value }}</a
            ><v-icon x-small class="ml-1">mdi-open-in-new</v-icon>
          </span>
        </template>

        <template v-slot:item.experiment.grade="{ item }">
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
</template>

<script>
import ExperimentGradeChip from "./ExperimentGradeChip";
import EditableChip from "./EditableChip";
import AnnotationChip from "./AnnotationChip";
import EditDialog from "./EditDialog";
import SampleGradeDataService from "../services/SampleGradeDataService";
import SampleCallDataService from "../services/SampleCallDataService";
import SampleAnnotationDataService from "../services/SampleAnnotationDataService";
import ExperimentAnnotationChip from "../components/ExperimentAnnotationChip";
import { PUBCHEM_SID_URL, CONTENT_SERVER_URL } from "@/main";

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
    EditableChip,
    AnnotationChip,
    ExperimentAnnotationChip
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
  },

  data() {
    return {
      editable: {
        mappedGradeT0: null,
        mappedGradeT4: null,
        mappedCall: null,
        annotation: null,
      },

      experimentHeaders: [
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
      ],
    };
  },

  methods: {
    setSampleGrades(id) {
      this.editable.mappedGradeT0 = null;
      this.editable.mappedGradeT4 = null;
      SampleGradeDataService.getBySampleId(id)
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

    setSampleCall(id) {
      this.editable.mappedCall = null;
      SampleCallDataService.getBySampleId(id)
        .then((response) => {
          this.editable.mappedCall = response.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    setSampleAnnotation(id) {
      this.editable.annotation = null;
      SampleAnnotationDataService.getBySampleId(id)
        .then((response) => {
          this.editable.annotation = response.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    setEditable(id) {
      this.setSampleGrades(id);
      this.setSampleCall(id);
      this.setSampleAnnotation(id);
    },

    save(edited) {
      this.saveEdited(edited)
        .then(() => {
          this.setEditable(this.sample.id);
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
        editedMappedGrade.sample = this.sample;
        savedMappedGrade = SampleGradeDataService.post(editedMappedGrade);
      } else if (editableMappedGrade) {
        if (!editedMappedGrade.grade.name) {
          savedMappedGrade = SampleGradeDataService.delete(
            editableMappedGrade.id
          );
        } else if (
          editableMappedGrade.grade.name !== editedMappedGrade.grade.name ||
          editableMappedGrade.validated !== editedMappedGrade.validated
        ) {
          savedMappedGrade = SampleGradeDataService.put(
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
        editedMappedCall.sample = this.sample;
        savedMappedCall = SampleCallDataService.post(editedMappedCall);
      } else if (editableMappedCall) {
        if (!editedMappedCall.call.name) {
          savedMappedCall = SampleCallDataService.delete(editableMappedCall.id);
        } else if (
          editableMappedCall.call.name !== editedMappedCall.call.name ||
          editableMappedCall.validated !== editedMappedCall.validated
        ) {
          savedMappedCall = SampleCallDataService.put(
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
        editedAnnotation.sample = this.sample;
        savedAnnotation = SampleAnnotationDataService.post(editedAnnotation);
      } else if (editableAnnotation && editedAnnotation.annotation) {
        savedAnnotation = SampleAnnotationDataService.put(
          editableAnnotation.id,
          editedAnnotation
        );
      } else if (editableAnnotation) {
        savedAnnotation = SampleAnnotationDataService.delete(
          editableAnnotation.id
        );
      }

      return Promise.resolve(savedAnnotation);
    },
  },

  mounted() {
    this.setEditable(this.sample.id);
  },
};
</script>
