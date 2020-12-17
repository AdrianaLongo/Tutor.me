<template>
  <div>
    <b-container>
      <div>
        <b-form-group
            id="fieldset-horizontal"
            label-cols-sm="4"
            label-cols-lg="3"
            description="Seleziona un tutor per il corso scelto."
            label="Seleziona un tutor: "
            label-for="input-horizontal"
        >
          <b-select v-model="tutor">
            <option v-for="elem in jsonTutor" :key="elem.id" :value="{id: elem.id, nome: elem.nome, cognome:elem.cognome}">{{
                elem.nome }}
            {{elem
                .cognome}}</option>
          </b-select>

          <div v-if="tutor.id !== undefined">
            <b-button @click="selectTutor" variant="success">Prenota tutor</b-button>
          </div>

          <div>
            <ul v-for="elem in jsonDisponibilita" :key="elem.sezione" :value="{s: elem.sezione}">
              {{elem.sezione}}
            </ul>
          </div>
        </b-form-group>

        <div v-if="this.$store.getters.tutorId !== ''">
          <b-container  v-model="prenotazione">
            <b-table class="availabilityTable" :fields="fields" :items="items">
              <!--        <template #cell(lun)="data">-->
              <!--          &lt;!&ndash; `data.value` is the value after formatted by the Formatter &ndash;&gt;-->
              <!--          <b-button v-if="Object.values(this.$store.getters.disponibilitaDocente).includes(data.value)" @click="selectSlot(data.value)"-->
              <!--                    variant="primary" v-b-modal.modal-1>-->
              <!--            Prenota-->
              <!--          </b-button>-->
              <!--        </template>-->

              <template #cell(lun)="data">
                <!-- `data.value` is the value after formatted by the Formatter -->
                <b-button  v-if="jsonDisponibilita.some(code => JSON.stringify(code) ===
                JSON.stringify({sezione: data.value}))"
                           @click="selectSlot(data.value)"
                           variant="success"
                           v-b-modal.modal-1>
                  Prenota
                </b-button>
                <b-button v-else-if="jsonDisponibilita.some(code => JSON.stringify(code) !==
                JSON.stringify({sezione: data.value}))" disabled
                          variant="danger"
                          v-b-modal.modal-1>
                  Tutor non disponibile
                </b-button>
              </template>

              <template #cell(mar)="data">
                <!-- `data.value` is the value after formatted by the Formatter -->
                <b-button  v-if="jsonDisponibilita.some(code => JSON.stringify(code) ===
                JSON.stringify({sezione: data.value}))"
                           @click="selectSlot(data.value)"
                           variant="success"
                           v-b-modal.modal-1>
                  Prenota
                </b-button>
                <b-button v-else-if="jsonDisponibilita.some(code => JSON.stringify(code) !==
                JSON.stringify({sezione: data.value}))" disabled
                          variant="danger"
                          v-b-modal.modal-1>
                  Tutor non disponibile
                </b-button>
              </template>

              <template #cell(mer)="data">
                <!-- `data.value` is the value after formatted by the Formatter -->
                <b-button  v-if="jsonDisponibilita.some(code => JSON.stringify(code) ===
                JSON.stringify({sezione: data.value}))"
                           @click="selectSlot(data.value)"
                           variant="success"
                           v-b-modal.modal-1>
                  Prenota
                </b-button>
                <b-button v-else-if="jsonDisponibilita.some(code => JSON.stringify(code) !==
                JSON.stringify({sezione: data.value}))" disabled
                          variant="danger"
                          v-b-modal.modal-1>
                  Tutor non disponibile
                </b-button>
              </template>

              <template #cell(gio)="data">
                <!-- `data.value` is the value after formatted by the Formatter -->
                <b-button  v-if="jsonDisponibilita.some(code => JSON.stringify(code) ===
                JSON.stringify({sezione: data.value}))"
                           @click="selectSlot(data.value)"
                           variant="success"
                           v-b-modal.modal-1>
                  Prenota
                </b-button>
                <b-button v-else-if="jsonDisponibilita.some(code => JSON.stringify(code) !==
                JSON.stringify({sezione: data.value}))" disabled
                          variant="danger"
                          v-b-modal.modal-1>
                  Tutor non disponibile
                </b-button>
              </template>

              <template #cell(ven)="data">
                <!-- `data.value` is the value after formatted by the Formatter -->
                <b-button  v-if="jsonDisponibilita.some(code => JSON.stringify(code) ===
                JSON.stringify({sezione: data.value}))"
                           @click="selectSlot(data.value)"
                           variant="success"
                           v-b-modal.modal-1>
                  Prenota
                </b-button>
                <b-button v-else-if="jsonDisponibilita.some(code => JSON.stringify(code) !==
                JSON.stringify({sezione: data.value}))" disabled
                          variant="danger"
                          v-b-modal.modal-1>
                  Tutor non disponibile
                </b-button>
              </template>

            </b-table>
            <b-modal id="modal-1" title="Vuoi confermare la prenotazione?" align="center">
              <p class="my-4"> Corso: {{ this.$store.getters.courseName }} </p>
              <p class="my-4"> Tutor: {{ this.$store.getters.tutorFullName }} </p>
              <p class="my-4"> Data e ora: {{ day }}, {{ hours }}</p>
            </b-modal>
          </b-container>

          <div>
            <ul v-for="elem in this.$store.getters.prenotazioneSlot" :key="elem.sezione" :value="{s: elem.sezione}">
              {{elem.sezione}}
            </ul>
          </div>
        </div>

    </div>


