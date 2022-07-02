<template>
  <v-tooltip top :disabled="!data.description">
    <template v-slot:activator="{ on, attrs }">
      <span v-if="title" class="text-body-1 text--secondary">{{ title }} </span>
      <v-chip
        :color="color"
        :outlined="!validated"
        :text-color="validated ? 'white' : color"
        class="ma-1"
        v-bind="attrs"
        v-on="on"
      >
        <strong>{{ data.name ? data.name : data }}</strong>
      </v-chip>
    </template>
    <span v-if="validated">Validated: </span>
    <span v-else>Provisional: </span>
    <span>{{ data.description }}</span>
  </v-tooltip>
</template>

<script>
import ColorByName from "../services/ColorByName";

export default {
  props: ["data", "useTripodColors", "title", "validated"],

  computed: {
    color() {
      if (this.data.name) {
        return ColorByName.colorByName(this.data.name, this.useTripodColors);
      } else {
        return ColorByName.colorByName(this.data, this.useTripodColors);
      }
    },
  },
};
</script>
