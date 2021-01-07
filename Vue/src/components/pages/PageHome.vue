<template>
  <div>
    <b-jumbotron header="Tutor.me">
      <p> Tutor.me è una piattaforma che ti permette di trovare il tutor adatto alle tue
        esigenze.
        Scegli il corso per cui hai bisogno, seleziona il tutor che preferisci ed è fatta!
      </p>
    </b-jumbotron>

    <show-catalogue></show-catalogue>
    <tutor-select v-if="this.$store.getters.elencoTutor !== ''"></tutor-select>
    <availabilityTable v-if="this.$store.getters.elencoDisponibilita !== ''"></availabilityTable>

  </div>

</template>

<script>
import showCatalogue from "@/components/obtainDataFromServlet/showCatalogue";
import tutorSelect from "@/components/obtainDataFromServlet/tutorSelect";
import availabilityTable from "@/components/obtainDataFromServlet/availabilityTable";

export default {
  name: "PageHome",
  components: {
    showCatalogue,
    tutorSelect,
    availabilityTable
  },
  data() {
    return {
      // courseSelected: false
    }
  },
  methods: {
  },
  // props: ['courseName'],
  created(){
    // console.log(this.courseName)
  }
}
</script>


