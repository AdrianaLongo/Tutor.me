<template>
  <b-container v-if="this.$store.getters.userLogged">
    <b-table class="availabilityTable" :fields="fields" :items="items" :jsonDisponibilita="jsonDisponibilita" :jsonPersonalHistory="jsonPersonalHistory">
      <template #cell(lun)="data">
        <!-- `data.value` is the value after formatted by the Formatter -->
        <div v-if="jsonDisponibilita.some(code => JSON.stringify(code) === JSON.stringify({sezione: data.value}))">
          <div v-if="JSON.stringify(jsonPersonalHistory).includes(data.value)">
            <b-button v-if="!slotPrenotatiInSessione.includes(data.value)"
                      disabled
                      variant="info"
                      v-b-tooltip.hover
                      title="Il tutor è libero ma questo slot è occupato nel tuo calendario. Per procedere, cancella la prenotazione nella tua pagina personale.">
              Prenota
            </b-button>
            <b-button v-else-if="slotPrenotatiInSessione.includes(data.value)"
                      @click="selectSlot(data.value)"
                      variant="success"
                      disabled
                      v-b-modal.modal-logged>
              Prenotato
            </b-button>
          </div>
          <div v-else-if="!JSON.stringify(jsonPersonalHistory).includes(data.value)">
            <b-button v-if="!slotPrenotatiInSessione.includes(data.value)"
                      @click="selectSlot(data.value)"
                      variant="info"
                      v-b-modal.modal-logged>
              Prenota
            </b-button>
            <b-button v-else-if="slotPrenotatiInSessione.includes(data.value)"
                      @click="selectSlot(data.value)"
                      variant="success"
                      disabled
                      v-b-modal.modal-logged>
              Prenotato
            </b-button>
          </div>
        </div>
        <b-button v-else-if="jsonDisponibilita.some(code => JSON.stringify(code) !==
                JSON.stringify({sezione: data.value}))" disabled
                  variant="danger"
                  v-b-modal.modal-logged>
          Tutor non disponibile
        </b-button>
      </template>
      <template #cell(mar)="data">
        <!-- `data.value` is the value after formatted by the Formatter -->
        <div v-if="jsonDisponibilita.some(code => JSON.stringify(code) === JSON.stringify({sezione: data.value}))">
          <div v-if="JSON.stringify(jsonPersonalHistory).includes(data.value)">
            <b-button v-if="!slotPrenotatiInSessione.includes(data.value)"
                      disabled
                      variant="info"
                      v-b-tooltip.hover
                      title="Il tutor è libero ma questo slot è occupato nel tuo calendario. Per procedere, cancella la prenotazione nella tua pagina personale.">
              Prenota
            </b-button>
            <b-button v-else-if="slotPrenotatiInSessione.includes(data.value)"
                      @click="selectSlot(data.value)"
                      variant="success"
                      disabled
                      v-b-modal.modal-logged>
              Prenotato
            </b-button>
          </div>
          <div v-else-if="!JSON.stringify(jsonPersonalHistory).includes(data.value)">
            <b-button v-if="!slotPrenotatiInSessione.includes(data.value)"
                      @click="selectSlot(data.value)"
                      variant="info"
                      v-b-modal.modal-logged>
              Prenota
            </b-button>
            <b-button v-else-if="slotPrenotatiInSessione.includes(data.value)"
                      @click="selectSlot(data.value)"
                      variant="success"
                      disabled
                      v-b-modal.modal-logged>
              Prenotato
            </b-button>
          </div>
        </div>
        <b-button v-else-if="jsonDisponibilita.some(code => JSON.stringify(code) !==
                JSON.stringify({sezione: data.value}))" disabled
                  variant="danger"
                  v-b-modal.modal-logged>
          Tutor non disponibile
        </b-button>
      </template>
      <template #cell(mer)="data">
        <!-- `data.value` is the value after formatted by the Formatter -->
        <div v-if="jsonDisponibilita.some(code => JSON.stringify(code) === JSON.stringify({sezione: data.value}))">
          <div v-if="JSON.stringify(jsonPersonalHistory).includes(data.value)">
            <b-button v-if="!slotPrenotatiInSessione.includes(data.value)"
                      disabled
                      variant="info"
                      v-b-tooltip.hover
                      title="Il tutor è libero ma questo slot è occupato nel tuo calendario. Per procedere, cancella la prenotazione nella tua pagina personale.">
              Prenota
            </b-button>
            <b-button v-else-if="slotPrenotatiInSessione.includes(data.value)"
                      @click="selectSlot(data.value)"
                      variant="success"
                      disabled
                      v-b-modal.modal-logged>
              Prenotato
            </b-button>
          </div>
          <div v-else-if="!JSON.stringify(jsonPersonalHistory).includes(data.value)">
            <b-button v-if="!slotPrenotatiInSessione.includes(data.value)"
                      @click="selectSlot(data.value)"
                      variant="info"
                      v-b-modal.modal-logged>
              Prenota
            </b-button>
            <b-button v-else-if="slotPrenotatiInSessione.includes(data.value)"
                      @click="selectSlot(data.value)"
                      variant="success"
                      disabled
                      v-b-modal.modal-logged>
              Prenotato
            </b-button>
          </div>
        </div>
        <b-button v-else-if="jsonDisponibilita.some(code => JSON.stringify(code) !==
                JSON.stringify({sezione: data.value}))" disabled
                  variant="danger"
                  v-b-modal.modal-logged>
          Tutor non disponibile
        </b-button>
      </template>
      <template #cell(gio)="data">
        <!-- `data.value` is the value after formatted by the Formatter -->
        <div v-if="jsonDisponibilita.some(code => JSON.stringify(code) === JSON.stringify({sezione: data.value}))">
          <div v-if="JSON.stringify(jsonPersonalHistory).includes(data.value)">
            <b-button v-if="!slotPrenotatiInSessione.includes(data.value)"
                      disabled
                      variant="info"
                      v-b-tooltip.hover
                      title="Il tutor è libero ma questo slot è occupato nel tuo calendario. Per procedere, cancella la prenotazione nella tua pagina personale.">
              Prenota
            </b-button>
            <b-button v-else-if="slotPrenotatiInSessione.includes(data.value)"
                      @click="selectSlot(data.value)"
                      variant="success"
                      disabled
                      v-b-modal.modal-logged>
              Prenotato
            </b-button>
          </div>
          <div v-else-if="!JSON.stringify(jsonPersonalHistory).includes(data.value)">
            <b-button v-if="!slotPrenotatiInSessione.includes(data.value)"
                      @click="selectSlot(data.value)"
                      variant="info"
                      v-b-modal.modal-logged>
              Prenota
            </b-button>
            <b-button v-else-if="slotPrenotatiInSessione.includes(data.value)"
                      @click="selectSlot(data.value)"
                      variant="success"
                      disabled
                      v-b-modal.modal-logged>
              Prenotato
            </b-button>
          </div>
        </div>
        <b-button v-else-if="jsonDisponibilita.some(code => JSON.stringify(code) !==
                JSON.stringify({sezione: data.value}))" disabled
                  variant="danger"
                  v-b-modal.modal-logged>
          Tutor non disponibile
        </b-button>
      </template>
      <template #cell(ven)="data">
        <!-- `data.value` is the value after formatted by the Formatter -->
        <div v-if="jsonDisponibilita.some(code => JSON.stringify(code) === JSON.stringify({sezione: data.value}))">
          <div v-if="JSON.stringify(jsonPersonalHistory).includes(data.value)">
            <b-button v-if="!slotPrenotatiInSessione.includes(data.value)"
                      disabled
                      variant="info"
                      v-b-tooltip.hover
                      title="Il tutor è libero ma questo slot è occupato nel tuo calendario. Per procedere, cancella la prenotazione nella tua pagina personale.">
              Prenota
            </b-button>
            <b-button v-else-if="slotPrenotatiInSessione.includes(data.value)"
                      @click="selectSlot(data.value)"
                      variant="success"
                      disabled
                      v-b-modal.modal-logged>
              Prenotato
            </b-button>
          </div>
          <div v-else-if="!JSON.stringify(jsonPersonalHistory).includes(data.value)">
            <b-button v-if="!slotPrenotatiInSessione.includes(data.value)"
                      @click="selectSlot(data.value)"
                      variant="info"
                      v-b-modal.modal-logged>
              Prenota
            </b-button>
            <b-button v-else-if="slotPrenotatiInSessione.includes(data.value)"
                      @click="selectSlot(data.value)"
                      variant="success"
                      disabled
                      v-b-modal.modal-logged>
              Prenotato
            </b-button>
          </div>
        </div>
        <b-button v-else-if="jsonDisponibilita.some(code => JSON.stringify(code) !==
                JSON.stringify({sezione: data.value}))" disabled
                  variant="danger"
                  v-b-modal.modal-logged>
          Tutor non disponibile
        </b-button>
      </template>
    </b-table>

    <b-modal id="modal-logged"
             title="Vuoi confermare la prenotazione?"
             align="center"
             :ok-disabled="JSON.stringify(jsonPersonalHistory).includes(this.$store.getters.prenotazioneSlot)"
             @ok="creaPrenotazione()">
      <p class="my-4"> Corso: {{ this.$store.getters.courseName }} </p>
      <p class="my-4"> Tutor: {{ this.$store.getters.tutorFullName }} </p>
      <p class="my-4"> Data e ora: {{ day }}, {{ hours }}</p>
      <p class="text-danger font-weight-bold"
         v-if="JSON.stringify(jsonPersonalHistory).includes(this.$store.getters.prenotazioneSlot)">
        Il tutor è libero ma questo slot è occupato nel tuo calendario. Per procedere, cancella la prenotazione nella tua pagina personale oppure seleziona un altro slot.
      </p>
    </b-modal>
  </b-container>

  <b-container v-else-if="!this.$store.getters.userLogged">
    <b-table class="availabilityTable" :fields="fields" :items="items" :jsonDisponibilita="jsonDisponibilita">
      <template #cell(lun)="data">
        <!-- `data.value` is the value after formatted by the Formatter -->
        <b-button v-if="jsonDisponibilita.some(code => JSON.stringify(code) ===
                JSON.stringify({sezione: data.value}))"
                  @click="selectSlot(data.value)"
                  variant="info"
                  v-b-modal.modal-notlogged>
          Prenota
        </b-button>
        <b-button v-else-if="jsonDisponibilita.some(code => JSON.stringify(code) !==
                JSON.stringify({sezione: data.value}))" disabled
                  variant="danger">
          Tutor non disponibile
        </b-button>
      </template>
      <template #cell(mar)="data">
        <!-- `data.value` is the value after formatted by the Formatter -->
        <b-button v-if="jsonDisponibilita.some(code => JSON.stringify(code) ===
                JSON.stringify({sezione: data.value}))"
                  @click="selectSlot(data.value)"
                  variant="info"
                  v-b-modal.modal-notlogged>
          Prenota
        </b-button>
        <b-button v-else-if="jsonDisponibilita.some(code => JSON.stringify(code) !==
                JSON.stringify({sezione: data.value}))" disabled
                  variant="danger">
          Tutor non disponibile
        </b-button>
      </template>
      <template #cell(mer)="data">
        <!-- `data.value` is the value after formatted by the Formatter -->
        <b-button v-if="jsonDisponibilita.some(code => JSON.stringify(code) ===
                JSON.stringify({sezione: data.value}))"
                  @click="selectSlot(data.value)"
                  variant="info"
                  v-b-modal.modal-notlogged>
          Prenota
        </b-button>
        <b-button v-else-if="jsonDisponibilita.some(code => JSON.stringify(code) !==
                JSON.stringify({sezione: data.value}))" disabled
                  variant="danger">
          Tutor non disponibile
        </b-button>
      </template>
      <template #cell(gio)="data">
        <!-- `data.value` is the value after formatted by the Formatter -->
        <b-button v-if="jsonDisponibilita.some(code => JSON.stringify(code) ===
                JSON.stringify({sezione: data.value}))"
                  @click="selectSlot(data.value)"
                  variant="info"
                  v-b-modal.modal-notlogged>
          Prenota
        </b-button>
        <b-button v-else-if="jsonDisponibilita.some(code => JSON.stringify(code) !==
                JSON.stringify({sezione: data.value}))" disabled
                  variant="danger">
          Tutor non disponibile
        </b-button>
      </template>
      <template #cell(ven)="data">
        <!-- `data.value` is the value after formatted by the Formatter -->
        <b-button v-if="jsonDisponibilita.some(code => JSON.stringify(code) ===
                JSON.stringify({sezione: data.value}))"
                  @click="selectSlot(data.value)"
                  variant="info"
                  v-b-modal.modal-notlogged>
          Prenota
        </b-button>
        <b-button v-else-if="jsonDisponibilita.some(code => JSON.stringify(code) !==
                JSON.stringify({sezione: data.value}))" disabled
                  variant="danger">
          Tutor non disponibile
        </b-button>
      </template>

    </b-table>

    <b-modal id="modal-notlogged" title="Vuoi confermare la prenotazione?" align="center" hide-footer>
      <p class="my-4"> Corso: {{ this.$store.getters.courseName }} </p>
      <p class="my-4"> Tutor: {{ this.$store.getters.tutorFullName }} </p>
      <p class="my-4"> Data e ora: {{ day }}, {{ hours }}</p>

      <p class="text-danger font-weight-bold">Per confermare è necessario procedere con il login.</p>
      <login></login>

    </b-modal>

  </b-container>


