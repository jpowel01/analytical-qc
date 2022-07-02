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
        <v-icon>{{ pred > threshold ? 'mdi-check' : 'mdi-close' }}</v-icon>
      </v-chip>
    </template>
    <span>{{ "Prediction: " + pred.toPrecision(3) }}</span>
  </v-tooltip>
</template>

<script>
export default {
  props: ["pred", "threshold", "true"],

  computed: {
    color() {
      if (this.pred > this.threshold) {
        return "green";
      } else {
        return "red";
      }
    },

    tooltip() {
      if (this.pred > this.threshold) {
        return "Amenable";
      } else {
        return "Unamenable";
      }
    },
  },
};
</script>
