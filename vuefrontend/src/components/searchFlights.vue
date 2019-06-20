<template>
   <div id = "searchFlights">

            <h2>Flight Search </h2>
        <br>

       <div class = "row">
        <table class = "searchForm"  style="text-align: left">
            <tr>
                <td>Flight company:</td>
                <td><input v-model="flightCompany" type="text"></td>
            </tr>
            <tr>
                <td>Transitions:</td>
                <td><input v-model="transitionsNum" type="number" placeholder="MaxNum of transitions"></td>
            </tr>
           <tr>
                <td>Start destination:</td>
                <td><input v-model="startDestination" type="text"></td>
                <td>End destination:</td>
                <td><input v-model="endDestination" type="text"></td>
            </tr>
            <tr>
                <td>Depart time:</td>
                <td><input v-model="startDate" type="date"></td>
                <td>Return time:</td>
                <td><input v-model="endDate" type="date"></td>
            </tr>
            <tr>
                <td>Flight duration:</td>
                <td><input v-model="flightDuration" type="number"></td>
                <td>Flight length:</td>
                <td><input v-model="flightLength" type="number"></td>
            </tr>
            <tr>
                <td>Min price:</td>
                <td><input v-model="MinPrice" type="number"></td>
                <td>Max price:</td>
                <td><input v-model="MaxPrice" type="number"></td>
            </tr>
            <tr style="text-align: center">
                     <td><div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" class="custom-control-input" id="eco" checked @click="checkedClass(1)" name="inlineDefaultRadiosExample">
                        <label class="custom-control-label" for="eco">Economic class</label>
                        </div>
                     </td>
                     <td><div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" class="custom-control-input" id="bus" @click="checkedClass(2)" name="inlineDefaultRadiosExample">
                        <label class="custom-control-label" for="bus">Business class</label>
                        </div>
                     </td>
                     <td><div class="custom-control custom-radio custom-control-inline">
                        <input type="radio" class="custom-control-input" id="first" @click="checkedClass(3)" name="inlineDefaultRadiosExample">
                        <label class="custom-control-label" for="first">First class</label>
                        </div>
                     </td>
            </tr>
            <tr>
                <td><Button class = "btn btn-primary" v-on:click="search">Search</Button></td>
            </tr>  
        </table>
        </div>
        <br>
        <div>
            <table border="1" hidden ref = "results" class = "table" >
                <thead class="thead-dark">
                   
                <tr>
                    <th>Flight company</th>
                    <th>Start destination</th>
                    <th>End destination</th>
                    <th>Start date</th>
                    <th>End date</th>
                    <th>Flight duration</th>
                    <th>Flight Length</th>
                    <th>Economic price</th>
                    <th>Buisiness price</th>
                    <th>First class price</th>
                    <th></th>
                </tr>
                </thead>
                <tr v-for="flight in flights" :key="flight.id"> 
                    <td>{{flight.flightCompany}}</td> 
                    <td>{{flight.startDestination}}</td>
                    <td>{{flight.endDestination}}</td>
                    <td>{{flight.startDate_str}}</td>
                    <td>{{flight.endDate_str}}</td>
                    <td>{{flight.flightDuration}}</td>
                    <td>{{flight.flightLength}}</td>
                    <td>{{flight.economicPrice}}</td>
                    <td>{{flight.businessPrice}}</td>
                    <td>{{flight.firstClassPrice}}</td>
                    <td> <Button class = "btn btn-outline-success" @click="goToDetails(flight.id)" >Details</Button></td>
                </tr>
            </table>
        </div>        
    </div>
</template>

<script>
import axios from "axios";

export default {
  name: "searchFlights",
  components: {
  },
  data: function () {
  return {
     startDestination: "",
     endDestination: "",
     startDate: "",
     endDate: "",
     flightDuration: "",
     flightLength: "",
     MinPrice: "",
     MaxPrice: "",
     flightCompany: "",
     check: 1,
     transitionsNum: "",
     flights:[]
  }
},
mounted(){
    },
    methods:{
        checkedClass: function(option){
            this.check = option;
        },
        search: function(){
            var MineconomicPrice = 0;
            var MaxeconomicPrice = 0;
            var MinbuisinesssPrice = 0;
            var MaxbuisinesssPrice = 0;
            var MinfirstClassPrice = 0;
            var MaxfirstClassPrice = 0;
            if (this.check == 1){
                MineconomicPrice = this.MinPrice;
                MaxeconomicPrice = this.MaxPrice;
            }
            if (this.check == 2){
                MinbuisinesssPrice = this.MinPrice;
                MaxbuisinesssPrice = this.MaxPrice;
            }
            if (this.check == 3) {
                MinfirstClassPrice = this.MinPrice;
                MaxfirstClassPrice = this.MaxPrice;
            }
            var flightForSearch = {startDestination: this.startDestination, endDestination: this.endDestination, startDate: this.startDate, endDate: this.endDate,
            minEconomic : MineconomicPrice, minBusiness: MinbuisinesssPrice, minFirstClass :MinfirstClassPrice,
            maxEconomic: MaxeconomicPrice, maxBusiness: MaxbuisinesssPrice, maxFirstClass:MaxfirstClassPrice,
            flightDuration: this.flightDuration, flightLength:  this.flightLength, flightCompany: this.flightCompany,
            transitionsNum: this.transitionsNum }
            axios.post("/api/flightSearch",flightForSearch)
            .then(response => {
                this.flights = response.data
            }); 
            this.$refs["results"].hidden = false;
        },
        goToDetails : function(flightID){
            localStorage.setItem("flightID",flightID)
            this.$router.push("/front/flight");
        }
    }
}

</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #303e4d;
}

.searchForm{
    margin-left: auto;
    margin-right: auto;
}

</style>
