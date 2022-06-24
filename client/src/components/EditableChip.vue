<template>
  <v-tooltip top>
    <template v-slot:activator="{ on, attrs }">
      <span v-if="title" class="text-body-1 text--secondary">{{ title }} </span>
      <v-chip
        :color="color"
        :outlined="!data.validated"
        :text-color="data.validated ? 'white' : color"
        class="ma-1"
        v-bind="attrs"
        v-on="on"
      >
        <strong>{{ name }}</strong>
      </v-chip>
    </template>
    <span v-if="data.validated">Validated: </span>
    <span v-else>Provisional: </span>
    <span>{{ description }}</span>
  </v-tooltip>
</template>

<script>
import ColorByName from "../services/ColorByName";

export default {
  props: ["data", "type", "useTripodColors", "title"],

  data() {
    return {
      name: "",
      description: "",
    };
  },

  computed: {
    color() {
      return ColorByName.colorByName(this.name, this.useTripodColors);
    },
  },

  methods: {
    parse() {
      if (this.type === "grade") {
        this.name = this.data.grade.name;
        this.description = this.data.grade.description;
      } else if (this.type === "call") {
        this.name = this.data.call.name;
        this.description = this.data.call.description;
      } else if (this.type === "flag") {
        this.name = this.data.flag.name;
        this.description = this.data.flag.description;
      }
    },
  },

  mounted() {
    this.parse();
  },
};
</script>
