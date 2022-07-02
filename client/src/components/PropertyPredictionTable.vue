<template>
  <v-card>
    <v-card-title>Substance Predictions</v-card-title>
    <v-tabs v-model="tab">
      <v-tab key="properties">Properties</v-tab>
      <v-tab key="amenability">Amenability</v-tab>
    </v-tabs>
    <v-tabs-items v-model="tab">
      <v-tab-item key="properties">
        <v-card flat>
          <v-simple-table>
            <tbody>
              <tr>
                <th scope="row">Melting Point (&deg;C)</th>
                <td v-if="propertyPrediction && propertyPrediction.mp">{{ propertyPrediction.mp.toFixed(0) }}</td>
              </tr>
              <tr>
                <th scope="row">Boiling Point (&deg;C)</th>
                <td v-if="propertyPrediction && propertyPrediction.bp">{{ propertyPrediction.bp.toFixed(0) }}</td>
              </tr>
              <tr>
                <th scope="row">Vapor Pressure (mmHg)</th>
                <td v-if="propertyPrediction && propertyPrediction.vp && propertyPrediction.vp < 0.1">
                  {{ propertyPrediction.vp.toExponential(2) }}
                </td>
                <td v-else-if="propertyPrediction && propertyPrediction.vp">
                  {{ propertyPrediction.vp.toFixed(2) }}
                </td>
              </tr>
              <tr>
                <th scope="row">
                  Octanol-Water Partition Coefficient (logK<sub>ow</sub>)
                </th>
                <td v-if="propertyPrediction && propertyPrediction.logP">{{ propertyPrediction.logP.toPrecision(2) }}</td>
              </tr>
            </tbody>
          </v-simple-table>
        </v-card>
      </v-tab-item>
      <v-tab-item key="amenability">
        <v-card flat class="align-start">
          <v-simple-table>
            <tbody>
              <tr>
                <th scope="row">LCMS (ESI-)</th>
                <td>
                  <AmenabilityChip
                    v-if="amenabilityPrediction && amenabilityPrediction.lcmsAmenNeg"
                    threshold="0.5"
                    :pred="amenabilityPrediction.lcmsAmenNeg"
                    :true="amenabilityPrediction.lcmsAmenNegTrue"
                  />
                </td>
              </tr>
              <tr>
                <th scope="row">LCMS (ESI+)</th>
                <td>
                  <AmenabilityChip
                    v-if="amenabilityPrediction && amenabilityPrediction.lcmsAmenPos"
                    threshold="0.5"
                    :pred="amenabilityPrediction.lcmsAmenPos"
                    :true="amenabilityPrediction.lcmsAmenPosTrue"
                  />
                </td>
              </tr>
              <tr>
                <th scope="row">NMR</th>
                <td>
                  <v-select
                    class="my-2"
                    v-model="newAmenabilityPrediction.nmrAmenFlag"
                    :items="nmrAmenFlags"
                    item-text="name"
                    item-value="name"
                    label="NMR Amenability Flag"
                    return-object
                    hide-details
                    clearable
                    @change="$emit('changed', newAmenabilityPrediction)"
                  >
                    <template v-slot:item="{ item }">
                      <template>
                        <v-list-item-content>
                          <v-list-item-title>{{ item.name }}</v-list-item-title>
                          <v-list-item-subtitle>{{
                            item.description
                          }}</v-list-item-subtitle>
                        </v-list-item-content>
                      </template>
                    </template>
                  </v-select>
                </td>
              </tr>
              <tr><th scope="row"><td></td></th></tr>
            </tbody>
          </v-simple-table>
        </v-card>
      </v-tab-item>
    </v-tabs-items>
  </v-card>
</template>

<script>
import AmenabilityChip from "./AmenabilityChip";
import NmrAmenFlagDataService from "../services/NmrAmenFlagDataService";

export default {
  props: ["propertyPrediction", "amenabilityPrediction"],

  data() {
    return {
      tab: null,
      nmrAmenFlags: [],
      newAmenabilityPrediction: {
        nmrAmenFlag: {},
      }
    }
  },

  components: {
    AmenabilityChip,
  },

  mounted() {
    NmrAmenFlagDataService.getAll()
      .then((response) => {
        this.nmrAmenFlags = response.data;
      })
      .catch((err) => {
        console.log(err);
      });

    if (this.amenabilityPrediction) {
      this.newAmenabilityPrediction = JSON.parse(
          JSON.stringify(this.amenabilityPrediction)
        );
    }
  }
};
</script>
