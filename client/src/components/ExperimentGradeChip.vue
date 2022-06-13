<template>
  <v-tooltip top>
    <template v-slot:activator="{ on, attrs }">
      <v-chip
        :color="color"
        text-color="white"
        class="ma-1"
        v-bind="attrs"
        v-on="on"
      >
        <strong>{{ grade.name }}</strong>
      </v-chip>
    </template>
    <span>{{ grade.description }}</span>
  </v-tooltip>
</template>

<script>
import ColorByGrade from "../services/ColorByGrade"

export default {
  props: [
    "grade",
    "useTripodColors",
  ],

  data() {
    return {
      color: "",
    }
  },

  mounted() {
    this.color = ColorByGrade.colorByGrade(this.grade.name, this.useTripodColors);
  },

  watch: {
    useTripodColors() {
      this.color = ColorByGrade.colorByGrade(this.grade.name, this.useTripodColors);
    }
  }
};
</script>
