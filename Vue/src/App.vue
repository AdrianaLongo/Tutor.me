<template>
  <div id="app">
    <Navbar />
    <router-view />
  </div>
</template>

<script>
import Navbar from "./components/Navbar.vue";


export default {
   name: 'App',
   components: {
     Navbar
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
