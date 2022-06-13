import http from "../http-common";

class SampleGradeDataService {
  get(id) {
    return http.get(`sample-grades/${id}`);
  }

  put(id, sampleGrade) {
    return http.put(`sample-grades/${id}`, sampleGrade);
  }

  post(sampleGrade) {
    return http.post(`sample-grades`, sampleGrade);
  }

  delete(id) {
    return http.delete(`sample-grades/${id}`);
  }
}

export default new SampleGradeDataService();
