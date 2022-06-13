import http from "../http-common";

class SubstanceFlagDataService {
  get(id) {
    return http.get(`substance-flags/${id}`);
  }

  put(id, substanceFlag) {
    return http.put(`substance-flags/${id}`, substanceFlag);
  }

  post(substanceFlag) {
    return http.post(`substance-flags`, substanceFlag);
  }

  delete(id) {
    return http.delete(`substance-flags/${id}`);
  }
}

export default new SubstanceFlagDataService();
