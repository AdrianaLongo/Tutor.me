<template>
  <b-container>
    <div>
      <b-form-group
          id="fieldset-horizontal"
          label-cols-sm="4"
          label-cols-lg="3"
          label="Seleziona un tutor: "
          label-for="input-horizontal"
          class="font-weight-bold text-lg-left"
      >
        <b-select v-model="tutor" @change.native="tutorHasChanged">
          <option v-for="t in this.$store.getters.elencoTutor"
                  :key="t.id"
                  :value="{id: t.id, nome: t.nome, cognome: t.cognome}">
            {{ t.nome }} {{t.cognome}}
          </option>
        </b-select>

        <div v-if="tutor.id !== undefined">
          <b-button @click="showAvailiability" variant="primary">Prenota tutor</b-button>
        </div>
      </b-form-group>
    </div>
  </b-container>
</template>

<script>

export default {
  name: "tutorSelect",
  data() {
    return {
      course: this.$store.getters.courseName,
      tutor : '',
    }
  },
  methods: {
    tutorHasChanged(){
      // Dovendo passare questi dati ad un altro componente, devo mantenerli nello store => mi sevono actions e mutations
      this.$store.commit('setJsonDisponibilita', '');
    },
    showAvailiability: function(){
      // Dovendo passare questi dati ad un altro componente, devo mantenerli nello store => mi sevono actions e mutations

      this.$store.commit("selectTutorId", this.tutor.id);
      this.$store.commit("selectTutorName", this.tutor.nome);
      this.$store.commit("selectTutorSurname", this.tutor.cognome);

      this.$store.dispatch('retrieveTutorAvailability', this.$store.getters.tutorId)
    },
  },
}
</script>

<style scoped>

</style>