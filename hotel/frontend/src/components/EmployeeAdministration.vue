<template>
  <b-container fluid="sm" style="width:1200px">

    <h1> Employee Administration</h1>
    <div class="row no-gutter">
      <div class="col-md-16 col-lg-6 " >
        <h2> Employee Administration Tools:</h2>
        <b-button class="btn btn-info" style="width:300px; margin-top: 20px" v-on:click="addCustomerServiceEmployee"> Add new customer service Employee
        </b-button>
        <b-button class="btn btn-info" style="width:300px; margin-top: 20px" v-on:click="addCleaningServiceEmployee"> Add new cleaning service Employee
        </b-button>
        <b-button class="btn btn-info" style="width:300px; margin-top: 20px" v-on:click="getCustomerServiceEmployees"> See all customer service employees
        </b-button>
        <b-button class="btn btn-info" style="width:300px; margin-top: 20px" v-on:click="getCleaningServiceEmployees"> See all cleaning service employees
        </b-button>
        <table v-if="employeesCust.length>0 && showTableCust" style="margin-top: 40px;">
          <tr>
            <th> ID</th>
            <th> Name</th>
            <th> Soc ID</th>
            <th> Hotel Address</th>
            <th> Phone Number</th>
            <th> Email</th>
            <th> Username</th>
            <th> Password</th>
            <th> Manage</th>
          </tr>
          <tr v-for="employee in employeesCust" :key="employee.employeeId">
            <td> {{ employee.employeeId}}</td>
            <td> {{ employee.firstName }} {{ employee.lastName }}</td>
            <td> {{ employee.socialId}}</td>
            <td> {{ employee.hotel.address }}</td>
            <td> {{ employee.phoneNumber}}</td>
            <td> {{ employee.email}}</td>
            <td> {{ employee.user.username}}</td>
            <td> {{ employee.user.password}}</td>
            <td>
              <button class="btn-outline-info" v-on:click="deleteCustomerEmployee(employee.employeeId)">Delete</button>
              <button class="btn-outline-info" v-on:click="editCustomerEmployee(employee.employeeId)">Edit</button>
            </td>
          </tr>
        </table>

        <div v-if="employeesCust.length==0" style="color: crimson"> No customer service employees found! Please, add one!</div>
        <table v-if="employeesCLien.length>0 && showTableClien" style="margin-top: 40px;">
          <tr>
            <th> ID</th>
            <th> Name</th>
            <th> Soc ID</th>
            <th> Hotel Address</th>
            <th> Work Hours/W</th>
            <th> Responsibility</th>
            <th> Manage</th>
          </tr>
          <tr v-for="employee in employeesCLien" :key="employee.employeeId">
            <td> {{ employee.employeeId}}</td>
            <td> {{ employee.firstName }} {{ employee.lastName }}</td>
            <td> {{ employee.socialId}}</td>
            <td> {{ employee.hotel.address }}</td>
            <td> {{ employee.workingHours}}</td>
            <td> {{ employee.responsibility}}</td>
            <td>
              <button class="btn-outline-info" v-on:click="deleteClieningEmployee(employee.employeeId)">Delete</button>
              <button class="btn-outline-info" v-on:click="editCleaningEmployee(employee.employeeId)">Edit</button>
            </td>
          </tr>
        </table>

        <div v-if="employeesCLien.length==0" style="color: crimson">No cleaning service employees found! Please, add one!</div>
      </div>

      <div class="col-md-3 col-lg-4  " style="margin-left: 180px">
        <customer-service-employee v-if="newCust"></customer-service-employee>
        <cleaning-service-employee v-if="newClean"></cleaning-service-employee>
        <b-alert variant="success" show v-if="showDeleteClien" style="margin-top: 120px"> <strong>{{deleteRespClien}}</strong>
        </b-alert>
        <b-alert variant="success" show v-if="showDeleteCust" style="margin-top: 120px"> <strong>{{deleteRespCust}}</strong>
        </b-alert>
        <edit-customer-employee :parent-data="$data.editResCust" style="margin-left: 20px" v-if="showEditCust"></edit-customer-employee>
        <edit-cleaning-employee :parent-data="$data.editResClien" style="margin-left: 20px" v-if="showEditClien"></edit-cleaning-employee>
      </div>


      </div>

  </b-container>
