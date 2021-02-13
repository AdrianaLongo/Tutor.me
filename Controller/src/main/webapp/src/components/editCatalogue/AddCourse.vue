<template>
  <b-container class="mt-4">
    <b-card bg-variant="light">
      <b-form-group
          label="Inserisci un nuovo corso nel catalogo"
          label-cols-lg="5"
          label-size="lg"
          label-class="font-weight-bold pt-0"
          class="mb-0"
      >
        <b-form-group
            label="Nome corso:"
            label-for="nested-course"
            label-cols-sm="3"
            label-align-sm="right"
        >
          <b-form-input id="nested-course" v-model="courseToAdd"></b-form-input>
        </b-form-group>

        <b-form-group
            label="Nome tutor:"
            label-for="nested-tutor"
            label-cols-sm="3"
            label-align-sm="right"
        >
          <b-form-select v-model="tutorSelected">
            <option v-for="tutor in jsonTutor" :key="tutor.id" :value="{id: tutor.id, nome: tutor.nome, cognome:tutor.cognome}">
              {{ tutor.nome }} {{tutor.cognome}}
            </option>
            <option id="newTutor">Inserisci un nuovo tutor...</option>
          </b-form-select>
          <b-form-input v-if="tutorSelected==='Inserisci un nuovo tutor...'" id="nested-course" v-model="tutorToAdd"></b-form-input>
        </b-form-group>

        <div v-if="courseToAdd !== '' && tutorSelected !== 'Inserisci un nuovo tutor...'">
          <b-button @click="insertCourse(courseToAdd, tutorSelected)" variant="primary">Inserisci corso</b-button>
        </div>

        <div v-if="courseToAdd !== '' && tutorSelected === 'Inserisci un nuovo tutor...'">
          <b-button @click="insertCourse(courseToAdd, tutorToAdd)" variant="primary">Inserisci corso e docente</b-button>
        </div>


      </b-form-group>

    </b-card>
  </b-container>
</template>

<script>
import $ from "jquery";
import jQuery from 'jquery'


export default {
  name: "newCourse",
  data(){
    return{
      jsonTutor: null,
      tutor: '',
      courseToAdd: '',
      courseAdded: false,
      tutorToPair: '',
      tutorSelected: '',
      tutorToAdd: '',
    }
  },
  beforeCreate: function() {
    var _this = this;
    $.getJSON('http://localhost:8080/TWEB_war_exploded/PopolaDocenteServlet', function(jsonTutor){
      _this.jsonTutor = jsonTutor;
    })
  },
  methods: {
    insertCourse(courseToAdd, tutor) {
      this.courseToAdd = this.courseToAdd.charAt(0).toUpperCase() + this.courseToAdd.slice(1)
      if(tutor.id !== undefined) {
        console.log("Tutor esistente")
        console.log("courseToAdd: " + courseToAdd)
        console.log("tutor.nome: " + tutor.nome)
        console.log("tutor.cognome: " + tutor.cognome)
        console.log("tutor.id: " + tutor.id)
        var _this = this;
        jQuery.post('http://localhost:8080/TWEB_war_exploded/CourseTutorAssociationServlet', {
          opCode: "insertCourse",
          nomeCorso: _this.courseToAdd,
          nomeDocente: tutor.nome,
          cognomeDocente: tutor.cognome,
          idDocente: tutor.id
        })
            .then(response => {
              console.log(response)
              setTimeout(() => {this.reset()}, 100)
              this.makeToast(tutor.nome, tutor.cognome);
            })
      } else {
        console.log("Nuovo tutor. Un nuovo id sarà generato dal backend")
        console.log("courseToAdd: " + courseToAdd)
        console.log("tutor: ")
        console.log(tutor)
        var nuovoTutor = tutor.split(' ');
        var nomeNuovoTutor = nuovoTutor[0].charAt(0).toUpperCase() + nuovoTutor[0].slice(1);
        console.log("nomeNuovoTutor: " + nomeNuovoTutor);
        var cognomeNuovoTutor = nuovoTutor[1].charAt(0).toUpperCase() + nuovoTutor[0].slice(1);
        console.log("cognomeNuovoTutor: " + cognomeNuovoTutor);
        _this = this;
        jQuery.post('http://localhost:8080/TWEB_war_exploded/CourseTutorAssociationServlet', {
          opCode: "insertCourseAndTutor",
          nomeCorso: _this.courseToAdd,
          nomeDocente: nomeNuovoTutor,
          cognomeDocente: cognomeNuovoTutor,
        })
            .then(response => {
              console.log(response)
              setTimeout(() => {this.reset()}, 100)
              this.makeToast(nomeNuovoTutor, cognomeNuovoTutor);
            })
      }
      this.courseAdded = true;

      console.log("Ho inserito il corso: " + this.courseToAdd)
    },
    makeToast(nomeTutor, cognomeTutor){
      this.$bvToast.toast(
          `Hai aggiunto in catalogo il corso ${this.courseToAdd} insegnato dal tutor ${nomeTutor} ${cognomeTutor}.`,
          {
            title: `Catalogo aggiornato con successo!`,
            variant: 'success',
            solid: true
          })
    },
    reset() {
      this.courseToAdd = ''
      this.tutorSelected = ''
    },
    pairCourseToTutor:function(){
      console.log("Ho associato il corso al tutor!")
    }
  }
}
</script>

<style scoped>

</style>