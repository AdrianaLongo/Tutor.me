<template>
  <div>
    <b-container class="m-4">
      <b-table responsive class="selectedClientHistoryTable" :fields="fields" :items="items" :jsonPrenotazioniCliente="jsonPrenotazioniCliente">

        <template #cell(lun)="data">
          <!-- `data.value` is the value after formatted by the Formatter -->
          <div v-if="JSON.stringify(jsonPrenotazioniCliente).includes(data.value)">
            <b-button v-if="JSON.stringify(jsonAttive).includes(data.value)"
                      disabled
                      variant="primary">
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
          <div v-if="JSON.stringify(jsonPrenotazioniCliente).includes(data.value)">
            <b-button v-if="JSON.stringify(jsonAttive).includes(data.value)"
                      disabled
                      variant="primary">
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
        <template #cell(mer)="data">
          <!-- `data.value` is the value after formatted by the Formatter -->
          <div v-if="JSON.stringify(jsonPrenotazioniCliente).includes(data.value)">
            <b-button v-if="JSON.stringify(jsonAttive).includes(data.value)"
                      disabled
                      variant="primary">
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
          <div v-if="JSON.stringify(jsonPrenotazioniCliente).includes(data.value)">
            <b-button v-if="JSON.stringify(jsonAttive).includes(data.value)"
                      disabled
                      variant="primary">
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
          <div v-if="JSON.stringify(jsonPrenotazioniCliente).includes(data.value)">
            <b-button v-if="JSON.stringify(jsonAttive).includes(data.value)"
                      disabled
                      variant="primary">
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

      jsonAttive: '',
      jsonEffettuate:'',
      jsonCancellate: '',
      jsonPrenotazioniCliente: '',

      fields: [
        {
          key: 'hours',
          stickyColumn: true,
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
  created: function() {
    this.jsonPrenotazioniCliente = this.$store.getters.elencoPrenotazioniCliente;
    this.jsonAttive = this.jsonPrenotazioniCliente.filter( element => element.stato === '0');
    this.jsonEffettuate = this.jsonPrenotazioniCliente.filter( element => element.stato === '1');
    this.jsonCancellate = this.jsonPrenotazioniCliente.filter( element => element.stato === '-1');
  },
}
</script>

<style scoped>
.selectedClientHistoryTable {
  background-color: white !important;
  text-align: center;
}
</style>