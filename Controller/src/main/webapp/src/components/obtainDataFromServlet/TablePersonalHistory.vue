<template>
  <div>
    <b-container class="m-4">
<!--      <b-button @click="simulaLogin">Simula login</b-button>-->

<!--      <b-table v-if="loginSucceded" class="personalHistoryTable" :fields="fields" :items="items" :jsonPersonalHistory="jsonPersonalHistory">-->
      <b-table class="personalHistoryTable" :fields="fields" :items="items" :jsonPersonalHistory="jsonPersonalHistory">

        <template #cell(lun)="data">
          <!-- `data.value` is the value after formatted by the Formatter -->
          <div v-if="JSON.stringify(jsonPersonalHistory).includes(data.value)">
            <b-dropdown v-if="JSON.stringify(jsonAttive).includes(data.value)"
                        :id = data.value
                        :variant="cancelledArray.includes(data.value) ? isCancelled : 'primary'"
                        :disabled="cancelledArray.includes(data.value) ? isCancelled === 'danger' : false"
                        :text="cancelledArray.includes(data.value) ? 'Prenotazione cancellata' : 'Prenotazione attiva'">
              <b-dropdown-item @click="disdiciPrenotazione(data.value)">Disdici</b-dropdown-item>
              <b-dropdown-item>Segna come effettuata</b-dropdown-item>
            </b-dropdown>
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
            <b-dropdown v-if="JSON.stringify(jsonAttive).includes(data.value)"
                        :id = data.value
                        :variant="cancelledArray.includes(data.value) ? isCancelled : 'primary'"
                        :disabled="cancelledArray.includes(data.value) ? isCancelled === 'danger' : false"
                        :text="cancelledArray.includes(data.value) ? 'Prenotazione cancellata' : 'Prenotazione attiva'">
              <b-dropdown-item @click="disdiciPrenotazione(data.value)">Disdici</b-dropdown-item>
              <b-dropdown-item>Segna come effettuata</b-dropdown-item>
            </b-dropdown>
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
            <b-dropdown v-if="JSON.stringify(jsonAttive).includes(data.value)"
                        :id = data.value
                        :variant="cancelledArray.includes(data.value) ? isCancelled : 'primary'"
                        :disabled="cancelledArray.includes(data.value) ? isCancelled === 'danger' : false"
                        :text="cancelledArray.includes(data.value) ? 'Prenotazione cancellata' : 'Prenotazione attiva'">
              <b-dropdown-item @click="disdiciPrenotazione(data.value)">Disdici</b-dropdown-item>
              <b-dropdown-item>Segna come effettuata</b-dropdown-item>
            </b-dropdown>
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
            <b-dropdown v-if="JSON.stringify(jsonAttive).includes(data.value)"
                        :id = data.value
                        :variant="cancelledArray.includes(data.value) ? isCancelled : 'primary'"
                        :disabled="cancelledArray.includes(data.value) ? isCancelled === 'danger' : false"
                        :text="cancelledArray.includes(data.value) ? 'Prenotazione cancellata' : 'Prenotazione attiva'">
              <b-dropdown-item @click="disdiciPrenotazione(data.value)">Disdici</b-dropdown-item>
              <b-dropdown-item>Segna come effettuata</b-dropdown-item>
            </b-dropdown>
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
            <b-dropdown v-if="JSON.stringify(jsonAttive).includes(data.value)"
                        :id = data.value
                        :variant="cancelledArray.includes(data.value) ? isCancelled : 'primary'"
                        :disabled="cancelledArray.includes(data.value) ? isCancelled === 'danger' : false"
                        :text="cancelledArray.includes(data.value) ? 'Prenotazione cancellata' : 'Prenotazione attiva'">
              <b-dropdown-item @click="disdiciPrenotazione(data.value)">Disdici</b-dropdown-item>
              <b-dropdown-item>Segna come effettuata</b-dropdown-item>
            </b-dropdown>
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
      <b-modal id="modal-op" title="Cosa vuoi fare con questa operazione?" align="center" hide-footer>
        <b-button variant="outline-danger" class="mr-3" @click="disdiciPrenotazione()">Disdire</b-button>
        <b-button variant="outline-success" class="ml-3" @click="segnaPrenotazioneComeEffettuata">Segnare come effettuata</b-button>
      </b-modal>
    </b-container>
  </div>
