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
          <v-simple-table v-if="propertyPrediction">
            <tbody>
              <tr>
                <th scope="row">Melting Point (&deg;C)</th>
                <td v-if="propertyPrediction.mp">{{ propertyPrediction.mp.toFixed(0) }}</td>
              </tr>
              <tr>
                <th scope="row">Boiling Point (&deg;C)</th>
                <td v-if="propertyPrediction.bp">{{ propertyPrediction.bp.toFixed(0) }}</td>
              </tr>
              <tr>
                <th scope="row">Vapor Pressure (mmHg)</th>
                <td v-if="propertyPrediction.vp && propertyPrediction.vp < 0.1">
                  {{ propertyPrediction.vp.toExponential(2) }}
                </td>
                <td v-else>
                  {{ propertyPrediction.vp.toFixed(2) }}
                </td>
              </tr>
              <tr>
                <th scope="row">
                  Octanol-Water Partition Coefficient (logK<sub>ow</sub>)
                </th>
                <td v-if="propertyPrediction.logP">{{ propertyPrediction.logP.toPrecision(2) }}</td>
              </tr>
            </tbody>
          </v-simple-table>
          <v-alert type="error" v-else>Property predictions unavailable</v-alert>
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
                    :prob="amenabilityPrediction.lcmsAmenNeg"
                  />
                </td>
              </tr>
              <tr>
                <th scope="row">LCMS (ESI+)</th>
                <td>
                  <AmenabilityChip
                    v-if="amenabilityPrediction && amenabilityPrediction.lcmsAmenPos"
                    threshold="0.5"
                    :prob="amenabilityPrediction.lcmsAmenPos"
                  />
                </td>
              </tr>
              <tr>
                <th scope="row">NMR</th>
                <td>
                </td>
              </tr>
              <tr>
                <th scope="row"></th>
                <td>
                </td>
              </tr>
            </tbody>
          </v-simple-table>
        </v-card>
      </v-tab-item>
    </v-tabs-items>
  </v-card>
</template>

<script>
import AmenabilityChip from "./AmenabilityChip";

export default {
  props: ["propertyPrediction", "amenabilityPrediction"],

  data() {
    return {
      tab: null,
    }
  },

  components: {
    AmenabilityChip,
  },
};
</script>
