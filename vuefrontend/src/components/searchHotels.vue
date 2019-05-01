<template>
   <div id = "searchHotels">
       <h1>Hotel Search</h1>
       <br>
       <div>
             <br>
             <div class="row">
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a  class="nav-link active" href="#" @click="selectTab(1)">Hotel Name</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(2)">Destination</a>
                        
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#" @click="selectTab(3)">Number Of Beds</a>
                    </li>
                </ul>
            </div>
       </div>
        <br>
       <div v-if="currentTab == 1">
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
           <div> 

                <table border="1" >
                    <tr>
                        <td>Hotel name</td>
                        <td>Destination</td>
                        <td>Adress</td>
                        <td>Description</td>
                        <td>Options</td>
                    </tr>
                    <tr v-for="h in hotels" :key="h.id">  
                        <td>{{h.name}}</td>
                        <td>{{h.destination.name}}</td>
                        <td>{{h.address}}</td>
                        <td>{{h.description}}</td>
                        <td><button @click="showDetails(h.name)">Details</button> </td>
                    </tr>
                </table>
            </div>
                
        </div>

        <div  v-if="currentTab == 2"> 
            <table>
                <tr>
                    <td> Enter Hotel Destination: </td>
                    <td>  <input type="text" name="destination" v-model="destination" > </td>
                </tr>
                <tr>
                    <td><button v-on:click="searchHotels('destination',destination)">Search</button> </td>
                </tr>
           </table>
           <br>
           <div> 

                <table border="1" >
                    <tr>
                        <td>Hotel name</td>
                        <td>Destination</td>
                        <td>Adress</td>
                        <td>Description</td>
                        <td>Options</td>
                    </tr>
                    <tr v-for="h in hotels" :key="h.id">  
                        <td>{{h.name}}</td>
                        <td>{{h.destination.name}}</td>
                        <td>{{h.address}}</td>
                        <td>{{h.description}}</td>
                        <td><button @click="showDetails(h.name)">Details</button> </td>
                    </tr>
                </table>
            </div>
                
        </div>
        <div  v-if="currentTab == 3">
            <table>
                <tr>
                    <td> Enter Number Of Rooms: </td>
                    <td>  <input type="text" name="nob" v-model="nob" > </td>
                </tr>
                <tr>
                    <td><button v-on:click="searchHotels('nob',nob)">Search</button> </td>
                </tr>
           </table>
           <br>
           <div> 

                <table border="1" >
                    <tr>
                        <td>Hotel name</td>
                        <td>Destination</td>
                        <td>Adress</td>
                        <td>Description</td>
                        <td>Options</td>
                    </tr>
                    <tr v-for="h in hotels" :key="h.id">  
                        <td>{{h.name}}</td>
                        <td>{{h.destination.name}}</td>
                        <td>{{h.address}}</td>
                        <td>{{h.description}}</td>
                        <td><button @click="showDetails(h.name)">Details</button> </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>    
</template>

<script>

export default {
  name: 'searchHotels',
  components: {
  },
  data: function () {
  return {
     hotels: [],
     hotelName: "",
     destination: "",
     nob: "",
    currentTab: 1

  }
},
mounted(){
    },
    methods:{
        selectTab: function(tabId){
            this.currentTab = tabId;
        },
        searchHotels: function(crit,val){
            axios.get("http://localhost:8080/api/searchHotels/" + crit +"|"+val)
            .then(response => {
                this.hotels = response.data;
            })  
        },
        showDetails: function(chosenHotel) {
            window.location ="./hotelProfile/"+ chosenHotel;
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
