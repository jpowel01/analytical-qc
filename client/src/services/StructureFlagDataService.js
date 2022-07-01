import http from "../http-common";

class StructureFlagDataService {
  getAll() {
    return http.get("structure-flags");
  }
}

export default new StructureFlagDataService();
