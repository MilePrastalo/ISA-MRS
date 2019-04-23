<template>
    <div id="unregisteredUserFrontPage">
        <div>
            <h1 id="planjourney">PlanJourney</h1>

            <table align="center">
            <br>
            <tr>
                <td><button v-on:click="logIn()">Login</button></td>
                <td><button v-on:click="register()">Register</button></td>
            </tr>
            <br>
            <br>
        </table>
        </div>
         <div class="row">
                <ul class="nav nav-tabs" id="tabs">
                    <li class="nav-item">
                        <a id="flsearch" class="nav-link active" href="#" @click="showFlight">Flight Search</a>
                    </li>
                    <li class="nav-item">
                        <a id="hotsearch" class="nav-link" href="#" @click="showHotel">Hotel Search</a>
                        
                    </li>
                    <li class="nav-item">
                        <a id="rentsearch" class="nav-link" href="#" @click="showRent">Rent a Car Search</a>
                    </li> 
                </ul>
        </div>
        <div class="row"  id="searches" >
                <div  class="searches" id="Flight" v-if="tabSelected==1"><SearchFlights></SearchFlights></div>
                <div  class="searches" id="Hotel" v-if="tabSelected==2"><searchHotels></searchHotels></div>
                <div class="searches" id="Rent" v-if="tabSelected==3"><searchRentACarCompany></searchRentACarCompany></div>
            </div>
    </div>
    
</template>

<script>
import SearchFlights from './searchFlights';
import searchRentACarCompany from './searchRentACarCompany.vue';
import searchHotels from './searchHotels.vue';

export default {
  name: 'unregisteredUserFrontPage',
  components: {
      SearchFlights,searchHotels,searchRentACarCompany
  },
  data: function(){
      return{
          tabSelected:1
      };
  },
  methods: {
        logIn: function() {
            this.$router.push('/loginPage');
        },
        register: function() {
            this.$router.push('/registerPage');
        },
        showFlight : function(){
            this.tabSelected = 1;
            document.getElementById("flsearch").className="nav-link active";
            document.getElementById("hotsearch").className="nav-link";
            document.getElementById("rentsearch").className="nav-link";
        },
        showHotel : function(){
            this.tabSelected = 2;
            document.getElementById("flsearch").className="nav-link";
            document.getElementById("hotsearch").className="nav-link active";
            document.getElementById("rentsearch").className="nav-link";
        },
        showRent: function(){
            this.tabSelected = 3;
            document.getElementById("rentsearch").className="nav-link active";
            document.getElementById("hotsearch").className="nav-link";
            document.getElementById("flsearch").className="nav-link";
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
#planjourney {
    font-size: 3.175em!important;
}
#tabs{
    margin: auto;
}
.searches{
    margin:auto;
}
</style>