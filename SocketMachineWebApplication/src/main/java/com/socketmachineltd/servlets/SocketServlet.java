package com.socketmachineltd.servlets;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class SocketServlet
 */
@WebServlet("/socketQuote")
public class SocketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SocketServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    String socketType,tempQuantity="",custName,custEmail;
    Integer finalQuantity;
    Double pricePerUnit=0.0,finalQuotePrice=0.0;
    Boolean quantityCheck=true;
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		//fetching input data from the form
		socketType=request.getParameter("socketType");
		tempQuantity=request.getParameter("quantity");
		custName=request.getParameter("custName");
		custEmail=request.getParameter("custEmail");
		
		

		// now we check quantity is valid or not
		try {
			

			finalQuantity=Integer.parseInt(tempQuantity);
			quantityCheck=true;
		}catch(NumberFormatException nfe) {

			quantityCheck=false;
		}
		
		
		 if(quantityCheck && finalQuantity > 0) {
			 
			 if(socketType.equals("Type A"))
				 pricePerUnit=5.0;
			 else if(socketType.equals("Type B"))
				 pricePerUnit=7.5;
			 else if(socketType.equals("Type C"))
				 pricePerUnit=10.5;
			 else pricePerUnit=0.0;
			 

			 //now calculating final quote price
			 finalQuotePrice=finalQuantity * pricePerUnit;
		}
		 
		
		 
		 //now we are trying to build output in response if all input details are correct and if not displaying error message
		 StringBuilder responseBody=new StringBuilder();
		 responseBody.append("<html><body>");
		 responseBody.append("<h1>THE FINAL QUOTE DETAILS ARE AS FOLLOWS</h1>");
		 
		 //now validating if all data is correct and building response
		 if(quantityCheck && pricePerUnit > 0) {
		 responseBody.append("<p>Selected Socket Type is: ").append(socketType).append("</p>");
		 responseBody.append("<p>Final Quantity Ordered: ").append(tempQuantity).append("</p>");
		 responseBody.append("<p>Price per each socket ordered: $").append(pricePerUnit).append("</p>");
		 responseBody.append("<p><strong>Total Quote Price: $").append(finalQuotePrice).append("</strong></p>");
		 responseBody.append("<p>Thank you, ").append(custName).append(" for ordering with us.</p>");
		 }else {
			 responseBody.append("<p>Error occured: Please , Enter valid  quantity or socket type and try again later.</p>");

		 }
		 
		 responseBody.append("</body></html>");
		 
		 
	     response.getWriter().write(responseBody.toString());
	     


		 
		 
		
		
	}

}
