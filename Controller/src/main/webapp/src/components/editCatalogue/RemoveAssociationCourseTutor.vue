<template>
  <b-container class="mt-4">
    <b-card bg-variant="light" title="Rimuovi l'associazione di un corso ad un tutor">
      <!--All'inizio, entrambe le select sono riempite con tutti i corsi e tutti i tutor:-->
      <!-- OPZIONE 1) L'utente sceglie un corso -> la select del tutor mostra solo i tutor che insegnano quel corso  -->
      <!-- OPZIONE 2) L'utente sceglie un tutor -> la select del corso mostra solo i corsi da lui insegnati  -->

<!--      &lt;!&ndash; DEFAULT: entrambe le select sono riempite con tutti i corsi&ndash;&gt;-->
<!--      <b-form-group v-if="courseSelected === '' && tutorSelected === ''"-->
<!--          label="Rimuovi l'associazione di un tutor ad un corso"-->
<!--          label-cols-lg="5"-->
<!--          label-size="lg"-->
<!--          label-class="font-weight-bold pt-0"-->
<!--          class="mb-0"-->
<!--      >-->
<!--        <b-form-group label="Corso:"-->
<!--            label-for="nested-course"-->
<!--            label-cols-sm="3"-->
<!--            label-align-sm="right"-->
<!--        >-->
<!--          <b-form-select v-model="courseSelected">-->
<!--            <option v-for="course in jsonCourses" :key="course.corso" :value="{nome: course.nome}">{{ course.nome }}</option>-->
<!--          </b-form-select>-->
<!--        </b-form-group>-->

<!--        <b-form-group label="Tutor:"-->
<!--            label-for="nested-tutor"-->
<!--            label-cols-sm="3"-->
<!--            label-align-sm="right"-->
<!--        >-->
<!--          <b-form-select v-model="tutorSelected">-->
<!--            <option v-for="tutor in jsonTutor" :key="tutor.id" :value="{id: tutor.id, nome: tutor.nome, cognome:tutor.cognome}">-->
<!--              {{ tutor.nome }} {{tutor.cognome}}-->
<!--            </option>-->
<!--          </b-form-select>-->
<!--        </b-form-group>-->


<!--        <div v-if="tutorSelected !== '' && courseSelected !== ''">-->
<!--          <b-button @click="removeAssociation" variant="primary">Rimuovi associazione</b-button>-->
<!--        </div>-->

<!--      </b-form-group>-->

<!--      &lt;!&ndash; OPZIONE 1) L'utente sceglie un corso -> la select del tutor mostra solo i tutor che insegnano quel corso  &ndash;&gt;-->
<!--      <b-form-group v-if="courseSelected !== '' && tutorSelected === ''"-->
<!--          label="Rimuovi l'associazione di un corso ad un tutor"-->
<!--          label-cols-lg="5"-->
<!--          label-size="lg"-->
<!--          label-class="font-weight-bold pt-0"-->
<!--          class="mb-0"-->
<!--      >-->
<!--        <b-form-group label="Corso:"-->
<!--            label-for="nested-course"-->
<!--            label-cols-sm="3"-->
<!--            label-align-sm="right"-->
<!--        >-->
<!--          <b-form-select v-model="courseSelected" v-on:change="searchTutorsForCourse">-->
<!--            <option v-for="course in jsonCourses" :key="course.corso" :value="{nome: course.nome}">{{ course.nome }}</option>-->
<!--          </b-form-select>-->
<!--        </b-form-group>-->


<!--        <b-form-group label="Tutor:"-->
<!--            label-for="nested-tutor"-->
<!--            label-cols-sm="3"-->
<!--            label-align-sm="right"-->
<!--        >-->
<!--          <b-form-select v-model="tutorSelected">-->
<!--            <option v-for="tutor in jsonTutorsForCourse" :key="tutor.id" :value="{id: tutor.id, nome: tutor.nome, cognome:tutor.cognome}">-->
<!--              {{ tutor.nome }} {{tutor.cognome}}-->
<!--            </option>-->
<!--          </b-form-select>-->
<!--        </b-form-group>-->


<!--        <div v-if="tutorSelected !== '' && courseSelected !== ''">-->
<!--          <b-button @click="removeAssociation" variant="primary">Rimuovi associazione</b-button>-->
<!--        </div>-->

<!--      </b-form-group>-->

<!--      &lt;!&ndash; OPZIONE 2) L'utente sceglie un tutor -> la select del corso mostra solo i corsi da lui insegnati  &ndash;&gt;-->
<!--      <b-form-group v-if="courseSelected === '' && tutorSelected !== ''"-->
<!--                    label="Rimuovi l'associazione di un tutor ad un corso"-->
<!--                    label-cols-lg="5"-->
<!--                    label-size="lg"-->
<!--                    label-class="font-weight-bold pt-0"-->
<!--                    class="mb-0"-->
<!--      >-->
<!--        <b-form-group label="Corso:"-->
<!--            label-for="nested-course"-->
<!--            label-cols-sm="3"-->
<!--            label-align-sm="right"-->
<!--        >-->
<!--          <b-form-select v-model="courseSelected" v-on:change="searchCoursesForTutor">-->
<!--            <option v-for="course in jsonCoursesForTutor" :key="course.corso" :value="{nome: course.nome}">{{ course.nome }}</option>-->
<!--          </b-form-select>-->
<!--        </b-form-group>-->

