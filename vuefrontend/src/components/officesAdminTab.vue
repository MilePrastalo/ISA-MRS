<template>
    <div id="officeAdmin">
        <h2>Branch Offices</h2>
        <addOffice v-if="selected == 1"></addOffice>
        <editOffice v-on:backadd="backAdd" v-on:vedited="veditedDraw" v-if="selected == 2" :iid="id" v-bind:iname="name" v-bind:idestination="destination" v-bind:iaddress="address" :ilongitude="longitude" :ilatitude="latitude"></editOffice>
        <table class="table centered">
            <tr>
                <th>Name</th>
                <th>Destination</th>
                 <th>Address</th>
                <th>Edit</th>
                <th>Remove</th>
            </tr>
            <tr v-for="office in officess" :key="office.id">
                <td>{{office.name}}</td>
                <td>{{office.destination}}</td>
                <td>{{office.address}}</td>
                <td><Button @click="editOfficeMethod(office.id,office.name,office.destination,office.address,office.latitude,office.longitude)">Edit</Button></td>
                <td><Button @click="removeOffice(office.id)">Remove</Button></td>
            </tr>
        </table>
    </div>
</template>

<script>

import addOffice from "./addOffice.vue";
import editOffice from "./editOffice.vue";

export default {
  name: "officeAdmin",
  components: {
    addOffice,
    editOffice
  },
  data: function(){
      return {
          selected : 1,
          officess :[],
          id:"",
          name:"aa",
          destination:"aaa",
          address:"",
          latitude:0,
          longitude:0

      }
  },
  mounted(){
        var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
        };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getOfficessByAdmin")
            .then(response => {
                this.officess = response.data;
            }); 
  },
  methods : {
      editOfficeMethod : function(id_,name_,destination_,address_,latitude,longitude){
          this.selected=2;
          this.id = id_;
          this.name = name_;
          this.destination = destination_;
          this.address = address_;
          this.latitude = latitude;
          this.longitude = longitude;
      },
      veditedDraw : function(){
         var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
        };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getOfficessByAdmin")
            .then(response => {
                this.officess = response.data;
            }); 
      },
      backAdd:function(){
          console.log("Called");
          this.selected = 1;
      },
      removeOffice:function(id){
          var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
        };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
        axios.delete("http://localhost:8080/api/removeOffice/"+id)
            .then(response => {
                alert("Success");
                console.log(response);
            }); 
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
table{
    border: 1px solid darkgray;
}
th{
    border: 1px solid darkgray;
}
#officeAdmin{
  margin-left: auto;
  margin-right: auto;
}
.centered{
  margin-left: auto;
  margin-right: auto;
  width: 800px;
}
h2{
    margin-top: 15px;
    margin-bottom: 15px;
}
</style>