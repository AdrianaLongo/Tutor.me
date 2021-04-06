import Vue from 'vue'
import App from './App.vue'
import {BootstrapVue, IconsPlugin} from "bootstrap-vue";
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import router from "./router";

import jQuery from 'jquery'
window.jQuery = jQuery()

import store from './store';

import VueSession from 'vue-session';
Vue.use(VueSession)

Vue.config.productionTip = false

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)


new Vue({
  el: '#app',
  router,
  store,
  beforeCreate() {
    setTimeout(function(){
      alert("Session expired. Refresh page");
      location.href='/#/'
      store.dispatch('logout');
    }, 1800000) // 1800000 30 minuti in millisecondi ( 20 secondi:20000 )
  },
  render: h => h(App)
}).$mount('#app')




