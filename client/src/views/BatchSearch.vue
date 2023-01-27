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
import http from "../http-common";
import {saveAs} from 'file-saver';
import FileSaver from 'file-saver';
import { SERVER_URL } from "@/store";
import axios from "axios";


export default {
      data() {
      return {
        search_box: ""
      };
      },

      methods: {
        async batch_search() {
          if(this.search_box != ""){
          const search_terms = this.search_box.split("\n")
          const response = await axios.post(SERVER_URL + `excel-download/`, {dtxsids: search_terms }, 
          { 
            // headers:
            // {
            //     'Content-Disposition': "attachment; filename=substances_batchquery_2023_01_26_.xlsx",
            //     'Content-Type': 'application/octet-stream'
            // },
            responseType: 'arraybuffer',
          },
          )
          if (response.data !== null) {
            // var bytes = new Uint8Array(response.data)
            // const url = window.URL.createObjectURL(new Blob(bytes), { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
          var blob = new Blob([response.data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
          saveAs(blob, 'fixi.xlsx');

            // console.log(response.data)
            // const link = document.createElement('a');
            // link.href = url;
            // link.setAttribute('download', 'substances_batchquery_2023_01_26_.xlsx');
            // document.body.appendChild(link);
            // link.click();

          }
        }
      }
    }
}
</script>