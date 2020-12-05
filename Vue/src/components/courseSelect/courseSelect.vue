<template>
  <div>
    <b-form-select>
      <option v-for="elem in json" :key="elem.corso">{{ elem.nome }}</option>
    </b-form-select>
  </div>
</template>

<script>
import $ from "jquery";

export default {
  name: "courseSelect",
  data() {
    return {
      url: 'http://localhost:8081/TWEB_war_exploded/PopulateServlet', // è il JSON!
      json: null
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
  }
}
</script>

<style scoped>

</style>