import http from "../http-common";

class SampleAnnotationDataService {
  get(id) {
    return http.get(`sample-annotations/${id}`);
  }

  getBySampleId(id) {
    return http.get(`sample-annotations/sample-id/${id}`);
  }

  put(id, sampleAnnotation) {
    return http.put(`sample-annotations/${id}`, sampleAnnotation);
  }

  post(sampleAnnotation) {
    return http.post(`sample-annotations`, sampleAnnotation);
  }

  delete(id) {
    return http.delete(`sample-annotations/${id}`);
  }
}

export default new SampleAnnotationDataService();
