<template>
  <div>
    <h1 class="text-center">Catalogo corsi</h1>

    <div class="container">
      <div class="row" id="showCatalogoCorsi">
        <div class="col-sm-4" v-for="course in courses" :key="course.nomeCorso">
          <b-card bg-variant="info" text-variant="white" header="\${course.nomeCorso}"
                  class="text-center">
            <b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
          </b-card>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "PageCourses",
  data() {
    return {
      courses:
          [
            {"nomeCorso":"Inglese","idDocente":"13","disponibilita":"0"},
            {"nomeCorso":"Filosofia","idDocente":"18","disponibilita":"1"},
            {"nomeCorso":"Fisica","idDocente":"19","disponibilita":"1"},
            {"nomeCorso":"Italiano","idDocente":"12","disponibilita":"1"},
            {"nomeCorso":"Matematica","idDocente":"16","disponibilita":"1"},
            {"nomeCorso":"Fisica","idDocente":"16","disponibilita":"1"},
            {"nomeCorso":"Matematica","idDocente":"19","disponibilita":"1"},
            {"nomeCorso":"Greco","idDocente":"21","disponibilita":"1"},
            {"nomeCorso":"Latino","idDocente":"21","disponibilita":"1"},
            {"nomeCorso":"Italiano","idDocente":"20","disponibilita":"1"}
          ],
    };
  }
}
</script>

<style scoped>

</style>