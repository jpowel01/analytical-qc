import http from "../http-common";

class CallDataService {
  getAll() {
    return http.get("calls");
  }
}

export default new CallDataService();
