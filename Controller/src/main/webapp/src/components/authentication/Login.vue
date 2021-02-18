<template>
  <div v-if="!this.$store.state.isLogged">
    <b-col md="4">
      <b-button variant="outline-success" v-b-modal.modal-login>Login</b-button>
    </b-col>

    <b-modal id="modal-login" title="Login" align="center" hide-footer>
      <b-form @submit.stop.prevent>
        <b-form-input id="input-username" v-model="username" required placeholder="Username"></b-form-input>
        <!--          <b-form-input id="input-password" v-model="password" required placeholder="Password" class="mt-3"></b-form-input>-->
        <b-form-input id="text-password" v-model="password" type="password" required placeholder="Password" class="mt-3"></b-form-input>
        <div class="mt-2">
          <b-button variant="primary" @click="login()">Login</b-button>
        </div>
      </b-form>
    </b-modal>
  </div>

</template>

<script>

// import jQuery from "jquery";

// import $ from "jquery";

export default {
  name: "login",
  data(){
    return {
      username: null,
      password: null,
      jsonPersonalHistory: '',
      jsonAttive: ''
    }
  },
  methods:{
    login: function(){
      this.$store.dispatch('login',{
        username: this.username,
        password: this.password,
      });


      if(this.$store.getters.courseName !== '' && this.$store.getters.tutorId !== '') {
        this.$store.dispatch('retrievePersonalHistory')
      }

      setTimeout(() => {this.makeToast()}, 200)
      console.log("Username inserito: " + this.username);
      console.log("Password inserita: " + this.password);
    },

    makeToast(){
      if(this.$store.state.isLogged){
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