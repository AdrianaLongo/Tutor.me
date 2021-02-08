<template>
  <!-- TODO: rimuovere associazioni corso-docente (manca la servlet)-->
  <b-container class="mt-4">
    <b-card bg-variant="light">
      <b-form-group
          label="Rimuovi l'associazione di un tutor ad un corso"
          label-cols-lg="5"
          label-size="lg"
          label-class="font-weight-bold pt-0"
          class="mb-0"
      >
        <b-form-group
            label="Tutor:"
            label-for="nested-tutor"
            label-cols-sm="3"
            label-align-sm="right"
        >
          <b-form-select v-model="tutorSelected">
            <option v-for="tutor in jsonTutor" :key="tutor.id" :value="{id: tutor.id, nome: tutor.nome, cognome:tutor.cognome}">
              {{ tutor.nome }} {{tutor.cognome}}
            </option>
          </b-form-select>
        </b-form-group>

        <b-form-group
            label="Corso:"
            label-for="nested-course"
            label-cols-sm="3"
            label-align-sm="right"
        >
          <b-form-select v-model="courseSelected">
            <option v-for="course in jsonCourses" :key="course.corso" :value="{nome: course.nome}">{{ course.nome }}</option>
          </b-form-select>
        </b-form-group>

        <div v-if="tutorSelected !== '' && courseSelected !== ''">
          <b-button @click="removeAssociation" variant="primary">Rimuovi associazione</b-button>
        </div>

      </b-form-group>
    </b-card>
  </b-container>
</template>

<script>
import $ from "jquery";

export default {
  name: "RemoveAssociationCourseTutor",
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
    removeAssociation: function(){
      this.makeToast()
      console.log("Associazione rimossa!")
      setTimeout(() => {this.reset()}, 100)
    },
    makeToast(){
      this.$bvToast.toast(
          // TODO: sistemare nome cognome tutor
          `Hai rimosso l'associazione del tutor ${this.tutorSelected.nome} ${this.tutorSelected.cognome} al corso di ${this.courseSelected.nome}.`,
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