import axios from "axios";
import { API_BASE_URL } from "../utils/requests";
import { getToken } from "./auth";

const token = getToken();

const api = axios.create({
    baseURL: API_BASE_URL,
    responseType: 'json',
    headers: {
        'Authorization': `Bearer ${token}`
    }
});