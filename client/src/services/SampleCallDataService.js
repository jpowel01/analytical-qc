import http from "../http-common";

class SampleCallDataService {
  get(id) {
    return http.get(`sample-calls/${id}`);
  }

  put(id, sampleCall) {
    return http.put(`sample-calls/${id}`, sampleCall);
  }

  post(sampleCall) {
    return http.post(`sample-calls`, sampleCall);
  }

  delete(id) {
    return http.delete(`sample-calls/${id}`);
  }
}

export default new SampleCallDataService();
