<template>
  <div id="app">
    <Navbar />
    <div></div>
    <router-view :key="$route.path"></router-view>
  </div>
</template>

<script>
import Navbar from "./components/Navbar.vue";
// import store from "./store"

export default {
  name: 'App',
  components: {
    Navbar
  },
  beforeCreate() {
    if (sessionStorage.hasntLoggedOut){
      // Devo chiamare l'action login perche' e' la funzione che riempie lo stato vue della sessione con il calendario
      // dell'utente (che di per se' non permane al refresh della pagina)
      this.$store.dispatch('login',{
        username: sessionStorage.username,
        password: atob(sessionStorage.password),
      });
    }
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
  /*padding: 0;*/
  margin: 0;
  background: linear-gradient(rgba(244, 244, 244, 0.3), rgba(44, 62, 80, 0.3)), url('img/background.jpg');
  background-attachment: fixed;
  background-size: cover;
  background-position: center;
  padding-top: 50px;
}
</style>