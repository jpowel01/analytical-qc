import http from "../http-common";

class AmenabilityPredictionDataService {
  get(id) {
    return http.get(`amenability-predictions/${id}`);
  }

  put(id, sampleAnnotation) {
    return http.put(`amenability-predictions/${id}`, sampleAnnotation);
  }

  post(sampleAnnotation) {
    return http.post(`amenability-predictions`, sampleAnnotation);
  }
}

export default new AmenabilityPredictionDataService();
