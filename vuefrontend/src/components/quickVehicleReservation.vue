<template>
   <div id = "quickVehicle">
        <h2 class="centered">Quick Tickets: </h2>
        <form action="" class="centered">
            <div class="form-label-group">
                <label>VehicleName</label>
                <h4>{{vehicleName}}</h4>
            </div>
                <div class="form-label-group">
                <label>Date From</label>
                <input v-model="dateFrom" type="date" required>
            </div>
            <div class="form-label-group">
                <label>Date To</label>
                <input v-model="dateTo" type="date" required>
            </div>
            <div class="form-label-group">
                <label>Office to pick from</label>
                <select v-model="pickoffice" name="" id=""><option v-for="office in offices" v-bind:value=office.id :key="office.id">{{office.name}}</option></select>
            </div>
            <div class="form-label-group">
                <label>Office to return to</label>
                <select v-model="returnoffice" name="" id=""><option v-for="office in offices" v-bind:value=office.id :key="office.id">{{office.name}}</option></select>
            </div>
            <div class="form-label-group">
                <label>Discount</label>
                <input v-model="discount" type="number" required>
            </div>
            <div class="form-label-group">
                <button class="btn-primary" type="submit" @click="addQuickReservation">Add</button>
            </div>
        </form>
        <div class="row">
            <br>
            <table  class="table centered">
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
        <div class = "row centered">
            <table border="1"  class = "table centered">
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
                alert("SUCCESS");
            })  
        },
        select:function(vehicle){
            this.vehicleName = vehicle.name;
            this.vehicleID = vehicle.id;
        }
    }
}

</script>

<style scoped>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
.centered{
  margin-left: auto;
  margin-right: auto;
  width:800px;
}
.form-signin {
  width: 100%;
}

.form-signin .btn {
  font-size: 80%;
  border-radius: 5rem;
  letter-spacing: .1rem;
  font-weight: bold;
  padding: 1rem;
  transition: all 0.2s;
}

.form-label-group {
  position: relative;
  margin-bottom: 1rem;
}

.form-label-group input {
  height: auto;
  border-radius: 2rem;
}

label{
  width: 20%;
}
.card-signin {
  border: 0;
  border-radius: 1rem;
  box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);
}

.card-signin .card-title {
  margin-bottom: 2rem;
  font-weight: 300;
  font-size: 1.5rem;
}

.card-signin .card-body {
  padding: 2rem;
}

.form-signin {
  width: 100%;
}

.form-signin .btn {
  font-size: 80%;
  border-radius: 5rem;
  letter-spacing: .1rem;
  font-weight: bold;
  padding: 1rem;
  transition: all 0.2s;
}

.form-label-group {
  position: relative;
  margin-bottom: 1rem;
}

.form-label-group input {
  height: auto;
  border-radius: 2rem;
}

.form-label-group>input,
.form-label-group>label {
  padding: var(--input-padding-y) var(--input-padding-x);
}
</style>
