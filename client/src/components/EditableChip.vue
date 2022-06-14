<template>
  <v-menu bottom offset-y v-if="data">
    <template v-slot:activator="{ on: menu, attrs }">
      <v-tooltip top>
        <template v-slot:activator="{ on: tooltip }">
          <span v-if="title" class="text-body-1 text--secondary"
            >{{ title }}:
          </span>
          <v-chip
            :color="color"
            :outlined="!data.validated"
            :text-color="data.validated ? 'white' : color"
            class="ma-1"
            v-bind="attrs"
            v-on="{ ...tooltip, ...menu }"
          >
            <strong>{{ name }}</strong>
          </v-chip>
        </template>
        <span v-if="data.validated">Validated: </span>
        <span v-else>Automatic: </span>
        <span>{{ description }}</span>
      </v-tooltip>
    </template>
    <v-list>
      <v-list-item v-if="!data.validated" @click="$emit('validated')">
        <v-list-item-title>Validate</v-list-item-title>
        <v-list-item-action>
          <v-icon>mdi-check</v-icon>
        </v-list-item-action>
      </v-list-item>
      <v-list-item @click="$emit('deleted')">
        <v-list-item-title>Delete</v-list-item-title>
        <v-list-item-action>
          <v-icon>mdi-delete</v-icon>
        </v-list-item-action>
      </v-list-item>
    </v-list>
  </v-menu>
</template>

<script>
import ColorByName from "../services/ColorByName";

export default {
  props: ["data", "type", "useTripodColors", "title"],

  emits: ["validated", "deleted"],

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
        this.name = this.data.flag.description;
        this.description = this.data.flag.description;
      }
    },
  },

  mounted() {
    this.parse();
  },
};
</script>
