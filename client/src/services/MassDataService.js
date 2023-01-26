import http from "../http-common";

class MassDataService {
    getAll(smiles) {
        return http.get(`localhost:9000/molmass_web.py?q=${smiles}`);
    }
}
