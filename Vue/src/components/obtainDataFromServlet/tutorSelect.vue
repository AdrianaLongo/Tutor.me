<template>
  <div>
    <h3>Per il corso di {{this.$store.getters.courseName}} sono disponibili i seguenti tutor: </h3>

    <b-form-select>
      <option v-for="elem in json" :key="elem.corso">{{ elem.nome }}</option>
    </b-form-select>
  </div>
</template>

<script>
import $ from "jquery";

export default {
  name: "tutorSelect",
  data() {
    return {
      url: 'http://localhost:8081/TWEB_war_exploded/PopulateServlet', // è il JSON!
      json: null
    }
  },
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