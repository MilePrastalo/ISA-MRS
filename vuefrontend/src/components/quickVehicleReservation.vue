<template>
   <div id = "quickVehicle">

        <br><br>
        <div class="row"> 
            <h2>Quick Tickets: </h2>
        </div>
        <br>
        <div class="row"> 
            <form action="">
                <table>
                    <tr>
                        <td>VehicleName</td>
                        <td>{{vehicleName}}</td>
                    </tr>
                    <tr>
                        <td>Date From</td>
                        <td><input v-model="dateFrom" type="date" required></td>
                    </tr>
                    <tr>
                        <td>Date To</td>
                        <td><input v-model="dateTo" type="date" required></td>
                    </tr>
                    <tr>
                        <td>Office to pick from</td>
                        <td><select v-model="pickoffice" name="" id=""><option v-for="office in offices" v-bind:value=office.id :key="office.id">{{office.name}}</option></select></td>
                    </tr>
                    <tr>
                        <td>Office to return to</td>
                        <td><select v-model="returnoffice" name="" id=""><option v-for="office in offices" v-bind:value=office.id :key="office.id">{{office.name}}</option></select></td>
                    </tr>
                    <tr>
                        <td>Discount</td>
                        <td><input v-model="discount" type="number" required></td>
                    </tr>
                    <tr>
                        <td><Button>Back</Button></td>
                        <td><button type="submit" @click="addQuickReservation">Add</button></td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="row">
            <table>
            <tr>
                <th>Name</th>
                <th>Maker</th>
                <th>Type</th>
                <th>Year</th>
                <th>Price</th>
                <th>Avaiable from</th>
                <th>Avaiable to</th>
                <th>Select</th>
            </tr>
            <tr v-for="car in cars" :key="car.id">
                <td>{{car.name}}</td>
                <td>{{car.maker}}</td>
                <td>{{car.type}}</td>
                <td>{{car.year}}</td>
                <td>{{car.price}}</td>
                <td>{{car.dateFrom}}</td>
                <td>{{car.dateTo}}</td>
                <td><Button @click="select(car)">Select</Button></td>
            </tr>
        </table>
        </div>
        <div class = "row">
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
                    <td><Button>Cancel</Button></td>
                </tr> 
                </tbody>             
            </table>  
        </div>   
    </div>
</template>
<script>
export default {
  name: 'quickVehicle',
  components: {},
  props: ["idCompany",],
  data: function () {
  return {
    role: "",
    quickReservations: [],
    vehicleName:"",
    cars:"",
    offices:[],
    returnoffice:"",
    pickoffice:"",
    dateFrom:"",
    dateTo:"",
    discount:"",
    vehicleID:""
  }
},

mounted(){

        var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken(); 
        axios.get("http://localhost:8080/api/getCarsByAdmin")
            .then(response => {
                this.cars = response.data;
            }); 
        axios.get("http://localhost:8080/api/getOfficessByAdmin")
            .then(response => {
                this.offices = response.data;
            }); 
        axios.get("http://localhost:8080/api/getQuickReservationsByAdmin")
            .then(response => {
                console.log(response.data);
                this.quickReservations = response.data;
            }); 


    },
    methods:{ 
        addQuickReservation:function(e){
            e.preventDefault();
            var getJwtToken = function() {
                return localStorage.getItem('jwtToken');
            };
            axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken(); 
            axios.post("http://localhost:8080/api/addQuickVehicleReservation",
            {vehicleId:this.vehicleID,dateFrom:this.dateFrom,dateTo:this.dateTo,locationPick:this.pickoffice,
            locationReturn:this.returnoffice,vehicleName:this.vehicleName,discount:this.discount})
            .then(response=>{
                alert("SUCC");
            })  
        },
        select:function(vehicle){
            this.vehicleName = vehicle.name;
            this.vehicleID = vehicle.id;
        }
    }
}

</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
