package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the values entered in the form.
    String messageValue = request.getParameter("message-input");
    String emailValue = request.getParameter("email");
    // Print the values so you can see it in the server logs.
    System.out.println("Email submitted: " + emailValue);
    System.out.println("Message submitted: " + messageValue);


    // Write the value to the response so the user can see it.
    response.getWriter().println("You submitted this email: " + emailValue);
    response.getWriter().println("You submitted this message: " + messageValue);
  }
}