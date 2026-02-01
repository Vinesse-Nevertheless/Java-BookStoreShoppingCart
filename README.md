# 📚 Java Book Store: Session-Based Shopping Cart

### 📝 Project Overview
This project involved maintaining and extending a legacy Java Web Application. The primary goal was to implement missing CRUD functionality within a session-based shopping cart system.

### 🛠️ Technical Challenges & Solutions

#### 1. Session State Management
* **Challenge:** The original application could add items to a cart, but users had no way to adjust quantities or remove items without clearing the entire session.
* **Implementation:** Leveraged the `HttpSession` API to manage a collection of book objects, ensuring state persistence as the user navigated the storefront.



#### 2. Implementing Cart CRUD Operations
* **Update:** Developed logic to synchronize the quantity input from the UI with the backend session object.
* **Delete:** Implemented a removal trigger that identified specific book IDs within the session collection and updated the total cost dynamically.



### 🎓 Learning Outcomes (Apprenticeship Context)
This was a guided project completed during my software development apprenticeship (circa 2022). It served as a deep dive into:
* **Java Servlets & JSP:** Understanding the request-response cycle in a web environment.
* **Git Workflow:** Using branch-based development to implement modular features (Module 1-3).
* **Legacy Codebases:** Navigating and modifying code written by other developers.


<img width="980" height="840" alt="![Shopping Cart Update Sequence Diagram](./shopping-cart-sequence.png)" src="https://github.com/user-attachments/assets/0f6cd76a-9217-47f5-84e5-cc0ce04986b4" />


---
### 🔗 Related Modules
* **[Database Admin Module](https://github.com/Vinesse-Nevertheless/Java-BookStore):** Explore the persistence layer where the bookstore inventory is managed and stored permanently.
  
---
*Note: This project was completed as part of a Pluralsight Hands-on Lab to master Java Web fundamentals.*
