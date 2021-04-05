<template>
  <b-container>
    <b-form-group
        id="fieldset-horizontal"
        label-cols-sm="4"
        label-cols-lg="3"
        label="Seleziona un corso: "
        label-for="input-horizontal"
        class="font-weight-bold text-lg-left"
    >
      <b-form-select v-model="courseSelected" @change.native="courseHasChanged">
        <!--"name" e' il nome che do alla variabile "nome" che mi arriva dal db -->
        <option v-for="c in courses" :key="c.corso" :value="{name: c.nome}">{{ c.nome }}</option>
      </b-form-select>

      <div v-if="courseSelected.name !== undefined">
        <b-button @click="showTutors" variant="primary">Cerca tutor per questo corso</b-button>
      </div>

    </b-form-group>
  </b-container>
</template>

<script>
import $ from "jquery";

export default {
  name: "showCatalogue",
  data() {
    return {
      courses: null,
      courseSelected: '',
    }
  },
  beforeCreate: function() {
    var _this = this;
    // Non c'e' bisogno di mantenere nello store la ricerca dei corsi,
    // quindi non abbiamo bisogno dell'action per fare la richiesta.
    $.getJSON('http://localhost:8080/TWEB_war_exploded/PopulateCorsiServlet', function (courses) {
      _this.courses = courses;
    });
  },

  methods: {
    courseHasChanged(){
      // Dovendo passare questi dati ad un altro componente, devo mantenerli nello store => mi sevono actions e mutations
      this.$store.commit('setJsonTutor', '');
      this.$store.commit('setJsonDisponibilita', '');
    },

    showTutors: function(){
      // Dovendo passare questi dati ad un altro componente, devo mantenerli nello store => mi sevono actions e mutations
      this.$store.commit("selectCourse", this.courseSelected.name);
      this.$store.dispatch('retrieveTutorsForCourse', this.courseSelected.name);
    },
  }

}
</script>

<style scoped>
</style>