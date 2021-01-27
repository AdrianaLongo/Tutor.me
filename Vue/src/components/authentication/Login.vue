<template>
<!--  PAGINA-->
<!--  <b-container>-->
<!--    <div class="container">-->
<!--      <div class="row-cols-1 m-5 text-center" label-cols-sm="4"-->
<!--           label-cols-lg="3">-->
<!--        <b-form @submit.stop.prevent>-->
<!--          <b-form-input id="input-username" v-model="username" required placeholder="Username"></b-form-input>-->
<!--&lt;!&ndash;          <b-form-input id="input-password" v-model="password" required placeholder="Password" class="mt-3"></b-form-input>&ndash;&gt;-->
<!--          <b-form-input id="text-password" v-model="password" type="password" required placeholder="Password" class="mt-3"></b-form-input>-->
<!--          <div class="mt-2">-->
<!--            <b-button variant="primary" @click="login()">Login</b-button>-->
<!--          </div>-->
<!--        </b-form>-->

<!--      </div>-->
<!--    </div>-->
<!--  </b-container>-->

<!--  MODAL-->
  <div v-if="this.$store.getters.currentToken === ''">
    <b-col md="4">
      <b-button type="primary" v-b-modal.modal-1>Login</b-button>
    </b-col>

    <b-modal id="modal-1" title="Login" align="center" hide-footer>
      <!-- TODO: fare ok con enter-->
      <!-- TODO: messaggio di errore per credenziali sbagliate-->
      <b-form @submit.stop.prevent>
        <b-form-input id="input-username" v-model="username" required placeholder="Username"></b-form-input>
        <!--          <b-form-input id="input-password" v-model="password" required placeholder="Password" class="mt-3"></b-form-input>-->
        <b-form-input id="text-password" v-model="password" type="password" required placeholder="Password" class="mt-3"></b-form-input>
        <div class="mt-2">
          <b-button variant="primary" @click="login()" type="submit">Login</b-button>
        </div>
      </b-form>
    </b-modal>
  </div>

</template>

<script>
export default {
  name: "login",
  data(){
    return {
      username: null,
      password: null,
    }
  },
  methods:{
    login(){
      this.$store.dispatch('login',{
        username: this.username,
        password: this.password,
      });
      setTimeout(() => {this.makeToast()}, 100)
      console.log("Username inserito: " + this.username);
      console.log("Password inserita: " + this.password);
    },
    makeToast(){
      console.log("toast creato")
      console.log("token:" + this.$store.state.token)
      console.log("userLogged:" + this.$store.getters.userLogged)
      if(this.$store.getters.currentToken !== ''){
        console.log("currentToken (if): " + this.$store.getters.currentToken)
        this.$bvToast.toast(
            `Benvenut*, ${this.username}!`,
            {
              title: `Login effettuato con successo`,
              variant: 'success',
              solid: true
            })
      } else {
        console.log("currentToken (else): " + this.$store.getters.currentToken)
        this.$bvToast.toast(
            `Controlla username e password.`,
            {
              title: `Login non effettuato!`,
              variant: 'danger',
              solid: true
            })
      }

    },
  }
}
</script>

<style scoped>

</style>