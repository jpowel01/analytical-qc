<template>
  <v-dialog 
    v-model="dialog"
    max-width="800px">
    <template v-slot:activator="{ on, attrs }">
      <v-icon class="ma-1" v-on="on" v-bind="attrs">mdi-plus-circle</v-icon>
    </template>
    <v-card>
      <v-card-title>Edit</v-card-title>
      <v-card-text>
        <v-container>
          <v-row>
            <v-col>
              <v-autocomplete
                v-model="t0Grade"
                :items="grades"
                :hint="`${t0Grade.description}`"
                item-text="name"
                item-value="name"
                label="T0"
                return-object
                persistent-hint>
                <template v-slot:item="data">
                  <template>
                    <v-list-item-content>
                      <v-list-item-title v-html="data.item.name"></v-list-item-title>
                      <v-list-item-subtitle v-html="data.item.description"></v-list-item-subtitle>
                    </v-list-item-content>
                  </template>
                </template>
              </v-autocomplete>
            </v-col>
            <v-col>
              <v-autocomplete
                v-model="t4Grade"
                :items="grades"
                :hint="`${t4Grade.description}`"
                item-text="name"
                item-value="name"
                label="T4"
                return-object
                persistent-hint>
                <template v-slot:item="data">
                  <template>
                    <v-list-item-content>
                      <v-list-item-title v-html="data.item.name"></v-list-item-title>
                      <v-list-item-subtitle v-html="data.item.description"></v-list-item-subtitle>
                    </v-list-item-content>
                  </template>
                </template>
              </v-autocomplete>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  props: ["grades", "calls", "existingT0", "existingT4", "existingCall", "gradeService", "callService"],

  data() {
    return {
      dialog: null,
      t0Grade: this.existingT0.grade,
      t4Grade: this.existingT4.grade,
      call: this.existingCall,
    };
  },
};
</script>