<!--        <b-form-group-->
<!--            label="Tutor:"-->
<!--            label-for="nested-tutor"-->
<!--            label-cols-sm="3"-->
<!--            label-align-sm="right"-->
<!--        >-->
<!--          <b-form-select v-model="tutorSelected">-->
<!--            <option v-for="tutor in jsonTutor" :key="tutor.id" :value="{id: tutor.id, nome: tutor.nome, cognome:tutor.cognome}">-->
<!--              {{ tutor.nome }} {{tutor.cognome}}-->
<!--            </option>-->
<!--          </b-form-select>-->
<!--        </b-form-group>-->


<!--        <div v-if="tutorSelected !== '' && courseSelected !== ''">-->
<!--          <b-button @click="removeAssociation" variant="primary">Rimuovi associazione</b-button>-->
<!--        </div>-->

<!--      </b-form-group>-->

<!--      &lt;!&ndash; INFINE&ndash;&gt;-->
<!--      <b-form-group v-if="courseSelected !== '' && tutorSelected !== ''"-->
<!--                    label="Rimuovi l'associazione di un tutor ad un corso"-->
<!--                    label-cols-lg="5"-->
<!--                    label-size="lg"-->
<!--                    label-class="font-weight-bold pt-0"-->
<!--                    class="mb-0"-->
<!--      >-->
<!--        <b-form-group-->
<!--            label="Corso:"-->
<!--            label-for="nested-course"-->
<!--            label-cols-sm="3"-->
<!--            label-align-sm="right"-->
<!--        >-->
<!--          <b-form-select v-model="courseSelected">-->
<!--            <option v-for="course in jsonCourses" :key="course.corso" :value="{nome: course.nome}">{{ course.nome }}</option>-->
<!--          </b-form-select>-->
<!--        </b-form-group>-->

<!--        <b-form-group-->
<!--            label="Tutor:"-->
<!--            label-for="nested-tutor"-->
<!--            label-cols-sm="3"-->
<!--            label-align-sm="right"-->
<!--        >-->
<!--          <b-form-select v-model="tutorSelected">-->
<!--            <option v-for="tutor in jsonTutor" :key="tutor.id" :value="{id: tutor.id, nome: tutor.nome, cognome:tutor.cognome}">-->
<!--              {{ tutor.nome }} {{tutor.cognome}}-->
<!--            </option>-->
<!--          </b-form-select>-->
<!--        </b-form-group>-->


<!--        <div v-if="tutorSelected !== '' && courseSelected !== ''">-->
<!--          <b-button @click="removeAssociation" variant="primary">Rimuovi associazione</b-button>-->
<!--        </div>-->

<!--      </b-form-group>-->

      <b-form-group label="Corso"
                    label-for="nested-course"
                    label-cols-md="2"
                    label-align-md="right"
      >
        <b-form-select v-if="jsonCoursesForTutor === null" v-model="courseSelected" v-on:change="searchTutorsForCourse">
          <option v-for="course in jsonCourses" :key="course.corso" :value="{nome: course.nome}">{{ course.nome }}</option>
        </b-form-select>
        <b-form-select v-else-if="jsonCoursesForTutor !== null" v-model="courseSelected">
          <option v-for="course in jsonCoursesForTutor" :key="course.corso" :value="{nome: course.nome}">{{ course.nome }}</option>
        </b-form-select>
      </b-form-group>


      <b-form-group label="Tutor"
                    label-for="nested-tutor"
                    label-cols-md="2"
                    label-align-md="right"
      >
        <!--Se viene selezionato un tutor, vengono mostrati i corsi relativi a quel tutor...-->
        <b-form-select v-if="jsonTutorsForCourse === null" v-model="tutorSelected" v-on:change="searchCoursesForTutor">
          <option v-for="tutor in jsonTutor" :key="tutor.id" :value="{id: tutor.id, nome: tutor.nome, cognome:tutor.cognome}">
            {{ tutor.nome }} {{tutor.cognome}}
          </option>
        </b-form-select>
        <!--...altrimenti di base vengono mostrati tutti i corsi-->
        <b-form-select v-else-if="jsonTutorsForCourse !== null" v-model="tutorSelected">
          <option v-for="tutor in jsonTutorsForCourse" :key="tutor.id" :value="{id: tutor.id, nome: tutor.nome, cognome:tutor.cognome}">
            {{ tutor.nome }} {{tutor.cognome}}
          </option>
        </b-form-select>
      </b-form-group>


      <div v-if="tutorSelected !== '' && courseSelected !== ''">
        <b-button @click="removeAssociation" variant="primary">Rimuovi associazione</b-button>
      </div>



      <!--      <b-form-group -->
