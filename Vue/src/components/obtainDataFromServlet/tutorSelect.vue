<template>
  <div>
    <h3>Per il corso di {{this.$store.getters.courseName}} sono disponibili i seguenti tutor: </h3>

    <b-select v-model="tutor">
      <option v-for="elem in json" :key="elem.id" :value="{id: elem.id}">{{ elem.nome }} {{elem
          .cognome}}</option>
    </b-select>

    <div v-if="tutor.id !== undefined">
      <b-button @click="selectTutor">Prenota tutor</b-button>
    </div>

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
      this.$store.commit("selectTutor", this.tutor.id);
      console.log(this.$store.getters.tutorId); // torna l'id
    }
  }

}
</script>

<style scoped>

</style>