import axios, { Axios } from "axios";

const baseUrl = "http://localhost:8080";
const viewAllBookmarks = "/bookmarks";
const postBookmarkRelative = "/bookmark";

export function getBookmarks() {
  return axios.get(baseUrl.concat(viewAllBookmarks)).then((res) => res.data);
}

export function postBookmark(resource) {
  return axios
    .post(baseUrl.concat(postBookmarkRelative).concat("?url=").concat(resource))
    .then((res) => res.data);
}
