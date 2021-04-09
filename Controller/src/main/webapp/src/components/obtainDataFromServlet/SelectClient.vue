<template>
  <div>
    <b-form-group
        id="fieldset-horizontal"
        label-cols-sm="4"
        label-cols-lg="3"
        label="Seleziona un cliente: "
        label-for="input-horizontal"
        class="font-weight-bold text-lg-left"
    >
      <!--        <b-form-select v-model="clientSelected" @change.native="courseHasChanged">-->
      <b-form-select v-model="clientSelected" @change.native="clientHasChanged">
        <option v-for="c in jsonClients" :key="c.id" :value="{name: c.nome, surname: c.cognome, id: c.id}">{{ c.nome }} {{ c.cognome }}</option>
      </b-form-select>

      <div v-if="clientSelected.name !== undefined">
        <b-button @click="showClientCalendar" variant="primary">Mostra calendario</b-button>
      </div>
    </b-form-group>
  </div>


</template>

<script>
import $ from "jquery";

export default {
  name: "SelectClient",
  data(){
    return{
      jsonClients: '',
      clientSelected: '',
    }
  },
  beforeCreate: function() {
    var _this = this;
    // Non c'e' bisogno di mantenere nello store la ricerca dei clienti,
    // quindi non abbiamo bisogno dell'action per fare la richiesta.
    $.getJSON('http://localhost:8080/TWEB_war_exploded/RetrieveClientsServlet', function (jsonClients) {
      _this.jsonClients = jsonClients;
    });
  },
  methods: {
    clientHasChanged(){
      // Dovendo passare questi dati ad un altro componente, devo mantenerli nello store => mi sevono actions e mutations
      this.$store.commit('setJsonClientsHistory', '');
    },

    showClientCalendar: function(){
      // Dovendo passare questi dati ad un altro componente, devo mantenerli nello store => mi sevono actions e mutations
      this.$store.commit("selectClientName", this.clientSelected.name)
      this.$store.commit("selectClientSurname", this.clientSelected.surname)
      this.$store.commit("selectClientId", this.clientSelected.id)
      this.$store.dispatch('retrieveClientsHistory', this.clientSelected.id)
    }
  }
}
</script>

<style scoped>

</style>