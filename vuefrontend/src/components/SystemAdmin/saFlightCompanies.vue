<template>
   <div id = "saFlightCompanies">

            <br>
             <div class="row"> 
            </div>
             <br>
             <div class="row">
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a  class="nav-link active" href="#" @click="selectTab(1)">Flight Companies</a>
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
                <table border="1" >
                    <tr>
                        <td>Flight company name</td>
                        <td>Adress</td>
                        <td>Description</td>
                        <td>Rating</td>
                    </tr>
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
                    <td> Name: </td>
                    <td>  <input type="text" name="name" v-model="name" > </td>
                </tr>
                <tr>
                    <td> Address: </td>
                    <td>  <input type="text" name="address" v-model="address" > </td>
                </tr>
                <tr>
                    <td> Description: </td>
                    <td> <textarea  rows="5" cols="22" name="description"  v-model="description" style="overflow:scroll;"></textarea> </td>        
                </tr>
                <tr>
                    <td>  </td>
                    <td><button v-on:click="addFlightCompany()">Add Flight Company</button> </td>      
                </tr>
            </table>      
            </div> 

            <div  v-if="currentTab == 3">
                <table>
                <tr>
                    <td> Enter flight company's name you want to remove: </td>
                    <td>  <input type="text" name="name" v-model="name" > </td>
                    <td><button v-on:click="removeFlightCompany()">Remove Flight Company</button> </td> 
                </tr>
                </table>
            </div>
            <div  v-if="currentTab == 4">
                 <table>
                <tr>
                    <td> Username: </td>
                    <td>  <input type="text" name="admin.username" v-model="admin.username" > </td>
                </tr>
                <tr>
                    <td> Password: </td>
                    <td>  <input type="text" name="admin.password" v-model="admin.password" > </td>
                </tr>
                <tr>
                    <td> First Name: </td>
                    <td> <input type="text" name="admin.firstName" v-model="admin.firstName" > </td>        
                </tr>
                <tr>
                    <td> Last Name: </td>
                    <td> <input type="text" name="admin.lastName" v-model="admin.lastName" > </td>        
                </tr>
                <tr>
                    <td> Email: </td>
                    <td> <input type="text" name="admin.email" v-model="admin.email" > </td>        
                </tr>
                <tr>
                    <td> Flight Company Name: </td>
                    <td> <input type="text" name="flightCompanyName" v-model="flightCompanyName" > </td>        
                </tr>
                <tr>
                    <td>  </td>
                    <td><button v-on:click="addFlightCompanyAdmin()">Add Flight Company Admin</button> </td>      
                </tr>
            </table>     
            </div>
            <div  v-if="currentTab == 5"> 
                <table>
                <tr>
                    <td> Enter flight company admin's username you want to remove: </td>
                    <td>  <input type="text" name="admin.username" v-model="admin.username" > </td>
                    <td><button v-on:click="removeFlightCompanyAdmin()">Remove Flight Company Admin</button> </td> 
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
            axios.post("http://localhost:8080/api/addFlightAdmin",{username:this.admin.username,password:this.admin.password,firstName:this.admin.firstName,lastName:this.admin.lastName,email:this.admin.email,flightCompany:{name:this.flightCompanyName}}).
            then(response =>{
                alert(response.data.username + " has been successfully added.");
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
