<template>
    <div id="rentACarReservation">
        <div v-if="rentProfile">
            <rentACarCompanyProfile v-on:back="back" :icompany="companyForDetails"/>
        </div>
        <searchRentACarCompany v-if="rentProfile==false" v-on:details="companyDetails" :ijustSearch="justSearch"  :ilocation = "location"  v-on:selected="showVehicles" :iflightDateArrive="flightDateArrive" :iflightDateLeaving="flightDateLeaving" />
        <div class="row centered" v-if="rentProfile==false">
            <ul class="nav nav-tabs col-lg-10 fromTop">
                <li class="nav-item centered bigTab">
                    <a id="normal" class="nav-link active " href="#" @click="normal">Vehicles</a>
                </li>
                <li class="nav-item centered bigTab">
                    <a id="quick" class="nav-link" href="#" @click="quick" v-if="justSearch == false">Quick Reservations</a>
                    
                </li>
            </ul>
        </div>
        <div id="classic" v-if="tabselected==0 && rentProfile==false">
            <searchVehicle :idateFrom="datefrom" :idateTo="dateto" v-if="selected" :iCompany="companySelected" v-on:searched="showFound"/>
            <table class="table"> 
                <tr>
                    <td></td>
                    <td>Name</td>
                    <td>Maker</td>
                    <td>Type</td>
                    <td>Year</td>
                    <td>Seats</td>
                    <td>Price</td>
                    <td>Rating</td>
                </tr>
                <tr v-for="car in cars" :key="car.id">
                    <td><img :src="sourceImg" width="50px" height="50px"/></td>
                    <td>{{car.name}}</td>
                    <td>{{car.maker}}</td>
                    <td>{{car.type}}</td>
                    <td>{{car.year}}</td>
                    <td>{{car.seats}}</td>
                    <td>{{getCarPrice(car)}}</td>
                    <td>{{car.rating}}</td>
                    <td><select v-if="justSearch == false" v-model="pickoffice" name="" id=""><option v-for="office in offices" v-bind:value=office.id :key="office.id">{{office.name}}</option></select></td>
                    <td><select v-if="justSearch == false" v-model="returnoffice" name="" id=""><option v-for="office in offices" v-bind:value=office.id :key="office.id">{{office.name}}</option></select></td>
                    <td><Button v-if="justSearch == false" @click="reserve(car.id)">Reserve</Button></td>
                </tr>
            </table>
        </div>
        <div id="quick" v-if="tabselected==1 && rentProfile==false">
            <table border="1"  class = "table" v-if="justSearch == false">
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
import searchRentACarCompany from "./searchRentACarCompany.vue";
import searchVehicle from "./searchVehicle.vue";
import rentACarCompanyProfile from "./rentACarCompanyProfile.vue";
import axios from "axios";

export default {
    name: "rentACarReservation",
    components: {
      searchRentACarCompany,searchVehicle,rentACarCompanyProfile
    },
    props:["ilocation","iflightDateArrive","iflightDateLeaving"],
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
            flightDateLeaving:this.iflightDateLeaving,
            tabselected :0,
            justSearch:false,
            companySelected:"",
            selected:false,
            rentProfile:false,
            companyForDetails:{},
            sourceImg:"../kola.jpg"
        };
    },
    mounted(){
        if(this.location == undefined){
            this.justSearch = true;
        }
        else{
            this.justSearch = false;
        }
    },
    methods: {
            normal: function(){
                this.tabselected = 0;
                document.getElementById("normal").className="nav-link active ";
                document.getElementById("quick").className="nav-link ";
            },
            getCarPrice(car){
                if(this.datefrom!="" && this.dateto !=""){
                    var df = Date.parse(this.datefrom);
                    var dt = Date.parse(this.dateto);
                    var d = Math.ceil((dt-df)/1000/3600/24);
                    return d*car.price;
                }
                else{
                    return car.price;
                }
            },
            quick: function(){
                this.tabselected = 1;
                document.getElementById("normal").className="nav-link  ";
                document.getElementById("quick").className="nav-link active";
            },
            showFound(data){
                this.cars = data;
                this.cars.forEach(function(item,index){
                    console.log(item);
                    if(item.rating == -1){
                        item.rating = "No ratings";
                    }
                });
            },
            showVehicles:function(dataPassed){
                this.companySelected = dataPassed.id;
                this.selected = true;
                this.offices= dataPassed.offices;
                this.id = dataPassed.id;
                this.datefrom = dataPassed.dateFrom;
                this.dateto = dataPassed.dateTo;
                if(!this.justSearch){
                    var getJwtToken = function() {
                        return localStorage.getItem("jwtToken");
                    };
                    axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
                    axios.post("/api/getQuickReservations",{id:this.id,dateFrom:this.datefrom,dateTo:this.dateto})
                        .then(response => {
                            console.log(response.data);
                            this.quickReservations = response.data;
                        });
                }
                
            },
            reserve:function(carid){
                var getJwtToken = function() {
                    return localStorage.getItem("jwtToken");
                };
                axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
                axios.post("/api/reserveVehicle",{id:carid,dateFrom:this.datefrom,dateTo:this.dateto,officePick:this.pickoffice,officeReturn:this.returnoffice})
                    .then(response => {
                        console.log(response);
                        alert("Success");
                        this.$emit("vehicleReserved",response.data);
                    }).catch(function(){
                        alert("Reservation failed");
                    }); 
            },
            reserveQuick : function(res){
                axios.post("/api/quickReserveVehicle",{id:res.id})
                    .then(response => {
                        console.log(response);
                        alert("Success");
                        this.$emit("vehicleReserved",response.data);
                    }).catch(function(){
                        alert("Reservation failed");
                    });; 
            },
            companyDetails(company){
                this.companyForDetails = company;
                this.rentProfile = true;
            },
            back(){
                this.rentProfile = false;
            }
        }
}
</script>
<style scoped>
.fromTop{
    margin-top: 5%;
    margin-bottom: 5%;
    margin-left: auto;
    margin-right: auto;
}
.centered{
    margin-left: auto;
    margin-right: auto;
}
.bigTab{
    width: 45%;
}
td{
    padding-top: 3px;
    align-content: center;
    text-align: center;
    vertical-align: middle;
    
}
</style>
