import Vue from 'vue'
import Router from 'vue-router'
import PageHome from "@/components/pages/PageHome.vue";
import PageCourses from "@/components/pages/PageCourses.vue";
import PageTutors from "@/components/pages/PageTutors.vue";
import PageAvailability from "@/components/pages/PageAvailability.vue";
import Login from "@/components/authentication/Login";
import PagePersonalHistory from "@/components/pages/PagePersonalHistory";
import PageClientsHistory from "@/components/pages/PageClientsHistory";
import PageEditCatalogue from "@/components/pages/PageEditCatalogue";

Vue.use(Router)

export default new Router({
    routes: [
        { path: '/', name: "Home", component: PageHome },
        { path: '/courses', name: "Courses", component: PageCourses },
        { path: '/jsonTutor', name: "Tutors", component: PageTutors },
        { path: '/calendar', name: "Availability", component: PageAvailability},
        { path: '/login', name: "Login", component: Login },
        { path: '/lemieprenotazioni', name: "MiePrenotazioni", component: PagePersonalHistory },
        { path: '/prenotazioniclienti', name: "PrenotazioniClienti", component: PageClientsHistory },
        { path: '/modificacatalogo', name: "ModificaCatalogo", component: PageEditCatalogue }
    ]
})