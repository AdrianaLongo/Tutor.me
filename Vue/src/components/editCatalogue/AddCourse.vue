<template>
  <b-container class="mt-4">
    <!--  TODO: inserire corsi (manca la servlet)-->
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
          <!-- TODO: controllare che il nome sia scritto bene, con le maiuscole-->
        </b-form-group>

        <!-- TODO: se viene cambiata la condizione dell'if, l'altro bottone deve sparire -->
        <div v-if="courseToAdd !== '' && tutorSelected !== ''">
          <!-- TODO: quando si inserisce nuovo docente, far comparire il bottone dopo inserimento-->
          <b-button @click="insertCourse" variant="primary">Inserisci corso</b-button>
        </div>

        <div v-if="courseToAdd !== '' && tutorToAdd !== ''">
          <!-- TODO: quando si inserisce nuovo docente, far comparire il bottone dopo inserimento-->
          <b-button @click="insertCourse" variant="primary">Inserisci corso e docente</b-button>
        </div>


      </b-form-group>

    </b-card>
  </b-container>
</template>

<script>
import $ from "jquery";

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
    $.getJSON('http://localhost:8081/TWEB_war_exploded/PopolaDocenteServlet', function(jsonTutor){
      _this.jsonTutor = jsonTutor;
    })
  },
  methods: {
    insertCourse: function (){
      this.courseAdded = true;
      setTimeout(() => {this.reset()}, 100)
      this.makeToast();
      console.log("Ho inserito il corso: " + this.courseToAdd.charAt(0).toUpperCase() + this.courseToAdd.slice(1))
    },
    makeToast(){
      this.$bvToast.toast(
          // TODO: sistemare nome cognome tutor
          `Hai aggiunto in catalogo il corso ${this.courseToAdd.nome} insegnato dal tutor ${this.tutorSelected.nome} ${this.tutorSelected.cognome}.`,
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