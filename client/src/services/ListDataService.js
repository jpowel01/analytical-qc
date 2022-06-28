import http from "../http-common";

class ListDataService {
  getAll() {
    return http.get(`lists`);
  }

  getDetail(id) {
    return http.get(`lists/${id}/detail`);
  }
}

export default new ListDataService();
