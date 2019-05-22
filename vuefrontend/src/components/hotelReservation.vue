<template>
   <div class="Search">
       <div class="hrSearch">
       <br>
       <h2>Hotel Search</h2>
       <br>
        <br>
       <div>
           <table>
                <tr>
                    <td> Enter Hotel Name: </td>
                    <td>  <input type="text" name="hotelName" v-model="hotelName" > </td>
                </tr>
                <tr>
                    <td><button v-on:click="searchHotels('name',hotelName)">Search</button> </td>
                </tr>
           </table>
           <br>
           <div v-if="currentPart === 1"> 

                <table border="1" id="DataTable" class="table">
                    <tr>
                        <td>Hotel name</td>
                        <td>Destination</td>
                        <td>Adress</td>
                        <td>Description</td>
                        <td>Options</td>
                    </tr>
                    <tr v-for="h in hotels" :key="h.id"> 
                        <template v-if="h.destination.name === location"> 
                        <td>{{h.name}}</td>
                        <td>{{h.destination.name}}</td>
                        <td>{{h.address}}</td>
                        <td>{{h.description}}</td>
                        <td><button @click="showDetails(h.name)">Details</button> </td>
                        </template>
                    </tr>
                </table>
            </div>
            
            <div v-if="currentPart === 2">
                <hotelProfile :hotelName = "iHotelName" v-on:rr="hotelReserved"/>

            </div>
                
        </div>
       </div> 
    </div>    
</template>

<script>
import hotelProfile from './hotelProfile.vue';
export default {
  name: 'hotelReservation',
  props: ["location","passangers"],
  components: {
      hotelProfile
  },
  data: function () {
  return {
     hotels: [],
     hotelName: "",
     nob: "",
    currentTab: 1,
    currentPart: 0,
    iHotelName: "",
    hotelID: 0

  }
},
mounted(){
    },
    methods:{
        selectTab: function(tabId){
            this.currentPart = tabId;
        },
        searchHotels: function(crit,val){
            axios.get("http://localhost:8080/api/getAllHotels/")
            .then(response => {
                this.hotels = response.data;
            });
            this.currentPart = 1;  
        },
        showDetails: function(chosenHotel) {
            this.iHotelName = chosenHotel;
            this.currentPart = 2;
        },
        hotelReserved: function(id){
            this.$emit("roomReserved",id);
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
#DataTable{
    border: 1px solid black;
    margin:auto;
    margin-top: 2%;
}
#DataTable td{
    border:1px solid black;
}
#DataTable th{
    border:1px solid black;
}
h2{
    margin: auto;
}
#inputfields table{
    margin-left: auto;
    margin-right: auto;
    margin-top: 2%;
}
.Search {
  text-align: center;
}

.hrSearch {
  display: inline-block;
}
</style>
