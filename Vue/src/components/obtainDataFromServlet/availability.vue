<template>
  <div>
<!--    <b-table class="availabilityTable" :fields="fields" :items="items">-->
<!--      <template #cell(name)="data">-->
<!--        &lt;!&ndash; `data.value` is the value after formatted by the Formatter &ndash;&gt;-->
<!--        <a :href="`#${data.value.replace(/[^a-z]+/i,'-').toLowerCase()}`">{{ data.value }}</a>-->
<!--      </template>-->
<!--    </b-table>-->
<!--    <div>-->
<!--      <label :for="id">Label text for {{id}}</label>-->
<!--      <input :id="id" type="text" />-->
<!--    </div>-->

    <div id="textExample">
      <label :for="id">Label text for {{id}}</label>
      <input :id="id" type="text" />

<!--      <div :v-for="i in 3">-->
<!--        <b-btn v-b-modal="modalId(i)">Launch demo modal</b-btn>-->
<!--        <b-modal :id="'modal' + i" title="Bootstrap-Vue">-->
<!--          <p class="my-4">Hello from modal {{ i }}!</p>-->
<!--        </b-modal>-->
<!--      </div>-->
      <table class="table table-bordered">
        <thead>
        <tr align="center">
          <th>Lunedi</th>
          <th>Martedi</th>
          <th>Mercoledi</th>
          <th>Giovedi</th>
          <th>Venerdi</th>
        </tr>
        </thead>
          <tr v-for="n in 4" :key="n" :value="{n: n}">
<!--            <td align="center"><b-button variant="primary">Lun {{n}}</b-button></td>-->
<!--            <td align="center"><b-button variant="primary">Mar {{n}}</b-button></td>-->
<!--            <td align="center"><b-button variant="primary">Mer {{n}}</b-button></td>-->
<!--            <td align="center"><b-button variant="primary">Gio {{n}}</b-button></td>-->
<!--            <td align="center"><b-button variant="primary">Ven {{n}}</b-button></td>-->
                <td
                    align="center"><b-button :id="n" variant="primary" v-b-modal="modalId(n)">Prenota
                  {{n}}</b-button></td>
                <b-modal :id="'modal' + n" title="Vuoi confermare la prenotazione?" align="center">
                  <p class="my-4">Slot: Lun {{n}}</p>
                </b-modal>
<!--                <td align="center"><b-button id="Mar + 'n'" variant="primary">Prenota</b-button></td>-->
<!--            <td align="center"><b-button :id="Mar + n" variant="primary">Prenota</b-button></td>-->
<!--            <td align="center"><b-button :n=n :id="Mar + n" variant="primary">Prenota</b-button></td>-->
<!--            <td align="center"><b-button :n=n :id="Mar + 'n'" variant="primary">Prenota</b-button></td>-->
<!--            <td align="center"><b-button :n=n id="Mar + n" variant="primary">Prenota</b-button></td>-->
<!--            <td align="center"><b-button :n="n" :id="(Mar + n).toString()" variant="primary">Prenota</b-button></td>-->
<!--            <td align="center"><b-button :n="n" :id="toString(Mar + n)" variant="primary">Prenota</b-button></td>-->
<!--                <td-->
<!--                    align="center"><b-button :n="n" :id="Mar + toString(n)" variant="primary">Prenota-->
<!--                </b-button></td>-->
<!--            <td align="center"><b-button :n="n" :id="toString(Mar + n)" variant="primary">Prenota</b-button></td>-->
<!--                <td align="center"><b-button :n="n" :id="n.append(n)" variant="primary">Prenota</b-button></td>-->

                <td align="center"><b-button :id="n+4" variant="primary">Prenota {{n+4}}</b-button></td>
                <td align="center"><b-button :id="n+8" variant="primary">Prenota {{n+8}}</b-button></td>
                <td align="center"><b-button :id="n+12" variant="primary">Prenota {{n+12}}</b-button></td>
                <td align="center"><b-button :id="n+16" variant="primary">Prenota {{n+16}}</b-button></td>
          </tr>



<!--          <tr><td>Lun1</td></tr>-->
<!--          <tr><td>Lun2</td></tr>-->
<!--          <tr><td>Lun3</td></tr>-->
<!--          <tr><td>Lun4</td></tr>-->
<!--&lt;!&ndash;          &ndash;&gt;-->
<!--&lt;!&ndash;          <tr v-for="n in 4" :key="n">&ndash;&gt;-->
<!--&lt;!&ndash;            <td :v-for="n in 5">Lun {{n}}</td>&ndash;&gt;-->
<!--        <tr><td>Mar1</td></tr>-->
<!--        <tr><td>Mar2</td></tr>-->
<!--        <tr><td>Mar3</td></tr>-->
<!--        <tr><td>Mar4</td></tr>-->
<!--&lt;!&ndash;          </tr>&ndash;&gt;-->

