import Vue from 'vue';
import Vuex from 'vuex';

import getters from './getters';
import mutations from './mutations';
import actions from './actions';

import jQuery from 'jquery'
window.jQuery = jQuery()

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        course: '',
        jsonCourses: '',

        tutorJSON: '',
        tutor: {
            nome: '',
            cognome: '',
            id: ''
        },
        disponibilitaJSON: '',
        disponibilita: {
            slot: ''
        },

        client: {
            nome: '',
            cognome: '',
            id: '',
            username: '',
            isLogged: false,
            role: '',
        },


        // jsonAttive: '',
        jsonEffettuate: '',
        jsonCancellate: '',
        //tutorsForCourses: '',
        jsonClientsHistory: '',

        prenotazione: {
            idPrenotazione: '',
            nomeCorso: '',
            nomeDocente: '',
            cognomeDocente: '',
            slot: '',
            stato: ''
        },

        jsonPersonalHistory: '',
        personalHistory:{
            slot: ''
        },

    },

    getters, // = getters in Java
    mutations, // = setters in Java (cambiamenti sincroni)
    actions, // chiamano le rispettive mutations per permettere cambiamenti asincroni

});

