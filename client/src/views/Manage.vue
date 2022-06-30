<template>
  <v-container fluid>
    <v-row class="ma-2">
      <div class="text-h4">Manage</div>
    </v-row>
    <v-tabs v-model="state.tab">
      <v-tab key="lists">Lists</v-tab>
      <v-tab key="files">Files</v-tab>
    </v-tabs>
    <v-tabs-items v-model="state.tab">
      <v-tab-item key="lists">
        <v-form v-model="rules.valid" @submit="submitList" class="ma-2">
          <v-row>
            <v-col cols="6">
              <v-combobox 
                label="Name" 
                hint="Select an existing list to add substances, or type a name to create a new list."
                persistent-hint
                v-model="listInput.name" 
                :items="state.lists" 
                :rules="rules.name" 
                required />
              <v-text-field label="Description" v-model="listInput.description" :rules="rules.description" />
              <v-radio-group v-model="listInput.createBy" row mandatory hide-details>
                <v-radio label="DTXSIDs" value="dtxsid" hide-details />
                <v-radio label="MW" value="mw" hide-details />
                <v-radio label="Annotation" value="annotation" hide-details />
              </v-radio-group>
              <v-textarea v-if="listInput.createBy === 'dtxsid'"
                label="DTXSIDs (any delimiter)"
                v-model="listInput.dtxsids"
                hide-details
              />
              <div v-if="listInput.createBy === 'mw'">
                <v-text-field label="Minimum" v-model.number="listInput.mwMin" hide-details type="number" />
                <v-text-field label="Maximum" v-model.number="listInput.mwMax" hide-details type="number" />
              </div>
              <div v-if="listInput.createBy === 'annotation'">
                <v-select label="T0" hide-details multiple chips
                  v-model="listInput.t0Grades"
                  :items="state.grades"
                  item-text="name"
                  item-value="id"
                />
                <v-select label="T4" hide-details multiple chips
                  v-model="listInput.t4Grades"
                  :items="state.grades"
                  item-text="name"
                  item-value="id"
                />
                <v-select label="Call" hide-details multiple chips
                  v-model="listInput.calls"
                  :items="state.calls"
                  item-text="name"
                  item-value="id"
                />
                <v-checkbox
                  v-model="listInput.hasText"
                  label="Has text annotation"
                  hide-details
                />
                <v-checkbox
                  v-model="listInput.hasNoText"
                  label="No text annotation"
                  hide-details
                />
              </div>
              <v-row class="mt-2">
                <v-col class="d-inline-flex col-auto">
                  <v-btn class="my-2" color="primary" @click="submitList" :disabled="!rules.valid || state.loading">
                    {{ listButton }}
                    <v-progress-circular
                      v-if="state.loading"
                      indeterminate
                      class="ml-1"
                      size="20"
                    />
                  </v-btn>
                </v-col>
                <v-col class="d-inline-flex col-auto">
                  <v-btn class="my-2" color="secondary" @click="hideList" :disabled="state.loading" v-if="state.lists.includes(listInput.name)">
                    Hide
                  </v-btn>
                </v-col>
              </v-row>
              <v-row class="mt-2" v-if="state.message">
                <v-col>
                  <v-alert type="error">{{ state.message }}</v-alert>
                </v-col>
              </v-row>
            </v-col>
          </v-row>
        </v-form>
      </v-tab-item>
      <v-tab-item key="files">
        <v-form @submit="submitFiles" v-model="rules.filesValid" class="ma-2">
          <v-row>
            <v-col cols="6">
              <v-combobox 
                label="Name" 
                hint="Select an existing list to add substances and files, type a name to create a new list, or leave blank."
                persistent-hint
                v-model="filesInput.name" 
                :items="state.lists" 
                :rules="rules.filesName" 
              />
              <v-text-field label="Description" v-model="filesInput.description" :rules="rules.description" required />
              <v-textarea
                hint="Enter DTXSID, file name (with extension), and notes as tab-delimited rows. Files must be uploaded to content-server/static for display."
                v-model="filesInput.files"
                persistent-hint
                label="Files"
                :rules="rules.files"
                required
              />
              <v-checkbox
                  v-model="filesInput.createSubstances"
                  label="Create new substances"
                  hide-details
                />
            </v-col>
          </v-row>
          <v-row class="mt-2">
            <v-col>
              <v-btn class="my-2" color="primary" @click="submitFiles" :disabled="!rules.filesValid || state.loading">
                Upload
                <v-progress-circular
                  v-if="state.loading"
                  indeterminate
                  class="ml-1"
                  size="20"
                />
              </v-btn>
            </v-col>
          </v-row>
        </v-form>
      </v-tab-item>
    </v-tabs-items>
  </v-container>
