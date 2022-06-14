import http from "../http-common";

class SubstanceDataService {
  getPaged(no, size) {
    return http.get("substances", { params: { pageNo: no, pageSize: size } });
  }

  searchPaged(str, no, size) {
    return http.get("substances", {
      params: { search: str, pageNo: no, pageSize: size },
    });
  }

  get(id) {
    return http.get(`substances/${id}`);
  }

  getDetail(id) {
    return http.get(`substances/${id}/detail`);
  }

  getFlags(id) {
    return http.get(`substances/${id}/flags`);
  }

  getGrades(id) {
    return http.get(`substances/${id}/grades`);
  }

  getCall(id) {
    return http.get(`substances/${id}/call`);
  }
}

export default new SubstanceDataService();
