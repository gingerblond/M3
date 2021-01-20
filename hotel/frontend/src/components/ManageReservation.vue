<template>
  <b-container fluid="sm">
    <h1> Manage reservations</h1>
    <b-form id="app" v-on:submit.prevent="getReservation">
      <div class="form-group">
        <label for="reservationID">Customer ID:</label>
        <input type="text" class="form-control" id="reservationID" placeholder="Please provide your CustomerID"
               v-model="form.customerID" required>
      </div>
      <div class="form-group">
        <button class="btn btn-info" style="margin-top: 10px">View Reservations List</button>
      </div>
    </b-form>
    <table v-if="reservations.length>0 && showTable">
      <tr>
        <th> Reservation ID</th>
        <th> Start Date</th>
        <th> Duration</th>
        <th> Total Price</th>
        <th> Customer Name</th>
        <th> Room Type</th>
        <th> Manage Reservation</th>
      </tr>
      <tr v-for="res in reservations" :key="res.date">
        <td> {{ res.reservationID }}</td>
        <td> {{ res.date }}</td>
        <td> {{ res.duration }}</td>
        <td> ${{ res.price }}</td>
        <td> {{ res.customer.firstName }} {{ res.customer.lastName }}</td>
        <td> {{ res.room.type }}</td>
        <td>
          <button class="btn-outline-info" v-on:click="deleteReservation(res.reservationID)">Delete</button>
          <button class="btn-outline-info" v-on:click="editReservation(res.reservationID)">Edit</button>
        </td>
      </tr>
    </table>
    <div>{{deleteRes}}</div>
    <div v-if="reservations.length==0" style="color: crimson"> No reservations for customer with ID: {{ form.customerID }}</div>
    <div  style="font-weight: bold;font-size: large;margin-top: 10px" v-if="showForm"> Edit reservation with ID: {{resId}}</div>
    <edit-reservation :parent-data="$data.editRes" v-if="showForm"></edit-reservation>
  </b-container>


</template>

<script>
import axios from 'axios';
import EditReservation from "./EditReservation";

export default {
  name: "ManageReservation",
  components: {EditReservation},
  methods: {
    getReservation() {
      this.showTable=true;
      axios.get(`http://localhost:8000/getReservationsByCustomer/${this.form.customerID}`).then(
          (res) => {
            this.reservations = res.data;
           }
      )
    },
    editReservation(id) {
      this.showForm = true;
      this.resId =id;
      axios.get(`http://localhost:8000/getReservation/${id}`).then(
          (res) =>{
            this.editRes = res.data;
          }
      )
    } ,
    deleteReservation(id) {
      this.showForm = false;
      axios.delete(`http://localhost:8000/deleteReservation/${id}`).then(
          (res) => {
            this.deleteRes = res.data;
          }
      )
      this.getReservation();
    },
    getAll() {
    }
  },
    data() {
      return {
        form: {
          customerID: null
        },
        deleteRes: null,
        reservations: [{
          reservationID: null,
          price: null,
          date: null,
          duration: null,
          customer: {
            customerId: null,
            firstName: null,
            lastName: null,
            idCard: null
          },
          room: {
            roomID: null,
            available: true,
            type: null
          }
        }
        ],
        resId:null,
        editRes: null,
        showForm: false,
        showTable: false,
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

table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
</style>
