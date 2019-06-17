<template>
    <div id="unregisteredUserFrontPage">
        <navbar :itype="0"/>

        <nav>
            <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                <a @click="showFlight" id="flsearch" class="nav-item nav-link active" data-toggle="tab" role="tab" aria-controls="nav-home" aria-selected="true">Flight Search</a>
                <a @click="showHotel" id="hotsearch" class="nav-item nav-link"  data-toggle="tab" role="tab" aria-controls="nav-profile" aria-selected="false">Hotel Search</a>
                <a @click="showRent" id="rentsearch" class="nav-item nav-link" data-toggle="tab"  role="tab" aria-controls="nav-contact" aria-selected="false">Rent a Car Search</a>
            </div>
        </nav>
        <div class="row"  id="searches" >
                <div  class="searches" id="Flight" v-if="tabSelected==1"><SearchFlights></SearchFlights></div>
                <div  class="searches" id="Hotel" v-if="tabSelected==2"><searchHotels></searchHotels></div>
                <div class="searches" id="Rent" v-if="tabSelected==3"><rentACarReservation></rentACarReservation></div>
            </div>
    </div>
    
</template>

<script>
import SearchFlights from './searchFlights';
import searchRentACarCompany from './searchRentACarCompany.vue';
import searchHotels from './searchHotels.vue';
import navbar from "./navbar.vue";
import rentACarReservation from "./rentACarReservation.vue";
export default {
  name: "unregisteredUserFrontPage",
  components: {
      SearchFlights,searchHotels,searchRentACarCompany,navbar,rentACarReservation
  },
  data: function(){
      return{
          tabSelected:1
      };
  },
  methods: {
        logIn: function() {
            this.$router.push("front/loginPage");
        },
        register: function() {
            this.$router.push("front/registerPage");
        },
        showFlight : function(){
            this.tabSelected = 1;
            document.getElementById("flsearch").className="nav-item nav-link active";
            document.getElementById("hotsearch").className="nav-item nav-link";
            document.getElementById("rentsearch").className="nav-item nav-link";
        },
        showHotel : function(){
            this.tabSelected = 2;
            document.getElementById("flsearch").className="nav-item nav-link";
            document.getElementById("hotsearch").className="nav-item nav-link active";
            document.getElementById("rentsearch").className="nav-item nav-link";
        },
        showRent: function(){
            this.tabSelected = 3;
            document.getElementById("rentsearch").className="nav-item nav-link active";
            document.getElementById("hotsearch").className="nav-item nav-link";
            document.getElementById("flsearch").className="nav-item nav-link";
        }
    }
}

</script>

<style scoped>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#tabs{
    margin: auto;
}
.searches{
    margin:auto;
}

nav > .nav.nav-tabs{

  border: none;
    color:#fff;
    background:#272e38;
    border-radius:0;

}
nav > div a.nav-item.nav-link,
nav > div a.nav-item.nav-link.active
{
  border: none;
    padding: 18px 25px;
    color:#fff;
    background:#272e38;
    border-radius:0;
}

nav > div a.nav-item.nav-link.active:after
 {
  content: "";
  position: relative;
  bottom: -60px;
  left: -10%;
  border: 15px solid transparent;
  border-top-color: #428bca ;
}
.tab-content{
  background: #fdfdfd;
    line-height: 25px;
    border: 1px solid #ddd;
    border-top:5px solid #428bca;
    border-bottom:5px solid #428bca;
    padding:30px 25px;
}

nav > div a.nav-item.nav-link:hover,
nav > div a.nav-item.nav-link:focus
{
  border: none;
    background: #428bca;
    color:#fff;
    border-radius:0;
    transition:background 0.20s linear;
}
#nav-tab{
    margin-bottom: 3%;
    margin-top: 3%;
    margin-left: 10%;
    margin-right: 10%;
}
</style>