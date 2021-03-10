package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;

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
    
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("ContactMe");
    FullEntity messageEntity =
    Entity.newBuilder(keyFactory.newKey())
        .set("Message", messageValue)
        .set("Email", emailValue)
        .build();
    datastore.put(messageEntity);
    
    response.sendRedirect("/index.html");
  }
}