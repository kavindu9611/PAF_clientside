$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	// Form validation-------------------
	var status = validatePaymentForm();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidPaymentIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
			{ 
				url : "PaymentAPI", 
				type : type, 
				data : $("#formPayment").serialize(), 
				dataType : "text", 
				complete : function(response, status) 
				{ 
					onPaymentSaveComplete(response.responseText, status); 
				} 
			});
	
});

function onPaymentSaveComplete(response, status)
{ 
	if (status == "success") 
	{ 
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") 
		{ 
			$("#alertSuccess").text("Successfully saved."); 
			$("#alertSuccess").show(); 
			$("#divItemsGrid").html(resultSet.data); 
		} else if (resultSet.status.trim() == "error") 
		{ 
			$("#alertError").text(resultSet.data); 
			$("#alertError").show(); 
		}
		
	} else if (status == "error") 
	{ 
		$("#alertError").text("Error while saving."); 
		$("#alertError").show(); 
	} else
	{ 
		$("#alertError").text("Unknown error while saving.."); 
		$("#alertError").show(); 
	} 
	$("#hidPaymentIDSave").val(""); 
	$("#formPayment")[0].reset(); 
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidPaymentIDSave").val($(this).closest("tr").find('#hidPaymentIDUpdate').val());
	$("#payment_id").val($(this).closest("tr").find('td:eq(0)').text());
	$("#customer_n").val($(this).closest("tr").find('td:eq(1)').text());
	$("#amount").val($(this).closest("tr").find('td:eq(2)').text());
	$("#email").val($(this).closest("tr").find('td:eq(3)').text());
	$("#card_type").val($(this).closest("tr").find('td:eq(4)').text());
	$("#card_no").val($(this).closest("tr").find('td:eq(5)').text());
	$("#exp_month").val($(this).closest("tr").find('td:eq(6)').text());
	$("#exp_year").val($(this).closest("tr").find('td:eq(7)').text());
	$("#cvn").val($(this).closest("tr").find('td:eq(8)').text());
});

//REMOVE======

$(document).on("click", ".btnRemove", function(event)
{ 
	$.ajax( 
	{ 
		url : "PaymentAPI", 
		type : "DELETE", 
		data : "payment_id=" + $(this).data("paymentid"),
		dataType : "text", 
		complete : function(response, status) 
		{ 
			onPaymentDeleteComplete(response.responseText, status); 
		} 
	}); 
});

function onPaymentDeleteComplete(response, status)
{ 
	if (status == "success") 
	{ 
		var resultSet = JSON.parse(response);
		
		if (resultSet.status.trim() == "success") 
		{ 
			$("#alertSuccess").text("Successfully deleted."); 
			$("#alertSuccess").show(); 
			$("#divItemsGrid").html(resultSet.data); 
		} else if (resultSet.status.trim() == "error") 
		{ 
			$("#alertError").text(resultSet.data); 
			$("#alertError").show(); 
		} 
	} else if (status == "error") 
	{ 
		$("#alertError").text("Error while deleting."); 
		$("#alertError").show(); 
	} else
	{ 
		$("#alertError").text("Unknown error while deleting.."); 
		$("#alertError").show(); 
	} 
}

// CLIENT-MODEL================================================================
function validatePaymentForm()
{
	// customer_n
	if ($("#customer_n").val().trim() == "")
	{
		return "Insert cus_name.";
	}
	
	// amount -------------------------------
	if ($("#amount").val().trim() == "")
	{
		return "Insert Payment amount.";
	}
	// is numerical value
	var tmpPrice = $("#amount").val().trim();
	if (!$.isNumeric(tmpPrice))
	{
		return "Insert a numerical value for amount.";
	}
	// convert to decimal price
	$("#amount").val(parseFloat(tmpPrice).toFixed(2));
	
	// email------------------------
	if ($("#email").val().trim() == "")
	{
		return "Insert email.";
	}
	
	// card_type------------------------
	if ($("#card_type").val().trim() == "")
	{
		return "Insert card_type.";
	}
	// card_no------------------------
	if ($("#card_no").val().trim() == "")
	{
		return "Insert card_no.";
	}
	// exp_month------------------------
	if ($("#exp_month").val().trim() == "")
	{
		return "Insert exp_month.";
	}
	// exp_year ------------------------
	if ($("#exp_year").val().trim() == "")
	{
		return "Insert exp_year.";
	}
	// cvn------------------------
	if ($("#cvn").val().trim() == "")
	{
		return "Insert cvn.";
	}
	
	return true;
}