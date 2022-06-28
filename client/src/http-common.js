import axios from "axios";
import { SERVER_URL } from "@/store";

axios.defaults.baseURL = SERVER_URL;

export default axios.create({
  baseURL: SERVER_URL,
  headers: {
    "Content-Type": "application/json",
  },
});
