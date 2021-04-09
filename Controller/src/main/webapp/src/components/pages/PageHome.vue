<template>
  <div class="mt-2">
    <b-jumbotron>
      <template #header>Tutor.me</template>

      <template #lead>
        Tutor.me è una piattaforma che ti permette di trovare il tutor adatto alle tue
        esigenze.
        Scegli il corso per cui hai bisogno, seleziona il tutor che preferisci ed è fatta!
      </template>
    </b-jumbotron>

    <select-course></select-course>
    <select-tutor v-if="this.$store.getters.elencoTutor !== ''"></select-tutor>
    <table-availability v-if="this.$store.getters.elencoDisponibilita !== ''"></table-availability>
  </div>

</template>

<script>
import SelectCourse from "@/components/obtainDataFromServlet/SelectCourse";
import SelectTutor from "@/components/obtainDataFromServlet/SelectTutor";
import TableAvailability from "@/components/obtainDataFromServlet/TableAvailability";

export default {
  name: "PageHome",
  components: {
    SelectCourse,
    SelectTutor,
    TableAvailability
  },
  beforeDestroy() {
    // Altrimenti rimane sempre l'ultima ricerca fatta
    this.$store.state.tutorJSON = ''
    this.$store.state.disponibilitaJSON = ''
  },
}
</script>

