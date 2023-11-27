<template lang="">
  <div>
    <label for="url">Enter your bookmark URL</label>
    <input
      type="text"
      v-model="url"
      name="url"
      id=""
      placeholder="Enter URL.."
    />
    <button @click="submit">Go!</button>
  </div>
</template>
<script setup>
import { postBookmark } from "../server_config/api";
import { ref } from "vue";
const url = ref("");
function submit() {
  postBookmark(url.value)
    .then((res) => console.log(res))
    .catch((err) => console.log(err));
}
</script>
<style lang=""></style>
