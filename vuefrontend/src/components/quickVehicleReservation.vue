<template>
   <div id = "quickVehicle">
        <h2 class="centered">Quick Tickets: </h2>
        <form v-if="add"  class="centered" @submit="addQuickReservation">
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
                <select v-model="pickoffice" name="" id=""><option v-for="office in offices" v-bind:value=office :key="office.id">{{office.name}}</option></select>
            </div>
            <div class="form-label-group">
                <label>Office to return to</label>
                <select v-model="returnoffice" name="" id=""><option v-for="office in offices" v-bind:value=office :key="office.id">{{office.name}}</option></select>
            </div>
            <div class="form-label-group">
                <label>Discount</label>
                <input v-model="discount" type="number" required>
            </div>
            <div class="form-label-group">
                <button class="btn-primary" type="submit">Add</button>
            </div>
        </form>
        <form v-else class="centered">
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
                <label>Discount</label>
                <input v-model="discount" type="number" required>
            </div>
            <div class="form-label-group">
                <button class="btn-primary" @click="back">Back</button>
                <button class="btn-primary" @click="editQuickReservation">Edit</button>
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
                    <td><Button @click="edit(res)">Edit</Button></td>
                    <td><Button @click="remove(res)">Cancel</Button></td>
                </tr> 
                </tbody>             
            </table>  
        </div>   
    </div>
</template>
<script>
export default {
  name: "quickVehicle",
  components: {},
  props: ["idCompany",],
  data: function () {
  return {
    role: "",
    quickId : "",
    quickReservations: [],
    vehicleName:"",
    cars:"",
    offices:[],
    returnoffice:"",
    pickoffice:"",
    dateFrom:"",
    dateTo:"",
    discount:"",
    vehicleID:"",
    add:true
  }
},

mounted(){
        var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
        };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken(); 
        axios.get("/api/getCarsByAdmin")
            .then(response => {
                this.cars = response.data;
            }); 
        axios.get("/api/getOfficessByAdmin")
            .then(response => {
                this.offices = response.data;
            }); 
        axios.get("/api/getQuickReservationsByAdmin")
            .then(response => {
                console.log(response.data);
                this.quickReservations = response.data;
            }); 


    },
    methods:{ 
        addQuickReservation:function(e){
            e.preventDefault();
            var getJwtToken = function() {
                return localStorage.getItem("jwtToken");
            };
            axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken(); 
            axios.post("/api/addQuickVehicleReservation",
            {vehicleId:this.vehicleID,dateFrom:this.dateFrom,dateTo:this.dateTo,locationPick:this.pickoffice.id,
            locationReturn:this.returnoffice.id,vehicleName:this.vehicleName,discount:this.discount})
            .then(response=>{
              
              alert(response.data);
              if(response.data=="Uspesno je rezervisano"){
                this.dateFrom = this.dateFrom.split('-');
                this.dateFrom = this.dateFrom[2]+"-"+this.dateFrom[1]+'-'+this.dateFrom[0];

                this.dateTo = this.dateTo.split('-');
                this.dateTo = this.dateTo[2]+"-"+this.dateTo[1]+'-'+this.dateTo[0];

                var vehicleName = "";
                var vehicleId = "";
                var price = 0;
                this.cars.forEach(element => {
                if (element.id == this.vehicleID){
                  vehicleId = element.id;
                  vehicleName = element.name;
                  price = element.price;
                }
              });
                this.quickReservations.push({vehicleId:vehicleId,dateFrom:this.dateFrom,dateTo:this.dateTo,locationPick:this.pickoffice.destination,
              locationReturn:this.returnoffice.destination,vehicleName:vehicleName,discount:this.discount, price:price});
              }
                
            });  
        },
        select:function(vehicle){
            this.vehicleName = vehicle.name;
            this.vehicleID = vehicle.id;
        },
        back:function(){
          this.add=true;
          this.dateFrom = "";
          this.dateTo= "";
          this.vehicleName = "";
          this.discount ="";
        },
        edit:function(reservation){
          this.add = false;
          var dateF = reservation.dateFrom.split("-");
          this.dateFrom = dateF[2]+"-"+dateF[1]+"-"+dateF[0];
          var dateT = reservation.dateTo.split("-");
          this.dateTo = dateT[2]+"-"+dateT[1]+"-"+dateT[0];
          this.vehicleName = reservation.vehicleName;
          this.discount = reservation.discount;
          this.quickId = reservation.id;
        },
        editQuickReservation:function(){
          console.log("Called");
          var getJwtToken = function() {
                return localStorage.getItem("jwtToken");
            };
            axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken(); 
            axios.post("/api/editQuickVehicleReservation",{id:this.quickId,dateFrom:this.dateFrom,dateTo:this.dateTo,vehicleName:this.vehicleName,discount:this.discount})
            .then(response=>{
                if(response.data == "OK"){
                  this.quickReservations.forEach(element => {
                    if(element.id == this.quickId){
                      this.dateFrom = this.dateFrom.split('-');
                this.dateFrom = this.dateFrom[2]+"-"+this.dateFrom[1]+'-'+this.dateFrom[0];

                this.dateTo = this.dateTo.split('-');
                this.dateTo = this.dateTo[2]+"-"+this.dateTo[1]+'-'+this.dateTo[0];

                      element.dateFrom=this.dateFrom;
                      element.dateTo=this.dateTo;
                      element.vehicleName=this.vehicleName;
                      element.discount=this.discount;
                    }
                  });
                  
                }
                else{
                  alert("ERROR");
                }
            });
        },
        remove:function(reservation){
          var getJwtToken = function() {
                return localStorage.getItem("jwtToken");
            };
            axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken(); 
            axios.post("/api/removeQuickReservation",
            {id:reservation.id})
            .then(response=>{
                if(response.data == "OK"){
                  var index = 0;
                  for (let element of quickReservations){
                    if(element.id == reservation.id){
                      break;
                    }
                    index++;
                  }
                  quickReservations.splice(index, 1);
                }else{
                  alert("RESERVATION IS TAKEN");
                }
            });
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
