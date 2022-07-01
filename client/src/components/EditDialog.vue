<template>
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
            <v-col cols="8">
              <v-select
                v-model="edited.mappedGradeT0.grade"
                :items="grades"
                item-text="name"
                item-value="name"
                label="T0"
                return-object
                :hint="
                  edited.mappedGradeT0.grade.description
                    ? `${edited.mappedGradeT0.grade.description}`
                    : 'None'
                "
                persistent-hint
                clearable
                @change="edited.mappedGradeT0.validated = true"
              >
                <template v-slot:item="{ item }">
                  <template>
                    <v-list-item-content>
                      <v-list-item-title>{{ item.name }}</v-list-item-title>
                      <v-list-item-subtitle>{{
                        item.description
                      }}</v-list-item-subtitle>
                    </v-list-item-content>
                  </template>
                </template>
              </v-select>
            </v-col>
            <v-col cols="4">
              <v-switch
                v-model="edited.mappedGradeT0.validated"
                :disabled="!edited.mappedGradeT0.grade.name"
                :label="
                  edited.mappedGradeT0.validated ? 'Validated' : 'Provisional'
                "
              />
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="8">
              <v-select
                v-model="edited.mappedGradeT4.grade"
                :items="grades"
                item-text="name"
                item-value="name"
                label="T4"
                return-object
                :hint="
                  edited.mappedGradeT4.grade.description
                    ? `${edited.mappedGradeT4.grade.description}`
                    : 'None'
                "
                persistent-hint
                clearable
                @change="edited.mappedGradeT4.validated = true"
              >
                <template v-slot:item="{ item }">
                  <template>
                    <v-list-item-content>
                      <v-list-item-title>{{ item.name }}</v-list-item-title>
                      <v-list-item-subtitle>{{
                        item.description
                      }}</v-list-item-subtitle>
                    </v-list-item-content>
                  </template>
                </template>
              </v-select>
            </v-col>
            <v-col cols="4">
              <v-switch
                v-model="edited.mappedGradeT4.validated"
                :disabled="!edited.mappedGradeT4.grade.name"
                :label="
                  edited.mappedGradeT4.validated ? 'Validated' : 'Provisional'
                "
              />
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="8">
              <v-select
                v-model="edited.mappedCall.call"
                :items="calls"
                item-text="name"
                item-value="name"
                label="Call"
                return-object
                :hint="
                  edited.mappedCall.call.description
                    ? `${edited.mappedCall.call.description}`
                    : 'None'
                "
                persistent-hint
                clearable
                @change="edited.mappedCall.validated = true"
              >
                <template v-slot:item="{ item }">
                  <template>
                    <v-list-item-content>
                      <v-list-item-title>{{ item.name }}</v-list-item-title>
                      <v-list-item-subtitle>{{
                        item.description
                      }}</v-list-item-subtitle>
                    </v-list-item-content>
                  </template>
                </template>
              </v-select>
            </v-col>
            <v-col cols="4">
              <v-switch
                v-model="edited.mappedCall.validated"
                :disabled="!edited.mappedCall.call.name"
                :label="
                  edited.mappedCall.validated ? 'Validated' : 'Provisional'
                "
              />
            </v-col>
          </v-row>
          <v-row v-if="this.showFlags">
            <v-col cols="8">
              <v-select
                v-model="edited.substanceFlags"
                :items="flagObjects"
                item-text="flag.name"
                item-value="flag.name"
                label="Flags"
                return-object
                clearable
                multiple
                chips
              >
                <template v-slot:item="{ item }">
                  <template>
                    <v-list-item-content>
                      <v-list-item-title>{{ item.flag.name }}</v-list-item-title>
                      <v-list-item-subtitle>{{
                        item.flag.description
                      }}</v-list-item-subtitle>
                    </v-list-item-content>
                  </template>
                </template>
              </v-select>
            </v-col>
          </v-row>
          <v-row>
            <v-col>
              <v-textarea
                label="Annotation"
                v-model="edited.annotation.annotation"
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
        <v-btn class="ma-1" dark color="primary" @click="save"> Save </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  props: ["grades", "calls", "flags", "editable", "showFlags"],

  emits: ["edited"],

  data() {
    return {
      dialog: null,
      edited: {
        substanceFlags: [],
        mappedGradeT0: {
          grade: {
            name: "",
            description: "",
          },
          t0_t4: false,
          validated: false,
        },
        mappedGradeT4: {
          grade: {
            name: "",
            description: "",
          },
          t0_t4: true,
          validated: false,
        },
        mappedCall: {
          call: {
            name: "",
            description: "",
          },
          validated: false,
        },
        annotation: {
          annotation: "",
        },
      },
    };
  },

  computed: {
    flagObjects() {
      var flagObjects = [];
      this.flags.forEach((f) => {
        flagObjects.push({ flag: f, validated: true })
      })
      return flagObjects;
    }
  },

  methods: {
    save() {
      this.dialog = false;
      this.$emit("edited", this.edited);
    },

    clearData() {
      this.clearFlags();
      this.clearGradeT0();
      this.clearGradeT4();
      this.clearCall();
      this.clearAnnotation();
    },

    clearFlags() {
      this.edited.substanceFlags = [];
    },

    clearGradeT0() {
      this.edited.mappedGradeT0 = {
        grade: {
          name: "",
          description: "",
        },
        t0_t4: false,
        validated: false,
      };
    },

    clearGradeT4() {
      this.edited.mappedGradeT4 = {
        grade: {
          name: "",
          description: "",
        },
        t0_t4: true,
        validated: false,
      };
    },

    clearCall() {
      this.edited.mappedCall = {
        call: {
          name: "",
          description: "",
        },
        validated: false,
      };
    },

    clearAnnotation() {
      this.edited.annotation = {
        annotation: "",
      };
    },

    setDataFromProps() {
      if (this.editable.substanceFlags) {
        this.edited.substanceFlags = JSON.parse(
          JSON.stringify(this.editable.substanceFlags)
        );
      }

      if (this.editable.mappedGradeT0) {
        this.edited.mappedGradeT0 = JSON.parse(
          JSON.stringify(this.editable.mappedGradeT0)
        );
      }

      if (this.editable.mappedGradeT4) {
        this.edited.mappedGradeT4 = JSON.parse(
          JSON.stringify(this.editable.mappedGradeT4)
        );
      }

      if (this.editable.mappedCall) {
        this.edited.mappedCall = JSON.parse(
          JSON.stringify(this.editable.mappedCall)
        );
      }

      if (this.editable.annotation) {
        this.edited.annotation = JSON.parse(
          JSON.stringify(this.editable.annotation)
        );
      }
    },
  },

  watch: {
    editable: {
      deep: true,
      immediate: true,
      handler() {
        this.clearData();
        this.setDataFromProps();
      },
    },

    dialog() {
      this.clearData();
      this.setDataFromProps();
    },

    "edited.mappedGradeT0.grade"() {
      if (this.edited.mappedGradeT0.grade == null) {
        this.clearGradeT0();
      }
    },

    "edited.mappedGradeT4.grade"() {
      if (this.edited.mappedGradeT4.grade == null) {
        this.clearGradeT4();
      }
    },

    "edited.mappedCall.call"() {
      if (this.edited.mappedCall.call == null) {
        this.clearCall();
      }
    },
  },
};
</script>
