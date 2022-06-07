import http from "../http-common";

class SubstanceDataService {
  getAll() {
    return http.get("substances");
  }

  get(id) {
    return http.get(`substances/${id}`);
  }
}

export default new SubstanceDataService();
