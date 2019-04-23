<template>
   <div id = "searchFlightCompany">


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
            <table border="1" hidden ref = "resCompanySearch" >
            <tr>
                <td>Name</td>
                <td>Address</td>
                <td>Description</td>
            </tr>
            <tr v-for="company in companies" :key="company.id"> 
                <td>{{company.name}}</td>
                <td>{{company.address}}</td>
                <td>{{company.description}}</td>
                <td> <Button>Details</Button></td>
            </tr>
            </table>
        </div>        
    </div>
</template>

<script>
export default {
  name: 'searchFlightCompany',
  components: {
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
        this.$refs["resCompanySearch"].hidden = false;
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
  margin: 5%;
}
</style>
