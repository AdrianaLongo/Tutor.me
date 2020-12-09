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
<!--    <availability v-if="this.$store.getters.tutorId !== ''"></availability>-->
    <availability></availability>
<!--     todo creare matrice/calendario in modo che si riempia con celle che hanno come id "lun1, mar2, ..."-->
  </div>

</template>

<script>
import courseSelect from "@/components/obtainDataFromServlet/courseSelect";
import tutorSelect from "@/components/obtainDataFromServlet/tutorSelect";
import availability from "@/components/obtainDataFromServlet/availability";

export default {
  name: "PageHome",
  components: {
    courseSelect,
    tutorSelect,
    availability
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
