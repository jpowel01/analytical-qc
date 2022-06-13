import http from "../http-common";

class SampleDataService {
  get(id) {
    return http.get(`samples/${id}`);
  }

  getGrades(id) {
    return http.get(`samples/${id}/grades`);
  }

  getCall(id) {
    return http.get(`samples/${id}/call`);
  }
}

export default new SampleDataService();
