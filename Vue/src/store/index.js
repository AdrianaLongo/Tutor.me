import Vue from 'vue';
import Vuex from 'vuex';

import jQuery from 'jquery'
import $ from "jquery";
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
        tutorJSON: '',
        tutor: {
            nome: '',
            cognome: '',
            id: ''
        },
        prenotazione: {
            idPrenotazione: '',
            nomeCorso: '',
            idDocente: '',
            idUtente: '',
            slot: '',
            stato: ''
        },
        disponibilitaJSON: '',
        disponibilita: {
            slot: ''
        },
        personalHistoryJSON: '',
        personalHistory:{
            slot: ''
        }
        // token: localStorage.getItem('access_token') || null
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
        elencoTutor: state => {
            return state.tutorJSON;
        },
        prenotazioneSlot: state => {
            return state.prenotazione.slot;
        },
        // disponibilitaDocente: state => {
        //     return state.disponibilita.slot;
        // },
        elencoDisponibilita: state => {
            return state.disponibilitaJSON;
        },
        elencoMiePrenotazioni: state => {
            return state.personalHistoryJSON;
        },
        // miePrenotazioni: state => {
        //     return state.personalHistory.slot;
        // }

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
        // retrieveTutors(state, tutors){
        //     state.tutorJSON = tutors
        // }
        resetTutors(state, payload){
            state.tutorJSON = payload;
        },
        resetAvailability(state, payload){
            state.disponibilitaJSON = payload;
        }
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
                    console.log("risposta: " + JSON.stringify(response))
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
        },

        retrieveTutors(context, course){
            var _this = this;
            jQuery.getJSON({
                type: "GET",
                url: 'http://localhost:8081/TWEB_war_exploded/cercaTutorServlet',
                data: 'corso='+course,
                success: function (jsonTutor) {
                    _this.jsonTutor = jsonTutor;
                    // console.log(jsonTutor);
                    // console.log(jsonTutor);
                    // this.$store.commit("retrieveTutors", jsonTutor)
                    // console.log("tutor in memoria " + this.$store.getters.elencoTutor);

                    _this.state.tutorJSON = jsonTutor;
                    console.log(_this.state.tutorJSON);

                }
            });
        },

        retrieveAvailability(context, tutor){
            var _this = this;
            $.getJSON({
                type: "GET",
                url: 'http://localhost:8081/TWEB_war_exploded/DisponibilitaTutorServlet',
                data: 'idDocente='+tutor,
                success: function (jsonDisponibilita) {
                    _this.jsonDisponibilita = jsonDisponibilita;
                    console.log("Slot disponibili per il tutor " + tutor + ": " + jsonDisponibilita);
                    _this.state.disponibilitaJSON = jsonDisponibilita;
                    console.log(_this.state.disponibilitaJSON);
                }
            });
            console.log("sto caricando: state.disponibilita.slot = " + this.state.disponibilita.slot);
            this.state.disponibilita.slot = this.state.disponibilitaJSON;
            console.log("caricato! state.disponibilita.slot = " + this.state.disponibilita.slot);
        },

        retrievePersonalHistory(context, user){
            var _this = this;
            $.getJSON({
                type: "GET",
                url: 'http://localhost:8081/TWEB_war_exploded/RetrievePrenotazioniUtenteServlet',
                data: 'idUtente='+ user,
                success: function (jsonPersonalHistory) {
                    _this.jsonPersonalHistory = jsonPersonalHistory;
                    console.log("Elenco prenotazioni " + jsonPersonalHistory);
                    _this.state.personalHistoryJSON = jsonPersonalHistory;
                    console.log(_this.state.personalHistoryJSON);
                }
            })
        }

    }
});

