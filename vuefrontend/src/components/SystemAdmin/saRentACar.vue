<template>
   <div id = "saRentACar">

            <br>
             <div class="row"> 
            </div>
             <br>
             <div class="row">
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a  class="nav-link active" href="#" @click="selectTab(1)" v-on:click="loadRentACars">Rent A Cars</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(2)">Add Rent A Car</a>
                        
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(3)">Remove Rent A Car</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(4)">Add Rent A Car Admin</a>
                    </li>
                    <li class="nav-item">
                        <a  class="nav-link" href="#" @click="selectTab(5)">Remove Rent A Car Admin</a>
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
            <tr v-for="r in this.racs" :key="r.id">  
                <td>{{r.name}}</td>
                <td>{{r.address}}</td>
                <td>{{r.description}}</td>
                <td>{{r.rating}}</td>
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
                    <td><button v-on:click="addRentACar()">Add Rent A Car</button> </td>      
                </tr>
            </table>      
            </div> 

            <div  v-if="currentTab == 3">
                <table>
                <tr>
                    <td> Enter rent a car's name you want to remove: </td>
                    <td>  <input type="text" name="name" v-model="name" > </td>
                    <td><button v-on:click="removeRentACar()">Remove Rent A Car</button> </td> 
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
                    <td> <input type="text" name="rentACarName" v-model="rentACarName" > </td>        
                </tr>
                <tr>
                    <td>  </td>
                    <td><button v-on:click="addRentACarAdmin()">Add Rent A Car Admin</button> </td>      
                </tr>
            </table>     
            </div>
            <div  v-if="currentTab == 5"> 
                <table>
                <tr>
                    <td> Enter rent a car admin's username you want to remove: </td>
                    <td>  <input type="text" name="admin.username" v-model="admin.username" > </td>
                    <td><button v-on:click="removeRentACarAdmin()">Remove Rent A Car Admin</button> </td> 
                </tr>
                </table>
            </div>
        </div>
</template>

<script>

export default {
  name: 'saRentACar',
  components: {
  },
  data: function () {
  return {
    racs: [],
    admin: [],
    name: "",
    address: "",
    description: "",
    rentACarName: "",
    currentTab: 1
  }
},
mounted(){
        var getJwtToken = function() {
            return localStorage.getItem('jwtToken');
        };
        axios.defaults.headers.common['Authorization'] = "Bearer " + getJwtToken();
    },
    methods:{
        selectTab: function(tabId){
            this.currentTab = tabId;
        },
        loadRentACars: function() {
            axios.get("http://localhost:8080/api/getAllRentACars")
            .then(response => {
                this.racs = response.data;
            })
        },
        addRentACar: function() {
            axios.post("http://localhost:8080/api/addRentACarCompany",{name:this.name,address:this.address,description:this.description}).
            then(response =>{
                alert(response.data.name + " has been successfully added.");
            })
        },
        removeRentACar: function() {
            axios.delete("http://localhost:8080/api/removeRentACarCompany/"+ this.name)
            .then(response => {
                alert(response.data.name + " has been successfully removed.");
            })
        },
        addRentACarAdmin: function() {
            axios.post("http://localhost:8080/api/addRentACarAdmin",{username:this.admin.username,password:this.admin.password,firstName:this.admin.firstName,lastName:this.admin.lastName,email:this.admin.email,service:{name:this.rentACarName}}).
            then(response =>{
                alert(response.data.username + " has been successfully added.");
            })
        },
        removeRentACarAdmin: function() {
            axios.delete("http://localhost:8080/api/removeRentACarAdmin/"+ this.admin.username)
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
