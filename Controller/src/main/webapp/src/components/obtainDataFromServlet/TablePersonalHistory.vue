<template>
  <div>
    <b-container class="m-4">
<!--      <b-button @click="simulaLogin">Simula login</b-button>-->

<!--      <b-table v-if="loginSucceded" class="personalHistoryTable" :fields="fields" :items="items" :jsonPersonalHistory="jsonPersonalHistory">-->
      <b-table class="personalHistoryTable" :fields="fields" :items="items" :jsonPersonalHistory="jsonPersonalHistory">

        <template #cell(lun)="data">
          <!-- `data.value` is the value after formatted by the Formatter -->
          <div v-if="JSON.stringify(jsonPersonalHistory).includes(data.value)">
            <div v-if="JSON.stringify(jsonAttive).includes(data.value)">
              <b-dropdown v-show="!(cancelledArray.includes(data.value)) && !(effettuateArray.includes(data.value))"
                          variant="primary"
                          text="Prenotazione attiva">
                <b-dropdown-item @click="disdiciPrenotazione(data.value)">Disdici</b-dropdown-item>
                <b-dropdown-item @click="segnaPrenotazioneComeEffettuata(data.value)">Segna come effettuata</b-dropdown-item>
              </b-dropdown>

              <b-button v-show="cancelledArray.includes(data.value)"
                          variant="danger"
                          disabled>
                Prenotazione cancellata
              </b-button>

              <b-button v-show="effettuateArray.includes(data.value)"
                          variant="light"
                          disabled>
                Ripetizione effettuata
              </b-button>
            </div>
            <b-button v-else-if="JSON.stringify(jsonEffettuate).includes(data.value)"
                      disabled
                      variant="light">
              Ripetizione effettuata
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
            <div v-if="JSON.stringify(jsonAttive).includes(data.value)">
              <b-dropdown v-show="!(cancelledArray.includes(data.value)) && !(effettuateArray.includes(data.value))"
                          variant="primary"
                          text="Prenotazione attiva">
                <b-dropdown-item @click="disdiciPrenotazione(data.value)">Disdici</b-dropdown-item>
                <b-dropdown-item @click="segnaPrenotazioneComeEffettuata(data.value)">Segna come effettuata</b-dropdown-item>
              </b-dropdown>

              <b-button v-show="cancelledArray.includes(data.value)"
                        variant="danger"
                        disabled>
                Prenotazione cancellata
              </b-button>

              <b-button v-show="effettuateArray.includes(data.value)"
                        variant="light"
                        disabled>
                Ripetizione effettuata
              </b-button>
            </div>
            <b-button v-else-if="JSON.stringify(jsonEffettuate).includes(data.value)"
                      disabled
                      variant="light">
              Prenotazione effettuata
            </b-button>
            <b-button v-else-if="JSON.stringify(jsonCancellate).includes(data.value)"
                      disabled
                      variant="danger">
              Ripetizione cancellata
            </b-button>
          </div>
        </template>

        <template #cell(mer)="data">
          <!-- `data.value` is the value after formatted by the Formatter -->
          <div v-if="JSON.stringify(jsonPersonalHistory).includes(data.value)">
            <div v-if="JSON.stringify(jsonAttive).includes(data.value)">
              <b-dropdown v-show="!(cancelledArray.includes(data.value)) && !(effettuateArray.includes(data.value))"
                          variant="primary"
                          text="Prenotazione attiva">
                <b-dropdown-item @click="disdiciPrenotazione(data.value)">Disdici</b-dropdown-item>
                <b-dropdown-item @click="segnaPrenotazioneComeEffettuata(data.value)">Segna come effettuata</b-dropdown-item>
              </b-dropdown>

              <b-button v-show="cancelledArray.includes(data.value)"
                        variant="danger"
                        disabled>
                Prenotazione cancellata
              </b-button>

              <b-button v-show="effettuateArray.includes(data.value)"
                        variant="light"
                        disabled>
                Ripetizione effettuata
              </b-button>
            </div>
            <b-button v-else-if="JSON.stringify(jsonEffettuate).includes(data.value)"
                      disabled
                      variant="light">
              Ripetizione effettuata
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
            <div v-if="JSON.stringify(jsonAttive).includes(data.value)">
              <b-dropdown v-show="!(cancelledArray.includes(data.value)) && !(effettuateArray.includes(data.value))"
                          variant="primary"
                          text="Prenotazione attiva">
                <b-dropdown-item @click="disdiciPrenotazione(data.value)">Disdici</b-dropdown-item>
                <b-dropdown-item @click="segnaPrenotazioneComeEffettuata(data.value)">Segna come effettuata</b-dropdown-item>
              </b-dropdown>

              <b-button v-show="cancelledArray.includes(data.value)"
                        variant="danger"
                        disabled>
                Prenotazione cancellata
              </b-button>

              <b-button v-show="effettuateArray.includes(data.value)"
                        variant="light"
                        disabled>
                Ripetizione effettuata
              </b-button>
            </div>
            <b-button v-else-if="JSON.stringify(jsonEffettuate).includes(data.value)"
                      disabled
                      variant="light">
              Ripetizione effettuata
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
            <div v-if="JSON.stringify(jsonAttive).includes(data.value)">
              <b-dropdown v-show="!(cancelledArray.includes(data.value)) && !(effettuateArray.includes(data.value))"
                          variant="primary"
                          text="Prenotazione attiva">
                <b-dropdown-item @click="disdiciPrenotazione(data.value)">Disdici</b-dropdown-item>
                <b-dropdown-item @click="segnaPrenotazioneComeEffettuata(data.value)">Segna come effettuata</b-dropdown-item>
              </b-dropdown>

              <b-button v-show="cancelledArray.includes(data.value)"
                        variant="danger"
                        disabled>
                Prenotazione cancellata
              </b-button>

              <b-button v-show="effettuateArray.includes(data.value)"
                        variant="light"
                        disabled>
                Ripetizione effettuata
              </b-button>
            </div>
            <b-button v-else-if="JSON.stringify(jsonEffettuate).includes(data.value)"
                      disabled
                      variant="light">
              Ripetizione effettuata
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
<!--      TODO: inserire modale di conferma operazione-->
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

      cancelledArray: [],

      effettuata: false,
      effettuateArray: [],

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



      variant: '',
      disabled: '',
      text: ''
    }
  },
  computed:{
    isCancelled(){
      return this.cancelled ? "danger" : "primary";
    },
    isEffettuata(){
      return this.effettuata ? "light" : "primary";
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

    }

    ,
    segnaPrenotazioneComeEffettuata: function(s){
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
      jQuery.post('http://localhost:8080/TWEB_war_exploded/PrenotazioneEffettuataServlet', {
        idPrenotazione: _this.idPrenotazione
      })
          .then(response => {
            console.log(response)
            console.log("Ripetizione segnata come effettuata: " + _this.idPrenotazione)
          })

      this.effettuata = true;
      this.effettuateArray.push(this.slot)
      console.log("effettuateArray: " + this.effettuateArray)

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