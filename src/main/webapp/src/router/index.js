import Vue from 'vue'
import Router from 'vue-router'
import PageHome from "@/components/pages/PageHome.vue";
import PageCourses from "@/components/pages/PageCourses.vue";
import PageTutors from "@/components/pages/PageTutors.vue";
import PageAvailability from "@/components/pages/PageAvailability.vue";

Vue.use(Router)

export default new Router({
    routes: [
        { path: '/', name: "Home", component: PageHome },
        { path: '/courses', name: "Courses", component: PageCourses },
        { path: '/tutors', name: "Tutors", component: PageTutors },
        { path: '/calendar', name: "Availability", component: PageAvailability}
    ]
})