<!--          label="Rimuovi l'associazione di un corso ad un tutor"-->
<!--          label-cols-lg="5"-->
<!--          label-size="lg"-->
<!--          label-class="font-weight-bold pt-0"-->
<!--          class="mb-0"-->
<!--      >-->
<!--        <b-form-group label="Corso:"-->
<!--            label-for="nested-course"-->
<!--            label-cols-sm="3"-->
<!--            label-align-sm="right"-->
<!--        >-->
<!--          <b-form-select v-if="tutorSelected === ''" v-model="courseSelected" v-on:change="searchTutorsForCourse">-->
<!--            <option v-for="course in jsonCourses" :key="course.corso" :value="{nome: course.nome}">{{ course.nome }}</option>-->
<!--          </b-form-select>-->
<!--          <b-form-select v-else-if="tutorSelected !== '' && courseSelected === ''" v-model="courseSelected">-->
<!--            <option v-for="course in jsonCoursesForTutor" :key="course.corso" :value="{nome: course.nome}">{{ course.nome }}</option>-->
<!--          </b-form-select>-->
<!--        </b-form-group>-->


<!--        <b-form-group label="Tutor:"-->
<!--            label-for="nested-tutor"-->
<!--            label-cols-sm="3"-->
<!--            label-align-sm="right"-->
<!--        >-->
<!--          &lt;!&ndash;Se viene selezionato un tutor, vengono mostrati i corsi relativi a quel tutor...&ndash;&gt;-->
<!--          <b-form-select v-if="courseSelected === ''" v-model="tutorSelected" v-on:change="searchCoursesForTutor">-->
<!--            <option v-for="tutor in jsonTutor" :key="tutor.id" :value="{id: tutor.id, nome: tutor.nome, cognome:tutor.cognome}">-->
<!--              {{ tutor.nome }} {{tutor.cognome}}-->
<!--            </option>-->
<!--          </b-form-select>-->
<!--          &lt;!&ndash;...altrimenti di base vengono mostrati tutti i corsi&ndash;&gt;-->
<!--          <b-form-select v-else-if="courseSelected !== '' && tutorSelected === ''" v-model="tutorSelected">-->
<!--            <option v-for="tutor in jsonTutorsForCourse" :key="tutor.id" :value="{id: tutor.id, nome: tutor.nome, cognome:tutor.cognome}">-->
<!--              {{ tutor.nome }} {{tutor.cognome}}-->
<!--            </option>-->
<!--          </b-form-select>-->
<!--        </b-form-group>-->


<!--        <div v-if="tutorSelected !== '' && courseSelected !== ''">-->
<!--          <b-button @click="removeAssociation" variant="primary">Rimuovi associazione</b-button>-->
<!--        </div>-->

<!--      </b-form-group>-->
    </b-card>
  </b-container>
</template>

<script>
import $ from "jquery";
import jQuery from "jquery";

export default {
  name: "RemoveAssociationCourseTutor",
  data(){
    return{
      jsonCourses: null,
      jsonTutor: null,
      jsonTutorsForCourse: null,
      jsonCoursesForTutor: null,
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
    searchCoursesForTutor(tutorSelected){
      console.log("sono in searchCoursesForTutor per il tutor: ")
      console.log(tutorSelected)
      var _this = this;
      jQuery.getJSON({
        type: "GET",
        url: 'http://localhost:8080/TWEB_war_exploded/cercaCorsoServlet',
        data: 'idDocente='+_this.tutorSelected.id,
        success: function (jsonCoursesForTutor) {
          _this.jsonCoursesForTutor = jsonCoursesForTutor;
          // console.log(jsonTutor);
          // console.log(jsonTutor);
          // this.$store.commit("retrieveTutors", jsonTutor)
          // console.log("tutor in memoria " + this.$store.getters.elencoTutor);

          // _this.state.tutorJSON = jsonTutor;
          console.log(jsonCoursesForTutor);

        }
      });
    },
    searchTutorsForCourse(courseSelected){
      console.log("sono in searchTutorsForCourse per il corso: ")
      console.log(courseSelected)
      var _this = this;
      jQuery.getJSON({
        type: "GET",
        url: 'http://localhost:8080/TWEB_war_exploded/cercaTutorServlet',
        data: 'corso='+_this.courseSelected.nome,
        success: function (jsonTutorsForCourse) {
          _this.jsonTutorsForCourse = jsonTutorsForCourse;
          // console.log(jsonTutor);
          // console.log(jsonTutor);
          // this.$store.commit("retrieveTutors", jsonTutor)
          // console.log("tutor in memoria " + this.$store.getters.elencoTutor);

          // _this.state.tutorJSON = jsonTutor;
          console.log(jsonTutorsForCourse);

        }
      });
    },
    removeAssociation: function(){
      var _this = this;
      console.log("_this.courseSelected: ")
      console.log(_this.courseSelected.nome)
      console.log("_this.tutorSelected: ")
      console.log(_this.tutorSelected.id)
      jQuery.post('http://localhost:8080/TWEB_war_exploded/DeleteAssociationServlet', {
        nomeCorso: _this.courseSelected.nome,
        idDocente: _this.tutorSelected.id
      })

      this.makeToast()
      console.log("Associazione rimossa!")
      setTimeout(() => {this.reset()}, 100)
    },
    makeToast(){
      this.$bvToast.toast(
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