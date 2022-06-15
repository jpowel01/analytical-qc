import http from "../http-common";

class SubstanceAnnotationDataService {
  get(id) {
    return http.get(`substance-annotations/${id}`);
  }

  getBySubstanceId(id) {
    return http.get(`substance-annotations/substance-id/${id}`);
  }

  put(id, substanceAnnotation) {
    return http.put(`substance-annotations/${id}`, substanceAnnotation);
  }

  post(substanceAnnotation) {
    return http.post(`substance-annotations`, substanceAnnotation);
  }

  delete(id) {
    return http.delete(`substance-annotations/${id}`);
  }
}

export default new SubstanceAnnotationDataService();
