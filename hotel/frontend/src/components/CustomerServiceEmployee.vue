<template>

  <b-form  v-on:submit.prevent="submitForm">
    <h2> Add new customer service employee:</h2>

    <div class="form-group" >
      <label for="firstName">Employee First Name:</label>
      <input type="text" class="form-control" id="firstName" placeholder="Please provide first name of employee"
             v-model="form.firstName" required>
    </div>

    <div class="form-group">
      <label for="lastName">Employee Last Name:</label>
      <input type="text" class="form-control" id="lastName" placeholder="Please provide last name of employee"
             v-model="form.lastName" required>
    </div>

    <div class="form-group">
      <label for="socialID">Employee Social ID:</label>
      <input type="text" class="form-control" id="socialID" placeholder="Please provide social ID of employee"
             v-model="form.socialId" required>
    </div>



    <div class="form-group">
      <label for="phoneNumber">Employee Phone  Number:</label>
      <input type="text" class="form-control" id="phoneNumber" placeholder="Please provide phone Number of employee"
             v-model="form.phoneNumber" required>
    </div>

    <div class="form-group">
      <label for="email">Employee Email:</label>
      <input type="text" class="form-control" id="email" placeholder="Please provide email of employee"
             v-model="form.email" required>
    </div>

    <div class="form-group">
      <button class="btn btn-info" style="margin-top: 10px">Add employee</button>
    </div>
    <b-alert variant="success" show v-if="showSuccess"> You added successfully new employee with ID: <strong>{{employeeId}}</strong>. Username is:<strong>{{form.user.username}}</strong>.
      Password is:<strong>{{form.user.password}}</strong>
    </b-alert>

  </b-form>
</template>

<script>
import axios from "axios";
export default {
  name: "CustomerServiceEmployee",
  methods: {
    getHotel(){
      axios.get("http://localhost:8000/getHotels").then(
          (res) =>{
            this.form.hotel.hotelId = res.data[0].hotelId;
            this.form.hotel.address = res.data[0].address;
            this.form.hotel.rooms = res.data[0].rooms;
          }
      )
    },
    getUser(){
      axios.get("http://localhost:8000/getUsers").then(
          (res) => {
            this.form.user.id = res.data[0].id;
            this.form.user.password = res.data[0].password;
            this.form.user.username = res.data[0].username;
          }
      )
    },
    submitForm(){
      axios.post("http://localhost:8000/addCustomerEmployee", this.form).then(
          (res) => {
            this.employeeId = res.data.employeeId;
            this.showSuccess =true;
          }
      )
    }
  },
  beforeMount() {
    this.getHotel();
    this.getUser();
  },
  data() {
    return {
      form:{
        firstName: null,
        lastName: null,
        socialId: null,
        hotel: {
          hotelId: null,
          address: null,
          rooms:[
            {
              roomID: null,
              available: null,
              type: null
            }
          ]
        },
        phoneNumber: null,
        email: null,
        user: {
          id: null,
          username: null,
          password: null
        }
      },
      employeeId: null,
      showSuccess: false
    };
  }
}
</script>

<style scoped>
h2 {
  font-weight: bold;
  font-size: large;
  color: #2c3e50;
}
</style>
