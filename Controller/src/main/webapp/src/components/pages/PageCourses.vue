<template>
  <b-container class="pageCourses">
    <h1 class="">Catalogo corsi</h1>

    <div class="container" >
      <div class="row">
        <div class="col-sm-4" v-for="course in courses" :key="course.nome" :value="{nome: course.nome}">
          <b-card v-model="tutor" bg-variant="info" text-variant="white" :header="course.nome" class="mb-sm-4 text-center" >
            <ul v-for="n in 3" :key="n">
              <li>n</li>
            </ul>
            <ul v-for="t in populateTutor(course.nome)" :key="t.id">
              <li>tutor</li>
            </ul>
            <!--            <b-card-text> non riconosce "nome" {{// populateTutor(nome)}} </b-card-text>-->
<!--            <b-card-text> console ok ma non mostra {{populateTutor(course.nome)}} </b-card-text>-->

            <!--            <b-card-text :jsonTutor="populateTutor(course.nome)"> loop {{ jsonTutor }} </b-card-text>-->
<!--            <b-card-text> corso vuoto {{ populateTutor(course.corso) }}  </b-card-text>-->
<!--            <b-card-text> corso vuoto {{ populateTutor(course.nomeCorso) }}  </b-card-text>-->
<!--            <b-card-text :value="{tutor: populateTutor(course.nome)}">-->
<!--              corso vuoto ma servlet restituisce cose giuste {{ tutor }}-->
<!--            </b-card-text>-->
<!--            <b-card-text :tutor=populateTutor(course.nome)>-->
<!--              corso vuoto ma servlet restituisce cose giuste {{ tutor }}-->
<!--            </b-card-text>-->
<!--            <b-card-text :tutor="populateTutor(course.nome)"> -->
<!--              corso vuoto ma servlet restituisce cose giuste {{tutor}}-->
<!--            </b-card-text>-->
<!--            <b-card-text :value="{jsonTutor: populateTutor(course.nome)}">-->
<!--              <ul v-for="tutor in jsonTutor" :key="tutor.id" >-->
<!--                <li> loop {{ tutor.id }} </li>-->
<!--              </ul>-->
<!--            </b-card-text>-->
<!--            <b-card-text>-->
<!--              <ul v-for="tutor in populateTutor(course.nome)" :key="tutor.id" :value="{id: tutor.id}">-->
<!--                <li> {{ tutor.id }} </li>-->
<!--              </ul>-->
<!--            </b-card-text>-->
<!--            <b-card-text v-for="tutor in populateTutor(course.nome)" :key="tutor.id">-->
<!--              prova-->
<!--            </b-card-text>-->

            <!--              <b-card-text :t="populateTutor(course.nome)"> corso vuoto ma servlet restituisce cose giuste {{-->
<!--                  t }}-->
<!--{{tutor}}-->
<!--              <ul v-for="tutor in populateTutor(course.nome)" :key="tutor.id" >-->
<!--                <li> prova </li>-->
<!--              </ul>-->

            <!--
<b-card-text>non lo trova e chiama la servlet in loop {{populateTutor(value)}}</b-card-text>-->
            <!--            <b-card-text> CORS policy {{ populateTutor(course) }}  </b-card-text>-->
<!--                        <b-card-text>-->
<!--&lt;!&ndash;                          {{ populateTutor(course.nome) }}&ndash;&gt;-->
<!--&lt;!&ndash;                          {{ jsonTutor }}&ndash;&gt;-->
<!--&lt;!&ndash;                          {{ jsonTutor.nome }}&ndash;&gt;-->
<!--&lt;!&ndash;                          {{ this.jsonTutor }}&ndash;&gt;-->
<!--&lt;!&ndash;                          {{ jsonTutor.id }}&ndash;&gt;-->


<!--                          <ul v-for="t in populateTutor(course.nome)" :key="t.id">-->
<!--                            <li>tutor = {{ t.id }}</li>-->
<!--                          </ul>-->
<!--                        </b-card-text>-->
            <!--            <b-card-text> corso vuoto (non legge populateTutor {{ populateTutor(courseName) }}
            </b-card-text>-->
            <!--            <b-card-text> corso undefined {{ populateTutor(courseName.nome) }}  </b-card-text>-->
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

<!--            <b-card-text>-->
<!--              <ul>-->
<!--                <li> {{populateTutor(course.nome)}}</li>-->
<!--              </ul>-->
<!--            </b-card-text>-->

            <!--            <b-button variant="light" @click="showTutors()">Controlla tutor disponibili</b-button>-->

          </b-card>
        </div>
      </div>
    </div>


  </b-container>
</template>

<script>
import $ from "jquery";

export default {
  name: "PageCourses",
  data() {
    return {
      courses: null,
      jsonTutor: null,
      courseName: '',
      tutor: 'tutor',
      t : 't'
    }
  },
  created: function() {
    var _this = this;
    $.getJSON('http://localhost:8080/TWEB_war_exploded/PopulateCorsiServlet', function (courses) {
      _this.courses = courses;
      console.log(courses); // array di 7 elementi
    });
  },

  methods: {
    populateTutor: function(corso){
      var _this = this;
      // console.log("Corso scelto: " + courseName);
      // var corso = this.$store.getters.courseName;
      // $.get('http://localhost:8080/TWEB_war_exploded/cercaTutorServlet', "corso", function
      //     (json) {
      //   _this.json = json;
      // });
      $.getJSON({
        type: "GET",
        url: 'http://localhost:8080/TWEB_war_exploded/cercaTutorServlet',
        data: 'corso=' + corso,
        success: function (jsonTutor) {
          _this.jsonTutor = jsonTutor;
          console.log("Ottengo tutor per il corso " + corso);
          console.log(jsonTutor); // array di tanti elementi quanti sono i tutor per quel corso
          // return tutor;
        }
      });
    },
    selectCourse: function(){
      console.log(this.courseName.nome);
      this.$store.commit("selectCourse", this.courseName.nome);
    },
    selectTutor: function(){
      // console.log(this.tutor.id); // torna l'id
      this.$store.commit("showAvailiability", this.tutor.id);
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