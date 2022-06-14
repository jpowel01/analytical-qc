<template>
  <v-card>
    <v-card-title>Predicted Properties</v-card-title>
    <v-simple-table>
      <tbody>
        <tr>
          <th scope="row">Melting Point (&deg;C)</th>
          <td>{{ propertyPrediction.mp.toFixed(0) }}</td>
        </tr>
        <tr>
          <th scope="row">Boiling Point (&deg;C)</th>
          <td>{{ propertyPrediction.bp.toFixed(0) }}</td>
        </tr>
        <tr>
          <th scope="row">Vapor Pressure (mmHg)</th>
          <td v-if="propertyPrediction.vp < 0.1">
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
          <td>{{ propertyPrediction.logP.toPrecision(2) }}</td>
        </tr>
        <tr>
          <th scope="row">LCMS Amenability</th>
          <td>
            <AmenabilityChip
              v-if="propertyPrediction.lcmsAmenNeg"
              charge="ESI-"
              threshold="0.5"
              :prob="propertyPrediction.lcmsAmenNeg"
            />
            <AmenabilityChip
              v-if="propertyPrediction.lcmsAmenPos"
              charge="ESI+"
              threshold="0.5"
              :prob="propertyPrediction.lcmsAmenPos"
            />
          </td>
        </tr>
      </tbody>
    </v-simple-table>
  </v-card>
</template>

<script>
import AmenabilityChip from "./AmenabilityChip";

export default {
  props: ["propertyPrediction"],

  components: {
    AmenabilityChip,
  },
};
</script>
