<template>
  <div>
    <b-container>
<!-- SELEZIONA TUTOR-->
      <div>
        <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            label="Seleziona un tutor: "
            label-for="input-horizontal"
            class="font-weight-bold text-lg-left"
        >
          <b-select
              v-model="tutor"
              @change.native="tutorHasChanged"
          >

            <option v-for="tutor in this.$store.getters.elencoTutor" :key="tutor.id" :value="{id: tutor.id, nome: tutor.nome, cognome:tutor.cognome}">
              {{ tutor.nome }} {{tutor.cognome}}
            </option>
          </b-select>

          <div v-if="tutor.id !== undefined">
            <b-button @click="showAvailiability" variant="primary">Prenota tutor</b-button>
          </div>
        </b-form-group>
      </div>
    </b-container>
  </div>
</template>

<script>

export default {
  name: "tutorSelect",
  data() {
    return {
      course: this.$store.getters.courseName,
      jsonTutor: null,
      jsonDisponibilita: null,
      tutor : '',
    }
  },

  methods: {
    tutorHasChanged(evt){
      let val = evt.target.value;
      console.log("cambio tutor" + val);
      this.$store.state.disponibilitaJSON = '';
      // this.$store.commit("resetAvailability", '');
    },
    showAvailiability: function(){
      // console.log(this.tutor.id); // torna l'id
      this.$store.commit("selectTutorId", this.tutor.id);
      this.$store.commit("selectTutorName", this.tutor.nome);
      this.$store.commit("selectTutorSurname", this.tutor.cognome);
      console.log("courseName in store = " + this.$store.getters.courseName); // torna il corso
      console.log("tutorId in store = " + this.$store.getters.tutorId); // torna l'id
      // this.retrieveAvailability();

      this.$store.dispatch('retrieveAvailability', this.$store.getters.tutorId)

      console.log("Slot in store (showAvailiability): " + this.$store.getters.prenotazioneSlot);
    },
  },
}


</script>

<style scoped>

</style>