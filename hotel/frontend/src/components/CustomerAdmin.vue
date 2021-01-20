<template>
  <b-container fluid="sm" style="width:1000px">

    <h1> Room Administration</h1>

    <div class="row no-gutter">
      <div class="col-md-8 col-lg-6 ">
        <h2 style="margin-top: 20px">Get customers by their First or Last Name, IdCard or Customer ID:</h2>
        <b-form class="form-control-sm" v-on:submit.prevent="getCustomersBySearch">
          <div class="form-group">
            <label for="search">Search:</label>
            <input type="text" class="form-control" id="search" placeholder="Please put your search criteria" v-model="form.search" required>
          </div>
          <div class="form-group">
            <button class="btn btn-info" style="margin-top: 10px">Get customers </button>
          </div>

          <table v-if="customersList.length>0 && showTable" style="margin-top: 40px;">
            <tr>
              <th> Customer ID</th>
              <th> First Name</th>
              <th> Last Name</th>
              <th> ID Card</th>
              <th> Delete Customer</th>
            </tr>
            <tr v-for="customer in customersList" :key="customer.customerId">
              <td> {{ customer.customerId }}</td>
              <td> {{customer.firstName }}</td>
              <td> {{ customer.lastName }}</td>
              <td> {{ customer.idCard }}</td>
              <td>
                <button class="btn-outline-info" v-on:click="deleteCustomer(customer.customerId)">Delete</button>
              </td>
            </tr>
          </table>
         </b-form>



        <div v-if="customersList.length==0" style="color: crimson; margin-top: 110px"> No available customers with this search criteria! Please, try another search !</div>
      </div>
      <div class="col-md-8 col-lg-6">
        <button class="btn btn-outline-info" v-on:click="$router.push('login')"  type="button" style="width:300px; margin-top: 20px" > Go back to admin tools </button>
        <button class="btn btn-outline-info" v-on:click="$router.push('reservation')"  type="button" style="width:300px; margin-top: 40px" > Add new customer/ reservation </button>

        <b-alert variant="success" show v-if="showDelete" style="margin-top: 190px"><strong>{{ resDelete }}</strong> </b-alert>
      </div>
    </div>

  </b-container>
</template>

<script>
import axios from "axios";
export default {
  name: "CustomerAdmin",
  methods:{
    getCustomersBySearch() {
      axios.get(`http://localhost:8000/getcustomersBySearch/${this.form.search}`).then(
          (res) => {
            this.customersList = res.data;
            this.showTable = true;
          }
      )
    },
    deleteCustomer(id){
      axios.delete(`http://localhost:8000/deleteCustomer/${id}`).then(
          (res) => {
            this.resDelete = res.data;
            this.showDelete = true;
          }
      )
    }
  },
  data() {
    return {
      form:{
        search: null
      },
      customersList: [
        {
          customerId: null,
          firstName: null,
          lastName: null,
          idCard: null
        }
      ],
      showTable: false,
      customerId: null,
      showDelete: false,
      resDelete: null
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
