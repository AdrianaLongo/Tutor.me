<template>
  <b-container>
    <h1 class="h1">Le mie prenotazioni</h1>
    <table-personal-history></table-personal-history>
  </b-container>


</template>

<script>
import TablePersonalHistory from "@/components/obtainDataFromServlet/TablePersonalHistory";

export default {
  name: "PagePersonalHistory",
  components: {
    TablePersonalHistory
  }

}
</script>

<style scoped>

</style>