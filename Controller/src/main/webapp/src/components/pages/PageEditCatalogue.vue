<template>
  <b-container class="mt-5">
    <h1>Modifica catalogo</h1>

    <div class="mt-4 mb-4">
      <b-card no-body>
        <b-tabs card lazy fill pills>
        <!-- LAZY ci permette di caricare il contenuto della tab solo quando attiva:
        in questo modo il contenuto delle select e' sempre aggiornato -->
          <b-tab title="Aggiungi corso" active>
            <add-course></add-course>
          </b-tab>
          <b-tab title="Rimuovi corso">
            <remove-course></remove-course>
          </b-tab>
          <b-tab title="Aggiungi tutor">
            <add-tutor></add-tutor>
          </b-tab>
          <b-tab title="Rimuovi tutor">
            <remove-tutor></remove-tutor>
          </b-tab>
          <b-tab title="Associa corso-docente">
            <add-association-course-tutor></add-association-course-tutor>
          </b-tab>
          <b-tab title="Rimuovi associazione corso-docente">
            <remove-association-course-tutor></remove-association-course-tutor>
          </b-tab>
        </b-tabs>
      </b-card>

  </div>

  </b-container>
</template>

<script>
import AddCourse from "@/components/editCatalogue/AddCourse";
import RemoveCourse from "@/components/editCatalogue/RemoveCourse";
import AddTutor from "@/components/editCatalogue/AddTutor";
import RemoveTutor from "@/components/editCatalogue/RemoveTutor";
import AddAssociationCourseTutor from "@/components/editCatalogue/AddAssociationCourseTutor";
import RemoveAssociationCourseTutor from "@/components/editCatalogue/RemoveAssociationCourseTutor";

export default {
  name: "PageEditCatalogue",
  components: {
    AddCourse,
    RemoveCourse,
    AddTutor,
    RemoveTutor,
    AddAssociationCourseTutor,
    RemoveAssociationCourseTutor
  },
}
</script>

<style scoped>

</style>