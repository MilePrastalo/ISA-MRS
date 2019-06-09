<template>
    <div id="registeredUserFrontPage">
        <navbar :itype="2"/>
        <div class="container">
            <div class="row">
                <ul class="nav nav-tabs centered">
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
                    <li class="nav-item">
                        <a id="Reports" class="nav-link" href="#" @click="showReports">Reports</a>
                    </li>
                </ul>                
            </div>
            <div class="row">
                <div id="Profile" v-if="tabSelected==1">
                    <user-profile></user-profile>
                </div>
                <div id="HotelsReservations centered" v-if="tabSelected==2"><rentacarprofile></rentacarprofile></div>
                
                <div id="office centered" v-if="tabSelected==3"><officeAdmin></officeAdmin></div>
                <div id="CarsReservations centered" v-if="tabSelected==4"><vehicleAdminTab></vehicleAdminTab></div>
                <div id="QuickReservations centered" v-if="tabSelected==5"><quickVehicle></quickVehicle></div>
                <div id="Report centered" v-if="tabSelected==6"><rentACarReport/></div>
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
import rentACarReport from "./rentACarReport.vue"
import navbar from "./navbar.vue";

export default {
  name: 'registeredUserFrontPage',
  components: {
    userProfile,
    rentacarprofile,
    officeAdmin,
    vehicleAdminTab,
    quickVehicle,
    rentACarReport,
    navbar
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
      showReports:function(){
          this.tabSelected=6;
          document.getElementById("profile").className="nav-link";
        document.getElementById("companyProfile").className="nav-link";
        document.getElementById("Branch_offices").className="nav-link";
        document.getElementById("Vehicles").className="nav-link";
        document.getElementById("QuickReservations").className="nav-link";
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
.centered{
    margin-left: auto;
    margin-right: auto;
    width: 100%;
      align-content: center;
  align-items: center;
  align-self: center;
}
.row{
    margin-right: auto;
    margin-left:auto;
}




</style>