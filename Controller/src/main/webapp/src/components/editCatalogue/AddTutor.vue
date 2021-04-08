<template>
  <b-container class="mt-4">
    <b-card bg-variant="light" title="Inserisci un nuovo tutor nel catalogo">
      <b-form-group label="Nome tutor"
          label-for="nested-tutor"
          label-cols-md="2"
          label-align-md="right"
      >
        <b-form-input id="nested-tutor" v-model="tutorToAdd"></b-form-input>
      </b-form-group>

      <b-form-group label="Nome corso"
          label-for="nested-course"
          label-cols-md="2"
          label-align-md="right"
      >
        <b-form-select v-model="courseSelected">
          <option v-for="course in jsonCourses" :key="course.corso" :value="{nome: course.nome}">{{ course.nome }}</option>
          <option id="newTutor">Inserisci un nuovo corso...</option>
        </b-form-select>
        <b-form-input v-if="courseSelected==='Inserisci un nuovo corso...'" id="nested-course" v-model="courseToAdd"></b-form-input>
      </b-form-group>

      <div v-if="tutorToAdd !== '' && courseSelected !== '' && courseSelected !== 'Inserisci un nuovo corso...'">
        <b-button @click="insertTutor(tutorToAdd, courseSelected)" variant="primary">Inserisci tutor</b-button>
      </div>

      <div v-if="tutorToAdd !== '' && courseSelected === 'Inserisci un nuovo corso...' && courseToAdd !== ''">
        <b-button @click="insertTutor(tutorToAdd, courseToAdd)" variant="primary">Inserisci tutor e corso</b-button>
      </div>


    </b-card>
  </b-container>
</template>

<script>
import $ from "jquery";
import jQuery from "jquery";

export default {
  name: "AddTutor",
  data(){
    return{
      jsonCourses: null,
      tutor: '',
      courseToAdd: '',
      courseSelected: '',
      tutorToAdd: '',
    }
  },
  beforeCreate: function() {
    var _this = this;
    // Non c'e' bisogno di mantenere nello store la ricerca dei corsi,
    // quindi non abbiamo bisogno dell'action per fare la richiesta.
    // Questo metodo si ripete anche in altri component della stessa "pagina" per far sì che,
    // a modifiche diverse del catalogo (e quindi a richieste diverse al db) ci sia sempre a monte
    // un elenco aggiornato di corsi e/o tutor
    $.getJSON('http://localhost:8080/TWEB_war_exploded/PopulateCorsiServlet', function (jsonCourses) {
      _this.jsonCourses = jsonCourses;
    });

  },
  methods:{
    insertTutor(tutorToAdd, course){
      // Controllo assenza di caratteri non validi nel nome e nel cognome
      var letters = /^[A-Za-z() ]+$/;
      if(letters.test(tutorToAdd)){
        var tutor = tutorToAdd.split(' ');

        var nomeNuovoTutor = tutor[0].toString().charAt(0).toUpperCase() + tutor[0].toString().slice(1);
        var cognomeNuovoTutor = tutor[1].toString().charAt(0).toUpperCase() + tutor[1].toString().slice(1);
        var nomeCorso
        if(course.nome === undefined) // corso creato dall'utente
          nomeCorso = course.charAt(0).toUpperCase() + course.slice(1)
        else // corso gia' esistente
          nomeCorso = course.nome

        // Non abbiamo bisogno di memorizzare nello store i dati inseriti,
        // quindi non abbiamo bisogno dell'action per fare la richiesta
        jQuery.post('http://localhost:8080/TWEB_war_exploded/TutorCourseServlet', {
          opCode: "insertTutor",
          nomeCorso: nomeCorso,
          nomeDocente: nomeNuovoTutor,
          cognomeDocente: cognomeNuovoTutor
        })
            .then(response => {
              setTimeout(() => {this.reset()}, 100)
              if(response.success === 1)
                this.makeToastOk(nomeNuovoTutor, cognomeNuovoTutor, nomeCorso);
              else
                this.makeToastErr()
            })
      } else {
        this.makeToastNotAlpha()
      }

    },
    makeToastOk(nomeNuovoTutor, cognomeNuovoTutor, nomeCorso){
      this.$bvToast.toast(
          `Hai aggiunto il tutor ${nomeNuovoTutor} ${cognomeNuovoTutor} per il corso ${nomeCorso} al catalogo.`,
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
    makeToastNotAlpha(){
      this.$bvToast.toast(
          `Il nome del tutor deve contenere solo lettere.`,
          {
            title: `Aggiornamento catalogo fallito`,
            variant: 'danger',
            solid: true
          })
    },
    reset() {
      this.tutorToAdd = ''
      this.courseSelected = ''
    },
  }
}
</script>

<style scoped>

</style>