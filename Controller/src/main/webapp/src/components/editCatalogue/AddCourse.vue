<template>
  <b-container class="mt-4">
    <b-card bg-variant="light" title="Inserisci un nuovo corso nel catalogo">
      <b-form-group label="Nome corso"
          label-for="nested-course"
          label-cols-md="2"
          label-align-md="right"
      >
        <b-form-input id="nested-course" v-model="courseToAdd"></b-form-input>
      </b-form-group>

      <b-form-group label="Nome tutor"
          label-for="nested-tutor"
          label-cols-md="2"
          label-align-md="right"
      >
        <b-form-select v-model="tutorSelected">
          <option v-for="tutor in jsonTutor" :key="tutor.id" :value="{id: tutor.id, nome: tutor.nome, cognome:tutor.cognome}">
            {{ tutor.nome }} {{tutor.cognome}}
          </option>
          <option id="newTutor">Inserisci un nuovo tutor...</option>
        </b-form-select>
        <b-form-input v-if="tutorSelected==='Inserisci un nuovo tutor...'" id="nested-course" v-model="tutorToAdd"></b-form-input>
      </b-form-group>

      <div v-if="courseToAdd !== '' && tutorSelected !== '' && tutorSelected !== 'Inserisci un nuovo tutor...'">
        <b-button @click="insertCourse(courseToAdd, tutorSelected)" variant="primary">Inserisci corso</b-button>
      </div>

      <div v-if="courseToAdd !== '' && tutorSelected === 'Inserisci un nuovo tutor...' && tutorToAdd !== ''">
        <b-button @click="insertCourse(courseToAdd, tutorToAdd)" variant="primary">Inserisci corso e docente</b-button>
      </div>



    </b-card>
  </b-container>
</template>

<script>
import $ from "jquery";
import jQuery from 'jquery'


export default {
  name: "newCourse",
  data(){
    return{
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
    // Non c'e' bisogno di mantenere nello store la ricerca dei tutor,
    // quindi non abbiamo bisogno dell'action per fare la richiesta.
    // Questo metodo si ripete anche in altri component della stessa "pagina" per far sì che,
    // a modifiche diverse del catalogo (e quindi a richieste diverse al db) ci sia sempre a monte
    // un elenco aggiornato di corsi e/o tutor
    $.getJSON('http://localhost:8080/TWEB_war_exploded/PopolaDocenteServlet', function(jsonTutor){
      _this.jsonTutor = jsonTutor;
    })
  },
  methods: {
    insertCourse(courseToAdd, tutor) {
      this.courseToAdd = this.courseToAdd.charAt(0).toUpperCase() + this.courseToAdd.slice(1)
      if(tutor.id !== undefined) { // tutor esiste in catalogo
        var _this = this;
        // Non c'è bisogno di mantenere il corso appena inserito nello store,
        // quindi non abbiamo bisogno di action per fare la richiesta
        jQuery.post('http://localhost:8080/TWEB_war_exploded/CourseTutorAssociationServlet', {
          opCode: "insertCourse",
          nomeCorso: _this.courseToAdd,
          nomeDocente: tutor.nome,
          cognomeDocente: tutor.cognome,
          idDocente: tutor.id
        })
            .then(response => {
              setTimeout(() => {this.reset()}, 100)
              if(response.success === 1)
                this.makeToastOk(tutor.nome, tutor.cognome);
              else
                this.makeToastErr()
            })
      } else { // admin aggiunge nuovo tutor
        var tutorString = tutor.toString()
        var spaceCount = (tutorString.split(" ").length - 1); // conta quanti spazi ci sono

        if (spaceCount === 1) {// inserito un solo nome e un solo cognome
          // Controllo assenza di caratteri non validi nel nome e nel cognome
          var letters = /^[A-Za-z() ]+$/;
          if (letters.test(tutor)) {
            var nuovoTutor = tutor.split(' ');
            var nomeNuovoTutor = nuovoTutor[0].charAt(0).toUpperCase() + nuovoTutor[0].slice(1);
            var cognomeNuovoTutor = nuovoTutor[1].charAt(0).toUpperCase() + nuovoTutor[1].slice(1);
            _this = this;
            jQuery.post('http://localhost:8080/TWEB_war_exploded/CourseTutorAssociationServlet', {
              opCode: "insertCourseAndTutor",
              nomeCorso: _this.courseToAdd,
              nomeDocente: nomeNuovoTutor,
              cognomeDocente: cognomeNuovoTutor,
            })
                .then(response => {
                  setTimeout(() => {
                    this.reset()
                  }, 100)
                  if (response.success === 1)
                    this.makeToastOk(nomeNuovoTutor, cognomeNuovoTutor);
                  else
                    this.makeToastErr()
                })
            this.courseAdded = true;
          } else {
            this.makeToastNotAlpha()
          }
        } else { // inserito solo un nome o solo un cognome o piu o nomi o piu cognomi
          this.makeToastTooManySpaces()
        }
      }
    },
    makeToastOk(nomeTutor, cognomeTutor){
      this.$bvToast.toast(
          `Hai aggiunto in catalogo il corso ${this.courseToAdd} insegnato dal tutor ${nomeTutor} ${cognomeTutor}.`,
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
    makeToastTooManySpaces(){
      this.$bvToast.toast(
          `Non possono esistere spazi dentro il nome o il cognome del tutor.`,
          {
            title: `Aggiornamento catalogo fallito`,
            variant: 'danger',
            solid: true
          })
    },
    reset() {
      this.courseToAdd = ''
      this.tutorSelected = ''
    },
  }
}
</script>

<style scoped>

</style>