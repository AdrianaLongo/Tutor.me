<template>
<!--  <div id="navbar">-->
<!--    <nav class="navbar navbar-expand-lg fixed-top" v-bind:class=" { 'navbarOpen': show }">-->
<!--      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" @click.stop="toggleNavbar()">-->
<!--        <span class="navbar-toggler-icon"></span>-->
<!--      </button>-->
<!--      <div class="collapse navbar-collapse" id="navbarSupportedContent" v-bind:class="{ 'show': !show }">-->
<!--&lt;!&ndash;        <ul class="navbar-nav mr-4">&ndash;&gt;-->
<!--        <ul class="navbar-nav mr-auto">-->
<!--          <li class="nav-item active">-->
<!--            <router-link class="nav-link" to="/">Home-->
<!--              <span class="sr-only">(current)</span>-->
<!--            </router-link>-->
<!--          </li>-->
<!--          <li class="nav-item">-->
<!--            <router-link class="nav-link" to="courses">Catalogo corsi</router-link>-->
<!--          </li>-->
<!--          <li class="nav-item">-->
<!--            <router-link class="nav-link" to="jsonTutor">Tutor</router-link>-->
<!--          </li>-->
<!--          <li class="nav-item">-->
<!--            <router-link class="nav-link" to="calendar">Calendario disponibilità</router-link>-->
<!--          </li>-->
<!--          <li class="nav-item">-->
<!--            <router-link class="nav-link" to="login">Login</router-link>-->
<!--&lt;!&ndash;            <a href="#LogInButton" role="button" class="btn btn-outline" data-toggle="modal">Login</a>&ndash;&gt;-->
<!--          </li>-->
<!--&lt;!&ndash;          <li>&ndash;&gt;-->
<!--&lt;!&ndash;            <a href="#" class="btn btn-outline">Sign up</a>&ndash;&gt;-->
<!--&lt;!&ndash;          </li>&ndash;&gt;-->
<!--        </ul>-->
<!--      </div>-->
<!--    </nav >-->
<!--  </div>-->

  <b-navbar
      sticky
      toggleable="lg"
      type="dark"
      variant="dark"
      class="header-nav bg-primary position-absolute w-100 p-sm-0"
  >
    <b-navbar-brand to="/" class="font-weight-lighter p-3">
      Tutor.me
    </b-navbar-brand>

    <b-navbar-toggle target="nav-collapse--menu" style="z-index: 90;"></b-navbar-toggle>

    <b-collapse id="nav-collapse--menu" v-model="showCollapse" is-nav>
<!--      <b-navbar-nav class="ml-auto custom-dropdown-menus" id="nav-nav">-->
      <b-navbar-nav>
<!--        <b-nav-item active>-->
<!--          <router-link class="nav-link ml-2" to="/">Home-->
<!--            <span class="sr-only">(current)</span>-->
<!--          </router-link>-->
<!--        </b-nav-item>-->
<!--        <b-nav-item>-->
<!--          <router-link class="nav-link ml-2" to="courses">Catalogo corsi</router-link>-->
<!--        </b-nav-item>-->
<!--        <b-nav-item>-->
<!--          <router-link class="nav-link ml-2" to="jsonTutor">Tutor</router-link>-->
<!--        </b-nav-item>-->
<!--        <b-nav-item>-->
<!--          <router-link class="nav-link ml-2" to="calendar">Calendario disponibilità</router-link>-->
<!--        </b-nav-item>-->
        <b-nav-item v-show="this.$store.getters.currentToken !== ''">
          <router-link class="nav-link ml-2" to="lemieprenotazioni">Le mie prenotazioni</router-link>
        </b-nav-item>

        <b-nav-item-dropdown v-show="this.$store.getters.currentToken !== ''" class="m-2"  text="Funzionalità amministratore" >
        <!-- TODO: Rendere dark il dropdown menu-->
          <b-dropdown-item>
            <router-link to="prenotazioniclienti">
              Prenotazioni clienti
            </router-link>
          </b-dropdown-item>
          <b-dropdown-item>
            <router-link to="modificacatalogo">
              Modifica catalogo
            </router-link>
          </b-dropdown-item>
        </b-nav-item-dropdown>

      </b-navbar-nav>

      <b-navbar-nav class="ml-auto">
<!--        PAGINA-->
<!--        <b-nav-item v-show="this.$store.getters.currentToken === ''">-->
<!--          <b-button @click="login">Login</b-button>-->
<!--          &lt;!&ndash;            <a href="#LogInButton" role="button" class="btn btn-outline" data-toggle="modal">Login</a>&ndash;&gt;-->
<!--        </b-nav-item>-->

        <b-nav-item v-show="!this.$store.getters.userLogged">
          <login></login>
        </b-nav-item>


        <b-nav-item v-show="this.$store.getters.userLogged">
          <a href="#" class="btn bg-danger" @click="logout">Log out</a>
          <!--            <a href="#LogInButton" role="button" class="btn btn-outline" data-toggle="modal">Login</a>-->
        </b-nav-item>
<!--        <b-nav-item>-->
<!--          <a href="#" class="btn btn-outline">Sign up</a>-->
<!--        </b-nav-item>-->
      </b-navbar-nav>
    </b-collapse>

  </b-navbar>




</template>

<script>
import Login from "@/components/authentication/Login";

export default {
  name: "navbar",
  components:{
    Login
  },

  data() {
    return {
      showCollapse: false,
      isLogged: false
    }
  },
  watch: {
    '$route' () {
      // This will close the collapse if any part of the route changes
      // including query, params, hash, name, or path
      this.showCollapse = false
    }
  },
  methods: {
    logout: function(){
      // TODO: sistemare logout (serve sessione)
      localStorage.access_token = '';
      this.isLogged = false;
      console.log("token in localstorage: " + localStorage.access_token)
      console.log("token in store: " + this.$store.getters.currentToken)

    },
    checkLogin: function(){
      if(localStorage.access_token !== null){
        this.isLogged = true;
      }
    },
    login: function(){

    }
  }
}
</script>

<style scoped>
</style>