<!--        <tr><td>Mer1</td></tr>-->
<!--        <tr><td>Mer2</td></tr>-->
<!--        <tr><td>Mer3</td></tr>-->
<!--        <tr><td>Mer4</td></tr>-->

<!--        <tr><td>Gio1</td></tr>-->
<!--        <tr><td>Gio2</td></tr>-->
<!--        <tr><td>Gio3</td></tr>-->
<!--        <tr><td>Gio4</td></tr>-->

<!--        <tr><td>Ven1</td></tr>-->
<!--        <tr><td>Ven2</td></tr>-->
<!--        <tr><td>Ven3</td></tr>-->
<!--        <tr><td>Ven4</td></tr>-->


        <!--        <tr :v-for="loan in loans[0]">-->
<!--          <td :v-for="otherloan in loans">{{otherloan[loan]}}</td>-->

<!--        </tr>-->



      </table>
    </div>
  </div>
</template>

<script>

// let uuid = 0;
export default {
  data() {
    return {
      id: null,
      json: null,
      // slots: [
      //   {"LUNEDI": "lun1", "lun2"},
      //   {}
      // ],

//       loans:[ { "1": 1, "2": 2, "3": 3, "4": 4, "5": 5, "6": 6, "7": 7, "8": 8, "9": 9, "10": 10, "11": 11, "12": 12 }, { "1": 70000, "2": 66524.45, "3": 62736.11, "4": 58606.81, "5": 54105.88, "6": 49199.86, "7": 43852.3, "8": 38023.47, "9": 31670.03, "10": 24744.79, "11": 17196.27, "12": 8968.39 }, { "1": 3475.55, "2": 3788.35, "3": 4129.3, "4": 4500.93, "5": 4906.02, "6": 5347.56, "7": 5828.84, "8": 6353.43, "9": 6925.24, "10": 7548.52, "11": 8227.88, "12": 8968.39 }, { "1": 6300, "2": 5987.2, "3": 5646.25, "4": 5274.61, "5": 4869.53, "6": 4427.99, "7": 3946.71, "8": 3422.11, "9": 2850.3, "10": 2227.03, "11": 1547.66, "12": 807.16 }, { "1": 9775.55, "2": 9775.55, "3": 9775.55, "4": 9775.55, "5": 9775.55, "6": 9775.55, "7": 9775.55, "8": 9775.55, "9": 9775.55, "10": 9775.55, "11": 9775.55, "12": 9775.55 }, { "1": "Mar 17, 2018", "2": "Apr 16, 2018", "3": "May 16, 2018", "4": "Jun 15, 2018", "5": "Jul 15, 2018", "6": "Aug 14, 2018", "7": "Sep 13, 2018", "8": "Oct 13, 2018", "9": "Nov 12, 2018", "10": "Dec 12, 2018", "11": "Jan 11, 2019", "12": "Feb 10, 2019" }, { "1": 66524.45, "2": 62736.11, "3": 58606.81, "4": 54105.88, "5": 49199.86, "6": 43852.3, "7": 38023.47, "8": 31670.03, "9": 24744.79, "10": 17196.27, "11": 8968.39, "12": 0 } ]
// ,
      fields: [
        {
          // A column that needs custom formatting,
          // calling formatter 'fullName' in this app
          key: 'name',
          label: 'Full Name',
          formatter: 'fullName'
        },
        // A regular column
        'age',
        {
          // A regular column with custom formatter
          key: 'sex',
          formatter: value => {
            return value.charAt(0).toUpperCase()
          }
        },
        {
          // A virtual column with custom formatter
          key: 'birthYear',
          label: 'Calculated Birth Year',
          formatter: (value, key, item) => {
            return new Date().getFullYear() - item.age
          }
        }
      ],
      items: [
        { name: { first: 'John', last: 'Doe' }, sex: 'Male', age: 42 },
        { name: { first: 'Jane', last: 'Doe' }, sex: 'Female', age: 36 },
        { name: { first: 'Rubin', last: 'Kincade' }, sex: 'male', age: 73 },
        { name: { first: 'Shirley', last: 'Partridge' }, sex: 'female', age: 62 }
      ]
    }
  },
  // beforeCreate(){
  //   this.uuid = uuid.toString();
  //   uuid += 1;
  // },
  mounted(){
    console.log(this.$store.state);
    this.id = this._uid;
  },
  methods: {
    fullName(value) {
      return `${value.first} ${value.last}`
    },
    modalId(i) {
      return 'modal' + i;
    }
  }
}
</script>

<style scoped>
.availabilityTable {
  background-color: white !important;
}
</style>