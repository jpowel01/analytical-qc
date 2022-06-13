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
import ColorByName from "../services/ColorByName"

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

  methods: {
    recolor() {
      this.color = ColorByName.colorByName(this.grade.name, this.useTripodColors);
    },
  },

  mounted() {
    this.recolor();
  },

  watch: {
    useTripodColors() {
      this.recolor();
    }
  }
};
</script>
