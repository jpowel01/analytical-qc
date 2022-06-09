import http from "../http-common";

class SubstanceFlagDataService {
  getAll() {
    return http.get("substance-flags");
  }

  get(id) {
    return http.get(`substance-flags/${id}`);
  }

  getBySubstanceId(substanceId) {
    return http.get(`substance-flags/substance-id/${substanceId}`);
  }
}

export default new SubstanceFlagDataService();
