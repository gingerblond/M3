<template>

  <b-container fluid="sm" style="width:600px">
    <h1> New Reservation</h1>
    <b-form id="app" v-on:submit.prevent="submitForm" >
      <div style="font-weight: bold"> Please fill in your personal data (*All fileds are required) :</div>
      <b-row>
        <div style="margin-left: 15px">
          <button class="btn btn-outline-info"   type="button" @click="newCustomer" style="margin-top: 5px">New Customer</button>
        </div>
        <div >
          <button class="btn btn-outline-info"  type="button" @click="oldCustomer" style="margin-top: 5px; margin-left: 10px">Registered Customer</button>
        </div>
      </b-row>
      <div class="form-group" v-if="newC">
        <label for="idCard">ID Card</label>
        <input type="text" class="form-control" id="idCard" placeholder="Please put your ID Card Number"
               v-model="form.customer.idCard" required>
      </div>

      <div class="form-group" v-if="newC">
        <label for="firstName">First Name</label>
        <input type="text" class="form-control" id="firstName" placeholder="Please put your First Name"
               v-model="form.customer.firstName" required>
      </div>

      <div class="form-group" v-if="newC">
        <label for="lastName">Last Name</label>
        <input type="text" class="form-control" id="lastName" placeholder="Please put your Last Name" v-model="form.customer.lastName"
                required>
      </div>
      <div v-if="newC" >
        <button class="btn btn-outline-info"  type="button" @click="addCustomer" style="margin-top: 5px">Add Customer</button>
      </div>
      <b-alert variant="success" show v-if="successNewCustomer && newC" style="margin-top: 10px"> Customer with ID: <strong>{{form.customer.customerId}}</strong>
        was successfully added! Hello, {{form.customer.firstName}} {{form.customer.lastName}}</b-alert>

      <div class="form-group" v-if="oldC">
        <label for="cusId">Customer Id</label>
        <input type="text" id="cusId" class="form-control" placeholder="Please put your Customer ID"
               v-model="form.customer.customerId" required>
      </div>

      <div v-if="oldC" >
        <button class="btn btn-outline-info"  type="button" @click="getCustomer" style="margin-top: 5px">Verify Customer</button>
      </div>
      <b-alert variant="success" show v-if="successCustomer && oldC" style="margin-top: 10px"> Customer with ID: <strong>{{form.customer.customerId}}</strong>
        was successfully found! Hello, {{form.customer.firstName}} {{form.customer.lastName}}
      </b-alert>

      <div style="font-weight: bold"> Please choose your room preferences and date :</div>

      <div class="form-group">
        <label for="roomType">Room Type</label>
        <b-form-select id="roomType" v-model="selected" v-on:change="onChange()"
                       :options="options">
        </b-form-select>
      </div>
      <div class="mt-3" v-if="selected">Selected: {{ selected }}. Price per Night ${{singlePrice}} </div>
      <div class="mt-3" v-if="roomsList.length===0 && selected" style="color: crimson">NO {{ selected }}s available right now! Please choose another type of room! </div>

      <div class="form-group">
        <label for="date">Date</label>
        <input type="date" class="form-control" id="date" placeholder="Please put date" v-model="form.date" required>
      </div>

      <div class="form-group">
        <label for="duration">Duration</label>
        <input type="number" class="form-control" id="duration" placeholder="Please choose how long will be your stay" v-model="form.duration" required>
      </div>

      <div >
        <button class="btn btn-outline-info"  type="button" @click="calculatePrice" style="margin-top: 5px">Calculate Total Price</button>
      </div>

      <b-card v-if="showCalc" style="margin-top: 10px">
        <b-row>
          <b-col> <strong>You want to make following reservation:</strong></b-col>
        </b-row>
        <b-row>
          <b-col><strong>Date: </strong> {{ form.date }}</b-col>
          <b-col><strong>Room type: </strong> {{ form.room.type }}</b-col>
        </b-row>
        <b-row>
          <b-col><strong>Price per night: </strong> ${{ singlePrice }}</b-col>
          <b-col><strong>Duration: </strong> {{ form.duration}} nights</b-col>
        </b-row>
        <b-row>
          <b-col><strong>Total price: </strong> ${{ form.price }}</b-col>
         </b-row>
      </b-card>

      <div class="form-group">
        <button class="btn btn-info" style="margin-top: 10px">Submit</button>
      </div>

    </b-form>
    <b-alert variant="success" show v-if="showSuccess"> You submitted successfully reservation with ID: <strong>{{reservationID}}</strong>
      Please save this Reservation ID: <strong>{{reservationID}}</strong>, if you want to edit or delete your reservation!
      Please save this Customer ID: <strong>{{this.form.customer.customerId}}</strong>, if you want to see your reservations!

    </b-alert>
  </b-container>
