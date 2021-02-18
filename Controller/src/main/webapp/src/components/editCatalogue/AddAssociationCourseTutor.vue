<template>
  <b-container class="mt-4">
    <b-card bg-variant="light" title="Associa un corso ad un tutor">
      <b-form-group label="Corso"
                    label-for="nested-course"
                    label-cols-md="1"
                    label-align-md="right"
      >
        <b-form-select v-model="courseSelected">
          <option v-for="course in jsonCourses" :key="course.corso" :value="{nome: course.nome}">{{ course.nome }}</option>
        </b-form-select>
      </b-form-group>

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
      jsonCourses: '',
      jsonTutor: '',
      tutorSelected: '',
      courseSelected: '',
    }
  },
  beforeCreate: function() {
    // Potrebbero esserci state delle modifiche nel catalogo nel frattempo,
    // quindi non mi conviene recuperare dallo store tutti i corsi in catalogo;
    // non abbiamo bisogno di memorizzarli in catalogo, quindi non abbiamo bisogno dell'action per fare la richiesta
    var _this = this;
    $.getJSON('http://localhost:8080/TWEB_war_exploded/PopulateCorsiServlet', function (jsonCourses) {
      _this.jsonCourses = jsonCourses;
    });

    // Non c'e' bisogno di mantenere nello store la ricerca dei tutor di una determinata materia,
    // quindi
    $.getJSON('http://localhost:8080/TWEB_war_exploded/PopolaDocenteServlet', function(jsonTutor){
      _this.jsonTutor = jsonTutor;
    })

  },
  methods: {
    // Non c'è bisogno di mantenere l'associazione appena fatta nello store,
    // quindi non abbiamo bisogno di action per fare la richiesta
    addAssociation: function(){
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