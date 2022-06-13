<template>
  <v-menu bottom offset-y>
    <template v-slot:activator="{ on: menu, attrs }">
      <v-tooltip top>
        <template v-slot:activator="{ on: tooltip }">
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
      <v-list-item>
        <v-list-item-title>Validate</v-list-item-title>
        <v-list-item-action>
          <v-checkbox v-model="data.validated" @click="validate" />
        </v-list-item-action>
      </v-list-item>
      <v-list-item>
        <v-list-item-title>Delete</v-list-item-title>
        <v-list-item-action>
          <v-icon @click="deleteData">mdi-delete</v-icon>
        </v-list-item-action>
      </v-list-item>
    </v-list>
  </v-menu>
</template>

<script>
import ColorByName from "../services/ColorByName"

export default {
  props: [
    "data",
    "type",
    "service",
    "useTripodColors",
  ],

  data() {
    return {
      color: "",
      name: "",
      description: "",
    }
  },

  methods: {
    validate() {
      this.service.put(this.data.id, this.data);
    },

    deleteData() {
      this.service.delete(this.data.id);
      this.$emit("deleted");
    },

    recolor() {
      this.color = ColorByName.colorByName(this.name, this.useTripodColors);
    },

    reparse() {
      if (this.type === "grade") {
        this.name = this.data.grade.name;
        this.description = this.data.grade.description;
      } else if (this.type === "call") {
        this.name = this.data.call.name;
        this.description = this.data.call.description;
      } if (this.type === "flag") {
        this.name = this.data.flag.description;
        this.description = this.data.flag.description;
      }
    }
  },

  mounted() {
    this.reparse();
    this.recolor();
  },

  watch: {
    useTripodColors() {
      this.recolor();
    }
  }
};
</script>
