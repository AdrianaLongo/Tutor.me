<template>
  <div>
    <b-container>
      <b-form-group
          id="fieldset-horizontal"
          label-cols-sm="4"
          label-cols-lg="3"
          label="Seleziona un corso: "
          label-for="input-horizontal"
      >
        <b-form-select
            v-model="courseName"
            @change.native="courseHasChanged"
        >
          <option v-for="course in jsonCourses" :key="course.corso" :value="{nome: course.nome}">{{ course.nome }}</option>
        </b-form-select>

        <!--    <p>Corso selezionato in courseSelect: {{ courseName.nome }}</p>-->

        <div v-if="courseName.nome !== undefined">
          <b-button @click="showTutors" variant="primary">Cerca tutor per questo corso</b-button>

        </div>
      </b-form-group>
    </b-container>
  </div>
</template>

<script>
import $ from "jquery";

export default {
  name: "showCatalogue",
  data() {
    return {
      jsonCourses: null,
      courseName: '',
      courseChange: false,
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
  beforeCreate: function() {
    var _this = this;
    $.getJSON('http://localhost:8081/TWEB_war_exploded/PopulateCorsiServlet', function (jsonCourses) {
      _this.jsonCourses = jsonCourses;
      // console.log(jsonCourses);
    });
    // console.log(this.jsonCourses); // null
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

    courseHasChanged(evt){
      let val = evt.target.value;
      console.log("cambio corso" + val);
      this.$store.commit("resetTutors", '');
      this.$store.commit("resetAvailability", '');
    },

    showTutors: function(){
      this.$store.commit("selectCourse", this.courseName.nome);
      console.log("Corso in store = " + this.$store.getters.courseName);
      this.$store.dispatch('retrieveTutors', this.courseName.nome);
    },
  }

}
</script>

<style scoped>
</style>