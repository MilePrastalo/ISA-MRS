<template>
   <div id = "saFlightCompanies" class="container">

            <br>
             <div class="row"> 
            </div>
             <br>
             <div class="row">
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(1)">Flight Companies</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(2)">Add Flight Company</a>
                        
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(3)">Remove Flight Company Admin</a>
                    </li>
                </ul>
            </div>
            
            <div v-if="currentTab == 1 && showFCs == true">
                <br>
                <br>
                <table class="table">
                    <thead class="thead-dark">
                     
                    <tr>
                        <th>Flight company name</th>
                        <th>Adress</th>
                        <th>Description</th>
                        <th>Rating</th>
                        <th>Options</th>
                        <th></th>
                    </tr>
                    </thead>
            <tr v-for="f in this.fcs" :key="f.id">  
                <td>{{f.name}}</td>
                <td>{{f.address}}</td>
                <td>{{f.description}}</td>
                <td>{{f.rating}}</td>
                <td><button v-on:click="addFCAdminDiv(f.name)" class="btn-primary" >Add Admin</button></td>
                <td><button v-on:click="removeFlightCompany(f.name)" class="btn-primary" style="background-color:red">Remove</button></td>
            </tr>
            </table>
            </div>

            <div  v-if="currentTab == 2"> 
                 <table>
                     
                <tr>
                    <td><b> Name: </b></td>
                    <td>  <input type="text" v-model="newFC.name" > </td>
                </tr>
                <tr>
                    <td><b> Address: </b></td>
                    <td>  <input type="text" v-model="newFC.address" > </td>
                </tr>
                <tr>
                    <td><b> Description: </b></td>
                    <td> <textarea  rows="5" cols="22"  v-model="newFC.description" style="overflow:scroll;"></textarea> </td>        
                </tr>
                <tr>
                    <td>  </td>
                    <td><button v-on:click="addFlightCompany()" class="btn-primary">Add Flight Company</button> </td>      
                </tr>
            </table>      
            </div> 
            <div  v-if="addingFCAdmin == true">
                 <table>
                      <tr>
                    <td><b> Flight Company Name: </b></td>
                    <td> {{flightCompanyName}}</td>        
                </tr>
                <tr>
                    <td><b> Username: </b></td>
                    <td>  <input type="text" v-model="admin.username" > </td>
                </tr>
                <tr>
                    <td><b> Password: </b></td>
                    <td>  <input type="text" v-model="admin.password" > </td>
                </tr>
                <tr>
                    <td><b> First Name: </b></td>
                    <td> <input type="text" v-model="admin.firstName" > </td>        
                </tr>
                <tr>
                    <td><b> Last Name: </b></td>
                    <td> <input type="text" v-model="admin.lastName" > </td>        
                </tr>
                <tr>
                    <td><b> Email: </b></td>
                    <td> <input type="text" v-model="admin.email" > </td>        
                </tr>
                <tr>
                    <td>  </td>
                    <td><button v-on:click="addFlightCompanyAdmin()" class="btn-primary">Add Flight Company Admin</button> </td>
                    <td><button v-on:click="cancelFCAdmin()" class="btn-primary">Cancel</button> </td>       
                </tr>
            </table>     
            </div>
            <div  v-if="currentTab == 3"> 
                <br>
                <br>
                <table class="table" >
                    <thead class="thead-dark">
                    <tr>
                        <th>Username</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Hotel Name</th>
                        <th>Options</th>
                    </tr>
                    </thead>
            <tr v-for="a in this.flightAdmins" :key="a.id">  
                <td>{{a.username}}</td>
                <td>{{a.firstName}}</td>
                <td>{{a.lastName}}</td>
                <td>{{a.hotelName}}</td>
                <td><button v-on:click="removeAdmin(a.username)" class="btn-primary" style="background-color:red">Remove</button></td>
            </tr>
            </table>
            </div>
        </div>
</template>

<script>
import axios from "axios";

export default {
  name: "saFlightCompanies",
  components: {
  },
  data: function () {
  return {
    fcs: [],
    flightAdmins: [],
    newFC: {},
    admin: {},
    addingFCAdmin: false,
    showFCs: true,
    flightCompanyName: "",
    name: "",
    address: "",
    description: "",
    currentTab: 1
  }
},
mounted(){
        var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
        };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
        axios.get("/api/getAllFlightCompanies")
            .then(response => {
                this.fcs = response.data;
            });

        axios.get("/api/getAllFlightAdmins")
            .then(response => {
                this.flightAdmins = response.data;
            });
    },
    methods:{
        selectTab: function(tabId){
            this.currentTab = tabId;
        },
        addFlightCompany: function() {
            axios.post("/api/addFlightCompany",this.newFC).
            then(response =>{
                 alert("Flight Company has been successfully added.");
                    this.fcs.push(this.newFC);
                    this.newFC = {};
                alert(response.data.name + " has been successfully added.");
            })
        },
        removeFlightCompany: function(name) {
            axios.delete("/api/removeFlightCompany/"+ name)
            .then(response => {
                var index;
                for(let f in this.fcs) {
                    if(this.fcs[f].name == name) {
                        index = f;
                        break;
                    }
                }
                this.fcs.splice(index,1);
                alert(response.data.name + " has been successfully removed.");
            })
        },
        addFCAdminDiv: function(name) {
            this.showFCs = false;
            this.flightCompanyName = name;
            this.addingFCAdmin = true;
        },
        cancelFCAdmin: function() {
          this.admin = {};
          this.addingFCAdmin = false;
          this.showFCs = true;  
        },
        addFlightCompanyAdmin: function() {
            axios.post("/api/addFlightAdmin",{username:this.admin.username,password:this.admin.password,firstName:this.admin.firstName,lastName:this.admin.lastName,email:this.admin.email,companyName:this.flightCompanyName}).
            then(response =>{
                if(response.data === true) {
                    alert("Flight admin has been successfully added.");
                    this.admin = {};
                } else {
                    alert("Username is taken.");
                }
            })
        },
        removeAdmin: function(name) {
            axios.delete("/api/removeFlightAdmin/"+ name)
            .then(response => {
                var index;
                for(let a in this.flightAdmins) {
                    if(this.flightAdmins[a].name == name) {
                        index = a;
                        break;
                    }
                }
                this.flightAdmins.splice(index,1);
                alert(response.data.username + " has been successfully removed.");
            })
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
  color: #2c3e50;
  margin-left: 5%;
}
</style>
