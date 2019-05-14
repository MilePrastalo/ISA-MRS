<template>
    <div id="registeredUserFrontPage">
        <div class="container">
            <div class="row">
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a id="profile" class="nav-link active" href="#" @click="showProfile">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a id="companyProfile" class="nav-link" href="#" @click="showCompanyProfile">Company Profile</a>
                        
                    </li>
                    <li class="nav-item">
                        <a id="Branch_offices" class="nav-link" href="#" @click="showOffices">Offices</a>
                    </li>
                    <li class="nav-item">
                        <a id="Vehicles" class="nav-link" href="#" @click="showVehicles">Vehicles</a>
                    </li>
                    <li class="nav-item">
                        <a id="QuickReservations" class="nav-link" href="#" @click="showQuick">Quick Reservation</a>
                    </li>
                </ul>
                <Button @click = "logout">Log Out</Button>
                
            </div>
            <div class="row">
                <div id="Profile" v-if="tabSelected==1">
                    <user-profile></user-profile>
                </div>
                <div id="HotelsReservations" v-if="tabSelected==2"><rentacarprofile></rentacarprofile></div>
                
                <div id="office" v-if="tabSelected==3"><officeAdmin></officeAdmin></div>
                <div id="CarsReservations" v-if="tabSelected==4"><vehicleAdminTab></vehicleAdminTab></div>
                <div id="QuickReservations" v-if="tabSelected==5"><quickVehicle></quickVehicle></div>
            </div>
        </div>
    </div>
</template>

<script>

import userProfile from "./userProfile.vue";
import rentacarprofile from "./rentacarprofile.vue";
import officeAdmin from "./officesAdminTab.vue";
import vehicleAdminTab from "./vehicleAdminTab.vue";
import quickVehicle from "./quickVehicleReservation.vue";

export default {
  name: 'registeredUserFrontPage',
  components: {
    userProfile,
    rentacarprofile,
    officeAdmin,
    vehicleAdminTab,
    quickVehicle
  },
  data: function(){
      return {
          tabSelected:1
      }
  },
  methods : {
      showProfile : function(){
            this.tabSelected = 1;
            document.getElementById("profile").className="nav-link active";
            document.getElementById("companyProfile").className="nav-link";
            document.getElementById("Branch_offices").className="nav-link";
            document.getElementById("Vehicles").className="nav-link";
            document.getElementById("QuickReservations").className="nav-link";

      },
      showCompanyProfile : function(){
            this.tabSelected = 2;
            document.getElementById("profile").className="nav-link";
            document.getElementById("companyProfile").className="nav-link active";
            document.getElementById("Branch_offices").className="nav-link";
            document.getElementById("Vehicles").className="nav-link";
            document.getElementById("QuickReservations").className="nav-link";

      },
      showOffices : function(){
            this.tabSelected = 3;
            document.getElementById("profile").className="nav-link";
            document.getElementById("companyProfile").className="nav-link";
            document.getElementById("Branch_offices").className="nav-link active";
            document.getElementById("Vehicles").className="nav-link";
            document.getElementById("QuickReservations").className="nav-link";

      },
      showVehicles: function(){
            this.tabSelected = 4;
            document.getElementById("profile").className="nav-link";
            document.getElementById("companyProfile").className="nav-link";
            document.getElementById("Branch_offices").className="nav-link";
            document.getElementById("Vehicles").className="nav-link active";
            document.getElementById("QuickReservations").className="nav-link";

      },
      showQuick:function(){
        this.tabSelected = 5;
        document.getElementById("profile").className="nav-link";
        document.getElementById("companyProfile").className="nav-link";
        document.getElementById("Branch_offices").className="nav-link";
        document.getElementById("Vehicles").className="nav-link";
        document.getElementById("QuickReservations").className="nav-link active";

        
      },
      logout :function(){
            localStorage.setItem('jwtToken',"");
            window.location="./";
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
table{
    border: 1px solid darkgray;
}
th{
    border: 1px solid darkgray;
}
</style>