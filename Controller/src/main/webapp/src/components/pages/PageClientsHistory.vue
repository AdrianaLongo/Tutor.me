<template>
  <b-container class="mt-5">
    <h1>Prenotazioni clienti</h1>
    <select-client></select-client>
    <table-clients-history v-if="this.$store.getters.elencoPrenotazioniCliente !== ''"></table-clients-history>
  </b-container>
</template>

<script>
import SelectClient from "@/components/obtainDataFromServlet/SelectClient";
import TableClientsHistory from "@/components/obtainDataFromServlet/TableClientsHistory";


export default {
  name: "PageClientsHistory",
  components: {
    SelectClient,
    TableClientsHistory,
  },
  beforeCreate() {
  }
}
</script>

<style scoped>

</style>