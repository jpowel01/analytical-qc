<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <v-icon>mdi-checkbox-multiple-marked</v-icon>
      <div class="text-h6 ma-2">Analytical QC</div>
      <v-spacer />
      <v-btn to="/substances" text>
        Substances
      </v-btn>
    </v-app-bar>

    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "App",
};
</script>
