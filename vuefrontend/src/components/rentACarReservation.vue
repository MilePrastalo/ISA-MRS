<template>
    <div id="rentACarReservation">
        <searchRentACarCompany :ilocation = "location"  v-on:selected="showVehicles" :iflightDateArrive="flightDateArrive" :iflightDateLeaving="flightDateLeaving" />
        <div id="classic">
            <table>
                <tr>
                    <td>Name</td>
                    <td>Maker</td>
                    <td>Type</td>
                    <td>Year</td>
                    <td>Price</td>
                    <td>Rating</td>
                </tr>
                <tr v-for="car in cars" :key="car.id">
                    <td>{{car.name}}</td>
                    <td>{{car.maker}}</td>
                    <td>{{car.type}}</td>
                    <td>{{car.year}}</td>
                    <td>{{car.price}}</td>
                    <td>{{car.rating}}</td>
                    <td><select v-model="pickoffice" name="" id=""><option v-for="office in offices" v-bind:value=office.id :key="office.id">{{office.name}}</option></select></td>
                    <td><select v-model="returnoffice" name="" id=""><option v-for="office in offices" v-bind:value=office.id :key="office.id">{{office.name}}</option></select></td>
                    <td><Button @click="reserve(car.id)">Reserve</Button></td>
                </tr>
            </table>
        </div>
        <div id="quick">
            <table border="1"  class = "table">
                <thead class="thead-dark">
                    <tr>
                    <th scope="col">Office to pick up</th>
                    <th scope="col">Office to return</th>
                    <th scope="col">Start date</th>
                    <th scope="col">End date</th>
                    <th scope="col">Vehicle name</th>
                    <th scope="col">Origin price</th>
                    <th scope="col">Discount</th>
                    <th scope="col">Total price</th>
                    <th scope="col">Reserve</th>
                    </tr>
                </thead>
                <tbody>
                <tr v-for="res in quickReservations" :key="res.id">  
                    <td>{{res.locationPick}}</td>
                    <td>{{res.locationReturn}}</td>
                    <td>{{res.dateFrom}}</td>
                    <td>{{res.dateTo}}</td>
                    <td>{{res.vehicleName}}</td>
                    <td>{{res.price}}</td>
                    <td>{{res.discount}}</td>
                    <td>{{(res.price*(100-res.discount)/100)}}</td>
                    <td><button @click="reserveQuick(res)">Reserve</button></td>
                </tr> 
                </tbody>             
            </table>
        </div>
        
    </div>
</template>
<script>
import searchRentACarCompany from './searchRentACarCompany.vue';

export default {
    name: 'rentACarReservation',
    components: {
      searchRentACarCompany
    },
    props:['ilocation','iflightDateArrive','iflightDateLeaving'],
    data: function(){
        return{
            location:this.ilocation,
            cars:[],
            offices:[],
            id:"",
            datefrom:"",
            dateto:"",
            returnoffice:"",
            pickoffice:"",
            quickReservations: [],
            flightDateArrive:this.iflightDateArrive,
            flightDateLeaving:this.iflightDateLeaving
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
                        this.cars.forEach(function(item,index){
                            console.log(item);
                            if(item.rating == -1){
                                item.rating = "No ratings";
                            }
                        });
                    }); 
                axios.post("http://localhost:8080/api/getQuickReservations",{id:this.id,dateFrom:this.datefrom,dateTo:this.dateto})
                    .then(response => {
                        console.log(response.data);
                        this.quickReservations = response.data;
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
                        this.$emit("vehicleReserved",response.data);
                    }); 
            },
            reserveQuick : function(res){
                axios.post("http://localhost:8080/api/quickReserveVehicle",{id:res.id})
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
