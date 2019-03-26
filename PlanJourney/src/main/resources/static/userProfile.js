/**
 * 
 */

var user = new Vue({ 
    el: '#userProfile',
    data: {
    	firstName: "",
    	lastName: "",
    	email: "",
    	password: "",
    	repeatedPassword: ""
    	
    },

    mounted () {
        axios
          .get('api/getUser')
          .then(function(response){
        	  setAttributes(response.data);
          });
    },
    
    methods: {
    	updateUserProfile : function(fName,lName,email,pass) {
    		
    		if (this.password == this.repeatedPassword){
    			axios
        		.post("api/updateUserProfile", {"firstName" : fName,"lastName": lName, "email": email, "password": pass })
        		.then(function(response){
        			setAttributes(response.data);
        			alert("Your profile has been successfuly updated!");
        		
        		});
    		}
    		else{
    			alert("Not valid confirmation of the password!");
    		}
    		
    	}
   	}
});


function setAttributes(data){
	user.firstName = data.firstName;
	user.lastName = data.lastName;
	user.email = data.email;
	user.password = data.password;
	user.repeatedPassword = data.password;
}