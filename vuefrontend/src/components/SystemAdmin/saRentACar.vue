<template>
   <div id = "saRentACar" class="container">

            <br>
             <div class="row"> 
            </div>
             <br>
             <div class="row">
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(1)">Rent A Cars</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(2)">Add Rent A Car</a>
                        
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(3)">Remove Rent A Car Admin</a>
                    </li>
                </ul>
            </div>
            
            <div v-if="currentTab == 1 && showRACs == true">
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
                        <th> </th>
                    </tr>
                    </thead>
            <tr v-for="r in this.racs" :key="r.id">  
                <td>{{r.name}}</td>
                <td>{{r.address}}</td>
                <td>{{r.description}}</td>
                <td>{{r.rating}}</td>
                <td><button v-on:click="addRACAdminDiv(r.name)" class="btn-primary" >Add Admin</button></td>
                <td><button v-on:click="removeRAC(r.name)" class="btn-primary" style="background-color:red">Remove</button></td>
            </tr>
            </table>
            </div>

            <div  v-if="currentTab == 2"> 
                 <table>
                <tr>
                    <td><b> Name: </b></td>
                    <td>  <input type="text" v-model="newRac.name" > </td>
                </tr>
                <tr>
                    <td><b> Address: </b></td>
                    <td>  <input type="text" v-model="newRac.address" > </td>
                </tr>
                <tr>
                    <td><b> Description: </b></td>
                    <td> <textarea  rows="5" cols="22" v-model="newRac.description" style="overflow:scroll;"></textarea> </td>        
                </tr>
                <tr>
                    <td>  </td>
                    <td><button v-on:click="addRentACar()" class="btn-primary">Add Rent A Car</button> </td>      
                       
                </tr>
            </table>      
            </div> 
            <div  v-if="addingRACAdmin == true">
                 <table>
                <tr>
                    <td><b> Flight Company Name: </b></td>      
                    <td>{{rentACarName}}</td>  
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
                    <td><button v-on:click="addRentACarAdmin()" class="btn-primary">Add Rent A Car Admin</button> </td>      
                    <td><button v-on:click="cancelRACAdmin()" class="btn-primary">Cancel</button> </td> 
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
            <tr v-for="a in this.racAdmins" :key="a.id">  
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

export default {
  name: "saRentACar",
  components: {
  },
  data: function () {
  return {
    racs: [],
    racAdmins: [],
    newRac:{},
    admin: {},
    addingRACAdmin: false,
    showRACs: true,
    name: "",
    address: "",
    description: "",
    rentACarName: "",
    currentTab: 1
  }
},
mounted(){
        var getJwtToken = function() {
            return localStorage.getItem("jwtToken");
        };
        axios.defaults.headers.common["Authorization"] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getAllRentACars")
            .then(response => {
                this.racs = response.data;
            });

        axios.get("http://localhost:8080/api/getAllRACAdmins")
            .then(response => {
                this.racAdmins = response.data;
            });
    },
    methods:{
        selectTab: function(tabId){
            this.currentTab = tabId;
        },
        addRentACar: function() {
            axios.post("http://localhost:8080/api/addRentACarCompany",this.newRac).
            then(response =>{
                alert(response.data.name + " has been successfully added.");
                    this.racs.push(this.newRac);
                    this.newRac = {};
            })
        },
        removeRAC: function(name) {
            axios.delete("http://localhost:8080/api/removeRentACarCompany/"+ name)
            .then(response => {
                var index;
                for(let r in this.racs) {
                    if(this.racs[r].name == name) {
                        index = r;
                        break;
                    }
                }
                this.racs.splice(index,1);
                alert(response.data.name + " has been successfully removed.");
            })
        },
        addRACAdminDiv: function(name) {
            this.showRACs = false;
            this.rentACarName = name;
            this.addingRACAdmin = true;
        },
        cancelRACAdmin: function() {
          this.admin = {};
          this.addingRACAdmin = false;
          this.showRACs = true;  
        },
        addRentACarAdmin: function() {
            axios.post("http://localhost:8080/api/addRentACarAdmin",{username:this.admin.username,password:this.admin.password,firstName:this.admin.firstName,lastName:this.admin.lastName,email:this.admin.email,companyName:this.rentACarName}).
            then(response =>{
                if(response.data === true) {
                    alert("Rent a car admin has been successfully added.");
                    this.admin = {};
                } else {
                    alert("There was a problem with adding new rent a car admin.");
                }
            })
        },
        removeAdmin: function(name) {
            axios.delete("http://localhost:8080/api/removeRentACarAdmin/"+ name)
            .then(response => {
                var index;
                for(let a in this.racAdmins) {
                    if(this.racAdmins[a].name == name) {
                        index = a;
                        break;
                    }
                }
                this.racAdmins.splice(index,1);
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
