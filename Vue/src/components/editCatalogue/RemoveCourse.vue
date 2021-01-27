<template>
  <b-container class="mt-4">
    <!--  TODO: rimuovere corsi (manca la servlet)-->

    <b-card bg-variant="light">
      <b-form-group
          label="Seleziona il corso che vuoi rimuovere dal catalogo"
          label-cols-lg="5"
          label-size="lg"
          label-class="font-weight-bold pt-0"
          class="mb-0"
      >
        <b-form-select v-model="courseToDelete">
          <option v-for="course in jsonCourses" :key="course.corso" :value="{nome: course.nome}">{{ course.nome }}</option>
        </b-form-select>

        <!--    <p>Corso selezionato in courseSelect: {{ courseName.nome }}</p>-->

        <div v-if="courseToDelete.nome !== undefined">
          <b-button @click="deleteCourse" variant="danger">Elimina corso</b-button>
        </div>
      </b-form-group>
    </b-card>
  </b-container>
</template>

<script>
import $ from "jquery";

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
    $.getJSON('http://localhost:8081/TWEB_war_exploded/PopulateCorsiServlet', function (jsonCourses) {
      _this.jsonCourses = jsonCourses;
    });
  },
  methods:{
    deleteCourse: function(){
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