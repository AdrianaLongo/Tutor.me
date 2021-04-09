<template>
  <div >
    <b-col md="4">
      <b-button variant="outline-danger" v-b-modal.modal-logout>Logout</b-button>
    </b-col>

    <b-modal v-if="this.$store.state.client.isLogged" id="modal-logout" title="Vuoi procedere con il logout?" align="center" hide-footer>

      <div class="mt-2">
        <b-button variant="danger" @click="logout()" type="submit">Logout</b-button>
      </div>
    </b-modal>
  </div>
</template>

<script>
export default {
  name: "Logout",
  methods:{
    logout(){
      this.$store.dispatch('logout');

      // Dobbiamo rimuovere i dati della sessione dalla sessionStorage
      sessionStorage.hasntLoggedOut = false;
      sessionStorage.username = "";
      sessionStorage.password = "";

      this.$router.push("/#").catch(()=>{}); // per reindirizzare l'utente alla homepage dopo il logout
    }
  }
}
</script>

<style scoped>

</style>