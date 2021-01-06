<template>
  <div>
    <b-container>
      <b-form-group
          id="fieldset-horizontal"
          label-cols-sm="4"
          label-cols-lg="3"
          description="Seleziona il corso per cui cerchi un tutor."
          label="Seleziona un corso: "
          label-for="input-horizontal"
      >
        <b-select v-model="courseName">
          <option v-for="elem in json" :key="elem.corso" :value="{nome: elem.nome}">{{ elem.nome }}</option>
        </b-select>
        <!--    <p>Corso selezionato in courseSelect: {{ courseName.nome }}</p>-->

        <div v-if="courseName.nome !== undefined">
          <b-button @click="selectCourse" variant="primary">Cerca tutor per questo corso</b-button>

        </div>
      </b-form-group>
<!--    <h3>Seleziona un corso: </h3>-->
<!--    <b-select v-model="courseName">-->
<!--      <option v-for="elem in json" :key="elem.corso" :value="{nome: elem.nome}">{{ elem.nome }}</option>-->
<!--    </b-select>-->
<!--    <p>Corso selezionato in courseSelect: {{ courseName.nome }}</p>-->

<!--    <div v-if="courseName.nome !== undefined">-->
<!--      <b-button @click="selectCourse" variant="success">Cerca tutor per questo corso</b-button>-->

<!--    </div>-->
    </b-container>
  </div>
</template>

<script>
import $ from "jquery";

export default {
  name: "courseSelect",
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
    $.getJSON('http://localhost:8081/TWEB_war_exploded/PopulateCorsiServlet', function (json) {
      _this.json = json;
      // console.log(json); // array di 7 elementi
    });
    // console.log(this.json); // null

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
      this.$store.commit("selectCourse", this.courseName.nome);
      console.log("this.courseName.nome in selectCourse = " + this.courseName.nome);
      console.log("courseName in store = " + this.$store.getters.courseName);
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