<template>
  <b-container class="mt-4">
    <!--  TODO: inserire tutor (manca la servlet)-->
    <b-card bg-variant="light">
      <b-form-group
          label="Inserisci un nuovo tutor nel catalogo"
          label-cols-lg="5"
          label-size="lg"
          label-class="font-weight-bold pt-0"
          class="mb-0"
      >
        <b-form-group
            label="Nome tutor:"
            label-for="nested-tutor"
            label-cols-sm="3"
            label-align-sm="right"
        >
          <b-form-input id="nested-tutor" v-model="tutorToAdd"></b-form-input>
        </b-form-group>

        <b-form-group
            label="Nome corso:"
            label-for="nested-course"
            label-cols-sm="3"
            label-align-sm="right"
        >
          <b-form-select v-model="courseSelected">
            <option v-for="course in jsonCourses" :key="course.corso" :value="{nome: course.nome}">{{ course.nome }}</option>
            <option id="newTutor">Inserisci un nuovo corso...</option>
          </b-form-select>
          <b-form-input v-if="courseSelected==='Inserisci un nuovo corso...'" id="nested-course" v-model="courseToAdd"></b-form-input>
          <!-- TODO: controllare che il nome sia scritto bene, con le maiuscole-->
        </b-form-group>

        <!-- TODO: se viene cambiata la condizione dell'if, l'altro bottone deve sparire -->
        <div v-if="tutorToAdd !== '' && courseSelected !== ''">
          <!-- TODO: quando si inserisce nuovo docente, far comparire il bottone dopo inserimento-->
          <b-button @click="insertTutor" variant="primary">Inserisci tutor</b-button>
        </div>

        <div v-if="tutorToAdd !== '' && courseToAdd !== ''">
        <!-- TODO: quando si inserisce nuovo docente, far comparire il bottone dopo inserimento-->
        <b-button @click="insertTutor" variant="primary">Inserisci tutor e corso</b-button>
      </div>


      </b-form-group>
    </b-card>
  </b-container>
</template>

<script>
import $ from "jquery";

export default {
  name: "AddTutor",
  data(){
    return{
        jsonCourses: null,
        courseName: '',
        courseChange: false,
      jsonTutor: null,
      tutor: '',
      courseToAdd: '',
      courseAdded: false,
      tutorToPair: '',
      courseSelected: '',
      tutorToAdd: '',
      tutorSelected: '',
    }
  },
  beforeCreate: function() {
    var _this = this;
    $.getJSON('http://localhost:8080/TWEB_war_exploded/PopulateCorsiServlet', function (jsonCourses) {
      _this.jsonCourses = jsonCourses;
    });

  },
  methods:{
    insertTutor: function(){
      this.makeToast()
      setTimeout(() => {this.reset()}, 100)
      console.log("Ho inserito un tutor!")
    },
    makeToast(){
      this.$bvToast.toast(
          // TODO: sistemare nome cognome tutor
          `Hai aggiunto il tutor ${this.tutorSelected.nome} ${this.tutorSelected.cognome} per il corso ${this.courseSelected.nome} al catalogo.`,
          {
            title: `Catalogo aggiornato con successo!`,
            variant: 'success',
            solid: true
          })
    },
    reset() {
      this.tutorToAdd = ''
      this.courseSelected = ''
    },
  }
}
</script>

<style scoped>

</style>