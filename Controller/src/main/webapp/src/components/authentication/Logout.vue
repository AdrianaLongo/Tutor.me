<template>
  <div v-if="this.$store.state.isLogged">
    <b-col md="4">
      <b-button variant="outline-danger" v-b-modal.modal-1>Logout</b-button>
    </b-col>

    <b-modal id="modal-1" title="Vuoi procedere con il logout?" align="center" hide-footer>

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
      console.log("Logout effettuato con successo")
    }
  }
}
</script>

<style scoped>

</style>