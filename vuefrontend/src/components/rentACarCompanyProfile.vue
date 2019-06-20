<template>
    <div id="rentACarCompanyProfile">
        <navbar/>
        <div class="row">
            <button @click="back">Back</button>
        </div>
        <nav>
            <div class="nav nav-tabs nav-fill big" id="nav-tab" role="tablist">
                <a @click="showCompanyProfile" id="info" class="nav-item nav-link active" data-toggle="tab" role="tab" aria-controls="nav-home" aria-selected="true">Info</a>
                <a @click="showOffices" id="offices" class="nav-item nav-link"  data-toggle="tab" role="tab" aria-controls="nav-profile" aria-selected="false">Offices</a>
                <a @click="showVehicles" id="Vehicles" class="nav-item nav-link" data-toggle="tab"  role="tab" aria-controls="nav-contact" aria-selected="false">Vehicles</a>
            </div>
        </nav>
        <div class="row">
            <div id="HotelsReservations centered" v-if="tabSelected==1">
                <table>
                    <tr>
                        <td colspan="2"><h2>Company profile</h2> </td>
                    </tr>
                    <tr>
                        <td>Name: </td>
                        <td>{{name}}</td>
                    </tr>
                    <tr>
                        <td>Address: </td>
                        <td>{{address}}</td>
                    </tr>
                    <tr>
                        <td>Description: </td>
                        <td>{{description}}</td>
                    </tr>
                    <tr>
                        <td>Rating: </td>
                        <td>{{rating}}</td>
                    </tr>

                </table>
                

            </div>
            <div id="office centered" v-if="tabSelected==2">
                <div class="row">

                    <table class="table centered col">
                    <tr>
                        <th>Name</th>
                        <th>Destination</th>
                        <th>Address</th>
                    </tr>
                    <tr  v-for="office in offices" :key="office.id" @click="officeMap(office)">
                        <td>{{office.name}}</td>
                        <td>{{office.destination}}</td>
                        <td>{{office.address}}</td>
                    </tr>
                </table>
                <div class="col mapa">
                    <yandex-map
                        :coords="[this.latitude,this.longitude]"
                        zoom="14"
                        style="width:350px;height:250px;"
                        :controlss="['zoomControl']"
                        map-type="hybrid"
                    >
                        <ymap-marker
                        marker-id="1"
                        marker-type="placemark"
                        :coords="[this.latitude,this.longitude]"
                        :marker-fill="{color: '#0E4779', opacity: 0.5}"
                        :marker-stroke="{color: '#0E4779',width: 4}"
                        ></ymap-marker>
                    </yandex-map>
                    </div>
                </div>
                
            </div>
            <div id="CarsReservations centered" v-if="tabSelected==3">
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
                    <tr v-for="car in vehicles" :key="car.id">
                        <td><img :src="sourceImg" width="50px" height="50px"/></td>
                        <td>{{car.name}}</td>
                        <td>{{car.maker}}</td>
                        <td>{{car.type}}</td>
                        <td>{{car.year}}</td>
                        <td>{{car.seats}}</td>
                        <td>{{car.price}}</td>
                        <td>{{car.rating}}</td>
                        </tr>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import navbar from "./navbar.vue";

export default {
  name: "rentACarCompanyProfile",
  components: {
      navbar
  },
  props:["icompany"],
  data: function () {
    return {
        company:this.icompany,
        tabSelected :1,
        offices:[],
        vehicles:[],
        name:"",
        description:"",
        address:"",
        rating:"",
        longitude:0,
        latitude:0,
        sourceImg:"../kola.jpg"

    }
  },
    mounted(){
            axios.get("/api/getRentACarCompanyInfo/"+this.company.id)
                .then(response => {
                    console.log(response.data);
                    this.name = response.data.name;
                    this.address = response.data.address;
                    this.description = response.data.description;
                    this.vehicles = response.data.vehicles;
                    this.offices = response.data.offices
                    this.rating = response.data.rating;
                }); 
    },
    methods:{
           showCompanyProfile : function(){
            this.tabSelected = 1;
            document.getElementById("info").className="nav-item nav-link active";
            document.getElementById("offices").className="nav-item nav-link";
            document.getElementById("Vehicles").className="nav-item nav-link";

      },
      showOffices : function(){
            this.tabSelected = 2;
            document.getElementById("info").className="nav-item nav-link";
            document.getElementById("offices").className="nav-item nav-link active";
            document.getElementById("Vehicles").className="nav-item nav-link";

      },
      showVehicles: function(){
            this.tabSelected = 3;
            document.getElementById("info").className="nav-item nav-link";
            document.getElementById("offices").className="nav-item nav-link";
            document.getElementById("Vehicles").className="nav-item nav-link active";

      }, 
      back(){
          this.$emit("back");
      },
      officeMap(office){
          this.longitude = office.longitude;
          this.latitude = office.latitude;
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
table{
    margin-top: 10%;
}
.big{
    margin-top: 5%;
    width: 800px;
}
.mapa{
    margin-top: 10%;
}
</style>
