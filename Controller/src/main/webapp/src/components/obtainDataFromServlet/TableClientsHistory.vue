<template>
  <div>
    <b-container class="m-4">
      <b-form-select>

      </b-form-select>
      <b-button @click="simulaLogin">Simula login</b-button>

      <b-table v-if="loginSucceded" class="personalHistoryTable" :fields="fields" :items="items" :jsonPersonalHistory="jsonPersonalHistory">

        <template #cell(lun)="data">
          <!-- `data.value` is the value after formatted by the Formatter -->
          <div v-if="JSON.stringify(jsonPersonalHistory).includes(data.value)">
            <b-button v-if="JSON.stringify(jsonAttive).includes(data.value)"
                      variant="primary"
                      v-b-modal.modal-1>
              Prenotazione attiva
            </b-button>
            <b-button v-else-if="JSON.stringify(jsonEffettuate).includes(data.value)"
                      disabled
                      variant="light">
              Prenotazione effettuata
            </b-button>
            <b-button v-else-if="JSON.stringify(jsonCancellate).includes(data.value)"
                      disabled
                      variant="danger">
              Prenotazione cancellata
            </b-button>
          </div>
        </template>

        <template #cell(mar)="data">
          <!-- `data.value` is the value after formatted by the Formatter -->
          <div v-if="JSON.stringify(jsonPersonalHistory).includes(data.value)">
            <b-button v-if="JSON.stringify(jsonAttive).includes(data.value)"
                      variant="primary"
                      v-b-modal.modal-1>
              Prenotazione attiva {{jsonAttive.idPrenotazione}}
            </b-button>
            <b-button v-else-if="JSON.stringify(jsonEffettuate).includes(data.value)"
                      disabled
                      variant="light">
              Prenotazione effettuata
            </b-button>
            <b-button v-else-if="JSON.stringify(jsonCancellate).includes(data.value)"
                      disabled
                      variant="danger">
              Prenotazione cancellata
            </b-button>
          </div>
        </template>

        <template #cell(mer)="data">
          <!-- `data.value` is the value after formatted by the Formatter -->
          <div v-if="JSON.stringify(jsonPersonalHistory).includes(data.value)">
            <b-button v-if="JSON.stringify(jsonAttive).includes(data.value)"
                      variant="primary"
                      v-b-modal.modal-1>
              Prenotazione attiva
            </b-button>
            <b-button v-else-if="JSON.stringify(jsonEffettuate).includes(data.value)"
                      disabled
                      variant="light">
              Prenotazione effettuata
            </b-button>
            <b-button v-else-if="JSON.stringify(jsonCancellate).includes(data.value)"
                      disabled
                      variant="danger">
              Prenotazione cancellata
            </b-button>
          </div>
        </template>

        <template #cell(gio)="data">
          <!-- `data.value` is the value after formatted by the Formatter -->
          <div v-if="JSON.stringify(jsonPersonalHistory).includes(data.value)">
            <b-button v-if="JSON.stringify(jsonAttive).includes(data.value)"
                      variant="primary"
                      v-b-modal.modal-1>
              Prenotazione attiva
            </b-button>
            <b-button v-else-if="JSON.stringify(jsonEffettuate).includes(data.value)"
                      disabled
                      variant="light">
              Prenotazione effettuata
            </b-button>
            <b-button v-else-if="JSON.stringify(jsonCancellate).includes(data.value)"
                      disabled
                      variant="danger">
              Prenotazione cancellata
            </b-button>
          </div>
        </template>

        <template #cell(ven)="data">
          <!-- `data.value` is the value after formatted by the Formatter -->
          <div v-if="JSON.stringify(jsonPersonalHistory).includes(data.value)">
            <b-button v-if="JSON.stringify(jsonAttive).includes(data.value)"
                      variant="primary"
                      v-b-modal.modal-1>
              Prenotazione attiva
            </b-button>
            <b-button v-else-if="JSON.stringify(jsonEffettuate).includes(data.value)"
                      disabled
                      variant="light">
              Prenotazione effettuata
            </b-button>
            <b-button v-else-if="JSON.stringify(jsonCancellate).includes(data.value)"
                      disabled
                      variant="danger">
              Prenotazione cancellata
            </b-button>
          </div>
        </template>

      </b-table>
      <b-modal id="modal-1" title="Cosa vuoi fare con questa operazione?" align="center" hide-footer>
        <b-button variant="outline-danger" class="mr-3" @click="eliminaPrenotazione">Cancellare</b-button>
        <b-button variant="outline-success" class="ml-3" @click="segnaPrenotazioneComeEffettuata">Segnare come effettuata</b-button>
      </b-modal>
    </b-container>
  </div>
