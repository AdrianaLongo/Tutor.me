<template>

  <b-container class="mt-4">

    <b-card bg-variant="light" title="Seleziona il tutor che vuoi rimuovere dal catalogo"
    >
      <b-form-select v-model="tutorSelected">
        <option v-for="tutor in jsonTutor" :key="tutor.id" :value="{id: tutor.id, nome: tutor.nome, cognome:tutor.cognome}">
          {{ tutor.nome }} {{tutor.cognome}}
        </option>
      </b-form-select>

      <!--    <p>Corso selezionato in courseSelect: {{ courseName.nome }}</p>-->

      <div v-if="tutorSelected.id !== undefined">
        <b-button @click="deleteTutor" variant="danger">Elimina tutor</b-button>
      </div>
    </b-card>
  </b-container>
</template>

<script>
import $ from "jquery";
import jQuery from "jquery";

export default {
  name: "RemoveTutor",
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
  methods:{
    deleteTutor: function(){
      var _this = this;
      console.log("_this.tutorSelected.id: ")
      console.log(_this.tutorSelected.id)
      jQuery.post('http://localhost:8080/TWEB_war_exploded/DeleteTutorServlet',{
        idDocente: _this.tutorSelected.id
      }).then(response=>{
        if(response.success === 1)
          this.makeToastOk()
        else
          this.makeToastErr()
      })
      setTimeout(() => {this.reset()}, 100)
      console.log("Tutor eliminato!")
    },
    makeToastOk(){
      this.$bvToast.toast(
          `Hai rimosso il tutor ${this.tutorSelected.nome} ${this.tutorSelected.cognome} dal catalogo.`,
          {
            title: `Catalogo aggiornato con successo!`,
            variant: 'success',
            solid: true
      })
    },
    makeToastErr(){
      this.$bvToast.toast(
          `Si e' verificato un errore. Riprova`,
          {
            title: `Aggiornamento catalogo fallito`,
            variant: 'danger',
            solid: true
          })
    },
    reset() {
      this.tutorSelected = ''
    },
  }
}
</script>

<style scoped>

</style>