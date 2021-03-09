<template>

  <b-navbar
      fixed="top"
      sticky
      toggleable="lg"
      type="dark"
      variant="dark"
      class="header-nav bg-primary position-absolute w-100 p-sm-0"
  >
    <b-navbar-brand to="/" class="font-weight-lighter p-lg-3 p-md-3 p-sm-3">
      Tutor.me
    </b-navbar-brand>

    <b-navbar-toggle target="nav-collapse--menu" style="z-index: 90;"></b-navbar-toggle>

    <b-collapse id="nav-collapse--menu" v-model="showCollapse" is-nav>
<!--      <b-navbar-nav class="ml-auto custom-dropdown-menus" id="nav-nav">-->
      <b-navbar-nav>
<!--        <b-nav-item v-show="this.$store.getters.userLogged">-->
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
        <b-nav-item v-show="!this.$store.getters.userLogged">
          <login></login>
        </b-nav-item>


        <b-nav-item v-show="this.$store.getters.userLogged">
          <logout></logout>
        </b-nav-item>
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
    }
  },
  watch: {
    '$route' () {
      // This will close the collapse if any part of the route changes
      // including query, params, hash, name, or path
      this.showCollapse = false
    },
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