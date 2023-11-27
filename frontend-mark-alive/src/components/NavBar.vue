<template lang="">
  <div>
    <ul>
      <li><router-link to="/">Home</router-link></li>
      <li><router-link to="/bookmarks">Your Saved Bookmarks</router-link></li>
    </ul>
    <router-view></router-view>
  </div>
</template>
<script setup></script>
<style>
ul {
  position: sticky;
  top: 0;
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #333;
}

li {
  float: left;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

/* Change the link color to #111 (black) on hover */
li a:hover {
  background-color: #111;
}
</style>
