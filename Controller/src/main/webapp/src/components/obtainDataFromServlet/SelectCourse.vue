<template>
  <div>
    <b-container>
      <b-form-group
          id="fieldset-horizontal"
          label-cols-sm="4"
          label-cols-lg="3"
          label="Seleziona un corso: "
          label-for="input-horizontal"
          class="font-weight-bold text-lg-left"
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
  beforeCreate: function() {
    var _this = this;
    $.getJSON('http://localhost:8080/TWEB_war_exploded/PopulateCorsiServlet', function (jsonCourses) {
      _this.jsonCourses = jsonCourses;
      // console.log(jsonCourses);
    });
    // console.log(this.jsonCourses); // null

    // // VERSIONE CON DISPATCHER
    // var _this = this;
    // $.getJSON('http://localhost:8080/TWEB_war_exploded/PopulateCorsiServlet', function (jsonCourses) {
    //   _this.jsonCourses = jsonCourses;
    //   // console.log(jsonCourses);
    // });
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
      this.$store.state.tutorJSON = '';
      this.$store.state.disponibilitaJSON = '';
      // this.$store.commit("resetTutors", '');
      // this.$store.commit("resetAvailability", '');
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