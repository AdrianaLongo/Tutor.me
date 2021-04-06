<template>
  <div v-if="!this.$store.state.client.isLogged">
    <b-col md="4">
      <b-button variant="outline-success" v-b-modal.modal-login>Login</b-button>
    </b-col>

    <b-modal id="modal-login" title="Login" align="center" hide-footer>
      <b-form @submit.stop.prevent>
        <b-form-input v-model="username" required placeholder="Username"></b-form-input>
        <b-form-input v-model="password" type="password" required placeholder="Password" class="mt-3"></b-form-input>
        <div class="mt-2">
          <b-button variant="primary" @click="login()">Login</b-button>
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
    login: function(){
      this.$store.dispatch('login',{
        username: this.username,
        password: this.password,
      });

      // Per la persistenza del login al refresh della pagina, memorizziamo nel sessionStorage MA dev'essere encrypted:
      // la funzione btoa('string') e' una funzione js che codifica la stringa data come argomento,
      // mentre atob('encoded_string') fa la decodifica della stringa data come argomento
      sessionStorage.hasntLoggedOut = true;
      sessionStorage.username = this.username;
      console.log("sessionStorage.username: " + sessionStorage.username)
      sessionStorage.password = btoa(this.password);
      console.log("sessionStorage.password: " + sessionStorage.password)

      setTimeout(() => {this.makeToast()}, 200)
      console.log("Username inserito: " + this.username);
      console.log("Password inserita: " + this.password);
    },
    makeToast(){
      if(this.$store.state.client.isLogged){
        this.$bvToast.toast(
            `Benvenut*, ${this.username}!`,
            {
              title: `Login effettuato con successo`,
              variant: 'success',
              solid: true
            })
      } else {
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