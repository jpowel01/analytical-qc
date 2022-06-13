<template>
  <v-expansion-panel>
    <v-expansion-panel-header class="text-h6">
        <v-row align="center">
            <div class="ma-2" v-if="sample.tox21Id">Tox21_{{ sample.tox21Id }}</div>
            <div class="ma-2" v-else-if="sample.ncgcId">{{ sample.ncgcId }}</div>
            <div class="ma-2" v-else-if="sample.bottleBarcode">{{ sample.bottleBarcode }}</div>
            <div class="ma-2" v-else>No sample number</div>
            <div class="mx-2" v-if="t0Grade || t4Grade || call">
            <div v-if="t0Grade" class="mx-1 d-inline-flex align-center">
                <span class="text-body-1 text--secondary">T0: </span>
                <EditableChip
                    :data="t0Grade"
                    type="grade"
                    :use-tripod-colors="useTripodColors"
                    :service="SampleGradeDataService"
                    @deleted="retrieveSampleGrades(sample.id)"
                    />
            </div>
            <div v-if="t4Grade" class="mx-1 d-inline-flex align-center">
                <span class="text-body-1 text--secondary">T4: </span>
                <EditableChip
                    :data="t4Grade"
                    type="grade"
                    :use-tripod-colors="useTripodColors"
                    :service="SampleGradeDataService"
                    @deleted="retrieveSampleGrades(sample.id)"
                    />
            </div>
            <div v-if="call" class="mx-1 d-inline-flex align-center">
                <span class="text-body-1 text--secondary">Call: </span>
                <EditableChip
                    :data="call"
                    type="call"
                    :use-tripod-colors="useTripodColors"
                    :service="SampleCallDataService"
                    @deleted="retrieveSampleCall(sample.id)"
                    />
            </div>
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
        <template v-slot:item.experiment.experimentDate="{ item }">
          {{ item.experiment.experimentDate | formatDate }}
        </template>

        <template v-slot:item.experiment.file.fileDate="{ item }">
          {{ item.experiment.file.fileDate | formatDate }}
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
          ><strong>Tox21 ID:</strong> Tox21_{{ sample.tox21Id }}</span
        >
        <span class="mx-1" v-if="sample.ncgcId"
          ><strong>NCGC ID:</strong> {{ sample.ncgcId }}</span
        >
        <span class="mx-1" v-if="sample.bottleBarcode"
          ><strong>EPA Bottle Barcode:</strong> {{ sample.bottleBarcode }}</span
        >
        <span class="mx-1" v-if="sample.pubchemSid"
          ><strong>PubChem SID:</strong>
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
import SampleDataService from "../services/SampleDataService";
import SampleGradeDataService from "../services/SampleGradeDataService";
import SampleCallDataService from "../services/SampleCallDataService";
import { PUBCHEM_SID_URL } from "@/main";

export default {
    props: ['sample', 'experiments', 'useTripodColors'],

    components: {
        ExperimentGradeChip,
        EditableChip,
    },

  computed: {
    PUBCHEM_SID_URL() {
      return PUBCHEM_SID_URL;
    },

    SampleGradeDataService() {
      return SampleGradeDataService;
    },

    SampleCallDataService() {
      return SampleCallDataService;
    }
  },

  data() {
    return {
        t0Grade: null,
        t4Grade: null,
        call: null,

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
    retrieveSampleGrades(id) {
      SampleDataService.getGrades(id)
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

    retrieveSampleCall(id) {
      SampleDataService.getCall(id)
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
      this.retrieveSampleGrades(id);
      this.retrieveSampleCall(id);
    },
  },

  mounted() {
    this.refresh(this.sample.id);
  }
};
</script>
