import http from "../http-common";

class FlagDataService {
  getAll() {
    return http.get("flags");
  }
}

export default new FlagDataService();
