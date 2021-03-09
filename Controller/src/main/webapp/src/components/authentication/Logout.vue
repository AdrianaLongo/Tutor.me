<template>
  <div v-if="this.$store.state.isLogged">
    <b-col md="4">
      <b-button variant="outline-danger" v-b-modal.modal-logout>Logout</b-button>
    </b-col>

    <b-modal id="modal-logout" title="Vuoi procedere con il logout?" align="center" hide-footer>

      <div class="mt-2">
          <b-button variant="danger" @click="logout()" type="submit">Logout</b-button>
        </div>
    </b-modal>
  </div>
</template>

<script>
// import jQuery from "jquery"

export default {
  name: "Logout",
  methods:{
    logout(){
      //TODO: AGGIORNARE SERVLET CON I COOKIES
      this.$store.dispatch('logout');
      this.$router.push('/'); // per reindirizzare l'utente alla homepage dopo il logout
    }
  }
}
</script>

<style scoped>

</style>