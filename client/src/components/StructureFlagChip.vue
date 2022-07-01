<template>
  <v-tooltip top>
    <template v-slot:activator="{ on, attrs }">
      <v-chip
        color="purple"
        :outlined="!substanceStructureFlag.validated"
        :text-color="substanceStructureFlag.validated ? 'white' : 'purple'"
        class="ma-1"
        v-bind="attrs"
        v-on="on"
      >
        <strong>{{ substanceStructureFlag.structureFlag.name }}</strong>
      </v-chip>
    </template>
    <span v-if="substanceStructureFlag.validated">Validated: </span>
    <span v-else>Provisional: </span>
    <span>{{ substanceStructureFlag.structureFlag.description }}</span>
  </v-tooltip>
</template>

<script>
export default {
  props: ["substanceStructureFlag"],
};
</script>
