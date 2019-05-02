<template>
    <div id="rentACarReservation">
        <searchRentACarCompany  v-on:selected="showVehicles" />
        <table>
            <tr v-for="car in cars" :key="car.id">
                <td>{{car.name}}</td>
                <td>{{car.maker}}</td>
                <td>{{car.type}}</td>
                <td>{{car.year}}</td>
                <td>{{car.price}}</td>
                <td><select v-model="pickoffice" name="" id=""><option v-for="office in offices" v-bind:value=office.id :key="office.id">{{office.name}}</option></select></td>
                <td><select v-model="returnoffice" name="" id=""><option v-for="office in offices" v-bind:value=office.id :key="office.id">{{office.name}}</option></select></td>
                <td><Button @click="reserve(car.id)">Reserve</Button></td>
            </tr>
        </table>
    </div>
</template>
<script>
import searchRentACarCompany from './searchRentACarCompany.vue';

export default {
    name: 'rentACarReservation',
    components: {
      searchRentACarCompany
    },
    data: function(){
        return{
            cars:[],
            offices:[],
            id:"",
            datefrom:"",
            dateto:"",
            returnoffice:"",
            pickoffice:""
        };
    },
    methods: {
            showVehicles:function(dataPassed){
                this.offices= dataPassed.offices;
                console.log(this.offices);
                this.id = dataPassed.id;
                this.datefrom = dataPassed.dateFrom;
                this.dateto = dataPassed.dateTo;
                console.log(dataPassed);
                var getJwtToken = function() {
                    return localStorage.getItem('jwtToken');
                };
                axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
                axios.post("http://localhost:8080/api/getAvaiableVehicles",{id:this.id,dateFrom:this.datefrom,dateTo:this.dateto})
                    .then(response => {
                        console.log(response);
                        this.cars = response.data;
                    }); 
            },
            reserve:function(carid){
                var getJwtToken = function() {
                    return localStorage.getItem('jwtToken');
                };
                axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
                axios.post("http://localhost:8080/api/reserveVehicle",{id:carid,dateFrom:this.datefrom,dateTo:this.dateto,officePick:this.pickoffice,officeReturn:this.returnoffice})
                    .then(response => {
                        console.log(response);
                        alert("Success :D");
                    }); 
            }
        }
}
</script>
<style scoped>

</style>
