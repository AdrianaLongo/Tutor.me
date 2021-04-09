import Vue from 'vue'
import Router from 'vue-router'
import PageHome from "@/components/pages/PageHome.vue";
import PagePersonalHistory from "@/components/pages/PagePersonalHistory";
import PageClientsHistory from "@/components/pages/PageClientsHistory";
import PageEditCatalogue from "@/components/pages/PageEditCatalogue";

Vue.use(Router)

export default new Router({
    routes: [
        { path: '/', name: "Home", component: PageHome },
        { path: '/lemieprenotazioni', name: "MiePrenotazioni", component: PagePersonalHistory },
        { path: '/prenotazioniclienti', name: "PrenotazioniClienti", component: PageClientsHistory },
        { path: '/modificacatalogo', name: "ModificaCatalogo", component: PageEditCatalogue }
    ]
})