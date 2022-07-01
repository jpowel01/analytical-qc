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
            <v-col>
              <v-select
                v-model="edited.annotation.t0Grade"
                :items="grades"
                item-text="name"
                item-value="name"
                label="T0"
                return-object
                :hint="
                  edited.annotation.t0Grade
                    ? `${edited.annotation.t0Grade.description}`
                    : 'None'
                "
                persistent-hint
                clearable
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
          </v-row>
          <v-row>
            <v-col>
              <v-select
                v-model="edited.annotation.t4Grade"
                :items="grades"
                item-text="name"
                item-value="name"
                label="T4"
                return-object
                :hint="
                  edited.annotation.t4Grade
                    ? `${edited.annotation.t4Grade.description}`
                    : 'None'
                "
                persistent-hint
                clearable
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
          </v-row>
          <v-row>
            <v-col>
              <v-select
                v-model="edited.annotation.call"
                :items="calls"
                item-text="name"
                item-value="name"
                label="Call"
                return-object
                :hint="
                  edited.annotation.call
                    ? `${edited.annotation.call.description}`
                    : 'None'
                "
                persistent-hint
                clearable
                @change="edited.annotation.validated = true"
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
          </v-row>
          <v-row v-if="this.showStructureFlags">
            <v-col cols="8">
              <v-select
                v-model="edited.substanceStructureFlags"
                :items="structureFlagObjects"
                item-text="structureFlag.name"
                item-value="structureFlag.name"
                label="Structure Flags"
                return-object
                clearable
                multiple
                chips
              >
                <template v-slot:item="{ item }">
                  <template>
                    <v-list-item-content>
                      <v-list-item-title>{{ item.structureFlag.name }}</v-list-item-title>
                      <v-list-item-subtitle>{{
                        item.structureFlag.description
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
        <v-checkbox
          v-model="edited.annotation.validated"
          hide-details
          label="Validate"
          class="shrink mr-2 mt-0"
        />
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
  props: ["grades", "calls", "structureFlags", "annotation", "substanceStructureFlags", "showStructureFlags"],

  emits: ["edited"],

  data() {
    return {
      dialog: null,
      edited: {
        annotation: {
          validated: false,
        },
        substanceStructureFlags: [],
      },
    };
  },

  computed: {
    structureFlagObjects() {
      let structureFlagObjects = [];
      this.structureFlags.forEach((f) => {
        structureFlagObjects.push({ structureFlag: f })
      })
      return structureFlagObjects;
    }
  },

  methods: {
    save() {
      this.dialog = false;
      this.$emit("edited", this.edited);
    },

    setDataFromProps() {
      if (this.substanceStructureFlags) {
        this.edited.substanceStructureFlags = JSON.parse(
          JSON.stringify(this.substanceStructureFlags)
        );
      }

      if (this.annotation) {
        this.edited.annotation = JSON.parse(
          JSON.stringify(this.annotation)
        );
      }
    },
  },

  watch: {
    editable: {
      deep: true,
      immediate: true,
      handler() {
        this.setDataFromProps();
      },
    },

    dialog() {
      this.setDataFromProps();
    },
  },
};
</script>
