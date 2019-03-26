/**
 * 
 */

var flightComany = new Vue({ 
    el: '#flightComanyProfile',
    data: {
    	name: "",
    	address: "",
    	description: ""   	
    },

    mounted () {
        axios
          .get('api/getFlightCompanyProfile')
          .then(function(response){
        	  setAttributes(response.data);
          });
    },
    
    methods: {
    	updateFlightCompanyProfile : function(name_,address_,description_) {
    		axios
    		.post("api/updateFlightCompanyProfile", {"name" : name_,"adress": address_, "description": description_ })
    		.then(function(response){
    			setAttributes(response.data);
    			
    			alert("Flight Company profile has been successfuly updated!");
    		
    		});	
    	}
   	}
});


function setAttributes(data){
	console.log(data);
	flightComany.name = data.name;
	flightComany.address = data.adress;
	flightComany.description = data.description;

}