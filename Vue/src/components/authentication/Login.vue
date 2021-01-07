<template>
  <b-container>
    <div class="container">
      <div class="row-cols-1 m-5 text-center" label-cols-sm="4"
           label-cols-lg="3">
        <b-form @submit.stop.prevent>
          <b-form-input id="input-username" v-model="username" required placeholder="Username"></b-form-input>
<!--          <b-form-input id="input-password" v-model="password" required placeholder="Password" class="mt-3"></b-form-input>-->
          <b-form-input id="text-password" v-model="password" type="password" required placeholder="Password" class="mt-3"></b-form-input>
          <div class="mt-2">
            <b-button variant="primary" @click="login()">Login</b-button>
          </div>
        </b-form>

      </div>
    </div>

  </b-container>
<!--  <div class="login-form">-->
<!--    <h2 class="login-heading">Login</h2>-->
<!--    <form action="#" @submit.prevent="login">-->

<!--      <div class="form-control">-->
<!--        <label for="email">Username/Email</label>-->
<!--        <input type="email" name="username" id="username" class="login-input" v-model="username">-->
<!--      </div>-->

<!--      <div class="form-control mb-more">-->
<!--        <label for="password">Password</label>-->
<!--        <input type="password" name="password" id="password" class="login-input" v-model="password">-->
<!--      </div>-->

<!--      <div class="form-control">-->
<!--        <button type="submit" class="btn-submit">Login</button>-->
<!--      </div>-->

<!--    </form>-->

<!--  </div>-->

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
      this.$store.dispatch('retrieveToken',{
        username: this.username,
        password: this.password,
      });
      console.log("Username inserito: " + this.username);
      console.log("Password inserita: " + this.password);
    }
  }
}
</script>

<style scoped>

</style>