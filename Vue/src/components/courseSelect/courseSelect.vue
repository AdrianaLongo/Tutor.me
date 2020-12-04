<template>
  <div class="container">
    <div class="row">
      <div class="col-lg-6">
<!--        <div>-->
<!--          <b-form-select v-model="selected" :options="courses"></b-form-select>-->
<!--          <div class="mt-3">Selected: <strong>{{ selected }}</strong></div>-->
<!--          <ul>-->
<!--            <li v-for="dog in dogs" :key="dog"> {{dog}} </li>-->
<!--&lt;!&ndash;            <li v-for="(rent, city) in avarageRent"> The avarage rent in {{city}} is ${{rent}} </li>&ndash;&gt;-->
<!--          </ul>-->

<!--        </div>-->
      </div>
    </div>
    <b-form-select v-model="courses">
      <option v-for="course in courses" :key="course">{{course.nome}}</option>
<!--      selected: {{course}}-->
    </b-form-select>
    <ul>
      <li v-for="course in courses" :key="course">{{course.nome}}</li>
      <!--      selected: {{course}}-->
    </ul>
  </div>
</template>

<script>
// import $ from "jquery";

export default {
  name: "courseSelect",
  data() {
    return {
      // selected: null,
      dogs: ['Rex', 'Rover', 'Henrietta', 'Alan'],
      // avarageRent:{
      //   london: 2650,
      //   paris: 1730,
      //   NYC: 3680
      // }
      courses: [
        // {
        //   "nome": "Filosofia"
        // },
        // {
        //   "nome": "Fisica"
        // },
        // {
        //   "nome": "Greco"
        // },
        // {
        //   "nome": "Inglese"
        // },
        // {
        //   "nome": "Italiano"
        // },
        // {
        //   "nome": "Latino"
        // },
        // {
        //   "nome": "Matematica"
        // }
      ],
      // link: 'http://192.168.1.118:8081/TWEB_war_exploded#/'
      url: 'http://localhost:8081/TWEB_war_exploded/PopulateServlet', // è proprio il JSON!
      proxyurl: "https://cors-anywhere.herokuapp.com/"
    }
  },
  // OPZIONE 1
  created() {
    fetch('http://localhost:8081/TWEB_war_exploded/PopulateServlet')
        .then((res) => res.json())
        .then((data) => {
          this.courses = data.courses;
          console.log(data);
        });
  }

  // OPZIONE 2
  // mounted() {
  //   fetch('http://localhost:8081/TWEB_war_exploded/PopulateServlet')
  //       .then((res) => res.json())
  //       .then((data) => {
  //         this.courses = data.courses;
  //         console.log("ciao");
  //       });
  // }

  // OPZIONE 3 (abilitare import jquery)
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

  // OPZIONE 4 (abilitare import jquery)
  // mounted() {
  //   var self = this;
  //   $.getJSON(this.link, function(data){
  //     self.courses = data;
  //   })
  // }
  // methods: {
  //   getCourses: function () {
  //     var self = this;
  //     $.get(this.link, function (data) {
  //       self.courses = data;
  //     });
  //   }
  // }
}
</script>

<style scoped>

</style>