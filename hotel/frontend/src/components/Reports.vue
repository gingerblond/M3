<template>
  <b-container fluid="sm" style="width:1200px">
    <h1> Reports</h1>
    <div v-if="$store.state.loggedIn" style="color: darkgreen; font-weight: bold">You are currently logged in!</div>
    <div v-if="!$store.state.loggedIn" style="color: crimson; font-weight: bold">You are currently NOT logged in! Please
      log in here:
      <b-button class="btn btn-info" v-on:click="$router.push('login')">
        LOGIN
      </b-button>
    </div>
    <div class="row no-gutter" v-if="$store.state.loggedIn">
      <div class="col-md-8 col-lg-6 ">
        <table style="margin-top: 50px;">
          <tr>
            <th> Description</th>
            <th> Columns</th>
            <th> Tables</th>
            <th> Get report</th>
          </tr>
          <tr>
            <td> How many of the cleaning service
              employees of Hotel 5 ,
              are working exactly 20h/week
            </td>
            <td>
              <ul style="align-self: flex-start">
                <li>hotel_id</li>
                <li>workingHours</li>
              </ul>
            </td>
            <td>
              <ul>
                <li>Hotel</li>
                <li>CleaningService
                  Employee
                </li>
              </ul>
            </td>
            <td>
              <button class="btn-outline-info" v-on:click="getCleaningEmployees">Get Report</button>
            </td>
          </tr>
          <tr>
            <td> How many of the "Single rooms" are booked for more than 2 days
            </td>
            <td>
              <ul>
                <li>duration</li>
                <li>roomType</li>
              </ul>
            </td>
            <td>
              <ul>
                <li>Reservation</li>
                <li>Reservation.Rooms.Room</li>
              </ul>
            </td>
            <td>
              <button class="btn-outline-info" v-on:click="getRooms">Get Report</button>
            </td>
          </tr>
          <tr>
            <td> All reservation with full customer, reservation and room information
            </td>
            <td>
            </td>
            <td>
              <ul>
                <li>Reservation.Customer</li>
                <li>Reservation.Rooms.Room</li>
              </ul>
            </td>
            <td>
              <button class="btn-outline-info" v-on:click="getReservations">Get Report</button>
            </td>
          </tr>
        </table>
      </div>
      <div class="col-md-6 col-lg-6 ">
        <button class="btn btn-outline-info" v-on:click="$router.push('login')" type="button"
                style="width:300px; margin-top: 20px"> Go back to admin tools
        </button>
        <h2 v-if="showCleanTable" style="margin-top: 20px"> Cleaning service employees of Hotel 5, working exactly 20h/week</h2>
        <h2 v-if="showCleanTable">SortedBy: CleaningServiceEmployee.LastName in descending order</h2>
        <table v-if="cleaningEmployees.length>0 && showCleanTable" style="margin-top: 30px;margin-left: 30px">
          <tr>
            <th> ID</th>
            <th> First Name</th>
            <th> Last Name</th>
            <th> Social ID</th>
            <th> Responsibility</th>
          </tr>
          <tr v-for="employee in cleaningEmployees" :key="employee.employeeId">
            <td> {{ employee.employeeId }}</td>
            <td> {{ employee.firstName }}</td>
            <td> {{ employee.lastName }}</td>
            <td> {{ employee.socialId }}</td>
            <td>{{ employee.responsibility }}</td>
          </tr>
        </table>

        <div v-if="cleaningEmployees.length==0 && showCleanTable" style="color: crimson;margin-top: 30px"> There are no cleaning service employees, who
          works 20 Hours a week right now!
        </div>

        <h2 v-if="showResTable" style="margin-top: 20px"> "Single Rooms", that are reserved for more than 2 days: </h2>
        <h2 v-if="showResTable"> SortedBy: Reservation.Duration in descending order</h2>
        <table v-if="roomsMoreThan2days.length>0 && showResTable" style="margin-top: 30px;margin-left: 30px">
          <tr>
            <th> Room ID</th>
            <th> ReservationId</th>
            <th> Customer Id Card</th>
            <th> Duration</th>
            <th> Start Date</th>
          </tr>
          <tr v-for="res in roomsMoreThan2days" :key="res.reservationId">
            <td> {{ res.room.roomId }}</td>
            <td> {{ res.reservationId}}</td>
            <td> {{ res.customer.idCard }}</td>
            <td>{{ res.duration }}</td>
            <td>{{ res.date }}</td>

          </tr>
        </table>

        <div v-if="roomsMoreThan2days.length==0 && showResTable" style="color: crimson;margin-top: 30px"> There are no single rooms, booked for more than
          2 days right now!
        </div>

        <h2 v-if="showReservTable" style="margin-top: 20px"> All reservation with full customer, reservation and room information:</h2>
        <h2 v-if="showReservTable"> SortedBy: Reservation.Rooms.Room.RoomId in ascending order</h2>
        <table v-if="reservations.length>0 && showReservTable" style="margin-top: 30px;margin-left: 30px">
          <tr>
            <th> Room ID</th>
            <th> Reservation ID</th>
            <th> Customer Id Card</th>
            <th> Customer Name</th>
            <th> Duration</th>
            <th>Room type</th>
            <th> Start Date</th>
          </tr>
          <tr v-for="res in reservations" :key="res.reservationId">
            <td> {{ res.room.roomId }}</td>
            <td> {{ res.reservationId }}</td>
            <td> {{ res.customer.idCard }}</td>
            <td>{{ res.customer.firstName }} {{ res.customer.lastName }}</td>
            <td>{{ res.duration }}</td>
            <td> {{ res.room.type }}</td>
            <td>{{ res.date }}</td>
          </tr>
        </table>

        <div v-if="reservations.length==0 && showReservTable" style="color: crimson;margin-top: 30px"> There are no current reservations!
        </div>
      </div>
    </div>
  </b-container>
</template>

<script>
import axios from "axios";

export default {
  name: "Reports",
  methods: {
    getCleaningEmployees() {
      axios.get("http://localhost:8000/mongoCleaningService20Hours").then(
          (res) => {
            this.cleaningEmployees = res.data;
            this.showCleanTable = true;
            this.showResTable = false;
            this.showReservTable = false;
          }
      )
    },
    getRooms() {
      axios.get("http://localhost:8000/mongoSingleRoomsMoreThan2Days").then(
          (res) => {
            this.roomsMoreThan2days = res.data;
            this.showResTable = true;
            this.showCleanTable = false;
            this.showReservTable = false;
          }
      )
    },
    getReservations() {
      axios.get("http://localhost:8000/mongoAllReservations").then(
          (res) => {
            this.reservations = res.data;
            this.showReservTable = true;
            this.showCleanTable = false;
            this.showResTable = false;


          }
      )
    }
  },
  data() {
    return {
      cleaningEmployees: [
        {
          employeeId: null,
          firstName: null,
          lastName: null,
          socialId: null,
          hotel: {
            address: null,
          },
          workingHours: null,
          responsibility: null,

        }
      ],
      roomsMoreThan2days: [
        {
          reservationId: null,
          firstName: null,
          lastName: null,
          customer:{
            idCard: null
          },
          date: null,
          duration: null,
          room:{
            roomId: null,
            type: null
          },

        }],
      reservations: [
        {
          reservationId: null,
          customer:{
            idCard: null,
            firstName: null,
            lastName: null
          },
          date: null,
          duration: null,
          room:{
            roomId: null,
            type: null
          },

        }],
      showCleanTable: false,
      showResTable: false,
      showReservTable: false,
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

h2 {
  font-weight: bold;
  font-size: large;
  color: #2c3e50;
}

table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
</style>
