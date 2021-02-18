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
        <b-nav-item v-show="this.$store.getters.userLogged">
          <router-link class="nav-link ml-2" to="lemieprenotazioni">Le mie prenotazioni</router-link>
        </b-nav-item>

        <b-nav-item-dropdown v-show="this.$store.getters.userLogged" class="m-2"  text="Funzionalità amministratore" >
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
<!--          <b-dropdown id="dropdown-login"-->
<!--                      right-->
<!--                      text="Login"-->
<!--                      variant="outline-success"-->
<!--                      ref="dropdown"-->
<!--                      class="m-2">-->
<!--            <b-dropdown-form>-->
<!--              <b-form-group label="Username" label-for="dropdown-login-username" @submit.stop.prevent>-->
<!--                <b-form-input-->
<!--                    id="dropdown-login-username"-->
<!--                    size="sm"-->
<!--                    placeholder="Username"-->
<!--                ></b-form-input>-->
<!--              </b-form-group>-->

<!--              <b-form-group label="Password" label-for="dropdown-login-password">-->
<!--                <b-form-input-->
<!--                    id="dropdown-login-password"-->
<!--                    type="password"-->
<!--                    size="sm"-->
<!--                    placeholder="Password"-->
<!--                ></b-form-input>-->
<!--              </b-form-group>-->

<!--              <b-button variant="primary" size="sm" @click="login()">Login</b-button>-->
<!--            </b-dropdown-form>-->
<!--          </b-dropdown>-->
        </b-nav-item>


        <b-nav-item v-show="this.$store.getters.userLogged">
<!--          <a href="#" class="btn bg-outline-danger" @click="logout">Log out</a>-->
          <logout></logout>
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
import Logout from "@/components/authentication/Logout";

export default {
  name: "navbar",
  components:{
    Login,
    Logout
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
  // methos: {
  //   login: function () {
  //     this.$store.dispatch('login', {
  //       username: this.username,
  //       password: this.password,
  //     });
  //
  //
  //     if (this.$store.getters.courseName !== '' && this.$store.getters.tutorId !== '') {
  //       this.$store.dispatch('retrievePersonalHistory')
  //     }
  //
  //     setTimeout(() => {
  //       this.makeToast()
  //     }, 200)
  //     console.log("Username inserito: " + this.username);
  //     console.log("Password inserita: " + this.password);
  //   },
  //
  //   makeToast() {
  //     if (this.$store.state.isLogged) {
  //       this.$bvToast.toast(
  //           `Benvenut*, ${this.username}!`,
  //           {
  //             title: `Login effettuato con successo`,
  //             variant: 'success',
  //             solid: true
  //           })
  //     } else {
  //       this.$bvToast.toast(
  //           `Controlla username e password.`,
  //           {
  //             title: `Login non effettuato!`,
  //             variant: 'danger',
  //             solid: true
  //           })
  //     }
  //
  //   },
  // }
}
</script>

<style scoped>

</style>