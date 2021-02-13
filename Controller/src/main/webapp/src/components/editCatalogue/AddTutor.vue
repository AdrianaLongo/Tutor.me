<template>
  <b-container class="mt-4">
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
        </b-form-group>

        <div v-if="tutorToAdd !== '' && courseSelected !== '' && courseSelected !== 'Inserisci un nuovo corso...'">
          <b-button @click="insertTutor(tutorToAdd, courseSelected)" variant="primary">Inserisci tutor</b-button>
        </div>

        <div v-if="tutorToAdd !== '' && courseSelected === 'Inserisci un nuovo corso...' && courseToAdd !== ''">
        <b-button @click="insertTutor(tutorToAdd, courseToAdd)" variant="primary">Inserisci tutor e corso</b-button>
      </div>


      </b-form-group>
    </b-card>
  </b-container>
</template>

<script>
import $ from "jquery";
import jQuery from "jquery";

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
    insertTutor(tutorToAdd, course){
      // tutorToAdd e' un oggetto (non una stringa come in AddCourse
      var tutor = tutorToAdd.split(' ');
      console.log(tutor)
      var nomeNuovoTutor = tutor[0].toString().charAt(0).toUpperCase() + tutor[0].toString().slice(1);
      console.log("nomeNuovoTutor: " + nomeNuovoTutor)
      var cognomeNuovoTutor = tutor[1].toString().charAt(0).toUpperCase() + tutor[1].toString().slice(1);
      console.log("cognomeNuovoTutor: " + cognomeNuovoTutor)
      var nomeCorso
      if(course.nome === undefined)
        nomeCorso = course.charAt(0).toUpperCase() + course.slice(1)
      else
        nomeCorso = course.nome
      console.log(course)
      // var _this = this;
      jQuery.post('http://localhost:8080/TWEB_war_exploded/TutorCourseServlet', {
        opCode: "insertTutor",
        nomeCorso: nomeCorso,
        nomeDocente: nomeNuovoTutor,
        cognomeDocente: cognomeNuovoTutor
      })
      .then(response => {
        console.log(response)
        setTimeout(() => {this.reset()}, 100)
        this.makeToast(nomeNuovoTutor, cognomeNuovoTutor, nomeCorso);
      })


      // this.makeToast()
      // setTimeout(() => {this.reset()}, 100)
      console.log("Ho inserito un tutor!")
    },
    makeToast(nomeNuovoTutor, cognomeNuovoTutor, nomeCorso){
      this.$bvToast.toast(
          `Hai aggiunto il tutor ${nomeNuovoTutor} ${cognomeNuovoTutor} per il corso ${nomeCorso} al catalogo.`,
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