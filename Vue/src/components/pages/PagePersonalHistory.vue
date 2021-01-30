<template>
  <b-container>
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
  beforeCreate() {
    // console.log("token in localstorage: " + localStorage.access_token)
    console.log("token in store: " + this.$store.getters.currentToken)

  }

}
</script>

<style scoped>

</style>