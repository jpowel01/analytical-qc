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
        <strong>{{ tooltip.toUpperCase() }}</strong>
      </v-chip>
    </template>
    <span>{{ "Prediction: " + prob.toPrecision(3) }}</span>
  </v-tooltip>
</template>

<script>
export default {
  props: ["prob", "threshold"],

  computed: {
    color() {
      if (this.prob > this.threshold) {
        return "green";
      } else {
        return "red";
      }
    },

    tooltip() {
      if (this.prob > this.threshold) {
        return "Amenable";
      } else {
        return "Unamenable";
      }
    },
  },
};
</script>
