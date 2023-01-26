<template>
  <v-container fluid>
    <v-row>
      <v-col class="text-h3 text-left"> Batch Search </v-col>
      
      
    </v-row>
          <p>To download an Excel Summary table of analytical data for a set of substances, input a list of DTXSIDs</p>
    <div class="batch-search-controls">
      <v-textarea outlined type="text" class="batch-search-input" rows="10" columns="25" v-model="search_box"></v-textarea>
      <br>
      <br>
      <br>
      <br>
      <br>
      <br>
      
      <v-btn @click="batch_search()" class="batch-search-button">Search</v-btn>
    </div>
  </v-container>
</template>

<style scoped>
  .batch-search-input {
    height: 150px;
  }

  .batch-search-button {
    width: 150px;
    margin: auto;
    text-align: center;
  }
    .batch-search-controls {
    height: 200px;
    width: 200px;
    display: flex;
    flex-direction: column;
    align-items: center;
  }

</style>

<script>
export default {
      data() {
      return {
        search_box: ""
      };
      },

      methods: {
        async batch_search() {
        }
      }
}
</script>
