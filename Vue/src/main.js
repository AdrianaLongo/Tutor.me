import Vue from 'vue'
import App from './App.vue'
import {BootstrapVue, IconsPlugin} from "bootstrap-vue";
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import router from "./router";

import jQuery from 'jquery'
//creo un oggetto jQuery chiamando la sua funzione
window.jQuery = jQuery()

import store from './store';

import VueSession from 'vue-session';
//serve per chiamare un plugin
Vue.use(VueSession)

Vue.config.productionTip = false
//serve per chiamare un plugin
Vue.use(BootstrapVue)
//serve per chiamare un plugin
Vue.use(IconsPlugin)

//Inizializzo la View come se fosse un Component
new Vue({
  //mi collego al tag in index.html
  el: '#app',
  //è il navigatore della spa
  router,
  store,
  render: h => h(App)     //render function(h){return h(App)}
}).$mount('#app')




