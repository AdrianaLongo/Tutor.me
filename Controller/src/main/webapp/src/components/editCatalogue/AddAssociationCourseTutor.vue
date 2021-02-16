<template>
  <b-container class="mt-4">
    <b-card bg-variant="light" title="Associa un tutor ad un corso">
      <b-form-group label="Tutor"
          label-for="nested-tutor"
          label-cols-md="1"
          label-align-md="right"
      >
        <b-form-select v-model="tutorSelected">
          <option v-for="tutor in jsonTutor" :key="tutor.id" :value="{id: tutor.id, nome: tutor.nome, cognome:tutor.cognome}">
            {{ tutor.nome }} {{tutor.cognome}}
          </option>
        </b-form-select>
      </b-form-group>

      <b-form-group label="Corso"
          label-for="nested-course"
          label-cols-md="1"
          label-align-md="right"
      >
        <b-form-select v-model="courseSelected">
          <option v-for="course in jsonCourses" :key="course.corso" :value="{nome: course.nome}">{{ course.nome }}</option>
        </b-form-select>
      </b-form-group>

      <div v-if="tutorSelected !== '' && courseSelected !== ''">
        <b-button @click="addAssociation" variant="primary">Aggiungi associazione</b-button>
      </div>

    </b-card>
  </b-container>
</template>

<script>
import $ from "jquery";
import jQuery from "jquery";

export default {
  name: "AddAssociationCourseTutor",
  data(){
    return{
      jsonCourses: null,
      jsonTutor: null,
      tutorSelected: '',
      courseSelected: '',
    }
  },
  beforeCreate: function() {
    _this = this;
    $.getJSON('http://localhost:8080/TWEB_war_exploded/PopulateCorsiServlet', function (jsonCourses) {
      _this.jsonCourses = jsonCourses;
    });

    var _this = this;
    $.getJSON('http://localhost:8080/TWEB_war_exploded/PopolaDocenteServlet', function(jsonTutor){
      _this.jsonTutor = jsonTutor;
    })
  },
  methods: {
    addAssociation: function(){
      console.log(this.courseSelected.nome)
      console.log(this.tutorSelected.nome)
      console.log(this.tutorSelected.cognome)
      console.log(this.tutorSelected.id)

      jQuery.post('http://localhost:8080/TWEB_war_exploded/ExistingEntAssociationServlet', {
        nomeCorso: this.courseSelected.nome,
        nomeDocente: this.tutorSelected.nome,
        cognomeDocente: this.tutorSelected.cognome,
        idDocente: this.tutorSelected.id
      })

      this.makeToast()

      setTimeout(() => {this.reset()}, 100)

    },
    makeToast(){
      this.$bvToast.toast(
          `Hai associato il tutor ${this.tutorSelected.nome} ${this.tutorSelected.cognome} al corso di ${this.courseSelected.nome}.`,
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