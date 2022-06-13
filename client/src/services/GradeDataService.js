import http from "../http-common";

class GradeDataService {
  getAll() {
    return http.get("grades");
  }
}

export default new GradeDataService();
