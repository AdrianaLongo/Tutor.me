// Chiamano le rispettive mutations per permettere cambiamenti asincroni

// Tutte le richieste alle servlet vanno qui perche sono ajax

import $ from "jquery";
import jQuery from 'jquery'


const login = (context, credentials) => {
    $.post('http://localhost:8080/TWEB_war_exploded/LoginServlet', {
        username: credentials.username,
        password: credentials.password,
    }).then(response => {
        console.log(typeof response)
        console.log(response)
        if(response.includes("Successful login")){
            console.log("OK!")
            // Quando l'utente fa il login, mi conviene mantenere sempre nello store...
            // 1) nome utente
            context.commit('setCurrentSession', '')
            // 2) storico delle prenotazioni dell'utente
            $.getJSON('http://localhost:8080/TWEB_war_exploded/RetrievePrenotazioniUtenteServlet', function (jsonPersonalHistory) {
                context.commit('setJsonPersonalHistoryComplete', jsonPersonalHistory)
                context.commit('setJsonPersonalHistoryAttive', jsonPersonalHistory.filter( element => element.stato === '0'))
                context.commit('setJsonPersonalHistory', jsonPersonalHistory.filter( element => element.stato === '0'))
            });
        }
    })
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

// const retrieveClientsHistory = (context, user) => {
const retrieveClientsHistory = (context, clientId) => {
    // var _this = this;
    // $.getJSON({
    //     type: "GET",
    //     url: 'http://localhost:8080/TWEB_war_exploded/RetrievePrenotazioniUtenteServlet',
    //     data: 'jSessionId=' + user,
    //     success: function (jsonPersonalHistory) {
    //         console.log("user: " + user);
    //         _this.jsonPersonalHistory = jsonPersonalHistory;
    //         console.log("Elenco prenotazioni " + jsonPersonalHistory);
    //         _this.state.personalHistoryJSON = jsonPersonalHistory;
    //         console.log(_this.state.personalHistoryJSON);
    //     }
    // });
    console.log("clientId arrivato: " + clientId)
    $.getJSON({
        type: "GET",
        url: 'http://localhost:8080/TWEB_war_exploded/RetrieveClientHistory',
        data: 'clientId='+clientId,
        success: function (jsonClientHistory) {
            context.commit('setJsonClientsHistory', jsonClientHistory)
            console.log(jsonClientHistory)
        }
    });
}

const retrieveAllTutors = (context) => {
    $.getJSON('http://localhost:8080/TWEB_war_exploded/PopolaDocenteServlet', function(jsonTutor){
        context.commit('setTutorsForCourses', jsonTutor);
    })
}

const logout = (context) => {
    jQuery.post('http://localhost:8080/TWEB_war_exploded/LogoutServlet',{

    })
        .then(response => {
            console.log(response)
            // Quando l'utente fa il logout, devo togliere tutte le sue info dallo store...
            // 1) nome utente
            context.commit('deleteCurrentSession', '')
            // 2) storico delle prenotazioni dell'utente
            context.commit('setJsonPersonalHistoryComplete', '')
            context.commit('setJsonPersonalHistoryAttive', '')
            context.commit('setJsonPersonalHistory', '')
            // il reindirizzamento alla homepage avviene in logout.vue
        })


};




export default{
    login,
    retrieveAllTutors,
    retrieveTutorAvailability,
    retrieveClientsHistory,
    retrievePersonalHistory,
    retrieveTutorsForCourse,
    logout
}