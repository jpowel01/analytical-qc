<template>
  <v-tooltip top>
    <template v-slot:activator="{ on, attrs }">
      <v-chip
        :color="colorByCall(call)"
        :outlined="!validated"
        :text-color="
          validated ? 'white' : colorByCall(call)
        "
        class="ma-1"
        v-bind="attrs"
        v-on="on"
      >
        <strong>{{ call }}</strong>
      </v-chip>
    </template>
    <span v-if="validated">Validated call: </span>
    <span v-else>Automatic call: </span>
    <span>{{ description }}</span>
  </v-tooltip>
</template>

<script>
export default {
  props: [
    "call",
    "description",
    "validated",
  ],

  methods: {
    colorByCall(call) {
      switch (call) {
        case "S":
          return "green";
        case "L":
        case "T":
          return "red";
        case "X":
          return "grey";
        default:
          return "grey";
      }
    },
  },
};
</script>
