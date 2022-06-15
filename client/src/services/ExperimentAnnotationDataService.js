import http from "../http-common";

class ExperimentAnnotationDataService {
  get(id) {
    return http.get(`experiment-annotations/${id}`);
  }

  getByExperimentId(id) {
    return http.get(`experiment-annotations/experiment-id/${id}`);
  }

  put(id, experimentAnnotation) {
    return http.put(`experiment-annotations/${id}`, experimentAnnotation);
  }

  post(experimentAnnotation) {
    return http.post(`experiment-annotations`, experimentAnnotation);
  }

  delete(id) {
    return http.delete(`experiment-annotations/${id}`);
  }
}

export default new ExperimentAnnotationDataService();