</template>

<script>
import axios from 'axios';

export default {
  name: "ReservationForm",
  props: {
    msg: String
  },
  methods: {
    oldCustomer() {
      this.clearData();
      this.oldC=true;
      this.newC = false;
    },
    newCustomer() {
      this.clearData();
      this.oldC=false;
      this.newC = true;
    },
    clearData(){
      this.form.customer.customerId = '';
      this.form.customer.firstName='';
      this.form.customer.lastName='';
      this.form.customer.idCard='';
    },
    addCustomer() {
      axios.post(`http://localhost:8000/addCustomer`,this.form.customer).then(
          (res) =>
          {
            this.successNewCustomer = true;
            this.successCustomer=false;
            this.form.customer.customerId = res.data.customerId;
            this.form.customer.firstName=res.data.firstName;
            this.form.customer.lastName=res.data.lastName;
            this.form.customer.idCard=res.data.idCard;
          }
      )
    },
    getCustomer() {
      axios.get( `http://localhost:8000/customerById/${this.form.customer.customerId}`).then(
          (res)=>{
            this.successCustomer=true;
            this.successNewCustomer = false;
            this.cusRes=res.data;
            this.form.customer.firstName=res.data.firstName;
            this.form.customer.lastName=res.data.lastName;
            this.form.customer.idCard=res.data.idCard;
          }
      )
    },
    calculatePrice() {
      this.form.price = this.singlePrice* this.form.duration;
      this.showCalc=true;
    },
    submitForm() {
      this.form.price = this.singlePrice* this.form.duration;
      axios.post('http://localhost:8000/addReservation', this.form).then(
          (res)=> {
            this.response = res.data;
            this.reservationID = this.response.reservationID;
            this.showSuccess = true;
          }
      )

    },
    onChange() {
      axios.get(`http://localhost:8000/getRooms/${this.selected}`).then(
          (res) => {
            this.roomsList = res.data;
            if(this.roomsList.length>0) {
              this.form.room.roomID = this.roomsList[0].roomID;
              this.form.room.type = this.roomsList[0].type;
              this.form.room.available = this.roomsList[0].available;
            }
            else{
              this.note = "Currently no available rooms of type:"
            }
            switch(this.selected){
              case "SINGLE_ROOM": {
                this.singlePrice = 70;
                break;
              }
              case "DOUBLE_ROOM": {
                this.singlePrice = 90;
                break;
              }
              case "APARTMENT": {
                this.singlePrice = 130;
                break;
              }
              case "HONEYMOON_SWEET": {
                this.singlePrice = 110;
                break;
              }
              default:
                break;
            }
          })
    }
  },
  beforeMount() {
  },
  data() {
    return {
      form: {
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
      },
      note: null,
      roomsList: [],
      selected: null,
      options: [{text: 'Choose room type', value: null}, {text: 'Single room', value: 'SINGLE_ROOM'}, {text: 'Double room', value: 'DOUBLE_ROOM'},
        {text: 'Apartment', value: 'APARTMENT'}, {text: 'Honeymoon Sweet', value: 'HONEYMOON_SWEET'}],
      reservationID: null,
      response: null,
      totalPrice: null,
      showCalc: false,
      showSuccess: false,
      oldC: false,
      newC: false,
      cusRes: null,
      successCustomer: false,
      successNewCustomer:false,
      singlePrice: null
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