</template>

<script>
import $ from "jquery";
import jQuery from "jquery";

export default {
  name: "personalHistoryTable",
  data(){
    return{
      slotVue: '',
      day: null,
      hours: null,
      idPrenotazione: null,
      jsonPersonalHistory: '',
      elenco: '',
      jsonAttive: '',
      jsonEffettuate:'',
      jsonCancellate: '',

      cancelled: false,
      buttonText: 'Prenotazione attiva',

      cancelledArray: [],

      slot: '',

      loginSucceded: false,

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
  computed:{
    isCancelled(){
      return this.cancelled ? "danger" : "primary";
    }
  },

  beforeCreate() {
    var _this = this;
    $.getJSON('http://localhost:8080/TWEB_war_exploded/RetrievePrenotazioniUtenteServlet', function (jsonPersonalHistory) {
      _this.jsonPersonalHistory = jsonPersonalHistory;
      console.log(jsonPersonalHistory);
      _this.jsonAttive = jsonPersonalHistory.filter( element => element.stato === '0');
      _this.jsonEffettuate = jsonPersonalHistory.filter( element => element.stato === '1');
      _this.jsonCancellate = jsonPersonalHistory.filter( element => element.stato === '-1');
    });
  },
  methods: {
    selectSlot: function(s){
      this.$store.commit("selectSlot", s);
      console.log("s: " + s)
      // console.log(this.jsonAttive) //restituisce oggetto
      // console.log(this.jsonAttive.idPrenotazione) // undefined
      // console.log(JSON.stringify(this.jsonAttive)) // restituisce oggetto in forma di stringa
      // console.log(JSON.stringify(this.jsonAttive.idPrenotazione)) // undefined

      var jsonAttiveParsified = JSON.parse(JSON.stringify(this.jsonAttive));
      // necessario perche jsonAttiveParsified e' un oggetto, mentre JSON.parse converte una stringa contenente notazione JSON in un oggetto javascript

      // for (var data in jsonAttiveParsified){
      //   console.log("idPrenotazione" + jsonAttiveParsified[data].idPrenotazione); //mi restituisce 2 idPrenotazione
      // }

      var selectedSlotData = jsonAttiveParsified.filter(({slot}) => slot === s)
      this.idPrenotazione = selectedSlotData[0].idPrenotazione;

      // console.log(selectedSlotData[0].idPrenotazione)


      // console.log("corso = " + this.$store.getters.courseName);
      // console.log("tutor = " + this.$store.getters.tutorFullName + ", " + this.$store.getters.tutorId );
      // console.log("slot = " + this.$store.getters.prenotazioneSlot);
    },
    // disdiciPrenotazione: function(){
    //   // TODO: implementare metodo per eliminare prenotazione
    //   var _this = this;
    //   jQuery.post('http://localhost:8080/TWEB_war_exploded/DisdettaServlet', {
    //     idPrenotazione: _this.idPrenotazione
    //   })
    //   .then(response => {
    //     console.log(response)
    //     console.log("Prenotazione cancellata: " + _this.idPrenotazione)
    //   })
    // },
    disdiciPrenotazione:function(s){
      this.slot = s;
      this.$store.commit("selectSlot", s);
      console.log("s: " + this.slot)


      var jsonAttiveParsified = JSON.parse(JSON.stringify(this.jsonAttive));
      // necessario perche jsonAttiveParsified e' un oggetto, mentre JSON.parse converte una stringa contenente notazione JSON in un oggetto javascript


      console.log("jsonAttiveParsified")
      console.log(jsonAttiveParsified)

      var selectedSlotData = jsonAttiveParsified.filter(({slot}) => slot === s)
      console.log("selectedSlotData")
      console.log(selectedSlotData)
      this.idPrenotazione = selectedSlotData[0].idPrenotazione;

      var _this = this;
      jQuery.post('http://localhost:8080/TWEB_war_exploded/DisdettaServlet', {
        idPrenotazione: _this.idPrenotazione
      })
          .then(response => {
            console.log(response)
            console.log("Prenotazione cancellata: " + _this.idPrenotazione)
          })

      this.cancelled = true;
      this.cancelledArray.push(this.slot)
      console.log("cancelledArray: " + this.cancelledArray)
      this.buttonText = "Prenotazione cancellata";

    }

    ,
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