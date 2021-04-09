// = setters in Java (cambiamenti sincroni)

// import getters from "@/store/getters";

const selectCourse = (state, payload) => {
    state.course = payload;
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
    state.prenotazione.idPrenotazione = payload.idPrenotazione;
    state.prenotazione.nomeCorso = payload.nomeCorso;
    state.prenotazione.nomeDocente = payload.nomeDocente;
    state.prenotazione.cognomeDocente = payload.cognomeDocente;
};

const setCurrentSession = (state, role) => {
    state.client.isLogged = true;
    state.client.role = role;
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

const deleteCurrentSession = (state, username) => {
    state.client.isLogged = false;
    state.client.username = username; // passo ''
}

const selectClientName = (state, payload) => {
    state.client.nome = payload;
};

const selectClientSurname = (state, payload) => {
    state.client.cognome = payload;
};

const selectClientId = (state, payload) => {
    state.client.id = payload;
};

const setJsonClientsHistory = (state, payload) => {
    state.jsonClientsHistory = payload;
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
    setTutorsForCourses,
    deleteCurrentSession,
    selectClientName,
    selectClientSurname,
    selectClientId,
    setJsonClientsHistory,
}