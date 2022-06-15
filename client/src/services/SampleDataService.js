import http from "../http-common";

class SampleDataService {
  get(id) {
    return http.get(`samples/${id}`);
  }
}

export default new SampleDataService();
