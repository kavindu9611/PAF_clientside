package com;

import model.Payment; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Payment") 

public class PaymentService 
{ 
	Payment paymentobj = new Payment();
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	 { 
	 return paymentobj.readItems(); 
	 }
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertPayment(@FormParam("customer_n") String customer_n, 
	 @FormParam("amount") String amount, 
	 @FormParam("email") String email,
	 @FormParam("card_type") String card_type, 
	 @FormParam("card_no") String card_no, 
	 @FormParam("exp_month") String exp_month,
	 @FormParam("exp_year") String exp_year,
	 @FormParam("cvn") String cvn)
	
	{ 
	 String output = paymentobj.insertPayment(customer_n, amount, email, card_type, card_no, exp_month, exp_year, cvn); 
	return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updatePayment(String paymentData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject itemObject = new JsonParser().parse(paymentData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String payment_id  = itemObject.get("payment_id").getAsString(); 
	 String customer_n = itemObject.get("customer_n").getAsString(); 
	 String amount = itemObject.get("amount").getAsString(); 
	 String email = itemObject.get("email").getAsString(); 
	 String card_type = itemObject.get("card_type").getAsString();
	 String card_no = itemObject.get("card_no").getAsString();
	 String exp_month = itemObject.get("exp_month").getAsString();
	 String exp_year = itemObject.get("exp_year").getAsString();
	 String cvn = itemObject.get("cvn").getAsString();
	 String output = paymentobj.updatePayment(payment_id, customer_n, amount, email, card_type, card_no, exp_month, exp_year, cvn); 
	return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deletePayment(String paymentData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String payment_id = doc.select("payment_id").text(); 
	 String output = paymentobj.deletePayment(payment_id);
	 return output; 
	}
		
}
