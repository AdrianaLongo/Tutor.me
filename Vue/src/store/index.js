import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        user: {
            username: 'matt',
            fullName: 'Matt Maribojoc'
        },
        course: {
            nome: ''
        }
    },

    // Quello che mettiamo in getters puo essere visto da altri componenti
    getters: {
        firstName: state => {
            return state.user.fullName.split(' ')[0]
        },
        lastName (state, getters) {
            return state.user.fullName.replace(getters.firstName, '');
        },
        prefixedName: (state, getters) => (prefix) => {
            return prefix + getters.lastName;
        },
        courseName: state => {
            return state.course.nome;
        }

    },

    // Cambiamenti sincroni
    mutations: {
        // changeName (state, payload) {
        //     state.user.fullName = payload
        // }
        selectCourse(state, payload){
            state.course.nome = payload;
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

