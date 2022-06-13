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
            <strong>{{ data.grade.name }}</strong>
          </v-chip>
        </template>
        <span v-if="data.validated">Validated grade: </span>
        <span v-else>Automatic grade: </span>
        <span>{{ data.grade.description }}</span>
      </v-tooltip>
    </template>
    <v-list>
      <v-list-item>
        <v-list-item-title>Validate</v-list-item-title>
        <v-list-item-action>
          <v-checkbox v-model="data.validated" @click="validate" />
        </v-list-item-action>
      </v-list-item>
    </v-list>
  </v-menu>
</template>

<script>
import ColorByGrade from "../services/ColorByGrade"

export default {
  props: [
    "data",
    "service",
    "useTripodColors",
  ],

  data() {
    return {
      color: "",
    }
  },

  methods: {
    validate() {
      this.service.put(this.data.id, this.data);
    }
  },

  mounted() {
    this.color = ColorByGrade.colorByGrade(this.data.grade.name, this.useTripodColors);
  },

  watch: {
    useTripodColors() {
      this.color = ColorByGrade.colorByGrade(this.data.grade.name, this.useTripodColors);
    }
  }
};
</script>
