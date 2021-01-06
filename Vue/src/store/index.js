import Vue from 'vue';
import Vuex from 'vuex';

import jQuery from 'jquery'
window.jQuery = jQuery()

Vue.use(Vuex)


export default new Vuex.Store({
    state: {
        // user: {
        //     username: 'matt',
        //     fullName: 'Matt Maribojoc'
        // },
        course: {
            nome: ''
        },
        tutor: {
            nome: '',
            cognome: '',
            id: ''
        },
        prenotazione: {
            // idPrenotazione: '',
            idDocente: '',
            nomeCorso: '',
            // idUtente: '',
            slot: '',
            // stato: ''
        },
        disponibilita: {
            slot: ''
        },
        token: localStorage.getItem('access_token') || null
    },

    // Quello che mettiamo in getters puo essere visto da altri componenti
    getters: {
        // firstName: state => {
        //     return state.user.fullName.split(' ')[0]
        // },
        // lastName (state, getters) {
        //     return state.user.fullName.replace(getters.firstName, '');
        // },
        // prefixedName: (state, getters) => (prefix) => {
        //     return prefix + getters.lastName;
        // },
        courseName: state => {
            return state.course.nome;
        },
        tutorName: state => {
            return state.tutor.nome;
        },
        tutorFullName: state => {
            return state.tutor.nome + " " + state.tutor.cognome;
        },
        tutorId: state => {
            return state.tutor.id;
        },
        prenotazioneSlot: state => {
            return state.prenotazione.slot;
        },
        disponibilitaDocente: state => {
            return state.disponibilita.slot;
        }

    },

    // Cambiamenti sincroni
    mutations: {
        // changeName (state, payload) {
        //     state.user.fullName = payload
        // }
        selectCourse(state, payload){
            state.course.nome = payload;
        },
        selectTutorName(state, payload){
            state.tutor.nome = payload;
        },
        selectTutorSurname(state, payload){
            state.tutor.cognome = payload;
        },
        selectTutorId(state, payload){
            state.tutor.id = payload;
        },
        selectSlot(state, payload){
            state.prenotazione.slot = payload;
        },
        popolaCalendarioDocente(state, payload){
            console.log("sto caricando: state.disponibilita.slot = " + state.disponibilita.slot);
            state.disponibilita.slot = payload;
            console.log("caricato! state.disponibilita.slot = " + state.disponibilita.slot);
        },
        // retrieveToken(state, token){
        //     state.token = token
        // }
    },

    // Cambiamenti asincroni
    actions: {
    //     changeName (context, payload) {
    //         setTimeout(() => {
    //             context.commit("changeName", payload);
    //         }, 2000);
    //     }
    //     popolaCalendarioDocente(context, payload){
    //         setTimeout(() => {
    //             context.commit("popolaCalendarioDocente", payload);
    //         }, 20);
    //     }
        retrieveToken(context, credentials){
            jQuery.post('http://localhost:8081/TWEB_war_exploded/LoginServlet', {
                username: credentials.username,
                password: credentials.password,
            })
                .then(response => {
                    console.log("risposta: " + response)
                    // todo: la response e' undefined
                    console.log("credentials.username: " + credentials.username)
                    console.log("credentials.password: " + credentials.password)
                    console.log("header: " + response.headers)
                    // const token = response.data.access_token
                    // console.log("token: " + token)
                    // localStorage.setItem('access_token', token)
                    // context.commit('retrieveToken', token)
                })
                .catch(error => {
                    console.log(error)
                })
        }
    }
});

