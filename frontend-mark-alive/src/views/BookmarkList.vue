<template lang="">
  <div>
    <NavBar />
    <table>
      <thead>
        <tr>
          <th>Number</th>
          <th>Your Bookmarks</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="bookmark in bookMarkList" v-bind:key="bookmark.id">
          <td>
            {{ bookmark.id }}
          </td>
          <td>
            {{ bookmark.url }}
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { getBookmarks } from "../service/BookmarkService";
import { ref, onMounted } from "vue";

const bookMarkList = ref(null);

onMounted(() => {
  getBookmarks().then((data) => {
    bookMarkList.value = data;
  });
});
</script>
<style lang=""></style>
