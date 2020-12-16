<template>
  <div>
    <b-container>
      <div>
        <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            description="Seleziona un tutor per il corso scelto."
            label="Seleziona un tutor: "
            label-for="input-horizontal"
        >
          <b-select v-model="tutor">
            <option v-for="elem in json" :key="elem.id" :value="{id: elem.id, nome: elem.nome, cognome:elem.cognome}">{{
                elem.nome }}
            {{elem
                .cognome}}</option>
          </b-select>

          <div v-if="tutor.id !== undefined">
            <b-button @click="selectTutor" variant="success">Prenota tutor</b-button>
          </div>
        </b-form-group>
    </div>


<!--    <h3>Per il corso di {{this.$store.getters.courseName}} sono disponibili i seguenti tutor: </h3>-->

<!--    <b-select v-model="tutor">-->
<!--      <option v-for="elem in json" :key="elem.id" :value="{id: elem.id}">{{ elem.nome }} {{elem-->
<!--          .cognome}}</option>-->
<!--    </b-select>-->

<!--    <div v-if="tutor.id !== undefined">-->
<!--      <b-button @click="selectTutor" variant="success">Prenota tutor</b-button>-->
<!--    </div>-->
    </b-container>
  </div>
</template>

<script>
import $ from "jquery";

export default {
  name: "tutorSelect",
  data() {
    return {
      url: 'http://localhost:8081/TWEB_war_exploded/cercaTutorServlet', // è il JSON!
      json: null,
      tutor : ''
    }
  },
  created: function() {
    var _this = this;
    console.log("Corso scelto: " + this.$store.getters.courseName);
    var corso = this.$store.getters.courseName;
    // $.get('http://localhost:8081/TWEB_war_exploded/cercaTutorServlet', "corso", function
    //     (json) {
    //   _this.json = json;
    // });
    $.getJSON({
      type: "GET",
      url: 'http://localhost:8081/TWEB_war_exploded/cercaTutorServlet',
      data: 'corso='+corso,
      success: function (json) {
        _this.json = json;
        // console.log(json); // array di tanti elementi quanti sono i tutor per quel corso
      }
    });
  },
  mounted () {},
  methods: {
    selectTutor: function(){
      // console.log(this.tutor.id); // torna l'id
      this.$store.commit("selectTutorId", this.tutor.id);
      this.$store.commit("selectTutorName", this.tutor.nome);
      this.$store.commit("selectTutorSurname", this.tutor.cognome);
      console.log("courseName in store = " + this.$store.getters.courseName); // torna il corso
      console.log("tutorId in store = " + this.$store.getters.tutorId); // torna l'id
    }
  }

}
</script>

<style scoped>

</style>