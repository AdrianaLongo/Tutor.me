<template>
  <b-container>
    <h1>Prenotazioni clienti</h1>
    <table-clients-history></table-clients-history>
  </b-container>
</template>

<script>
import TableClientsHistory from "@/components/obtainDataFromServlet/TableClientsHistory";

export default {
  name: "PageClientsHistory",
  components: {
    TableClientsHistory
  },
  beforeCreate() {
    // console.log("token in localstorage: " + localStorage.access_token)
    console.log("token in store: " + this.$store.getters.currentToken)

  }
}
</script>

<style scoped>

</style>