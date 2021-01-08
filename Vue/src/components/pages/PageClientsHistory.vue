<template>
  <div>
    <!--  TODO: tabella con prenotazioni attive, effettuate e cancellate dei vari clienti-->
    <b-container>
      <!--        <p>elencoDisponibilita: {{this.$store.getters.elencoDisponibilita}}</p>-->
      <!--    <p>disponibilitaDocente: {{this.$store.getters.disponibilitaDocente}}</p>-->

      <b-table class="availabilityTable" :fields="fields" :items="items" :jsonDisponibilita="jsonDisponibilita">
        <!--        <template #cell(lun)="data">-->
        <!--          &lt;!&ndash; `data.value` is the value after formatted by the Formatter &ndash;&gt;-->
        <!--          <b-button v-if="Object.values(this.$store.getters.disponibilitaDocente).includes(data.value)" @click="selectSlot(data.value)"-->
        <!--                    variant="primary" v-b-modal.modal-1>-->
        <!--            Prenota-->
        <!--          </b-button>-->
        <!--        </template>-->

        <template #cell(lun)="data">
          <!-- `data.value` is the value after formatted by the Formatter -->
          <b-button v-if="jsonDisponibilita.some(code => JSON.stringify(code) ===
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
          <b-button v-if="jsonDisponibilita.some(code => JSON.stringify(code) ===
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
      <b-modal id="modal-1" title="Vuoi confermare la prenotazione?" align="center"
               @ok="creaPrenotazione(course, tutor, slot)">
        <p class="my-4"> Corso: {{ this.$store.getters.courseName }} </p>
        <p class="my-4"> Tutor: {{ this.$store.getters.tutorFullName }} </p>
        <p class="my-4"> Data e ora: {{ day }}, {{ hours }}</p>
      </b-modal>
    </b-container>
  </div>
</template>

<script>
export default {
  name: "PageClientsHistory",
  data(){
    return{
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
  }
}
</script>

<style scoped>

</style>