<!--    <h3>Per il corso di {{this.$store.getters.courseName}} sono disponibili i seguenti tutor: </h3>-->

<!--    <b-select v-model="tutor">-->
<!--      <option v-for="elem in json" :key="elem.id" :value="{id: elem.id}">{{ elem.nome }} {{elem-->
<!--          .cognome}}</option>-->
<!--    </b-select>-->

<!--    <div v-if="tutor.id !== undefined">-->
<!--      <b-button @click="selectTutor" variant="success">Prenota tutor</b-button>-->
<!--    </div>-->
    </b-container>
  </div>
</template>

<script>
import $ from "jquery";

export default {
  name: "tutorSelect",
  data() {
    return {
      url: 'http://localhost:8081/TWEB_war_exploded/cercaTutorServlet', // è il JSON!
      jsonTutor: null,
      jsonDisponibilita: null,
      tutor : '',
      course: this.$store.getters.courseName,
      // tutor: this.$store.getters.tutorFullName,
      slot: '',
      day: null,
      hours: null,
      id: null,
      json: null,
      prenotazione: null,
      pren: null,

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
  created: function() {
    var _this = this;
    console.log("Corso scelto: " + this.$store.getters.courseName);
    var corso = this.$store.getters.courseName;
    // $.get('http://localhost:8081/TWEB_war_exploded/cercaTutorServlet', "corso", function
    //     (jsonTutor) {
    //   _this.jsonTutor = jsonTutor;
    // });
    $.getJSON({
      type: "GET",
      url: 'http://localhost:8081/TWEB_war_exploded/cercaTutorServlet',
      data: 'corso='+corso,
      success: function (jsonTutor) {
        _this.jsonTutor = jsonTutor;
        // console.log(jsonTutor); // array di tanti elementi quanti sono i tutor per quel corso
      }
    });
  },
  mounted () {},
  methods: {
    selectTutor: function(){
      // console.log(this.tutor.id); // torna l'id
      this.$store.commit("selectTutorId", this.tutor.id);
      this.$store.commit("selectTutorName", this.tutor.nome);
      this.$store.commit("selectTutorSurname", this.tutor.cognome);
      console.log("courseName in store = " + this.$store.getters.courseName); // torna il corso
      console.log("tutorId in store = " + this.$store.getters.tutorId); // torna l'id
      this.retrieveDisponibilita();
      console.log("Slot in store (selectTutor): " + this.$store.getters.prenotazioneSlot);
    },
    selectSlot: function(s){
      this.$store.commit("selectSlot", s);
      console.log("corso = " + this.$store.getters.courseName);
      console.log("tutor = " + this.$store.getters.tutorFullName + ", " + this.$store.getters.tutorId );
      console.log("slot = " + this.$store.getters.prenotazioneSlot);
    },
    retrieveDisponibilita: function(){
      var _this = this;
      console.log("Professore scelto: " + this.$store.getters.tutorId);
      var tutor = this.$store.getters.tutorId;
      $.getJSON({
        type: "GET",
        url: 'http://localhost:8081/TWEB_war_exploded/DisponibilitaTutorServlet',
        data: 'idDocente='+tutor,
        success: function (jsonDisponibilita) {
          _this.jsonDisponibilita = jsonDisponibilita;
          console.log("Slot disponibili: " + jsonDisponibilita);
          // array di tanti elementi quanti sono gli slot per quel tutor
        }
      });
      this.checkDisponibilita();
    },
    checkDisponibilita: function(){
      this.$store.commit("popolaCalendarioDocente", this.jsonDisponibilita);
      // while(this.$store.getters.prenotazioneSlot === ''){
      //   console.log("sto caricando........")
      // }
      console.log("Slot in store (checkDisponibilita): " + this.$store.getters.prenotazioneSlot);
    }
  }

}
</script>

<style scoped>

</style>