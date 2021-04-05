// = getters in Java

const courseName = state => state.course;

const tutorName = state => state.tutor.nome;

const tutorFullName = state => state.tutor.nome + " " + state.tutor.cognome;

const tutorId = state => state.tutor.id;

const elencoTutor = state => state.tutorJSON;

const prenotazioneSlot = state => state.prenotazione.slot;

const elencoDisponibilita = state => state.disponibilitaJSON;

const elencoMiePrenotazioni = state => state.jsonPersonalHistory;

//const currentToken = state => state.token;

const userLogged = state => state.client.isLogged;

const role = state => state.client.role;

const clientName = state => state.client.nome;

const clientSurname = state => state.client.cognome;

const clientId = state => state.client.id;

const elencoPrenotazioniCliente = state => state.jsonClientsHistory;

export default {
    courseName,
    tutorName,
    tutorFullName,
    tutorId,
    elencoTutor,
    prenotazioneSlot,
    elencoDisponibilita,
    elencoMiePrenotazioni,
    //currentToken,
    role,
    userLogged,
    clientName,
    clientSurname,
    clientId,
    elencoPrenotazioniCliente
}