<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="info">
      <b-navbar-brand href="#">Hotel 5</b-navbar-brand>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item v-on:click="$router.push('/')">
            Home
          </b-nav-item>
          <b-nav-item v-on:click="$router.push('reservation')">
            New Reservation
          </b-nav-item>
          <b-nav-item v-on:click="$router.push('manageReservations')">
            Manage Reservations
          </b-nav-item>
        </b-navbar-nav>
        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-item v-on:click="$router.push('db')"> Fill-in DB</b-nav-item>
          <b-nav-item v-on:click="$router.push('login')">LogIn as ADMIN</b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: "header"
}
</script>

<style scoped>

</style>
