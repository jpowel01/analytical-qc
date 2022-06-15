<template>
  <v-dialog v-model="dialog" max-width="800px">
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        class="ma-1"
        dark
        color="secondary"
        v-on="on"
        v-bind="attrs"
      >
        <v-icon color="white">mdi-pencil</v-icon>
      </v-btn>
    </template>
    <v-card>
      <v-card-title>Edit</v-card-title>
      <v-card-text>
        <v-container>
          <v-row>
            <v-col>
              <v-select
                v-model="gradeT0"
                :items="grades"
                item-text="name"
                item-value="name"
                label="T0"
                return-object
              >
                <template v-slot:item="{ item }">
                  <template>
                    <v-list-item-content>
                      <v-list-item-title>{{ item.name }}</v-list-item-title>
                      <v-list-item-subtitle>{{ item.description }}</v-list-item-subtitle>
                    </v-list-item-content>
                  </template>
                </template>
              </v-select>
            </v-col>
            <v-col>
              <v-select
                v-model="gradeT4"
                :items="grades"
                item-text="name"
                item-value="name"
                label="T4"
              >
                <template v-slot:item="{ item }">
                  <template>
                    <v-list-item-content>
                      <v-list-item-title>{{ item.name }}</v-list-item-title>
                      <v-list-item-subtitle>{{ item.description }}</v-list-item-subtitle>
                    </v-list-item-content>
                  </template>
                </template>
              </v-select>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  props: [
    "grades",
    "calls",
    "existingGradeT0",
    "existingGradeT4",
    "existingCall",
  ],

  data() {
    return {
      dialog: null,
      gradeT0: this.existingGradeT0.grade.name,
      gradeT4: this.existingGradeT4.grade.name,
      call: this.existingCall.call.name,
    };
  },
};
</script>
