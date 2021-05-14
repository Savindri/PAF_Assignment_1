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
	    var status = validateOrderForm();
		if (status != true)
		{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
		}
		
		 // If valid------------------------
		 var type = ($("#hidIdSave").val() == "") ? "POST" : "PUT"; 
		 $.ajax( 
		 { 
		 url : "OrderAPI", 
		 type : type, 
		 data : $("#formOrder").serialize(), 
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onOrderSaveComplete(response.responseText, status); 
		 } 
 	}); 
});
		
// UPDATE==========================================
	$(document).on("click", ".btnUpdate", function(event)
	{
	$("#hidIdSave").val($(this).data("oid"));
	$("#name").val($(this).closest("tr").find('td:eq(0)').text());
	$("#category").val($(this).closest("tr").find('td:eq(1)').text());
	$("#paymethod").val($(this).closest("tr").find('td:eq(2)').text());
	$("#payment").val($(this).closest("tr").find('td:eq(3)').text());
});
	
// DELETE===========================================
	$(document).on("click", ".btnRemove", function(event)
	{ 
	 $.ajax( 
	 { 
	 url : "OrderAPI", 
	 type : "DELETE", 
	 data : "id=" + $(this).data("oid"),
	 dataType : "text", 
	 complete : function(response, status) 
	 { 
	 onOrderDeleteComplete(response.responseText, status); 
	 } 
	 }); 
});
	// CLIENT-MODEL================================================================
	function validateOrderForm()
		{
		// Order Name
		if ($("#name").val().trim() == "")
		{
		return "Insert Order Name.";
		}
		// is string value
		var tmpoName = $("#name").val().trim();
		if ($.isNumeric(tmpoName))
		{
		return "Order Name cannot be just a value.";
		}
		// Category
		if ($("#category").val().trim() == "")
		{
		return "Insert Category.";
		}
		// is string value
		var tmpcat = $("#category").val().trim();
		if ($.isNumeric(tmpcat))
		{
		return "Category cannot be just a value.";
		}
		// Payment Method-------------------------------
		if ($("#paymethod").val().trim() == "")
		{
		return "Insert Payment Method.";
		}
		
		// Order Payment-------------------------------
		if ($("#payment").val().trim() == "")
		{
		return "Insert Order Payment.";
		}
		// is string value
		var tmppay = $("#payment").val().trim();
		if (!$.isNumeric(tmppay))
		{
		return "Insert payment in numeric values.";
		}
				return true;
	}
function onOrderSaveComplete(response, status)
	{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully saved."); 
	 $("#alertSuccess").show();
	 $("#divOrdersGrid").html(resultSet.data); 
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
	 $("#hidIdSave").val(""); 
	 $("#formOrder")[0].reset(); 
}

function onOrderDeleteComplete(response, status)
	{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully deleted."); 
	 $("#alertSuccess").show();
	 $("#divOrdersGrid").html(resultSet.data); 
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