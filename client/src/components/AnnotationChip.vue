<template>
  <v-menu bottom offset-y open-on-hover v-if="annotation">
    <template v-slot:activator="{ on, attrs }">
      <v-chip class="ma-1" color="primary" v-bind="attrs" v-on="on">
        <v-icon small class="pa-0">mdi-text</v-icon>
      </v-chip>
    </template>
    <v-card max-width="600">
      <v-card-text>{{ annotation }}</v-card-text>
    </v-card>
  </v-menu>
</template>

<script>
export default {
  props: ["annotation"],
};
</script>

<style>
v-btn {
  min-width: "0";
}
</style>
