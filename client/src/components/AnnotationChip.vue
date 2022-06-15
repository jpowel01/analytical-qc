<template>
  <v-menu bottom offset-y open-on-hover>
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        class="mr-2"
        dark
        small
        fab
        color="primary"
        v-bind="attrs"
        v-on="on"
      >
        <v-icon> mdi-message-text </v-icon>
      </v-btn>
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
