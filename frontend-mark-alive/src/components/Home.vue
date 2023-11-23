<template lang="">
  <div>
    <div v-for="bookmark in bookMarkList">
      <li>{{ bookmark.id }}</li>
      <li>{{ bookmark.url }}</li>
    </div>
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
