import Vue from 'vue'
import App from './App.vue'
import {BootstrapVue, IconsPlugin} from "bootstrap-vue";
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
// import VueRouter from 'vue-router'
import router from "./router";

 // import $ from 'jquery'

import jQuery from 'jquery'
window.jQuery = jQuery()

// import 'expose?$!expose?jQuery!jquery'

// import PageHome from "@/components/pages/PageHome.vue";
// import PageCourses from "@/components/pages/PageCourses.vue";
// import PageTutors from "@/components/pages/PageTutors.vue";
// import PageAvailability from "@/components/pages/PageAvailability.vue";

Vue.config.productionTip = false

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
 // Vue.use(VueRouter)
 // Vue.use($)

new Vue({
  el: '#app',
  router,
  render: h => h(App)
}).$mount('#app')

// const router = new VueRouter({
//   mode: 'history',
//   base: __dirname,
//   routes: [
//     { path: '/', component: PageHome },
//     { path: '/courses', component: PageCourses },
//     { path: '/tutors', component: PageTutors },
//     { path: '/calendar', component: PageAvailability}
//   ]
// });

// new Vue({
//   router,
//   template: `
//     <div>
//       <nav class="navbar navbar-toggleable-md navbar-light bg-faded">
//         <div class="collapse navbar-collapse" id="navbarNav">
//           <ul class="navbar-nav">
//             <li class="nav-item"><router-link to="/" class="nav-link">Home</router-link></li>
//             <li class="nav-item"><router-link to="/courses" class="nav-link">Catalogo corsi</router-link></li>
//             <li class="nav-item"><router-link to="/tutors" class="nav-link">Tutor</router-link></li>
//             <li class="nav-item"><router-link to="/calendar" class="nav-link">Calendario disponibilità</router-link></li>
//           </ul>
//         </div>
//       </nav>
//       <router-view class="view"></router-view>
//     </div>
//   `
// }).$mount('#app');


