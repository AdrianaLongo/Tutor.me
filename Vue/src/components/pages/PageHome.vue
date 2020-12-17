<template>
  <div>
    <b-jumbotron header="Tutor.me">
      <p> Tutor.me è una piattaforma che ti permette di trovare il tutor adatto alle tue
        esigenze.
        Scegli il corso per cui hai bisogno, seleziona il tutor che preferisci ed è fatta!
      </p>
    </b-jumbotron>

    <course-select></course-select>
    <tutor-select v-if="this.$store.getters.courseName !== ''"></tutor-select>
<!--    <availabilityTable v-if="this.$store.getters.tutorId !== ''"></availabilityTable>-->
  </div>

</template>

<script>
import courseSelect from "@/components/obtainDataFromServlet/courseSelect";
import tutorSelect from "@/components/obtainDataFromServlet/tutorSelect";
// import availabilityTable from "@/components/obtainDataFromServlet/availabilityTable";

export default {
  name: "PageHome",
  components: {
    courseSelect,
    tutorSelect,
    // availabilityTable
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


