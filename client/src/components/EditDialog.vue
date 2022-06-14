<template>
  <v-dialog v-model="dialog">
    <template v-slot:activator="{ on, attrs }">
      <v-icon class="ma-1" v-on="on" v-bind="attrs">mdi-plus-circle</v-icon>
    </template>
  </v-dialog>
</template>

<script>
export default {
  data() {
    return {
      dialog: null,
    };
  },
};
</script>
