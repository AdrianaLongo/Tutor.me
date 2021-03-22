import Vue from 'vue';
import Vuex from 'vuex';

import getters from './getters';
import mutations from './mutations';
import actions from './actions';


import jQuery from 'jquery'
// import $ from "jquery";
window.jQuery = jQuery()

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        course: '',
        tutorJSON: '',
        tutor: {
            nome: '',
            cognome: '',
            id: ''
        },
        prenotazione: {
            idPrenotazione: '',
            nomeCorso: '',
            nomeDocente: '',
            cognomeDocente: '',
            slot: '',
            stato: ''
        },
        disponibilitaJSON: '',
        disponibilita: {
            slot: ''
        },
        jsonPersonalHistory: '',
        personalHistory:{
            slot: ''
        },
        username: '',
        token: '',
        isLogged: false,
        jsonAttive: '',
        jsonEffettuate: '',
        jsonCancellate: '',

        jsonCourses: '',

        tutorsForCourses: '',

        client: {
            nome: '',
            cognome: '',
            id: ''
        },
        jsonClientsHistory: '',

    },

    getters, // = getters in Java
    mutations, // = setters in Java (cambiamenti sincroni)
    actions, // chiamano le rispettive mutations per permettere cambiamenti asincroni

});

