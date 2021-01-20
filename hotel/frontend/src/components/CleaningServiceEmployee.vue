<template>

  <b-form  v-on:submit.prevent="submitForm">
    <h2> Add new cleaning service employee:</h2>

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
      <label for="workingHours">Employee Working Hours per week:</label>
      <input type="text" class="form-control" id="workingHours" placeholder="Please provide working hours/week of employee"
             v-model="form.workingHours" required>
    </div>

    <div class="form-group">
      <label for="responsibility">Employee responsibility:</label>
      <b-form-select id="responsibility" v-model="selected" v-on:change="onChange()"
                     :options="options" required>
      </b-form-select>

    </div>

    <div class="form-group">
      <button class="btn btn-info" style="margin-top: 10px">Add employee</button>
    </div>
    <b-alert variant="success" show v-if="showSuccess"> You added successfully new employee with ID: <strong>{{employeeId}}</strong>.
    </b-alert>

  </b-form>

</template>

<script>
import axios from "axios";
export default {
  name: "CleaningServiceEmployee",
  methods: {
    getHotel() {
      axios.get("http://localhost:8000/getHotels").then(
          (res) => {
            this.form.hotel.hotelId = res.data[0].hotelId;
            this.form.hotel.address = res.data[0].address;
            this.form.hotel.rooms = res.data[0].rooms;
          }
      )
    },
    submitForm(){
      axios.post("http://localhost:8000/addCleaningEmployee", this.form).then(
          (res) => {
            this.employeeId = res.data.employeeId;
            this.showSuccess =true;
          }
      )
    },
    onChange() {
      this.form.responsibility = this.selected;
    }
  },
  beforeMount() {
  this.getHotel();
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
        workingHours: null,
        responsibility: null,
      },
      options: [{text: 'Changing sheets', value: "Changing sheets"}, {
        text: 'Cleaning bathroom',
        value: 'Cleaning bathroom'
      }, {text: 'Cleaning floor', value: 'Cleaning floor'}],
      selected: null,
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
