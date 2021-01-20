<template>
  <b-container fluid="lg" style="width:700px">
    <b-card
        title="Hotel 5 Room Reservation"
        style="max-width: 80rem;margin-top:20px;"
        class="mb-2"
    >
      <b-card-text>
        You can find information about price and availability of the rooms here. Please click on image to see available
        rooms right now.
      </b-card-text>
      <b-container class="bv-example-row">
        <b-row>
          <b-col>
            <div style="font-weight: bold">Single Room / $70</div>
            <img v-on:click="getAvailableRooms('SINGLE_ROOM')" src="../assets/single-room1.jpg"/>
          </b-col>
          <b-col>
            <div style="font-weight: bold">Double Room / $90</div>
            <img v-on:click="getAvailableRooms('DOUBLE_ROOM')" src="../assets/double_room.jpg"/>
          </b-col>
          <div class="w-100"></div>
          <b-col>
            <div style="font-weight: bold">Apartment / $130</div>
            <img v-on:click="getAvailableRooms('APARTMENT')" src="../assets/apart.jpg"/>
          </b-col>
          <b-col>
            <div style="font-weight: bold">Honeymoon Sweet / $110</div>
            <img v-on:click="getAvailableRooms('HONEYMOON_SWEET')" src="../assets/honeymoon.jpg"/>
          </b-col>
        </b-row>
        <b-row>
          <b-card style="margin-left: 50px; margin-top:10px">
            <b-row> Available:</b-row>
            <b-row v-if="type==='SINGLE_ROOM'" >Single rooms:
              <div style="font-weight: bold"> {{ count }}</div>
            </b-row>
            <b-row v-if="type==='DOUBLE_ROOM'">Double rooms:
              <div style="font-weight: bold"> {{ count }}</div>
            </b-row>
            <b-row v-if="type==='APARTMENT'"> Apartments:
              <div style="font-weight: bold"> {{ count }}</div>
            </b-row>
            <b-row v-if="type==='HONEYMOON_SWEET'">Honeymoon sweets:
              <div style="font-weight: bold"> {{ count }}</div>
            </b-row>
          </b-card>
        </b-row>
      </b-container>
    </b-card>
    <b-button class="btn btn-info" v-on:click="$router.push('reservation')">  Make reservation</b-button>
  </b-container>
</template>

<script>
import axios from 'axios'

export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },


  methods: {
    getAvailableRooms(type) {
      this.type = type;
      axios.get(`http://localhost:8000/getRoomsCount/${this.type}`)
          .then((res) => {
            this.count = res.data;
          })

    }

    /*handleButtonClick() {
      console.log("clicked button")
      this.error_bool = false
      axios.get('http://localhost:8000/helloworld')
      .then((response) => {
          this.payload = response.data
          console.log(response.data)
          this.clicked = "clicked"
    })
    .catch(error => {
        this.payload = {'message': "something went wrong! Your Api isn't running properly."}
        this.error_bool = true
        this.helper = "right click -> Inspect -> Console. Check the console error message after button click"
        this.error_msg = error
    })
  }*/
  },
  data() {

    return {
      count: null,
      type: '',
      helper: "",
      error_msg: "",
      error_bool: null,
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

h3 {
  margin: 40px 0 0;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

p {
  color: #42b983;
}
</style>
