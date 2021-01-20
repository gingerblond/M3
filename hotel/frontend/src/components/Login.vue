<template>
<b-container fluid="sm" style="width:400px">
  <h1> Login as admin</h1>

  <b-form class="form-control-sm" v-on:submit.prevent="submitForm">
    <div class="form-group">
      <label for="userName">Username:</label>
      <input type="text" class="form-control" id="userName" placeholder="Please put your username" v-model="form.username" required>
    </div>

    <div class="form-group">
      <label for="password">Password:</label>
      <input type="text" class="form-control" id="password" placeholder="Please put your password" v-model="form.password" required>
    </div>

    <div class="form-group">
      <button class="btn btn-info" style="margin-top: 10px">Login</button>
    </div>
    <b-alert variant="success" show v-if="result==='SUCCESS'"> You are successfully logged in! </b-alert>
    <b-alert variant="danger" show v-if="result==='FAILURE'"> You do not have admin credentials! </b-alert>

  </b-form>


  <b-card class="bv-example-row" style="margin-top: 300px" v-if="result==='SUCCESS'">
    <b-card-title>Administrator Tools:</b-card-title>
    <b-card-body>
    <b-row>
      <b-col v-on:click="$router.push('roomAdmin')"><b-button class="btn-info">Room Administration</b-button></b-col>
      <b-col v-on:click="$router.push('customerAdmin')"><b-button class="btn-info">Customer Administration</b-button></b-col>
      <div class="w-auto" style="margin-top: 10px"></div>
      <b-col style="margin-top: 10px" v-on:click="$router.push('employeeAdmin')"><b-button class="btn-info">Employee Administration</b-button></b-col>
      <b-col style="margin-top: 10px" v-on:click="$router.push('reports')"><b-button class="btn-info">Reports Administration</b-button></b-col>
    </b-row>
    </b-card-body>
  </b-card>
</b-container>
</template>

<script>
import axios from 'axios';

export default {
  name: "Login",
  methods: {
    submitForm() {
      axios.post("http://localhost:8000/users/login", this.form).then(
          (res) => {
            this.result = res.data;
          }
      )
    },
    getUsers(){
      axios.get("http://localhost:8000/getUsers").then(
          (res) => {
            this.usersList = res.data;
            this.form.id = this.usersList[0].id;
          }
      )
    }
  },
  beforeMount() {
    this.getUsers();
  },
  data() {
    return{
      form:{
        id: null,
        username: null,
        password: null,
      },
      result: null,
      usersList:[
        {
          id: null,
          username: null,
          password: null,
          loggedIn: null
        }
      ]
    };
  }
}
</script>

<style scoped>
h1 {
  font-weight: bold;
  font-size: xx-large;
  color: #2c3e50;
}
</style>