</template>

<script>
export default {
  name: "tableClientsHistory",
  data(){
    return{
      slotVue: '',
      day: null,
      hours: null,
      jsonPersonalHistory: '',
      elenco: '',
      jsonAttive: '',
      jsonEffettuate:'',
      jsonCancellate: '',

      loginSucceded: false,

      jsonClients: '',

      fields: [
        {
          key: 'hours',
          label: 'Fascia oraria',
          formatter: value => {
            return value
          }
        }, {
          key: 'lun',
          label: 'Lunedi',
          formatter: value => {
            return value.slotVue
          }
        },{
          key: 'mar',
          label: 'Martedi',
          formatter: value => {
            return value.slotVue
          }
        },{
          key: 'mer',
          label: 'Mercoledi',
          formatter: value => {
            return value.slotVue
          }
        },{
          key: 'gio',
          label: 'Giovedi',
          formatter: value => {
            return value.slotVue
          }
        }, {
          key: 'ven',
          label: 'Venerdi',
          formatter: value => {
            return value.slotVue
          }
        }
      ],
      items: [
        { hours: '15:00 - 16:00',
          lun: {day: 'Lunedi', slotVue: 'LUN1'},
          mar: {day: 'Martedi', slotVue: 'MAR1'},
          mer: {day: 'Mercoledi', slotVue: 'MER1'},
          gio: {day: 'Giovedi', slotVue: 'GIO1'},
          ven: {day: 'Venerdi', slotVue: 'VEN1'},
        },
        { hours: '16:00 - 17:00',
          lun: {day: 'Lunedi', slotVue: 'LUN2'},
          mar: {day: 'Martedi', slotVue: 'MAR2'},
          mer: {day: 'Mercoledi', slotVue: 'MER2'},
          gio: {day: 'Giovedi', slotVue: 'GIO2'},
          ven: {day: 'Venerdi', slotVue: 'VEN2'},
        },
        { hours: '17:00 - 18:00',
          lun: {day: 'Lunedi', slotVue: 'LUN3'},
          mar: {day: 'Martedi', slotVue: 'MAR3'},
          mer: {day: 'Mercoledi', slotVue: 'MER3'},
          gio: {day: 'Giovedi', slotVue: 'GIO3'},
          ven: {day: 'Venerdi', slotVue: 'VEN3'},
        },
        { hours: '18:00 - 19:00',
          lun: {day: 'Lunedi', slotVue: 'LUN4'},
          mar: {day: 'Martedi', slotVue: 'MAR4'},
          mer: {day: 'Mercoledi', slotVue: 'MER4'},
          gio: {day: 'Giovedi', slotVue: 'GIO4'},
          ven: {day: 'Venerdi', slotVue: 'VEN4'},
        },
      ],
    }
  },
  beforeCreate() {
    // TODO: sostituire questo metodo con vero login
    this.$store.dispatch("retrieveClientsHistory", "this.$store.getters.currentToken");
    console.log("Log simulato con idUtente 123");

  },
  methods: {
    // simulaLogin: function(){
    //   this.loginSucceded = true;
    //   this.jsonPersonalHistory = this.$store.getters.elencoMiePrenotazioni;
    //   console.log("Elenco prenotazioni: ");
    //   console.log(this.jsonPersonalHistory);
    //   this.jsonAttive = this.jsonPersonalHistory.filter( element => element.stato === 'attiva');
    //   console.log(this.jsonAttive);
    //   this.jsonEffettuate = this.jsonPersonalHistory.filter( element => element.stato === 'effettuata');
    //   this.jsonCancellate = this.jsonPersonalHistory.filter( element => element.stato === 'cancellata');
    //
    //
    // },
    selectSlot: function(s){
      this.$store.commit("selectSlot", s);
      console.log("corso = " + this.$store.getters.courseName);
      console.log("tutor = " + this.$store.getters.tutorFullName + ", " + this.$store.getters.tutorId );
      console.log("slot = " + this.$store.getters.prenotazioneSlot);
    },
    eliminaPrenotazione: function(corso, tutor, slot){
      // TODO: implementare metodo per eliminare prenotazione
      console.log(corso + tutor + slot);
    },
    segnaPrenotazioneComeEffettuata: function(){
      // TODO: implementare metodo per segnare prenotazione come effettuata

    }
  }
}
</script>

<style scoped>
.personalHistoryTable {
  background-color: white !important;
  text-align: center;
}
</style>