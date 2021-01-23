<template>
  <b-container fluid="sm" style="width:1000px">

    <h1> Room Administration</h1>
    <div v-if="$store.state.loggedIn" style="color: darkgreen; font-weight: bold">You are currently logged in!</div>
    <div v-if="!$store.state.loggedIn" style="color: crimson; font-weight: bold">You are currently NOT logged in! Please log in here: <b-button class="btn btn-info" v-on:click="$router.push('login')">
      LOGIN</b-button> </div>
    <div class="row no-gutter" v-if="this.$store.state.loggedIn">
      <div class="col-md-8 col-lg-6 ">
        <div>Get available Rooms or get all rooms under construction(unavailable, but not reserved:</div>
        <b-button class="btn btn-info" style="width:300px; margin-top: 20px" v-on:click="getRooms"> Get rooms
        </b-button>
         <table v-if="roomsList.length>0 && showTable" style="margin-top: 50px;">
          <tr>
            <th> Room ID</th>
            <th> Room Type</th>
            <th> Is available?</th>
            <th> Manage Rooms</th>
          </tr>
          <tr v-for="room in roomsList" :key="room.roomId">
            <td> {{ room.roomId }}</td>
            <td> {{ room.type }}</td>
            <td> {{ room.available }}</td>
            <td>
              <button class="btn-outline-info" v-on:click="deleteRoom(room.roomId)">Delete</button>
              <button class="btn-outline-info" v-on:click="editRoom(room.roomId)">Edit</button>
            </td>
          </tr>
        </table>

        <div v-if="roomsList.length==0" style="color: crimson"> No available rooms right now! Please, try later !</div>
      </div>
      <div class="col-md-8 col-lg-6">
        <button class="btn btn-outline-info" v-on:click="$router.push('login')"  type="button" style="width:300px; margin-top: 20px" > Go back to admin tools
        </button>
        <b-alert variant="success" show v-if="showDelete" style="margin-top: 95px"><strong>{{ delRoomMsg }}</strong>
        </b-alert>
        <div style="margin-top: 40px;" v-if="showEdit">
          <h2> Please edit room with ID {{this.form.roomId}}:</h2>
          <b-form v-on:submit.prevent="submitForm">
            <div class="form-group" style="margin-top: 25px">
              <label for="roomType">Room Type</label>
              <b-form-select id="roomType" v-model="selected" v-on:change="onChange()"
                             :options="options" required>
              </b-form-select>
            </div>
            <div class="form-group">
              <label for="availability">Availability:</label>
              <input type="text" class="form-control" id="availability" placeholder="Please choose true(if you want to set room on available and false(if you want to set room on unavailable )"
                     v-model="form.available" required>
            </div>
            <div class="form-group">
              <button class="btn btn-info" style="margin-top: 10px">Submit changes</button>
            </div>

            <b-alert variant="success" show v-if="submitSuccess"> You updated successfully room with ID: <strong>{{roomId}}</strong>
            </b-alert>
          </b-form>
        </div>
      </div>
    </div>

  </b-container>
</template>

<script>

import axios from 'axios';

export default {
  name: "RoomAdmin",
  methods: {
    getRooms() {
      axios.get('http://localhost:8000/getAvailableRooms').then(
          (res) => {
            this.showTable = true;
            this.roomsList = res.data;
          }
      )
    },
    deleteRoom(id) {
      axios.delete(`http://localhost:8000/deleteRoom/${id}`).then(
          (res) => {
            this.showDelete = true;
            this.delRoomMsg = res.data;
            this.roomsList= this.roomsList.filter(room=>room.roomId!=id);

          }
      )
    },
    onChange() {
      this.form.type = this.selected;
    },
    editRoom(id){
      this.form.roomId = id;
      this.showEdit = true;
    },
    submitForm(){
      axios.put("http://localhost:8000/updateRoom",this.form).then(
          (res) => {
            this.roomId = res.data.roomId;
            this.submitSuccess = true;
            this.getRooms();
          }
      )
    }

  },
  data() {
    return {
      form: {
        roomId: null,
        type: null,
        available: null,
      },
      roomsList: [
        {
          roomId: null,
          type: null,
          available: null
        }
      ],
      selected: null,
      options: [{text: 'Choose room type', value: null}, {
        text: 'Single room',
        value: 'SINGLE_ROOM'
      }, {text: 'Double room', value: 'DOUBLE_ROOM'},
        {text: 'Apartment', value: 'APARTMENT'}, {text: 'Honeymoon Sweet', value: 'HONEYMOON_SWEET'}],
      showTable: false,
      showDelete: false,
      showEdit: false,
      delRoomMsg: null,
      roomId: null,
      submitSuccess:false,
      unavailable: null
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
