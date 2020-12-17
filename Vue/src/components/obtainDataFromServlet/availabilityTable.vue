<!--<template>-->
<!--  <div>-->
<!--    <b-container v-model="prenotazione">-->
<!--      <b-table class="availabilityTable" :fields="fields" :items="items">-->
<!--        <template #cell(lun)="data">-->
<!--          &lt;!&ndash; `data.value` is the value after formatted by the Formatter &ndash;&gt;-->
<!--          <b-button v-if="data.value" @click="selectSlot(data.value)" variant="primary" v-b-modal.modal-1>-->
<!--            Prenota-->
<!--          </b-button>-->
<!--        </template>-->
<!--        <template #cell(mar)="data">-->
<!--          &lt;!&ndash; `data.value` is the value after formatted by the Formatter &ndash;&gt;-->
<!--          <b-button @click="selectSlot(data.value)" variant="primary" v-b-modal.modal-1>-->
<!--            Prenota-->
<!--          </b-button>-->
<!--        </template>-->
<!--        <template #cell(mer)="data">-->
<!--          &lt;!&ndash; `data.value` is the value after formatted by the Formatter &ndash;&gt;-->
<!--          <b-button @click="selectSlot(data.value)" variant="primary" v-b-modal.modal-1>-->
<!--            Prenota-->
<!--          </b-button>-->
<!--        </template>-->
<!--        <template #cell(gio)="data">-->
<!--          &lt;!&ndash; `data.value` is the value after formatted by the Formatter &ndash;&gt;-->
<!--          <b-button @click="selectSlot(data.value)" variant="primary" v-b-modal.modal-1>-->
<!--            Prenota-->
<!--          </b-button>-->
<!--        </template>-->
<!--        <template #cell(ven)="data">-->
<!--          &lt;!&ndash; `data.value` is the value after formatted by the Formatter &ndash;&gt;-->
<!--          <b-button @click="selectSlot(data.value)" variant="primary" v-b-modal.modal-1>-->
<!--            Prenota-->
<!--          </b-button>-->
<!--        </template>-->

<!--      </b-table>-->
<!--      <b-modal id="modal-1" title="Vuoi confermare la prenotazione?" align="center">-->
<!--        <p class="my-4"> Corso: {{ course }} </p>-->
<!--        <p class="my-4"> Tutor: {{ tutor }} </p>-->
<!--        <p class="my-4"> Data e ora: {{ day }}, {{ hours }}</p>-->
<!--      </b-modal>-->
<!--    </b-container>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->

<!--// let uuid = 0;-->
<!--export default {-->
<!--  data() {-->
<!--    return {-->
<!--      course: this.$store.getters.courseName,-->
<!--      tutor: this.$store.getters.tutorFullName,-->
<!--      slot: '',-->
<!--      day: null,-->
<!--      hours: null,-->
<!--      id: null,-->
<!--      json: null,-->
<!--      prenotazione: null,-->
<!--      pren: null,-->

<!--      fields: [-->
<!--        {-->
<!--          key: 'hours',-->
<!--          label: 'Fascia oraria',-->
<!--          formatter: value => {-->
<!--            return value-->
<!--          }-->
<!--        }, {-->
<!--          key: 'lun',-->
<!--          label: 'Lunedi',-->
<!--          formatter: value => {-->
<!--            return value.slot-->
<!--          }-->
<!--        },{-->
<!--          key: 'mar',-->
<!--          label: 'Martedi',-->
<!--          formatter: value => {-->
<!--            return value.slot-->
<!--          }-->
<!--        },{-->
<!--          key: 'mer',-->
<!--          label: 'Mercoledi',-->
<!--          formatter: value => {-->
<!--            return value.slot-->
<!--          }-->
<!--        },{-->
<!--          key: 'gio',-->
<!--          label: 'Giovedi',-->
<!--          formatter: value => {-->
<!--            return value.slot-->
<!--          }-->
<!--        }, {-->
<!--          key: 'ven',-->
<!--          label: 'Venerdi',-->
<!--          formatter: value => {-->
<!--            return value.slot-->
<!--          }-->
<!--        }-->
<!--      ],-->
<!--      items: [-->
<!--        { hours: '15:00 - 16:00',-->
<!--          lun: {day: 'Lunedi', slot: 'LUN1'},-->
<!--          mar: {day: 'Martedi', slot: 'MAR1'},-->
<!--          mer: {day: 'Mercoledi', slot: 'MER1'},-->
<!--          gio: {day: 'Giovedi', slot: 'GIO1'},-->
<!--          ven: {day: 'Venerdi', slot: 'VEN1'},-->
<!--        },-->
<!--        { hours: '16:00 - 17:00',-->
<!--          lun: {day: 'Lunedi', slot: 'LUN2'},-->
<!--          mar: {day: 'Martedi', slot: 'MAR2'},-->
<!--          mer: {day: 'Mercoledi', slot: 'MER2'},-->
<!--          gio: {day: 'Giovedi', slot: 'GIO2'},-->
<!--          ven: {day: 'Venerdi', slot: 'VEN2'},-->
<!--        },-->
<!--        { hours: '17:00 - 18:00',-->
<!--          lun: {day: 'Lunedi', slot: 'LUN3'},-->
<!--          mar: {day: 'Martedi', slot: 'MAR3'},-->
<!--          mer: {day: 'Mercoledi', slot: 'MER3'},-->
<!--          gio: {day: 'Giovedi', slot: 'GIO3'},-->
<!--          ven: {day: 'Venerdi', slot: 'VEN3'},-->
<!--        },-->
<!--        { hours: '18:00 - 19:00',-->
<!--          lun: {day: 'Lunedi', slot: 'LUN4'},-->
<!--          mar: {day: 'Martedi', slot: 'MAR4'},-->
<!--          mer: {day: 'Mercoledi', slot: 'MER4'},-->
<!--          gio: {day: 'Giovedi', slot: 'GIO4'},-->
<!--          ven: {day: 'Venerdi', slot: 'VEN4'},-->
<!--        },-->
<!--      ],-->
<!--    }-->
<!--  },-->
<!--  methods: {-->
<!--    selectSlot: function(s){-->
<!--      this.$store.commit("selectSlot", s);-->
<!--      console.log("corso = " + this.$store.getters.courseName);-->
<!--      console.log("tutor = " + this.$store.getters.tutorFullName + ", " + this.$store.getters.tutorId );-->
<!--      console.log("slot = " + this.$store.getters.prenotazioneSlot);-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->
<!--.availabilityTable {-->
<!--  background-color: white !important;-->
<!--}-->
<!--</style>-->