</template>

<script>
import axios from "axios";
import CustomerServiceEmployee from "./CustomerServiceEmployee";
import CleaningServiceEmployee from "./CleaningServiceEmployee";
import EditCustomerEmployee from "./EditCustomerEmployee";
import EditCleaningEmployee from "./EditCleaningEmployee";
export default {
  name: "EmployeeAdministration",
  components: {EditCleaningEmployee, EditCustomerEmployee, CleaningServiceEmployee, CustomerServiceEmployee},
  methods: {
    addCustomerServiceEmployee(){
      this.newCust = true;
      this.newClean = false;
      this.showTableCust= false;
      this.showDeleteCust = false;
      this.showTableClien= false;
      this.showDeleteClien= false;
      this.showEditClien=false;
      this.showEditCust=false;
    },
    addCleaningServiceEmployee(){
      this.newClean = true;
      this.newCust = false;
      this.showTableCust= false;
      this.showTableClien= false;
      this.showDeleteCust = false;
      this.showDeleteClien= false;
      this.showEditClien=false;
      this.showEditCust=false;
      },
    getCustomerServiceEmployees(){
      this.showTableCust= true;
      this.showTableClien= false;
      this.newClean = false;
      this.newCust = false;
      this.showDeleteCust = false;
      this.showEditClien=false;
      this.showEditCust=false;

      axios.get("http://localhost:8000/getCustomerEmployees").then(
          (res)=> {
            this.employeesCust = res.data;
          }
      )
    },
    getCleaningServiceEmployees(){
      this.showTableClien= true;
      this.showTableCust= false;
      this.newClean = false;
      this.newCust = false;
      this.showDeleteCust = false;
      this.showEditClien=false;
      this.showEditCust=false;

      axios.get("http://localhost:8000/getCleaningEmployees").then(
          (res)=> {
            this.employeesCLien = res.data;
          }
      )
    },
    deleteCustomerEmployee(id){
      axios.delete(`http://localhost:8000/deleteCustomerEmployee/${id}`).then(
          (res) => {
            this.deleteRespCust = res.data;
            this.showDeleteCust = true;
          }
      )
    },
    deleteClieningEmployee(id){
      axios.delete(`http://localhost:8000/deleteCleaningEmployee/${id}`).then(
          (res) => {
            this.deleteRespClien = res.data;
            this.showDeleteClien = true;
          }
      )
    },
    editCustomerEmployee(id){
      this.showEditCust = true;
      axios.get(`http://localhost:8000/getCustomerEmployee/${id}`).then(
          (res) => {
            this.editResCust = res.data;
          }
      )
    },
    editCleaningEmployee(id){
      this.showEditClien = true;
      axios.get(`http://localhost:8000/getCleaningEmployee/${id}`).then(
          (res) => {
            this.editResClien = res.data;
          }
      )
    }
  },
  data() {
    return {
      newCust: false,
      newClean: false,
      showTableCust: false,
      showTableClien: false,
      employeesCust:[
        {
          employeeId:null,
          firstName: null,
          lastName: null,
          socialId: null,
          hotel: {
            address: null,
          },
          phoneNumber:null,
          email:null,
          user: {
            username:null,
            password: null,
          }
        }
      ],
      employeesCLien:[
        {
          employeeId:null,
          firstName: null,
          lastName: null,
          socialId: null,
          hotel: {
            address: null,
          },
          workingHours:null,
          responsibility:null,

        }
      ],
      deleteRespCust: null,
      deleteRespClien: null,
      showDeleteCust: false,
      showDeleteClien: false,
      showEditCust: false,
      showEditClien: false,
      editResCust: null,
      editResClien:null
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
