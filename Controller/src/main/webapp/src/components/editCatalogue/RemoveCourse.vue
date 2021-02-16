<template>
  <b-container class="mt-4">

    <b-card bg-variant="light" title="Seleziona il corso che vuoi rimuovere dal catalogo"
    >
      <b-form-select v-model="courseToDelete">
        <option v-for="course in jsonCourses" :key="course.corso" :value="{nome: course.nome}">{{ course.nome }}</option>
      </b-form-select>

      <!--    <p>Corso selezionato in courseSelect: {{ courseName.nome }}</p>-->

      <div v-if="courseToDelete.nome !== undefined">
        <b-button @click="deleteCourse" variant="danger">Elimina corso</b-button>
      </div>
    </b-card>
  </b-container>
</template>

<script>
import $ from "jquery";
import jQuery from "jquery";


export default {
  name: "RemoveCourse",
  data(){
    return{
      jsonCourses: null,
      courseToDelete: '',
      courseChange: false,
    }
  },
  beforeCreate: function(){
    var _this = this;
    $.getJSON('http://localhost:8080/TWEB_war_exploded/PopulateCorsiServlet', function (jsonCourses) {
      _this.jsonCourses = jsonCourses;
    });
  },
  methods:{
    deleteCourse: function(){
      // console.log(this.courseToDelete.nome)
      jQuery.post('http://localhost:8080/TWEB_war_exploded/DeleteCourseServlet', {
        nomeCorso: this.courseToDelete.nome
      })
      this.makeToast()
      setTimeout(() => {this.reset()}, 100)
      console.log("Corso eliminato!")
    },
    makeToast(){
      this.$bvToast.toast(
          `Hai rimosso il corso ${this.courseToDelete.nome} dal catalogo.`,
          {
            title: `Catalogo aggiornato con successo!`,
            variant: 'success',
            solid: true
          })
    },
    reset() {
      this.courseToDelete = ''
    },
  }
}
</script>

<style scoped>

</style>