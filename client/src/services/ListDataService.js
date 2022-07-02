import http from "../http-common";

class ListDataService {
  getAll() {
    return http.get(`lists`);
  }

  getDetail(id) {
    return http.get(`lists/${id}/detail`);
  }

  get(id) {
    return http.get(`lists/${id}`);
  }

  post(request) {
    return http.post(`lists`, request);
  }

  delete(name) {
    return http.delete(`lists/name/${name}`)
  }
}

export default new ListDataService();
