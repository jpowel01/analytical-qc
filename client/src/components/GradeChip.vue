<template>
  <v-tooltip top>
    <template v-slot:activator="{ on, attrs }">
      <v-chip
        :color="colorByGrade(grade)"
        :outlined="showValidation && !validated"
        :text-color="
          validated || !showValidation ? 'white' : colorByGrade(grade)
        "
        class="ma-1"
        v-bind="attrs"
        v-on="on"
      >
        <strong>{{ grade }}</strong>
      </v-chip>
    </template>
    <span v-if="showValidation && validated">Validated grade: </span>
    <span v-else-if="showValidation">Automatic grade: </span>
    <span>{{ description }}</span>
  </v-tooltip>
</template>

<script>
export default {
  props: [
    "grade",
    "description",
    "showValidation",
    "validated",
    "useTripodColors",
  ],

  methods: {
    colorByGrade(grade) {
      return this.useTripodColors
        ? this.colorByGradeTripod(grade)
        : this.colorByGradeDefault(grade);
    },

    colorByGradeDefault(grade) {
      switch (grade) {
        case "A":
          return "green";
        case "B":
          return "yellow darken-1";
        case "C":
          return "orange";
        case "D":
        case "Fc":
        case "Fns":
        case "F":
          return "red";
        case "Ac":
          return "green lighten-2";
        case "Bc":
          return "yellow lighten-1";
        case "Cc":
          return "orange lighten-2";
        case "I":
        case "M":
        case "Z":
          return "purple";
        case "U":
        case "W":
          return "grey";
        default:
          return "grey";
      }
    },

    colorByGradeTripod(grade) {
      switch (grade) {
        case "A":
        case "B":
        case "C":
          return "green";
        case "Ac":
        case "Bc":
        case "Cc":
        case "Fc":
        case "Fns":
        case "F":
          return "red";
        case "Z":
        case "D":
        case "W":
          return "yellow darken-1";
        case "I":
        case "M":
          return "blue";
        case "U":
          return "grey";
        default:
          return "grey";
      }
    },
  },
};
</script>
