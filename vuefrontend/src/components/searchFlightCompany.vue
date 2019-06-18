<template>
   <div id = "searchFlightCompany">
       <navbar  itype="1"/>
            <h2>Flight Company Search </h2>
        <br>

       <div class = "row">
        <table  style="text-align: left" class = "centered">
           <tr>
                <td>Company Name:</td>
                <td><input v-model="companyName" type="text"></td>
            </tr>
            <tr>
                <td> <button class = "btn btn-primary" @click="search">Search</button> </td>
            </tr>  
        </table>
        </div>
        <br>
        <div class = "companyTable">
            <br>
            <table border="1" class = "table centered">
                <thead class="thead-dark">
                    <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Address</th>
                    <th scope="col">Description</th>
                    <th></th>
                    </tr>
                </thead>
                <tbody>
                <tr v-for="company in companies" :key="company.id"> 
                    <td>{{company.name}}</td>
                    <td>{{company.address}}</td>
                    <td>{{company.description}}</td>
                    <td> <Button class = "btn btn-outline-primary" @click="goToDetails(company.id)">Details</Button></td>
                </tr>
                </tbody>              
            </table>  
        </div>        
    </div>
</template>

<script>
import navbar from "./navbar.vue";
import axios from "axios";

export default {
  name: "searchFlightCompany",
  components: {
      navbar
    },
  data: function () {
  return {
     companyName: "",
     companies:[]
  }
},
mounted(){
    var name = "-";
    axios.get("/api/searchFlightCompanies/" + name)
        .then(response => {
        console.log(response);
        this.companies = response.data
    }); 
},

methods:{
    checkedClass: function(option){
        this.check = option;
    },
    search: function(){
        if (this.companyName == ""){
            var name = "-";
            axios.get("/api/searchFlightCompanies/" + name)
            .then(response => {
            console.log(response);
            this.companies = response.data
        }); 
        }
        else{
            axios.get("/api/searchFlightCompanies/" + this.companyName)
            .then(response => {
            console.log(response);
            this.companies = response.data
        }); 
        }
    },
    goToDetails: function(id){
        localStorage.setItem("idCompany", id);
        this.$router.push("front/CompanyProfile");
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
  color: #303e4d;
}

.centered{
    margin-right: auto;
    margin-left: auto;
}

.companyTable{
    width: 60%;
    margin-right: auto;
    margin-left: auto;

}
</style>
