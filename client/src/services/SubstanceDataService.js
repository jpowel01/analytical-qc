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

  getAlternate(query, type) {
    return http.get(`substances/${type}/${query}`);
  }

  getDetail(id) {
    return http.get(`substances/${id}/detail`);
  }

  getDetailAlternate(query, type) {
    return http.get(`substances/${type}/${query}/detail`);
  }
}

export default new SubstanceDataService();
