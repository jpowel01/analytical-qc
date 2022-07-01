import http from "../http-common";

class NmrAmenFlagDataService {
  getAll() {
    return http.get("nmr-amen-flags");
  }
}

export default new NmrAmenFlagDataService();
