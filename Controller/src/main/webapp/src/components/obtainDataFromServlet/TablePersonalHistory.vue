<template>
  <div>
    <b-container class="m-4">

      <b-table responsive="" class="personalHistoryTable" :fields="fields" :items="items" :jsonPersonalHistory="jsonPersonalHistory">
        <template #cell(lun)="data">
          <!-- `data.value` is the value after formatted by the Formatter -->
          <div v-if="JSON.stringify(jsonPersonalHistory).includes(data.value)">
            <div v-if="JSON.stringify(jsonAttive).includes(data.value)">
              <b-dropdown v-show="!(cancelledArray.includes(data.value)) && !(effettuateArray.includes(data.value))"
                          variant="primary"
                          text="Prenotazione attiva">
                <b-dropdown-item v-b-modal.modal-del @click="selectSlot(data.value)">Disdici</b-dropdown-item>
                <b-dropdown-item v-b-modal.modal-eff @click="selectSlot(data.value)">Segna come effettuata</b-dropdown-item>
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
                <b-dropdown-item v-b-modal.modal-del @click="selectSlot(data.value)">Disdici</b-dropdown-item>
                <b-dropdown-item v-b-modal.modal-eff @click="selectSlot(data.value)">Segna come effettuata</b-dropdown-item>
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
                <b-dropdown-item v-b-modal.modal-del @click="selectSlot(data.value)">Disdici</b-dropdown-item>
                <b-dropdown-item v-b-modal.modal-eff @click="selectSlot(data.value)">Segna come effettuata</b-dropdown-item>
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
                <b-dropdown-item v-b-modal.modal-del @click="selectSlot(data.value)">Disdici</b-dropdown-item>
                <b-dropdown-item v-b-modal.modal-eff @click="selectSlot(data.value)">Segna come effettuata</b-dropdown-item>
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
                <b-dropdown-item v-b-modal.modal-del @click="selectSlot(data.value)">Disdici</b-dropdown-item>
                <b-dropdown-item v-b-modal.modal-eff @click="selectSlot(data.value)">Segna come effettuata</b-dropdown-item>
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

      <b-modal id="modal-del" title="Vuoi disdire la prenotazione selezionata?" align="center"
               @ok="disdiciPrenotazione()">
        <p class="my-4"> Corso: {{ this.$store.state.prenotazione.nomeCorso }} </p>
        <p class="my-4"> Tutor: {{ this.$store.state.prenotazione.nomeDocente }} {{this.$store.state.prenotazione.cognomeDocente}} </p>
        <p class="my-4"> Data e ora: {{ day }}, {{ hours }}</p>
      </b-modal>

      <b-modal id="modal-eff" title="Vuoi segnare come effettuata la ripetizone selezionata?" align="center"
               @ok="segnaRipetizioneComeEffettuata()">
        <p class="my-4"> Corso: {{ this.$store.state.prenotazione.nomeCorso }} </p>
        <p class="my-4"> Tutor: {{ this.$store.state.prenotazione.nomeDocente }} {{this.$store.state.prenotazione.cognomeDocente}} </p>
        <p class="my-4"> Data e ora: {{ day }}, {{ hours }}</p>
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

      jsonPersonalHistory: '',
      //elenco: '',
      jsonAttive: '',
      jsonEffettuate:'',
      jsonCancellate: '',
      cancelled: false,
      cancelledArray: [],
      effettuata: false,
      effettuateArray: [],
      slot: '',
      selectedSlot: '',
      cognomeDocente: '',
      nomeDocente: '',
      idPrenotazione: null,
      nomeCorso: '',
      //loginSucceded: false,
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
  beforeCreate() {
    var _this = this;
    // Anche se otteniamo lo storico gia' nel login
    // (e lo memorizziamo nello store in modo che sia  disponibile per
    // l'intersezione delle disponibilita' calendario dell'utente con quelle del docente),
    // e' possibile che l'utente effettui una prenotazione prima di vedere il proprio storico:
    // in quel caso, la versione del calendario dell'utente in store non sarebbe aggiornata,
    // e' quindi necessario richiederlo nuovamente.
    // Eventuali modifiche che apportera' in questo component (segnando come cancellata o effettuata una certa prenotazione)
    // andranno a modificare direttamente la versione del calendario che era gia' memorizzata nello store:
    // non c'e' problema di inconsistenza di dati perche' gli unici component che modificano il calendario dell'utente
    // sono questo e quello della prenotazione (TableAvailability.vue), ed entrambi fanno nuove richieste al db
    // per ottenere il calendario (che ovviamente sara' sempre aggiornato con le ultime modifiche)
    $.getJSON('http://localhost:8080/TWEB_war_exploded/RetrievePrenotazioniUtenteServlet', function (jsonPersonalHistory) {
      _this.jsonPersonalHistory = jsonPersonalHistory;
      _this.jsonAttive = jsonPersonalHistory.filter( element => element.stato === '0');
      _this.jsonEffettuate = jsonPersonalHistory.filter( element => element.stato === '1');
      _this.jsonCancellate = jsonPersonalHistory.filter( element => element.stato === '-1');
    });
  },
  methods: {
    selectSlot: function(slot){
      var jsonAttiveParsified = JSON.parse(JSON.stringify(this.jsonAttive));
      // necessario perche jsonAttiveParsified e' un oggetto, mentre JSON.parse converte una stringa contenente notazione JSON in un oggetto javascript

      var selectedSlotData = jsonAttiveParsified.filter(element => element.slot === slot)

      this.idPrenotazione = selectedSlotData[0].idPrenotazione;
      this.nomeCorso = selectedSlotData[0].nomeCorso;
      this.nomeDocente = selectedSlotData[0].nomeDocente;
      this.cognomeDocente = selectedSlotData[0].cognomeDocente;
      this.selectedSlot = selectedSlotData[0].slot;

      // Ci serve chiamare una mutation per rendere disponibile lo slot selezionato al modale che conferma l'azione
      this.$store.commit("selectForDelete", {
        idPrenotazione: this.idPrenotazione,
        nomeCorso: this.nomeCorso,
        nomeDocente: this.nomeDocente,
        cognomeDocente: this.cognomeDocente
      });

      switch(this.selectedSlot.slice(0,3)){
        case 'LUN': this.day = "Lunedi"; break;
        case 'MAR': this.day = "Martedi"; break;
        case 'MER': this.day = "Mercoledi"; break;
        case 'GIO': this.day = "Giovedi"; break;
        case 'VEN': this.day = "Venerdi"; break;
        default: this.day = "default";
      }
      switch(this.selectedSlot.slice(3)){
        case '1': this.hours = "15:00 - 16:00"; break;
        case '2': this.hours = "16:00 - 17:00"; break;
        case '3': this.hours = "17:00 - 18:00"; break;
        case '4': this.hours = "18:00 - 19:00"; break;
        default: this.hours = "default";
      }
      return this.selectedSlot;
    },

    disdiciPrenotazione:function(){
      var responseStatus
      var _this = this;

      // Non ci serve passare dallo store, quindi facciamo la richiesta direttamente qui
      jQuery.post('http://localhost:8080/TWEB_war_exploded/DisdettaServlet', {
        idPrenotazione: _this.$store.state.prenotazione.idPrenotazione
      }).then(response => {
            responseStatus = response.success
          })
      this.cancelled = true;
      this.cancelledArray.push(this.selectedSlot)
      setTimeout(() => {this.makeToastDel(responseStatus)}, 200)
    },
    segnaRipetizioneComeEffettuata: function(){
      var responseStatus
      var _this = this;

      // Non ci serve passare dallo store, quindi facciamo la richiesta direttamente qui
      jQuery.post('http://localhost:8080/TWEB_war_exploded/PrenotazioneEffettuataServlet', {
        idPrenotazione: _this.idPrenotazione
      }).then(response => {
            responseStatus = response.success
          })
      this.effettuata = true;
      this.effettuateArray.push(this.selectedSlot)
      setTimeout(() => {this.makeToastEff(responseStatus)}, 200)
    },
    makeToastDel(responseStatus){
      if(responseStatus === 1){
        this.$bvToast.toast(
            `Hai disdetto la prenotazione di ${this.nomeCorso} col tutor ${this.nomeDocente} ${this.cognomeDocente} delle ore ${this.hours} di ${this.day} `,
            {
              title: `Prenotazione disdetta con successo!`,
              variant: 'success',
              solid: true
            }
        )
      } else {
        this.$bvToast(
            `Qualcosa è andato storto. Riprova o effettua nuovamente il login.`,
            {
              title: `Errore`,
              variant: 'danger',
              solid: true
            }
        )
      }
    },
    makeToastEff(responseStatus){
      if(responseStatus === 1){
        this.$bvToast.toast(
            `Hai segnato come effettuata la ripetizione di ${this.nomeCorso} col tutor ${this.nomeDocente} ${this.cognomeDocente} delle ore ${this.hours} di ${this.day} `,
            {
              title: `Cambio stato ripetizione effettuata con successo!`,
              variant: 'success',
              solid: true
            }
        )
      } else {
        this.$bvToast(
            `Qualcosa è andato storto. Riprova o effettua nuovamente il login.`,
            {
              title: `Errore`,
              variant: 'danger',
              solid: true
            }
        )
      }
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