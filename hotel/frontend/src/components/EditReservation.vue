<template>
  <b-container fluid="sm" style="width:400px">

    <b-form v-on:submit.prevent="submitForm">
    <div class="form-group">
      <label for="roomType">Room Type</label>
      <b-form-select id="roomType" v-model="selected" v-on:change="onChange()"
                     :options="options">
      </b-form-select>
    </div>
    <div class="mt-3">Selected: {{ selected }}. Price per Night ${{ singlePrice }}</div>
    <div class="mt-3" v-if="roomsList.length===0 && selected" style="color: crimson">NO {{ selected }}s available right
      now! Please choose another type of room!
    </div>

    <div class="form-group">
      <label for="date">Date</label>
      <input type="date" class="form-control" id="date" placeholder="Please put date" v-model="form.date" required>
    </div>

    <div class="form-group">
      <label for="duration">Duration</label>
      <input type="number" class="form-control" id="duration" placeholder="Please choose how long will be your stay"
             v-model="form.duration" required>
    </div>

    <div>
      <button class="btn btn-outline-info" type="button" v-on:click="calculatePrice" style="margin-top: 5px">Calculate
        Total Price
      </button>
    </div>

    <b-card v-if="showCalc" style="margin-top: 10px">
      <b-row>
        <b-col><strong>You want to make following reservation:</strong></b-col>
      </b-row>
      <b-row>
        <b-col><strong>Date: </strong> {{ form.date }}</b-col>
        <b-col><strong>Room type: </strong> {{ form.room.type }}</b-col>
      </b-row>
      <b-row>
        <b-col><strong>Price per night: </strong> ${{ singlePrice }}</b-col>
        <b-col><strong>Duration: </strong> {{ form.duration }} nights</b-col>
      </b-row>
      <b-row>
        <b-col><strong>Total price: </strong> ${{ form.price }}</b-col>
      </b-row>
    </b-card>

    <div class="form-group">
      <button class="btn btn-info" style="margin-top: 10px">Update reservation</button>
    </div>

      <b-alert variant="success" show v-if="success"> You updated successfully reservation with ID: <strong>{{parentData.reservationID}}</strong>
      </b-alert>
  </b-form>
  </b-container>
</template>

<script>
import axios from 'axios';

export default {
  name: "EditReservation",
  methods: {
    setData() {
      this.form.reservationID = this.parentData.reservationID;
      this.form.customer.customerId = this.parentData.customer.customerId;
      this.form.customer.firstName = this.parentData.customer.firstName;
      this.form.customer.lastName = this.parentData.customer.lastName;
      this.form.customer.idCard = this.parentData.customer.idCard;

    },
    onChange() {
      axios.get(`http://localhost:8000/getRooms/${this.selected}`).then(
          (res) => {
            this.roomsList = res.data;
            if (this.roomsList.length > 0) {
              this.form.room.roomID = this.roomsList[0].roomID;
              this.form.room.type = this.roomsList[0].type;
              this.form.room.available = this.roomsList[0].available;
            } else {
              this.note = "Currently no available rooms of type:"
            }
            switch (this.selected) {
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
    },
    calculatePrice() {
      this.form.price = this.singlePrice * this.form.duration;
      //this.totalPrice = this.form.price * this.form.duration;
      this.showCalc = true;
    },
    submitForm(){
      this.form.price = this.singlePrice* this.form.duration;
      this.setData();
      axios.put("http://localhost:8000/updateReservation", this.form).then(
          (res)=>{
            this.success=true;
            this.parentData.reservationID=res.data.reservationID;
          }
      )
    }
  },

  data() {
    return {
      form: {
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
      },
      roomsList: [],
      selected: null,
      options: [{text: 'Choose room type', value: null}, {
        text: 'Single room',
        value: 'SINGLE_ROOM'
      }, {text: 'Double room', value: 'DOUBLE_ROOM'},
        {text: 'Apartment', value: 'APARTMENT'}, {text: 'Honeymoon Sweet', value: 'HONEYMOON_SWEET'}],
      singlePrice: null,
      showCalc: false,
      success: false
    };
  },
  props: {
    parentData: {
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
  }
}
</script>

<style scoped>

</style>
