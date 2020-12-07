<template>
  <div>
    <nav class="navbar navbar-expand-lg fixed-top ">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse " id="navbarSupportedContent">
        <ul class="navbar-nav mr-4">
          <li class="nav-item active">
            <router-link class="nav-link" to="/">Home
              <span class="sr-only">(current)</span>
            </router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="courses">Catalogo corsi</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="tutors">Tutor</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="calendar">Calendario disponibilità</router-link>
          </li>
          <li>
            <a href="#LogInButton" role="button" class="btn btn-outline" data-toggle="modal">Login</a>
          </li>
          <li>
            <a href="#" class="btn btn-outline">Sign up</a>
          </li>
        </ul>
      </div>
    </nav>
  </div>




</template>

<script>
export default {
  name: "Navbar"
}
</script>

<style scoped>
  .navbar {
    background: #5e6174;
  }
  .nav-link,
  .navbar-brand {
    color: #f4f4f4;
    cursor: pointer;
  }
  .nav-link {
    margin-right: 1em !important;
  }
  .nav-link:hover {
    background: #f4f4f4;
    color: #5e6174;
  }
  .btn-outline {
    border: 1px solid #324530;
    background: #538b5e;
    color: #fff;
    margin-left: 5px;
    margin-right: 5px;
  }
  .btn-outline:hover,
  .btn-outline:active,
  .btn-outline:focus,
  .btn-outline.active {
    background: #fff;
    color: #538b5e;
    border-color: #4fbfa8;
  }
  .navbar-collapse {
    justify-content: flex-end;
  }
  .navbar-toggler {
    background: #fff !important;
  }
</style>