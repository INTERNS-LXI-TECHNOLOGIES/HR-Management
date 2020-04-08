
 $( function() {
    
 	  $( ".autocomplete" ).autocomplete({
 		  appendTo: "#container",
 		 classes: {
 		    "ui-autocomplete": "highlight"
 		  },
     	  source: function(request, response ){
     		  console.log("working");
     		  $.ajax({
     			    url: "/statusform",
     			    dataType: "json",
     			    data: {
     			    	firstName: request.term
     			    },
     			    success: function (data) {
     			    	console.log("Successss"+JSON.stringify(data));
     			        response($.map(data, function (item) {
     			        	console.log("Response"+JSON.stringify(item));
     			            return {
     			                label: item.firstName,
     			                value: item.firstName
     			            };
     			        }));
     			    }
     			});
     	  }
     });
     
    
   } );