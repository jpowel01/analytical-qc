<template>
  <v-container fluid>
    <v-row class="ma-2">
      <div class="text-h4">Manage</div>
    </v-row>
    <v-tabs v-model="state.tab">
      <v-tab key="createList">Lists</v-tab>
    </v-tabs>
    <v-tabs-items v-model="state.tab">
      <v-tab-item key="createList">
        <v-form v-model="rules.valid" @submit="submit" class="ma-2">
          <v-row>
            <v-col cols="6">
              <v-combobox 
                label="Name" 
                hint="Select an existing list to add substances, or type a name to create a new list."
                persistent-hint
                v-model="input.name" 
                :items="state.lists" 
                :rules="rules.name" 
                required />
              <v-text-field label="Description" v-model="input.description" :rules="rules.description" />
              <v-radio-group v-model="input.createBy" row mandatory hide-details>
                <v-radio label="DTXSIDs" value="dtxsid" hide-details />
                <v-radio label="MW" value="mw" hide-details />
                <v-radio label="Annotation" value="annotation" hide-details />
              </v-radio-group>
              <v-textarea v-if="input.createBy === 'dtxsid'"
                label="DTXSIDs (any delimiter)"
                v-model="input.dtxsids"
                hide-details
              />
              <div v-if="input.createBy === 'mw'">
                <v-text-field label="Minimum" v-model.number="input.mwMin" hide-details type="number" />
                <v-text-field label="Maximum" v-model.number="input.mwMax" hide-details type="number" />
              </div>
              <div v-if="input.createBy === 'annotation'">
                <v-select label="T0" hide-details multiple chips
                  v-model="input.t0Grades"
                  :items="state.grades"
                  item-text="name"
                  item-value="id"
                />
                <v-select label="T4" hide-details multiple chips
                  v-model="input.t4Grades"
                  :items="state.grades"
                  item-text="name"
                  item-value="id"
                />
                <v-select label="Call" hide-details multiple chips
                  v-model="input.calls"
                  :items="state.calls"
                  item-text="name"
                  item-value="id"
                />
                <v-checkbox
                  v-model="input.hasText"
                  label="Has text annotation"
                  hide-details
                />
                <v-checkbox
                  v-model="input.hasNoText"
                  label="No text annotation"
                  hide-details
                />
              </div>
              <v-row class="mt-2">
                <v-col>
                  <v-btn class="my-2" color="primary" @click="submit" :disabled="!rules.valid || state.loading">
                    {{ listButton }}
                    <v-progress-circular
                      v-if="state.loading"
                      indeterminate
                      class="ml-1"
                      size="20"
                    />
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
    </v-tabs-items>
  </v-container>
</template>

<script>
import ListDataService from '../services/ListDataService';
import GradeDataService from '../services/GradeDataService';
import CallDataService from '../services/CallDataService';

export default {
  data() {
    return {
      input: {
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
        name: [
          v => !!v || 'List name is required',
          v => v.length <= 31 || 'Name must be less than 31 characters',
        ],
        description: [
          v => v.length <= 255 || 'Description must be less than 255 characters',
        ],
      }
    }
  },

  computed: {
    listButton() {
      if (!this.input.name || !this.state.lists.includes(this.input.name)) {
        return "Create";
      } else {
        return "Add";
      }
    }
  },

  methods: {
    submit() {
      this.state.message = "";
      this.state.loading = true;
      ListDataService.post(this.input)
        .then(() => {
          this.state.loading = false;
          this.state.lists.push(this.input.name);
          this.$emit("update");
        })
        .catch(() => {
          this.state.loading = false;
          this.state.message = "Error creating list";
        })
    },
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
