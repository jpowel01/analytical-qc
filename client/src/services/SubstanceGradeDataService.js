import http from "../http-common";

class SubstanceGradeDataService {
  get(id) {
    return http.get(`substance-grades/${id}`);
  }

  getBySubstanceId(id) {
    return http.get(`substance-grades/substance-id/${id}`);
  }

  put(id, substanceGrade) {
    return http.put(`substance-grades/${id}`, substanceGrade);
  }

  post(substanceGrade) {
    return http.post(`substance-grades`, substanceGrade);
  }

  delete(id) {
    return http.delete(`substance-grades/${id}`);
  }
}

export default new SubstanceGradeDataService();
