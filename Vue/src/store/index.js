import Vue from 'vue';
import Vuex from 'vuex';

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
            idPrenotazione: '',
            idDocente: '',
            nomeCorso: '',
            idUtente: '',
            slot: '',
            stato: ''
        }
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
        tutorId: state => {
            return state.tutor.id;
        },
        prenotazioneSlot: state => {
            return state.prenotazione.slot;
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
        selectTutor(state, payload){
            state.tutor.id = payload;
        }
    },

    // Cambiamenti asincroni
    // actions: {
    //     changeName (context, payload) {
    //         setTimeout(() => {
    //             context.commit("changeName", payload);
    //         }, 2000);
    //     }
    // }
});

