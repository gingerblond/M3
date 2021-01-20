<template>
  <div>
    <b-card
        title="Db Feeding Scrypt"
        style="max-width: 40rem;margin-top:20px;margin-left: 600px;"
        class="mb-2"
    >
      <b-card-text style=" text-align: left">
        In order to perform all calls ,you can feed our DB with:
        <ul>
          <li>100 Generated rooms</li>
          <li>1 Hotel</li>
          <li>30 cleaning service employees</li>
          <li>20 customer service employees</li>
          <li>20 customer service employees</li>
          <li>1 Customer</li>
          <li>5 Reservations</li>
        </ul>
      </b-card-text>
      <b-button @click="dbFeed"> Feed Database</b-button>
      <b-alert variant="warning" show v-if="showSuccess" style="margin-top: 150px"><strong>{{
          this.payload
        }}</strong>
      </b-alert>
    </b-card>
    <b-button @click="migrateToMongo()"> Migrate To Mongo</b-button>
    <b-alert variant="warning" show v-if="migrationSuccess" style="margin-top: 150px"><strong>{{
        this.migrationPayload
      }}</strong>
    </b-alert>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: "RoomDetails",
  methods: {
    dbFeed() {
      axios.post('http://localhost:8000/dbFeeding').then(
          (res) => {
            this.showSuccess = true
            this.payload = res.data
          }
      );
    },
    migrateToMongo() {
      axios.post('http://localhost:8000/migration').then(
          (res) => {
            this.migrationSuccess = true
            this.migrationPayload = res.data
          }
      );
    }
  },
  data() {
    return {
      payload: null,
      showSuccess: false,
      migrationPayload: null,
      migrationSuccess: false
    }
  }
}
</script>

<style scoped>

</style>
