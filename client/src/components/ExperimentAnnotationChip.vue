<template>
  <div style="white-space: nowrap">
    <div class="d-inline-flex ma-0">
      <v-dialog v-model="dialog" max-width="600">
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            class="ma-1 pa-2"
            dark
            color="secondary"
            v-on="on"
            v-bind="attrs"
            icon
          >
            <v-icon>mdi-pencil</v-icon>
          </v-btn>
        </template>
        <v-card>
          <v-card-title>Edit</v-card-title>
          <v-card-text>
            <v-container>
              <v-row>
                <v-col>
                  <v-textarea
                    label="Annotation"
                    v-model="editedAnnotation.annotation"
                    hide-details
                  />
                </v-col>
              </v-row>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-spacer />
            <v-btn class="ma-1" dark color="secondary" @click="dialog = false">
              Cancel
            </v-btn>
            <v-btn class="ma-1" dark color="primary" @click="save">
              Save
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
    <div
      class="d-inline-flex ma-0"
      v-if="editableAnnotation && editableAnnotation.annotation"
    >
      <v-menu bottom offset-y open-on-hover>
        <template v-slot:activator="{ on, attrs }">
          <v-chip class="ma-1" color="primary" v-bind="attrs" v-on="on">
            <v-icon small class="pa-0">mdi-text</v-icon>
          </v-chip>
        </template>
        <v-card
          max-width="600"
          v-if="editableAnnotation && editableAnnotation.annotation"
        >
          <v-card-text>{{ editableAnnotation.annotation }}</v-card-text>
        </v-card>
      </v-menu>
    </div>
  </div>
</template>

<script>
import ExperimentAnnotationDataService from "../services/ExperimentAnnotationDataService";

export default {
  props: ["experiment"],

  data() {
    return {
      dialog: false,
      editableAnnotation: {
        annotation: "",
      },
      editedAnnotation: {
        annotation: "",
      },
    };
  },

  methods: {
    save() {
      this.saveAnnotation(this.editableAnnotation, this.editedAnnotation)
        .then(() => {
          this.setAnnotation(this.experiment.id);
          this.dialog = false;
        })
        .catch((err) => {
          console.log(err);
          this.dialog = false;
        });
    },

    saveAnnotation(editableAnnotation, editedAnnotation) {
      let savedAnnotation = editableAnnotation;
      if (!editableAnnotation && editedAnnotation.annotation) {
        editedAnnotation.experiment = this.experiment;
        savedAnnotation =
          ExperimentAnnotationDataService.post(editedAnnotation);
      } else if (editableAnnotation && editedAnnotation.annotation) {
        savedAnnotation = ExperimentAnnotationDataService.put(
          editableAnnotation.id,
          editedAnnotation
        );
      } else if (editableAnnotation) {
        savedAnnotation = ExperimentAnnotationDataService.delete(
          editableAnnotation.id
        );
      }

      return Promise.resolve(savedAnnotation);
    },

    setAnnotation() {
      this.editableAnnotation = null;
      ExperimentAnnotationDataService.getByExperimentId(this.experiment.id)
        .then((resp) => {
          this.editableAnnotation = resp.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    setDataFromProps() {
      if (this.editableAnnotation) {
        this.editedAnnotation = JSON.parse(
          JSON.stringify(this.editableAnnotation)
        );
      }
    },

    clearAnnotation() {
      this.editedAnnotation = {
        annotation: "",
      };
    },
  },

  watch: {
    editableAnnotation: {
      deep: true,
      immediate: true,
      handler() {
        this.clearAnnotation();
        this.setDataFromProps();
      },
    },

    dialog() {
      this.clearAnnotation();
      this.setDataFromProps();
    },
  },

  created() {
    this.setAnnotation();
    this.setDataFromProps();
  },
};
</script>

<style>
v-btn {
  min-width: "0";
}
</style>
