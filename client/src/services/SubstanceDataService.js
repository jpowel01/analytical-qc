import http from "../http-common";

class SubstanceDataService {
  getRowsPaged(str, id, no, size) {
    return http.get(`substances`, { params: { search: str, listId: id, pageNo: no, pageSize: size } });
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
