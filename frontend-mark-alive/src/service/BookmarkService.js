import axios, { Axios } from "axios";

const baseUrl = "http://localhost:8080";

export function getBookmarks() {
  return axios.get(baseUrl + "/bookmarks").then((res) => res.data);
}
