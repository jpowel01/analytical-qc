import http from "../http-common";

class SubstanceCallDataService {
  get(id) {
    return http.get(`substance-calls/${id}`);
  }

  getBySubstanceId(id) {
    return http.get(`substance-calls/substance-id/${id}`);
  }

  put(id, substanceCall) {
    return http.put(`substance-calls/${id}`, substanceCall);
  }

  post(substanceCall) {
    return http.post(`substance-calls`, substanceCall);
  }

  delete(id) {
    return http.delete(`substance-calls/${id}`);
  }
}

export default new SubstanceCallDataService();
