<template>
  <div class="pageCourses">
    <h1 class="">Catalogo corsi</h1>

    <div class="container">
      <div class="row">
        <div class="col-sm-4" v-for="course in courses" :key="course.nome" :value="{nome: course.nome}">
          <b-card bg-variant="info" text-variant="white" :header="course.nome" class="text-center" >


<!--            <b-card-text> CORS policy {{ populateTutor(course) }}  </b-card-text>-->
<!--            <b-card-text>-->
<!--              {{ // populateTutor(course.nome) }}-->
<!--&lt;!&ndash;              {{ tutors }}&ndash;&gt;-->
<!--&lt;!&ndash;              {{ tutors.nome }}&ndash;&gt;-->
<!--&lt;!&ndash;              {{ this.tutors }}&ndash;&gt;-->
<!--&lt;!&ndash;              {{ tutors.id }}&ndash;&gt;-->


<!--              <ul v-for="t in populateTutor(course.nome)" :key="t.id">-->
<!--                <li>tutor = {{ t.id }}</li>-->
<!--              </ul>-->
<!--            </b-card-text>-->
<!--            <b-card-text> corso undefined {{ populateTutor(course.corso) }}  </b-card-text>-->
<!--            <b-card-text> corso undefined {{ populateTutor(course.nomeCorso) }}  </b-card-text>-->
<!--            <b-card-text> corso vuoto {{ populateTutor(courseName) }}  </b-card-text>-->
<!--            <b-card-text> corso undefined {{ populateTutor(courseName.nome) }}  </b-card-text>-->
<!--            <b-card-text> errore: nome di undefined {{ populateTutor(course.nome) }}  </b-card-text>-->
<!--            <b-card-text> errore: courseName di undefined {{ populateTutor(this.courseName) }}  </b-card-text>-->
<!--            <b-card-text> errore: $store di undefined {{ populateTutor(this.$store.getters.courseName) }}
</b-card-text> -->
<!--            <b-card-text> corso undefined  {{ populateTutor(Fisica) }} </b-card-text>-->
<!--            <b-card-text> mette nell'header i prof di Fisica e li richiama all'infinito {{ populateTutor("Fisica")
 }}
</b-card-text>-->
<!--            <b-card-text>-->
<!--              <ul >-->
<!--&lt;!&ndash;                <li>{{ populateTutor("Fisica") }}</li>&ndash;&gt;-->
<!--              </ul>-->

<!--            </b-card-text>-->

<!--            <b-button variant="light" @click="selectCourse()">Controlla tutor disponibili</b-button>-->

          </b-card>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import $ from "jquery";

export default {
  name: "PageCourses",
  data() {
    return {
      courses: null,
      tutors: null,
      courseName: ''
    }
  },
  created:
    // CHIAMATA A SERVLET PER GENERARE LE CARD DEI CORSI
      function() {
        var _this = this;
        $.getJSON('http://localhost:8081/TWEB_war_exploded/PopulateCorsiServlet', function (courses) {
          _this.courses = courses;
          console.log(courses); // array di 7 elementi
        });
  },

  methods: {
    populateTutor: function(corso){
      var _this = this;
      // console.log("Corso scelto: " + courseName);
      // var corso = this.$store.getters.courseName;
      // $.get('http://localhost:8081/TWEB_war_exploded/cercaTutorServlet', "corso", function
      //     (json) {
      //   _this.json = json;
      // });
      $.getJSON({
        type: "GET",
        url: 'http://localhost:8081/TWEB_war_exploded/cercaTutorServlet',
        data: 'corso=' + corso,
        success: function (tutors) {
          _this.tutors = tutors;
          console.log(tutors); // array di tanti elementi quanti sono i tutor per quel corso
          // return tutor;
        }
      });
      return this.tutors;
    },
    selectCourse: function(){
      console.log(this.courseName.nome);
      this.$store.commit("selectCourse", this.courseName.nome);
    },
    selectTutor: function(){
      // console.log(this.tutor.id); // torna l'id
      this.$store.commit("selectTutor", this.tutor.id);
      console.log(this.$store.getters.tutorId); // torna l'id
    },
  }
}

</script>

<style scoped>
.pageCourses{
  /*padding-top: 50px;*/
}
</style>