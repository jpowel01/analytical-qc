<template>
  <v-card>
    <v-card-title>Substance Information</v-card-title>
    <v-simple-table>
      <tbody>
        <tr>
          <th scope="row">DTXSID</th>
          <td>
            <a
              target="_blank"
              rel="noreferrer noopener"
              :href="`${DASHBOARD_DETAILS_URL}/${substance.dtxsid}`"
              >{{ substance.dtxsid }}</a
            ><v-icon x-small class="ml-1">mdi-open-in-new</v-icon>
          </td>
        </tr>
        <tr>
          <th scope="row">CASRN</th>
          <td>{{ substance.casrn }}</td>
        </tr>
        <tr>
          <th scope="row">Mol. Formula</th>
          <td>{{ substance.molFormula }}</td>
        </tr>
        <tr>
          <th scope="row">Mol. Weight</th>
          <td>{{ substance.molWeight }}</td>
        </tr>
        <tr>
          <th scope="row">PubChem CID</th>
          <td>
            <div v-if="substance.pubchemCid">
              <a
                target="_blank"
                rel="noreferrer noopener"
                :href="`${PUBCHEM_CID_URL}/${substance.pubchemCid}`"
                >{{ substance.pubchemCid }}</a
              ><v-icon x-small class="ml-1">mdi-open-in-new</v-icon>
            </div>
          </td>
        </tr>
      </tbody>
    </v-simple-table>
  </v-card>
</template>

<script>
import { DASHBOARD_DETAILS_URL, PUBCHEM_CID_URL } from "@/main";

export default {
  props: ["substance"],

  computed: {
    DASHBOARD_DETAILS_URL() {
      return DASHBOARD_DETAILS_URL;
    },
    PUBCHEM_CID_URL() {
      return PUBCHEM_CID_URL;
    },
  },
};
</script>
