import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import ReservationForm from "./components/ReservationForm";
import HelloWorld from "./components/HelloWorld";
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import NewCustomer from "./components/NewCustomer";
import DB from "./components/DB"
import ManageReservation from "./components/ManageReservation"
import Login from "./components/Login"
import RoomAdmin from "./components/RoomAdmin"
import CustomerAdmin from "./components/CustomerAdmin"
import EmployeeAdmin from "./components/EmployeeAdministration"
import Reports from "./components/Reports"




Vue.config.productionTip = false
Vue.use(VueRouter);
Vue.use(BootstrapVue);

const routes = [
    { path: '/reservation', component: ReservationForm },
    { path: '/', component: HelloWorld},
    { path: '/customer', component: NewCustomer},
    { path: "/db", component: DB},
    { path: '/manageReservations', component: ManageReservation},
    { path: '/login', component: Login},
    { path: '/roomAdmin', component: RoomAdmin},
    { path: '/customerAdmin', component: CustomerAdmin},
    { path: '/employeeAdmin', component: EmployeeAdmin},
    { path: '/reports', component: Reports},




];

const router = new VueRouter({
    routes: routes
});

new Vue({
    router,
    render: h => h(App),
}).$mount('#app')
