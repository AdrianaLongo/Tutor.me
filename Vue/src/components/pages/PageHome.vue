<template>
  <div>
    <b-jumbotron header="Tutor.me">
      <p> Tutor.me è una piattaforma che ti permette di trovare il tutor adatto alle tue
        esigenze.
        Scegli il corso per cui hai bisogno, seleziona il tutor che preferisci ed è fatta!
      </p>
    </b-jumbotron>
<!--    <div>-->
<!--      <b-form-select v-model="selected" :options="options"></b-form-select>-->
<!--      <div class="mt-3">Selected: <strong>{{ selected }}</strong></div>-->
<!--    </div>-->
    <course-select></course-select>

  </div>

</template>

<script>
import courseSelect from "@/components/courseSelect/courseSelect";
export default {
  name: "PageHome",
  components: {
    courseSelect
  },
  data() {
    return {
      selected: null,
      options: [
        { value: null, text: 'Please select an option' },
        { value: 'a', text: 'This is First option' },
        { value: 'b', text: 'Selected Option', disabled: true },
        {
          label: 'Grouped options',
          options: [
            { value: { C: '3PO' }, text: 'Option with object value' },
            { value: { R: '2D2' }, text: 'Another option with object value' }
          ]
        }
      ]
    }
  },

  created(){

  }
}
</script>
