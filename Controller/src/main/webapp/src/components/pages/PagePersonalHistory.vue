<template>
  <b-container class="mt-5">
    <h1>Le mie prenotazioni</h1>
    <table-personal-history></table-personal-history>
  </b-container>


</template>

<script>
import TablePersonalHistory from "@/components/obtainDataFromServlet/TablePersonalHistory";

export default {
  name: "PagePersonalHistory",
  components: {
    TablePersonalHistory
  },
}
</script>

<style scoped>

</style>