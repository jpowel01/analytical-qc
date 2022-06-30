import http from "../http-common";

class SubstanceFileDataService {
  post(request) {
    return http.post(`substance-files`, request);
  }
}

export default new SubstanceFileDataService();
