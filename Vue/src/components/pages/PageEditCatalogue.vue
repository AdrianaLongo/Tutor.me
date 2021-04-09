<template>
  <div class="mt-4 mb-4">

    <add-course></add-course>

    <add-tutor></add-tutor>

    <remove-course></remove-course>

    <remove-tutor></remove-tutor>

    <add-association-course-tutor></add-association-course-tutor>

    <remove-association-course-tutor></remove-association-course-tutor>

    <!--    LAYOUT CON COLONNE-->
    <!--    <b-row>-->
    <!--      <b-col>-->
    <!--        <add-course></add-course>-->
    <!--      </b-col>-->
    <!--      <b-col>-->
    <!--        <add-tutor></add-tutor>-->
    <!--      </b-col>-->
    <!--    </b-row>-->

    <!--    <b-row>-->
    <!--      <b-col>-->
    <!--        <remove-course></remove-course>-->
    <!--      </b-col>-->
    <!--      <b-col>-->
    <!--        <remove-tutor></remove-tutor>-->
    <!--      </b-col>-->
    <!--    </b-row>-->

    <!--    <b-row>-->
    <!--      <b-col>-->
    <!--        <add-association-course-tutor></add-association-course-tutor>-->
    <!--      </b-col>-->
    <!--      <b-col>-->
    <!--        <remove-association-course-tutor></remove-association-course-tutor>-->
    <!--      </b-col>-->
    <!--    </b-row>-->

  </div>

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