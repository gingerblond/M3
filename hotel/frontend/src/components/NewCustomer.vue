<template>
  <form id="app" v-on:submit.prevent="addCustomer">
    <div class="form-group">
      <label for="idCard">ID Card</label>
      <input type="text" class="form-control" id="idCard" placeholder="Please put your ID Card Number" v-model="form.idCard">
    </div>

    <div class="form-group">
      <label for="firstName">First Name</label>
      <input type="text" class="form-control" id="firstName" placeholder="Please put your First Name" v-model="form.firstName">
    </div>

    <div class="form-group">
      <label for="lastName">Last Name</label>
      <input type="text" class="form-control" id="lastName" placeholder="Please put your Last Name" v-model="form.lastName">
    </div>

    <div class="form-group">
      <button class="btn btn-primary">Submit</button>
    </div>

  </form>
</template>

<script>

import axios  from 'axios';

export default {
  name: "NewCustomer",
  methods: {
    addCustomer(){
      axios.post('http://localhost:8000/addCustomer',this.form);
    }
  },
  data() {
    return {
      form: {
        idCard: null,
        firstName: null,
        lastName: null
      }
    }
  }
}
</script>

<style scoped>

</style>
