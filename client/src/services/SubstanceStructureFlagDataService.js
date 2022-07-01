import http from "../http-common";

class SubstanceStructureFlagDataService {
  get(id) {
    return http.get(`substance-structure-flags/${id}`);
  }

  getBySubstanceId(id) {
    return http.get(`substance-structure-flags/substance-id/${id}`);
  }

  put(id, substanceFlag) {
    return http.put(`substance-structure-flags/${id}`, substanceFlag);
  }

  post(substanceFlag) {
    return http.post(`substance-structure-flags`, substanceFlag);
  }

  delete(id) {
    return http.delete(`substance-structure-flags/${id}`);
  }
}

export default new SubstanceStructureFlagDataService();
