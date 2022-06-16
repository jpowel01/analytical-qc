<template>
  <v-expansion-panel>
    <v-expansion-panel-header class="text-h6">
      <v-row align="center">
        <div v-if="sampleAnnotation" class="mx-1 d-inline-flex align-center">
          <AnnotationChip
            :annotation="sampleAnnotation.annotation"
          />
        </div>
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
        <v-spacer></v-spacer>
        <div
          class="mx-2"
          v-if="
            (sampleGradeT0 || sampleGradeT4 || sampleCall) && !sample.withdrawn
          "
        >
          <div v-if="sampleGradeT0" class="mx-1 d-inline-flex align-center">
            <EditableChip
              :data="sampleGradeT0"
              type="grade"
              title="T0"
              :use-tripod-colors="useTripodColors"
              @validated="onValidate(sampleGradeT0, SampleGradeDataService)"
              @deleted="onDelete(sampleGradeT0, SampleGradeDataService)"
            />
          </div>
          <div v-if="sampleGradeT4" class="mx-1 d-inline-flex align-center">
            <EditableChip
              :data="sampleGradeT4"
              type="grade"
              title="T4"
              :use-tripod-colors="useTripodColors"
              @validated="onValidate(sampleGradeT4, SampleGradeDataService)"
              @deleted="onDelete(sampleGradeT4, SampleGradeDataService)"
            />
          </div>
          <div v-if="sampleCall" class="mx-1 d-inline-flex align-center">
            <EditableChip
              :data="sampleCall"
              type="call"
              title="Call"
              @validated="onValidate(sampleCall, SampleCallDataService)"
              @deleted="onDelete(sampleCall, SampleCallDataService)"
            />
          </div>
        </div>
        <div class="mx-2" v-else-if="sample.withdrawn">
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
import SampleGradeDataService from "../services/SampleGradeDataService";
import SampleCallDataService from "../services/SampleCallDataService";
import SampleAnnotationDataService from "../services/SampleAnnotationDataService";
import { PUBCHEM_SID_URL, CONTENT_SERVER_URL } from "@/main";

export default {
  props: ["sample", "experiments", "useTripodColors", "showSpectrusFiles"],

  components: {
    ExperimentGradeChip,
    EditableChip,
    AnnotationChip,
  },

  computed: {
    PUBCHEM_SID_URL() {
      return PUBCHEM_SID_URL;
    },

    CONTENT_SERVER_URL() {
      return CONTENT_SERVER_URL;
    },

    SampleGradeDataService() {
      return SampleGradeDataService;
    },

    SampleCallDataService() {
      return SampleCallDataService;
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
      sampleGradeT0: null,
      sampleGradeT4: null,
      sampleCall: null,
      sampleAnnotation: null,

      experimentHeaders: [
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
    async onValidate(data, service) {
      data.validated = true;
      data = await service.put(data.id, data);
    },

    async onDelete(data, service) {
      data = await service.delete(data.id);
      this.refresh(this.sample.id);
    },

    async retrieveSampleGrades(id) {
      this.sampleGradeT0 = null;
      this.sampleGradeT4 = null;
      let response = await SampleGradeDataService.getBySampleId(id);
      if (response) {
        response.data.forEach((resp) => {
          if (resp.t0_t4) {
            this.sampleGradeT4 = resp;
          } else {
            this.sampleGradeT0 = resp;
          }
        });
      }
    },

    async retrieveSampleCall(id) {
      this.sampleCall = null;
      let response = await SampleCallDataService.getBySampleId(id);
      if (response) {
        this.sampleCall = response.data;
      }
    },

    async retrieveSampleAnnotation(id) {
      this.sampleAnnotation = null;
      let response = await SampleAnnotationDataService.getBySampleId(id);
      if (response) {
        this.sampleAnnotation = response.data;
      }
    },

    refresh(id) {
      this.retrieveSampleGrades(id);
      this.retrieveSampleCall(id);
      this.retrieveSampleAnnotation(id);
    },
  },

  mounted() {
    this.refresh(this.sample.id);
  },
};
</script>
