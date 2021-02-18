// Chiamano le rispettive mutations per permettere cambiamenti asincroni

// Tutte le richieste alle servlet vanno qui perche sono ajax

import $ from "jquery";
import jQuery from 'jquery'


const login = (context, credentials) => {
    //  TODO: gestire timeout sessione
    jQuery.post('http://localhost:8080/TWEB_war_exploded/LoginServlet', {
        username: credentials.username,
        password: credentials.password,
    })

    // Quando l'utente fa il login, mi conviene mantenere sempre nello store...
    // 1) nome utente
    context.commit('setCurrentSession', credentials.username)
    // 2) storico delle prenotazioni dell'utente
    $.getJSON('http://localhost:8080/TWEB_war_exploded/RetrievePrenotazioniUtenteServlet', function (jsonPersonalHistory) {
        context.commit('setJsonPersonalHistoryComplete', jsonPersonalHistory)
        context.commit('setJsonPersonalHistoryAttive', jsonPersonalHistory.filter( element => element.stato === '0'))
        context.commit('setJsonPersonalHistory', jsonPersonalHistory.filter( element => element.stato === '0'))
    });

};

const retrieveTutorsForCourse = (context, course) => {
    jQuery.getJSON({
        type: "GET",
        url: 'http://localhost:8080/TWEB_war_exploded/cercaTutorServlet',
        data: 'corso='+course,
        success: function (jsonTutor) {
            context.commit('setJsonTutor', jsonTutor)
        }
    });
};

const retrieveTutorAvailability = (context, tutor) => {
    $.getJSON({
        type: "GET",
        url: 'http://localhost:8080/TWEB_war_exploded/DisponibilitaTutorServlet',
        data: 'idDocente='+tutor,
        success: function (jsonDisponibilita) {
            context.commit('setJsonDisponibilita', jsonDisponibilita)
        }
    })
};

const retrievePersonalHistory = (context) => {
    $.getJSON('http://localhost:8080/TWEB_war_exploded/RetrievePrenotazioniUtenteServlet', function (jsonPersonalHistory) {
        context.commit('setJsonPersonalHistoryComplete', jsonPersonalHistory)
        context.commit('setJsonPersonalHistoryAttive', jsonPersonalHistory.filter( element => element.stato === '0'))
        context.commit('setJsonPersonalHistory', jsonPersonalHistory.filter( element => element.stato === '0'))
    });
};

// retrieveClientsHistory(context, user){
// TODO: risistemare dopo la servlet
// var _this = this;
// $.getJSON({
//     type: "GET",
//     url: 'http://localhost:8080/TWEB_war_exploded/RetrievePrenotazioniUtenteServlet',
//     data: 'jSessionId='+ user,
//     success: function (jsonPersonalHistory) {
//         console.log("user: " + user);
//         _this.jsonPersonalHistory = jsonPersonalHistory;
//         console.log("Elenco prenotazioni " + jsonPersonalHistory);
//         _this.state.personalHistoryJSON = jsonPersonalHistory;
//         console.log(_this.state.personalHistoryJSON);
//     }
// });

const retrieveAllTutors = (context) => {
    $.getJSON('http://localhost:8080/TWEB_war_exploded/PopolaDocenteServlet', function(jsonTutor){
        context.commit('setTutorsForCourses', jsonTutor);
    })
}

export default{
    login,
    retrieveAllTutors,
    retrieveTutorAvailability,
    // retrieveClientsHistory,
    retrievePersonalHistory,
    retrieveTutorsForCourse
}