<template>
  <div>
<!--    <p>Questa è la select dei corsi:</p>-->
<!--    <b-form-select>-->
<!--      <option v-for="elem in json" :key="elem.corso">{{ elem.nome }}</option>-->
<!--    </b-form-select>-->
    <h3>Seleziona un corso: </h3>
    <b-select v-model="courseName">
      <option v-for="elem in json" :key="elem.corso" :value="{nome: elem.nome}">{{ elem.nome }}</option>
    </b-select>
    <p>Corso selezionato in courseSelect: {{ courseName.nome }}</p>

    <b-button @click="selectCourse">Cerca tutor per questo corso</b-button>
<!--    <p>course: {{this.$store.getters.courseName}}</p>-->
<!--    <p>username: {{ this.$store.state.user.username }}</p>-->
<!--    <p>firstname: {{this.$store.getters.firstName}}</p>-->
<!--    <p>fullname: {{this.$store.getters.prefixedName("Mr.")}}</p>-->
<!--    <button @click="mut">Prova mutation</button>-->
  </div>
</template>

<script>
import $ from "jquery";

export default {
  name: "courseName",
  data() {
    return {
      url: 'http://localhost:8081/TWEB_war_exploded/PopulateServlet', // è il JSON!
      json: null,
      courseName: ''
    }
  },
  // OPZIONE 1
  // created() {
  //   fetch('http://localhost:8081/TWEB_war_exploded/PopulateServlet')
  //       .then((res) => res.json())
  //       .then((data) => {
  //         this.courses = data.courses;
  //         console.log(data);
  //       });
  // }

  // OPZIONE 2
  // mounted() {
  //   fetch('http://localhost:8081/TWEB_war_exploded/PopulateServlet')
  //       .then((res) => res.json())
  //       .then((data) => {
  //         this.courses = data.courses;
  //         console.log(data); //array
  //         // console.log(data.courses); // undefined
  //       });
  // }

  // OPZIONE 3 (abilitare import jquery) MA NON CREA JSON
  // created() {
  //   $(window).on('load', function(){
  //     $.get("PopulateServlet", function(response){
  //       console.log(response);
  //     });
  //   });
  //   this.getCourses();
  // },
  // methods: {
  //   getCourses: function () {
  //     var self = this;
  //     $.get(this.url, function (data) {
  //       self.courses = data;
  //     });
  //   }
  // }

  // OPZIONE 4 (abilitare import jquery) MA NON CREA JSON
  // mounted() {
  //   var self = this;
  //   $.getJSON(this.link, function(data){
  //     self.courses = data;
  //   })
  // },
  // methods: {
  //   getCourses: function () {
  //     var self = this;
  //     $.get(this.link, function (data) {
  //       self.courses = data;
  //     });
  //   }
  // }

  // OPZIONE 5 (abilitare import jquery)
  created: function() {
    var _this = this;
    $.getJSON('http://localhost:8081/TWEB_war_exploded/PopulateServlet', function (json) {
      _this.json = json;
    });
  },
  mounted () {
    console.log(this.$store.state.course.nome);
  },
  methods: {
    // mut: function(){
    //   this.$store.commit("changeName", "New Name");
    //   this.$store.commit({
    //     type: "changeName",
    //     newName: "New Name 2"
    //   })
    // },

    // act: function(){
    //   this.$store.dispatch("changeName", {
    //     newName: "New Name from Action"
    //   });
    // }

    selectCourse: function(){
      console.log(this.courseName.nome);
      this.$store.commit("selectCourse", this.courseName.nome);
    }

    // selectCourse: function () {
    //   console.log(this.courseName.nome);
    //   this.$store.commit({
    //     type: "selectCourse",
    //     nome: this.courseName.nome
    //   })
    // }
  }

}
</script>

<style scoped>

</style>