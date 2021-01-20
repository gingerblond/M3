<template>
  <b-form  v-on:submit.prevent="submitForm">
    <h2> Edit customer service employee with ID {{parentData.employeeId}}:</h2>

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
      <button class="btn btn-info" style="margin-top: 10px">Update employee</button>
    </div>
    <b-alert variant="success" show v-if="showSuccess"> You updated successfully employee with ID: <strong>{{employeeId}}</strong>. Username is:<strong>{{form.user.username}}</strong>.
      Password is:<strong>{{form.user.password}}</strong>
    </b-alert>

  </b-form>

</template>

<script>
import axios from "axios";
export default {
  name: "EditCustomerEmployee",
  methods: {
    setData() {
      this.form.hotel = this.parentData.hotel;
      this.form.user = this.parentData.user;
      this.form.employeeId = this.parentData.employeeId;
    },
    submitForm(){
      this.setData();
      axios.put("http://localhost:8000/updateCustomerEmployee", this.form).then(
          (res) => {
            this.employeeId = res.data.employeeId;
            this.showSuccess =true;
          }
      )
    }
  },

  data() {
    return {
      form:{
        employeeId: null,
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
  },
  props: {
    parentData: {
      employeeId: null,
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
        username: null,
        password: null,
      }
    }
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