</template>

<script>
import ListDataService from '../services/ListDataService';
import GradeDataService from '../services/GradeDataService';
import CallDataService from '../services/CallDataService';
import SubstanceFileDataService from '../services/SubstanceFileDataService';

export default {
  data() {
    return {
      listInput: {
        name: "",
        description: "",
        createBy: "dtxsid",
        dtxsids: null,
        mwMin: null,
        mwMax: null,
        t0Grades: [],
        t4Grades: [],
        calls: [],
        hasText: true,
        hasNoText: true,
      },

      filesInput: {
        files: "",
        name: "",
        description: "",
        createSubstances: false,
      },

      state: {
        loading: false,
        tab: null,
        message: "",
        grades: [],
        calls: [],
        lists: [],
      },

      rules: {
        valid: true,
        filesValid: true,
        name: [
          v => !!v || 'List name is required.',
          v => v.length <= 31 || 'Name must be less than 31 characters.',
        ],
        filesName: [
          v => v.length <= 31 || 'Name must be less than 31 characters.',
          v => !this.state.lists.includes(v) || 'Name already in use.',
        ],
        description: [
          v => v.length <= 255 || 'Description must be less than 255 characters.',
        ],
        files: [
          v => !!v || 'Files cannot be empty.',
        ],
      }
    }
  },

  computed: {
    listButton() {
      if (!this.listInput.name || !this.state.lists.includes(this.listInput.name)) {
        return "Create";
      } else {
        return "Add";
      }
    }
  },

  methods: {
    submitList() {
      this.state.message = "";
      this.state.loading = true;
      ListDataService.post(this.listInput)
        .then(() => {
          this.state.loading = false;
          this.state.lists.push(this.listInput.name);
          this.$emit("update");
        })
        .catch(() => {
          this.state.loading = false;
          this.state.message = "Error creating list";
        })
    },

    hideList() {
      this.state.message = "";
      this.state.loading = true;
      ListDataService.delete(this.listInput.name)
        .then(() => {
          this.state.loading = false;
          this.$emit("update");
        })
        .catch(() => {
          this.state.loading = false;
          this.state.message = "Error creating list";
        })
    },

    submitFiles() {
      this.state.message = "";
      this.state.loading = true;
      SubstanceFileDataService.post(this.filesInput)
        .then(() => {
          this.state.loading = false;
          this.state.lists.push(this.filesInput.name);
          this.$emit("update");
        })
        .catch(() => {
          this.state.loading = false;
          this.state.message = "Error uploading files";
        })
    }
  },

  mounted() {
    GradeDataService.getAll()
      .then((response) => {
        this.state.grades = response.data;
        this.state.grades.push({ name: 'None', id: 0 })
      })
    CallDataService.getAll()
      .then((response) => {
        this.state.calls = response.data;
        this.state.calls.push({ name: 'None', id: 0 })
      })
    ListDataService.getAll()
      .then((response) => {
        response.data.forEach((resp) => {
          this.state.lists.push(resp.name);
        })
      })
  }
};
</script>
