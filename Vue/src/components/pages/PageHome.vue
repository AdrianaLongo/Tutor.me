<template>
  <div>
    <b-jumbotron header="Tutor.me">
      <p> Tutor.me è una piattaforma che ti permette di trovare il tutor adatto alle tue
        esigenze.
        Scegli il corso per cui hai bisogno, seleziona il tutor che preferisci ed è fatta!
      </p>
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


