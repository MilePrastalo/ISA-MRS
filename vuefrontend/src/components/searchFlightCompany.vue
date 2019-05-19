<template>
   <div id = "searchFlightCompany">
       <navbar/>

        <div class = "row">
            <h2>Flight Company Search </h2>
        </div>
        <br>

       <div class = "row">
        <table  style="text-align: left">
           <tr>
                <td>Company Name:</td>
                <td><input v-model="companyName" type="text"></td>
            </tr>
            <tr>
                <td> <button @click="search">Search</button> </td>
            </tr>  
        </table>
        </div>
        <br>
        <div>
            <table border="1" class = 'table'>
                <thead class="thead-dark">
                    <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Address</th>
                    <th scope="col">Description</th>
                    </tr>
                </thead>
                <tbody>
                <tr v-for="company in companies" :key="company.id"> 
                    <td>{{company.name}}</td>
                    <td>{{company.address}}</td>
                    <td>{{company.description}}</td>
                    <td> <Button @click="goToDetails(company.id)">Details</Button></td>
                </tr>
                </tbody>              
            </table>  
        </div>        
    </div>
</template>

<script>
import navbar from "./navbar.vue";

export default {
  name: 'searchFlightCompany',
  components: {
      navbar
    },
  data: function () {
  return {
     companyName: "-",
     companies:[]
  }
},
mounted(){},

methods:{
    checkedClass: function(option){
        this.check = option;
    },
    search: function(){
        axios.get("http://localhost:8080/api/searchFlightCompanies/" + this.companyName)
        .then(response => {
            console.log(response);
            this.companies = response.data
        }); 
    },
    goToDetails: function(id){
        localStorage.setItem("idCompany", id);
        window.location = './CompanyProfile'
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
  color: #303e4d;
  margin-left: 5%;
  margin-right: 5%;
}
</style>
