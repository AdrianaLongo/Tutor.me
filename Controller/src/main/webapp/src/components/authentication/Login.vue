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
// import Vue from 'vue';
// import jQuery from 'jquery'
// // import $ from "jquery";
// window.jQuery = jQuery()

import jQuery from "jquery";

export default {
  name: "login",
  data(){
    return {
      username: null,
      password: null,
    }
  },
  methods:{
    // login: function(){
    //   this.$store.dispatch('login',{
    //     username: this.username,
    //     password: this.password,
    //   });
    //
    //   setTimeout(() => {this.makeToast()}, 200)
    //   console.log("Username inserito: " + this.username);
    //   console.log("Password inserita: " + this.password);
    // },
    login: function() {
      // var self = this;
      jQuery.post('http://localhost:8080/TWEB_war_exploded/LoginServlet', {
        username: this.username,
        password: this.password,
      })
          .then(response => {
            // .then(function(response))
            // console.log("session: " + response.getSession());
            //this.$session.start();
            //console.log("session started");
            // console.log("this.$session.exists() = " + this.$session.exists())
            // var id = this.session.getAttribute("jSessionId");
            // console.log("id = " + id)
            var resp = JSON.parse(response);
            // console.log("request" + sessionStorage)
            if(resp.success === 1) {
              console.log("response: " + response)
              console.log("credentials.username: " + this.username)
              console.log("credentials.password: " + this.password)
              this.$store.state.token = 87;
              localStorage.setItem('access_token', this.$store.state.token)
              // console.log("token in localstorage: " + localStorage.access_token)
              console.log("token in store: " + this.$store.state.token)
              this.$session.set("session-id",JSON.parse(response).object);
              console.log("this.$session.get(): " + this.$session.get("session-id"))
              // context.commit('login', token)
              this.$store.state.isLogged = true;
              console.log("isLogged: " + this.$store.state.isLogged)
            }

          })
          .catch(error => {
            console.log(error)
          })
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