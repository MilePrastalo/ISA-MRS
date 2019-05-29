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
                        <a  class="nav-link" href="#" @click="selectTab(3)">Remove Flight Company</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(4)">Add Flight Company Admin</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(5)">Remove Flight Company Admin</a>
                    </li>
                </ul>
            </div>
            
            <div v-if="currentTab == 1">
                <br>
                <br>
                <table class="table">
                    <thead class="thead-dark">
                     
                    <tr>
                        <th>Flight company name</th>
                        <th>Adress</th>
                        <th>Description</th>
                        <th>Rating</th>
                    </tr>
                    </thead>
            <tr v-for="f in this.fcs" :key="f.id">  
                <td>{{f.name}}</td>
                <td>{{f.address}}</td>
                <td>{{f.description}}</td>
                <td>{{f.rating}}</td>
            </tr>
            </table>
            </div>

            <div  v-if="currentTab == 2"> 
                 <table>
                     
                <tr>
                    <td><b> Name: </b></td>
                    <td>  <input type="text" name="name" v-model="name" > </td>
                </tr>
                <tr>
                    <td><b> Address: </b></td>
                    <td>  <input type="text" name="address" v-model="address" > </td>
                </tr>
                <tr>
                    <td><b> Description: </b></td>
                    <td> <textarea  rows="5" cols="22" name="description"  v-model="description" style="overflow:scroll;"></textarea> </td>        
                </tr>
                <tr>
                    <td>  </td>
                    <td><button v-on:click="addFlightCompany()" class="btn-primary">Add Flight Company</button> </td>      
                </tr>
            </table>      
            </div> 

            <div  v-if="currentTab == 3">
                <table>
                <tr>
                    <td> Enter flight company's name you want to remove: </td>
                    <td>  <input type="text" name="name" v-model="name" > </td>
                    <td><button v-on:click="removeFlightCompany()" class="btn-primary">Remove Flight Company</button> </td> 
                </tr>
                </table>
            </div>
            <div  v-if="currentTab == 4">
                 <table>
                <tr>
                    <td><b> Username: </b></td>
                    <td>  <input type="text" name="admin.username" v-model="admin.username" > </td>
                </tr>
                <tr>
                    <td><b> Password: </b></td>
                    <td>  <input type="text" name="admin.password" v-model="admin.password" > </td>
                </tr>
                <tr>
                    <td><b> First Name: </b></td>
                    <td> <input type="text" name="admin.firstName" v-model="admin.firstName" > </td>        
                </tr>
                <tr>
                    <td><b> Last Name: </b></td>
                    <td> <input type="text" name="admin.lastName" v-model="admin.lastName" > </td>        
                </tr>
                <tr>
                    <td><b> Email: </b></td>
                    <td> <input type="text" name="admin.email" v-model="admin.email" > </td>        
                </tr>
                <tr>
                    <td><b> Flight Company Name: </b></td>
                    <td> <input type="text" name="flightCompanyName" v-model="flightCompanyName" > </td>        
                </tr>
                <tr>
                    <td>  </td>
                    <td><button v-on:click="addFlightCompanyAdmin()" class="btn-primary">Add Flight Company Admin</button> </td>      
                </tr>
            </table>     
            </div>
            <div  v-if="currentTab == 5"> 
                <table>
                <tr>
                    <td> Enter flight company admin's username you want to remove: </td>
                    <td>  <input type="text" name="admin.username" v-model="admin.username" > </td>
                    <td><button v-on:click="removeFlightCompanyAdmin()" class="btn-primary">Remove Flight Company Admin</button> </td> 
                </tr>
                </table>
            </div>
        </div>
</template>

<script>

export default {
  name: 'saFlightCompanies',
  components: {
  },
  data: function () {
  return {
    fcs: [],
    admin: [],
    flightCompanyName: "",
    name: "",
    address: "",
    description: "",
    currentTab: 1
  }
},
mounted(){
        var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
        axios.get("http://localhost:8080/api/getAllFlightCompanies")
            .then(response => {
                this.fcs = response.data;
            })
    },
    methods:{
        selectTab: function(tabId){
            this.currentTab = tabId;
        },
        addFlightCompany: function() {
            axios.post("http://localhost:8080/api/addFlightCompany",{name:this.name,address:this.address,description:this.description}).
            then(response =>{
                alert(response.data.name + " has been successfully added.");
            })
        },
        removeFlightCompany: function() {
            axios.delete("http://localhost:8080/api/removeFlightCompany/"+ this.name)
            .then(response => {
                alert(response.data.name + " has been successfully removed.");
            })
        },
        addFlightCompanyAdmin: function() {
            axios.post("http://localhost:8080/api/addFlightAdmin",{username:this.admin.username,password:this.admin.password,firstName:this.admin.firstName,lastName:this.admin.lastName,email:this.admin.email,companyName:this.flightCompanyName}).
            then(response =>{
                if(response.data === true) {
                    alert("Flight admin has been successfully added.");
                } else {
                    alert("Username is taken.");
                }
            })
        },
        removeFlightCompanyAdmin: function() {
            axios.delete("http://localhost:8080/api/removeFlightAdmin/"+ this.admin.username)
            .then(response => {
                alert(response.data.username + " has been successfully removed.");
            })
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
  margin-left: 5%;
}
</style>
