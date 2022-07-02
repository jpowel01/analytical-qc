<template>
  <v-menu bottom offset-y rounded="xl">
    <template v-slot:activator="{ on: menu, attrs }">
      <v-tooltip top>
        <template v-slot:activator="{ on: tooltip }">
          <v-chip
            v-if="trueVal != null"
            :color="trueVal ? 'green' : 'red'"
            text-color="white"
            class="ma-1"
            v-bind="attrs"
            v-on="{ ...tooltip, ...menu }"
          >
            <v-icon>{{ trueVal ? 'mdi-thumb-up' : 'mdi-thumb-down' }}</v-icon>
          </v-chip>
          <v-chip
            v-else
            :color="color"
            outlined
            class="ma-1"
            v-bind="attrs"
            v-on="{ ...tooltip, ...menu }"
          >
            <v-icon>{{ pred > threshold ? 'mdi-thumb-up' : 'mdi-thumb-down' }}</v-icon>
          </v-chip>
        </template>
        <span v-if="(trueVal != null) && (trueVal == pred > threshold)">{{ "Validated prediction: " + pred.toPrecision(3) }}</span>
        <span v-else-if="trueVal != null">{{ "Overridden prediction: " + pred.toPrecision(3) }}</span>
        <span v-else>{{ "Prediction: " + pred.toPrecision(3) }}</span>
      </v-tooltip>
    </template>
    <v-list dense>
        <v-list-item @click="$emit('validate')">
          <v-list-item-title>Validate</v-list-item-title>
          <v-list-item-icon><v-icon>mdi-check</v-icon></v-list-item-icon>
        </v-list-item>
        <v-list-item @click="$emit('override')">
          <v-list-item-title>Override</v-list-item-title>
          <v-list-item-icon><v-icon>mdi-close</v-icon></v-list-item-icon>
        </v-list-item>
      </v-list>
  </v-menu>
</template>

<script>
export default {
  props: ["pred", "threshold", "trueVal"],

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
