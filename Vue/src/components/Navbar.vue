<template>
<!--  <div id="navbar">-->
<!--    <nav class="navbar navbar-expand-lg fixed-top" v-bind:class=" { 'navbarOpen': show }">-->
<!--      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" @click.stop="toggleNavbar()">-->
<!--        <span class="navbar-toggler-icon"></span>-->
<!--      </button>-->
<!--      <div class="collapse navbar-collapse" id="navbarSupportedContent" v-bind:class="{ 'show': !show }">-->
<!--&lt;!&ndash;        <ul class="navbar-nav mr-4">&ndash;&gt;-->
<!--        <ul class="navbar-nav mr-auto">-->
<!--          <li class="nav-item active">-->
<!--            <router-link class="nav-link" to="/">Home-->
<!--              <span class="sr-only">(current)</span>-->
<!--            </router-link>-->
<!--          </li>-->
<!--          <li class="nav-item">-->
<!--            <router-link class="nav-link" to="courses">Catalogo corsi</router-link>-->
<!--          </li>-->
<!--          <li class="nav-item">-->
<!--            <router-link class="nav-link" to="jsonTutor">Tutor</router-link>-->
<!--          </li>-->
<!--          <li class="nav-item">-->
<!--            <router-link class="nav-link" to="calendar">Calendario disponibilità</router-link>-->
<!--          </li>-->
<!--          <li class="nav-item">-->
<!--            <router-link class="nav-link" to="login">Login</router-link>-->
<!--&lt;!&ndash;            <a href="#LogInButton" role="button" class="btn btn-outline" data-toggle="modal">Login</a>&ndash;&gt;-->
<!--          </li>-->
<!--&lt;!&ndash;          <li>&ndash;&gt;-->
<!--&lt;!&ndash;            <a href="#" class="btn btn-outline">Sign up</a>&ndash;&gt;-->
<!--&lt;!&ndash;          </li>&ndash;&gt;-->
<!--        </ul>-->
<!--      </div>-->
<!--    </nav >-->
<!--  </div>-->
  <b-navbar
      sticky
      toggleable="lg"
      type="dark"
      variant="dark"
      class="header-nav bg-primary position-absolute w-100 p-0"
  >
    <b-navbar-brand to="/" class="font-weight-lighter p-3">
      Home
    </b-navbar-brand>

    <b-navbar-toggle target="nav-collapse--menu" style="z-index: 90;"></b-navbar-toggle>

    <b-collapse id="nav-collapse--menu" v-model="showCollapse" is-nav>
      <b-navbar-nav class="ml-auto custom-dropdown-menus" id="nav-nav">
        <ul class="navbar-nav mr-auto">
<!--          <li class="nav-item active">-->
<!--            <router-link class="nav-link" to="/">Home-->
<!--              <span class="sr-only">(current)</span>-->
<!--            </router-link>-->
<!--          </li>-->
          <li class="nav-item">
            <router-link class="nav-link" to="courses">Catalogo corsi</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="jsonTutor">Tutor</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="calendar">Calendario disponibilità</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="login">Login</router-link>
            <!--            <a href="#LogInButton" role="button" class="btn btn-outline" data-toggle="modal">Login</a>-->
          </li>
          <!--          <li>-->
          <!--            <a href="#" class="btn btn-outline">Sign up</a>-->
          <!--          </li>-->
        </ul>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>



</template>

<script>
export default {
  name: "navbar",
  // data(){
  //   return{
  //     show: true
  //   }
  // },
  // methods: {
  //   toggleNavbar(){
  //     this.show = !this.show
  //   }
  // }

  data() {
    return {
      showCollapse: false
    }
  },
  watch: {
    '$route' () {
      // This will close the collapse if any part of the route changes
      // including query, params, hash, name, or path
      this.showCollapse = false
    }
  }
}
</script>

<style scoped>
  /*.navbar {*/
  /*  background: #5e6174;*/
  /*}*/
  /*.nav-link,*/
  /*.navbar-brand {*/
  /*  color: #f4f4f4;*/
  /*  cursor: pointer;*/
  /*}*/
  /*.nav-link {*/
  /*  margin-right: 1em !important;*/
  /*}*/
  /*.nav-link:hover {*/
  /*  background: #f4f4f4;*/
  /*  color: #5e6174;*/
  /*}*/
  /*.btn-outline {*/
  /*  border: 1px solid #324530;*/
  /*  background: #538b5e;*/
  /*  color: #fff;*/
  /*  margin-left: 5px;*/
  /*  margin-right: 5px;*/
  /*}*/
  /*.btn-outline:hover,*/
  /*.btn-outline:active,*/
  /*.btn-outline:focus,*/
  /*.btn-outline.active {*/
  /*  background: #fff;*/
  /*  color: #538b5e;*/
  /*  border-color: #4fbfa8;*/
  /*}*/
  /*.navbar-collapse {*/
  /*  justify-content: flex-end;*/
  /*}*/
  /*.navbar-toggler {*/
  /*  background: #fff !important;*/
  /*}*/
</style>