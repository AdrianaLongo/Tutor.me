// = setters in Java (cambiamenti sincroni)

// import jQuery from "jquery";

const selectCourse = (state, payload) => {
        state.course.nome = payload;
    };

const selectTutorName = (state, payload) => {
        state.tutor.nome = payload;
    };

const selectTutorSurname = (state, payload) => {
        state.tutor.cognome = payload;
    };

const selectTutorId = (state, payload) => {
        state.tutor.id = payload;
    };
const selectSlot = (state, payload) => {
        state.prenotazione.slot = payload;
    };
const selectForDelete = (state, payload) => {
        // state.prenotazione.slot = payload.slot;
        state.prenotazione.idPrenotazione = payload.idPrenotazione;
        state.prenotazione.nomeCorso = payload.nomeCorso;
        state.prenotazione.nomeDocente = payload.nomeDocente;
        state.prenotazione.cognomeDocente = payload.cognomeDocente;
    };

const setCurrentSession = (state, username) => {
    state.isLogged = true;
    state.username = username;
}

const setJsonTutor = (state, jsonTutor) => {
    state.tutorJSON = jsonTutor;
}

const setJsonDisponibilita = (state, jsonDisponibilita) => {
    state.disponibilitaJSON = jsonDisponibilita;
    state.disponibilita.slot = state.disponibilitaJSON;
}

const setJsonPersonalHistoryComplete = (state, jsonPersonalHistory) => {
    state.jsonPersonalHistory = jsonPersonalHistory;
}

const setJsonPersonalHistoryAttive = (state, jsonAttive) => {
    state.jsonAttive = jsonAttive;
}

const setJsonPersonalHistory = (state, jsonAttive) => {
    state.jsonPersonalHistory = jsonAttive;
}


const setTutorsForCourses = (state, jsonTutor) => {
    state.tutorsForCourses = jsonTutor
}

export default{
    selectCourse,
    selectTutorName,
    selectTutorSurname,
    selectTutorId,
    selectSlot,
    selectForDelete,

    setCurrentSession,
    setJsonTutor,
    setJsonDisponibilita,
    setJsonPersonalHistoryComplete,
    setJsonPersonalHistoryAttive,
    setJsonPersonalHistory,
    setTutorsForCourses
}