</template>

<script>
import $ from "jquery";
import Login from "@/components/authentication/Login";

export default {
  components:{
    Login
  },
  data() {
    return {
      day: null,
      hours: null,
      jsonDisponibilita: '',
      jsonPersonalHistory: '',
      slotPrenotatiInSessione: [],

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
            return value.slot
          }
        },{
          key: 'mar',
          label: 'Martedi',
          formatter: value => {
            return value.slot
          }
        },{
          key: 'mer',
          label: 'Mercoledi',
          formatter: value => {
            return value.slot
          }
        },{
          key: 'gio',
          label: 'Giovedi',
          formatter: value => {
            return value.slot
          }
        }, {
          key: 'ven',
          label: 'Venerdi',
          formatter: value => {
            return value.slot
          }
        }
      ],
      items: [
        { hours: '15:00 - 16:00',
          lun: {day: 'Lunedi', slot: 'LUN1'},
          mar: {day: 'Martedi', slot: 'MAR1'},
          mer: {day: 'Mercoledi', slot: 'MER1'},
          gio: {day: 'Giovedi', slot: 'GIO1'},
          ven: {day: 'Venerdi', slot: 'VEN1'},
        },
        { hours: '16:00 - 17:00',
          lun: {day: 'Lunedi', slot: 'LUN2'},
          mar: {day: 'Martedi', slot: 'MAR2'},
          mer: {day: 'Mercoledi', slot: 'MER2'},
          gio: {day: 'Giovedi', slot: 'GIO2'},
          ven: {day: 'Venerdi', slot: 'VEN2'},
        },
        { hours: '17:00 - 18:00',
          lun: {day: 'Lunedi', slot: 'LUN3'},
          mar: {day: 'Martedi', slot: 'MAR3'},
          mer: {day: 'Mercoledi', slot: 'MER3'},
          gio: {day: 'Giovedi', slot: 'GIO3'},
          ven: {day: 'Venerdi', slot: 'VEN3'},
        },
        { hours: '18:00 - 19:00',
          lun: {day: 'Lunedi', slot: 'LUN4'},
          mar: {day: 'Martedi', slot: 'MAR4'},
          mer: {day: 'Mercoledi', slot: 'MER4'},
          gio: {day: 'Giovedi', slot: 'GIO4'},
          ven: {day: 'Venerdi', slot: 'VEN4'},
        },
      ],
    }
  },
  created() {
    this.jsonDisponibilita = this.$store.getters.elencoDisponibilita;
    this.jsonPersonalHistory = this.$store.state.jsonPersonalHistory;
  },
  beforeMount() {
    if(this.$store.state.isLogged) {
      this.jsonPersonalHistory = this.$store.state.jsonAttive;
    }
  },
  updated(){
    console.log("UPDATED")
    setTimeout(() => {this.jsonPersonalHistory = this.$store.state.jsonAttive}, 300)
  },
  methods: {
    selectSlot: function(s){
      // Dovendo passare questi dati ad un altro componente, devo mantenerli nello store => mi serve una mutation
      this.$store.commit("selectSlot", s);
      switch(s.slice(0,3)){
        case 'LUN': this.day = "Lunedi"; break;
        case 'MAR': this.day = "Martedi"; break;
        case 'MER': this.day = "Mercoledi"; break;
        case 'GIO': this.day = "Giovedi"; break;
        case 'VEN': this.day = "Venerdi"; break;
        default: this.day = "";
      }
      switch(s.slice(3)){
        case '1': this.hours = "15:00 - 16:00"; break;
        case '2': this.hours = "16:00 - 17:00"; break;
        case '3': this.hours = "17:00 - 18:00"; break;
        case '4': this.hours = "18:00 - 19:00"; break;
        default: this.hours = "";
      }
    },
    creaPrenotazione: function(){
      if (this.$store.getters.userLogged){
        $.post('http://localhost:8080/TWEB_war_exploded/EffettuaPrenotazioneServlet', {
          idDocente: this.$store.getters.tutorId,
          slot: this.$store.getters.prenotazioneSlot,
          nomeCorso: this.$store.getters.courseName
        }).then(response => {
          if(response.success === 1){
            this.$store.dispatch('retrievePersonalHistory')
            this.slotPrenotatiInSessione.push(this.$store.getters.prenotazioneSlot)
            this.makeToastEff()
          }
        })
      }
    },
    makeToastEff(){
      this.$bvToast.toast(
          `Hai prenotato una ripetizione di ${this.$store.getters.courseName} col tutor ${this.$store.getters.tutorFullName} delle ore ${this.hours} di ${this.day} `,
          {
            title: `Prenotazione effettuata con successo!`,
            variant: 'success',
            solid: true
          }
      )
    }
  }
}
</script>

<style scoped>
.availabilityTable {
  background-color: white !important;
}
</style>