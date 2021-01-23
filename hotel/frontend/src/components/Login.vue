<template>
  <b-container fluid="sm" style="width:400px">
    <h1> Login/Logout as admin</h1>
    <div v-if="!$store.state.loggedIn" style="color: darkgreen; font-weight: bold">You are currently logged out!</div>
    <div v-if="!$store.state.loggedIn" style="color: darkgreen; font-weight: bold">Please provide your credentials:
    </div>
    <b-form class="form-control-sm" v-on:submit.prevent="submitForm" v-if="!$store.state.loggedIn">
      <div class="form-group">
        <label for="userName">Username:</label>
        <input type="text" class="form-control" id="userName" placeholder="Please put your username"
               v-model="form.username" required>
      </div>

      <div class="form-group">
        <label for="password">Password:</label>
        <input type="text" class="form-control" id="password" placeholder="Please put your password"
               v-model="form.password" required>
      </div>

      <div class="form-group">
        <button class="btn btn-info" style="margin-top: 10px">Login</button>
      </div>
      <b-alert variant="success" show v-if="result==='SUCCESS'"> You are successfully logged in!</b-alert>
      <b-alert variant="danger" show v-if="result==='FAILURE'"> You do not have admin credentials!</b-alert>

    </b-form>
    <div v-if="$store.state.loggedIn" style="color: darkgreen; font-weight: bold">You are currently logged in!</div>
    <div v-if="$store.state.loggedIn" style="color: darkgreen; font-weight: bold">If you want to log-out , please click
      on the button:
    </div>
    <button class="btn btn-info" style="margin-top: 10px" v-if="$store.state.loggedIn" v-on:click="logOut">Logout</button>


    <b-card class="bv-example-row" style="margin-top: 300px" v-if="$store.state.loggedIn">
      <b-card-title>Administrator Tools:</b-card-title>
      <b-card-body>
        <b-row>
          <b-col v-on:click="$router.push('roomAdmin')">
            <b-button class="btn-info">Room Administration</b-button>
          </b-col>
          <b-col v-on:click="$router.push('customerAdmin')">
            <b-button class="btn-info">Customer Administration</b-button>
          </b-col>
          <div class="w-auto" style="margin-top: 10px"></div>
          <b-col style="margin-top: 10px" v-on:click="$router.push('employeeAdmin')">
            <b-button class="btn-info">Employee Administration</b-button>
          </b-col>
          <b-col style="margin-top: 10px" v-on:click="$router.push('reports')">
            <b-button class="btn-info">Reports Administration</b-button>
          </b-col>
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
            if (this.result === 'SUCCESS') {
              this.$store.state.loggedIn = true;
            } else this.$store.state.loggedIn = false;

          }
      )
    },
    logOut() {
      axios.post(`http://localhost:8000/users/logout/${this.form.userId}` ).then(
          (res) => {
            this.logOutRes = res.data;
            if (this.logOutRes === 'SUCCESS') {
              this.$store.state.loggedIn = false;
              this.getUsers();
            } else this.$store.state.loggedIn = true;
          }
      )
    },
    getUsers() {
      axios.get("http://localhost:8000/getUsers").then(
          (res) => {
            this.usersList = res.data;
            this.form.userId = this.usersList[0].userId;
            this.$store.state.loggedIn = this.usersList[0].loggedIn;
          }
      )
    }
  },
  beforeMount() {
    this.getUsers();
  },
  data() {
    return {
      form: {
        userId: null,
        username: null,
        password: null,
      },
      result: null,
      logOutRes: null,
      usersList: [
        {
          userId: null,
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
