<template>
  <v-card>
    <v-card-title>OPERA Predicted Properties</v-card-title>
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
          <th scope="row">Octanol-Water Partition Coefficient (logP)</th>
          <td>{{ propertyPrediction.logP.toPrecision(2) }}</td>
        </tr>
      </tbody>
    </v-simple-table>
  </v-card>
</template>

<script>
export default {
  props: ["propertyPrediction"],
};
</script>
