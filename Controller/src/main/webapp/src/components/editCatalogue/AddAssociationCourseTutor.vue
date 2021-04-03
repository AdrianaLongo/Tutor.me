<template>
  <b-container class="mt-4">
    <b-card bg-variant="light" title="Associa un corso ad un tutor">
      <b-form-group label="Corso"
                    label-for="nested-course"
                    label-cols-md="1"
                    label-align-md="right"
      >
        <b-form-select v-model="courseSelected">
<!--          TODO: non far comparire i corsi che quel tutor insegna gia-->
          <option v-for="c in courses" :key="c.corso" :value="{courseName: c.nome}">{{ c.nome }}</option>
        </b-form-select>
      </b-form-group>

      <b-form-group label="Tutor"
          label-for="nested-tutor"
          label-cols-md="1"
          label-align-md="right"
      >
        <b-form-select v-model="tutorSelected">
<!--          TODO: non far comparire i tutor che già insegnano quel corso-->
          <option v-for="t in tutors" :key="t.id" :value="{tutorId: t.id, tutorName: t.nome, tutorSurname: t.cognome}">
            {{ t.nome }} {{t.cognome}}
          </option>
        </b-form-select>
      </b-form-group>

      <div v-if="tutorSelected !== '' && courseSelected !== ''">
        <b-button @click="addAssociation" variant="primary">Aggiungi associazione</b-button>
      </div>

    </b-card>
  </b-container>
</template>

<script>
// TODO: non far comparire il corso a cui e' gia associato
import jQuery from "jquery";
import $ from "jquery";

export default {
  name: "AddAssociationCourseTutor",
  data(){
    return{
      courses: '',
      tutors: '',
      tutorSelected: '',
      tutorName: '',
      tutorSurname: '',
      tutorId: '',
      courseSelected: '',
      courseName: '',
    }
  },
  beforeCreate: function() {
    // Potrebbero esserci state delle modifiche nel catalogo nel frattempo,
    // quindi non mi conviene recuperare dallo store tutti i corsi in catalogo;
    // non abbiamo bisogno di memorizzarli in catalogo, quindi non abbiamo bisogno dell'action per fare la richiesta
    var _this = this;
    $.getJSON('http://localhost:8080/TWEB_war_exploded/PopulateCorsiServlet', function (courses) {
      _this.courses = courses;
    });

    // Non c'e' bisogno di mantenere nello store la ricerca dei tutor di una determinata materia,
    // quindi
    $.getJSON('http://localhost:8080/TWEB_war_exploded/PopolaDocenteServlet', function(tutors){
      _this.tutors = tutors;
    })

  },
  methods: {
    // Non c'è bisogno di mantenere l'associazione appena fatta nello store,
    // quindi non abbiamo bisogno di action per fare la richiesta
    addAssociation: function(){
      console.log("corso" + this.courseSelected.courseName)
      console.log("tutor" + this.tutorSelected.tutorName + this.tutorSelected.tutorSurname)
      jQuery.post('http://localhost:8080/TWEB_war_exploded/ExistingEntAssociationServlet', {
        nomeCorso: this.courseSelected.courseName,
        nomeDocente: this.tutorSelected.tutorName,
        cognomeDocente: this.tutorSelected.tutorSurname,
        idDocente: this.tutorSelected.tutorId
      })

      this.makeToast()

      setTimeout(() => {this.reset()}, 100)

    },
    makeToast(){
      this.$bvToast.toast(
          `Hai associato il tutor ${this.tutorSelected.tutorName} ${this.tutorSelected.tutorSurname} al corso di ${this.courseSelected.courseName}.`,
          {
            title: `Catalogo aggiornato con successo!`,
            variant: 'success',
            solid: true
          })
    },
    reset() {
      this.tutorSelected = ''
      this.courseSelected = ''
    },
  }
}
</script>

<style scoped>

</style>