<template>
  <div id="app">
    <Navbar />
    <router-view />
  </div>
</template>

<script>
// import Navbar from "./components/Navbar.vue";
// import PageHome from "./components/pages/PageHome.vue";
// import PageCourses from "./components/pages/PageCourses.vue";
// import PageTutors from "./components/pages/PageTutors.vue";
// import PageAvailability from "./components/pages/PageAvailability.vue";

import Navbar from "./components/Navbar.vue";

export default {
   name: 'App',
   components: {
     Navbar
  //   Navbar,
  //   PageHome,
  //   PageCourses,
  //   PageTutors,
  //   PageAvailability
  }
}


</script>

<style>
/*#app {*/
/*  font-family: Avenir, Helvetica, Arial, sans-serif;*/
/*  -webkit-font-smoothing: antialiased;*/
/*  -moz-osx-font-smoothing: grayscale;*/
/*  text-align: center;*/
/*  color: #2c3e50;*/
/*  margin-top: 60px;*/
/*}*/

body {
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
  padding: 0;
  margin: 0;
  background: linear-gradient(rgba(244, 244, 244, 0.3), rgba(44, 62, 80, 0.3)), url('img/background.jpg');
  background-attachment: fixed;
  background-size: cover;
  background-position: center;
}
</style>
