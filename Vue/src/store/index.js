import Vue from 'vue';
import Vuex from 'vuex';

import jQuery from 'jquery'
import $ from "jquery";
window.jQuery = jQuery()

Vue.use(Vuex)

//Questa classe viene usata per condividere i dati tra i vari components
//si occupa di comunicare con la servlet, ottenere i dati e di storarli e renderli disponibili alle view
export default new Vuex.Store({
    state: {

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
        },
        user: {
            id: '',
            nome: '',
            cognome: '',
            ruolo: ''
        },
        token: '',
        isLogged: false
    },

    // Quello che mettiamo in getters puo essere visto da altri componenti
    getters: {
        // firstName: state => {
        //     return state.user.fullName.split(' ')[0]
        // },
        // lastName (state, getters) {
        //     return state.user.fullName.replace(getters.firstName, '');
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
        currentToken: state => {
            return state.token;
        },
        userLogged: state => {
            return state.isLogged;
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
        // login(state, token){
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
        },
        logout(state, payload){
            state.token=payload;
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
    //     login(context, credentials){
    //         // var self = this;
    //         jQuery.post('http://localhost:8081/TWEB_war_exploded/LoginServlet', {
    //             username: credentials.username,
    //             password: credentials.password,
    //         })
    //             .then(response => {
    //             // .then(function(response))
    //                 // console.log("session: " + response.getSession());
    //                 this.$session.start();
    //                 console.log("session started");
    //                 // console.log("this.$session.exists() = " + this.$session.exists())
    //                 var id = this.session.getAttribute("jSessionId");
    //                 console.log("id = " + id)
    //                 var resp = JSON.parse(response);
    //                 // console.log("request" + sessionStorage)
    //                 if(resp.success === 1) {
    //                     console.log("response: " + response)
    //                     console.log("credentials.username: " + credentials.username)
    //                     console.log("credentials.password: " + credentials.password)
    //                     this.state.token = JSON.parse(response).object
    //                     localStorage.setItem('access_token', this.state.token)
    //                     // console.log("token in localstorage: " + localStorage.access_token)
    //                     console.log("token in store: " + this.state.token)
    //                     // context.commit('login', token)
    //                     this.state.isLogged = true;
    //                     console.log("isLogged: " + this.state.isLogged)
    //                 }
    //
    //             })
    //             .catch(error => {
    //                 console.log(error)
    //             })
    //     },

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
            })
            console.log("sto caricando: state.disponibilita.slot = " + this.state.disponibilita.slot);
            this.state.disponibilita.slot = this.state.disponibilitaJSON;
            console.log("caricato! state.disponibilita.slot = " + this.state.disponibilita.slot);
        },

        retrievePersonalHistory(context, user){
            var _this = this;
            // $.getJSON({
            //     type: "GET",
            //     url: 'http://localhost:8081/TWEB_war_exploded/RetrievePrenotazioniUtenteServlet',
            //     data: 'jSessionId='+ user,
            //     success: function (jsonPersonalHistory) {
            //         console.log("user: " + user);
            //         _this.jsonPersonalHistory = jsonPersonalHistory;
            //         console.log("Elenco prenotazioni " + jsonPersonalHistory);
            //         _this.state.personalHistoryJSON = jsonPersonalHistory;
            //         console.log(_this.state.personalHistoryJSON);
            //     }
            // });
            jQuery.get('http://localhost:8081/TWEB_war_exploded/RetrievePrenotazioniUtenteServlet',{
                jSessionId: user
            })
                .then(response => {
                    var jsonPersonalHistory = response;
                    console.log("user: " + user);
                    _this.jsonPersonalHistory = jsonPersonalHistory;
                    console.log("Elenco prenotazioni " + jsonPersonalHistory);
                    _this.state.personalHistoryJSON = jsonPersonalHistory;
                    console.log(_this.state.personalHistoryJSON);
                })
        },

        retrieveClientsHistory(context, user){
            var _this = this;
            $.getJSON({
                type: "GET",
                url: 'http://localhost:8081/TWEB_war_exploded/RetrievePrenotazioniUtenteServlet',
                data: 'jSessionId='+ user,
                success: function (jsonPersonalHistory) {
                    console.log("user: " + user);
                    _this.jsonPersonalHistory = jsonPersonalHistory;
                    console.log("Elenco prenotazioni " + jsonPersonalHistory);
                    _this.state.personalHistoryJSON = jsonPersonalHistory;
                    console.log(_this.state.personalHistoryJSON);
                }
            })
        },

        deleteCourseFromCatalogue(context, credentials){
            // TODO: cambiare appena c'è la servlet
            jQuery.post('http://localhost:8081/TWEB_war_exploded/LoginServlet', {
                username: credentials.username,
                password: credentials.password,
            })
                .then(response => {
                    // console.log("session: " + response.getSession());
                    var resp = JSON.parse(response);
                    if(resp.success === 1) {
                        console.log("response: " + response)
                        console.log("credentials.username: " + credentials.username)
                        console.log("credentials.password: " + credentials.password)
                        this.state.token = JSON.parse(response).object
                        // localStorage.setItem('access_token', this.state.token)
                        // console.log("token in localstorage: " + localStorage.access_token)
                        console.log("token in store: " + this.state.token)
                        // context.commit('login', token)
                        this.state.isLogged = true;
                    }
                })
                .catch(error => {
                    console.log(error)
                })
        },

    }
});

