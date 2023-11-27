import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/Home.vue";
import BookmarkList from "../views/BookmarkList.vue";

const routes = [
  { path: "/", name: "Home", component: Home },
  { path: "/bookmarks", name: "BookmarkList", component: BookmarkList },
];

export const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
