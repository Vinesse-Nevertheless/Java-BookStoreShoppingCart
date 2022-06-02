package com.pluralsight;

import static org.mockito.ArgumentMatchers.intThat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.inject.Inject;
/**
 * Servlet implementation class HelloWorld
 */

public class CartController extends HttpServlet {
		private static final long serialVersionUID = 1L;
		private DBConnection dbConnection;

		@Inject
    private BookDAO bookDAO;

    public void init() {
			dbConnection = new DBConnection();
			bookDAO = new BookDAO(dbConnection.getConnection());
    }

		public void destroy() {
			dbConnection.disconnect();
		}

    public CartController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		// The requested URL path
		String action = request.getPathInfo();

		// Do different things depending on the action (or path requested)
		try {
			switch(action) {
				case "/addcart":
					 addToCart(request, response);
			    case "/delete":
				     deleteFromCart(request, response);
				case "/update":
					     updateCart(request, response);
           break;
        default:
           break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect("../ShoppingCart.jsp");
	}

  private void updateCart(HttpServletRequest request, HttpServletResponse response) 
		  throws ServletException, IOException{
	   HttpSession session = request.getSession();
	   String idxStr = request.getParameter("index");
	   int index = Integer.parseInt(idxStr);
	   String quantityStr = request.getParameter("quantity");
	   int quantity = Integer.parseInt(quantityStr);

	  
	   ShoppingCart objCartBean = (ShoppingCart) session.getAttribute("cart");
	   
	// Update this item and quantity to the ShoppingCart
	   objCartBean.updateCartItem(index, quantity);
		
	}

private void deleteFromCart(HttpServletRequest request, HttpServletResponse response) 
  throws ServletException, IOException{
	   HttpSession session = request.getSession();
	   String idxStr = request.getParameter("index");
	   int index = Integer.parseInt(idxStr);
/*
  The Session should already have a "cart" attribute saved to it from 
  when the item was originally added to the cart (see the addToCart()
  method). We want to get that ShoppingCart and store it in a variable,
  so use session’s getAttribute() method with the key "cart" as a 
  parameter and cast the result to a ShoppingCart object.
  
  getAttribute("cart") gets a shoppingcart object to use to call delete method on
 */
	   ShoppingCart objCartBean = (ShoppingCart)session.getAttribute("cart");

		 // Delete this item and quantity to the ShoppingCart
	   objCartBean.deleteCartItem(index);
	  }
	  
		

protected void addToCart(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
   HttpSession session = request.getSession();
   String idStr = request.getParameter("id");
   int id = Integer.parseInt(idStr);
   String quantityStr = request.getParameter("quantity");
   int quantity = Integer.parseInt(quantityStr);

	 // Get the book from the database
   Book existingBook = bookDAO.getBook(id);

	 // Check if a ShoppingCart exists in the Session variable
	 // If not create one
   ShoppingCart shoppingCart = null;
   Object objCartBean = session.getAttribute("cart");

   if(objCartBean!=null) {
    shoppingCart = (ShoppingCart) objCartBean ;
   } else {
    shoppingCart = new ShoppingCart();
    session.setAttribute("cart", shoppingCart);
   }

	 // Add this item and quantity to the ShoppingCart
   shoppingCart.addCartItem(existingBook, quantity);
  }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
