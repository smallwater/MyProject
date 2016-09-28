/* file: sample_server_request.js */
$(document).ready(function() {

	//Default mainContent upon landing.
	$("#randomNumberGenerator").hide();


	$("#homeMenuItem").click(function(event){
		$("#randomNumberGenerator").hide();
		$("#mainContent").show();
	});

	$("#randomNumberGeneratorMenuItem").click(function(event){
		$("#mainContent").hide();
		$("#randomNumberGenerator").show();
	});


	$("#generate").click(function(event){
		var quantity = $("#quantity").val();
		if(quantity < 1){
			alert("Please enter a quantity greater than 0.");
			return;
		}

		var bound = $("#bound").val();
		if(bound < 1){
			alert("Please enter a bound greater than 0.");
			return;
		}

		$.post('/RandomNumberGeneratorServlet', {"quantity":quantity, "bound":bound})

			.done(function(response){
				$("#stage").html(response);

				//convert the response into a JSON object
				var parsedResponse = JSON.parse(response);
				//convert the JSON object into a plain array
				var chartData = [];
				for(var x in parsedResponse){
				  chartData.push(parsedResponse[x]);
				}

				//use the D3.js library to draw a bar chart visulization of the random numbers we received
				//scale the vizualization to 325 pixels wide
		        var x = d3.scale.linear()
		            .domain([0, bound])
		            .range([0, 325]);
		        //clear the chart, if anything was previously there
		        d3.select(".chart")
		          .selectAll("div")
		          	.remove();
		        //populate the chart
		        d3.select(".chart")
		          .selectAll("div")
		            .data(chartData)
		          .enter().append("div")
		            .style("width", function(d) { return x(d) + "px"; })
		            .text(function(d) { return d; });


			})

			.fail(function(jqXHR, textStatus, errorThrown){
				alert("An unexpected error occured: " + errorThrown)
			})
		;
	});//end #generate.click
});

