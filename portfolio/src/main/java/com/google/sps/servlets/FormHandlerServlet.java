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

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Sanitize user input to remove Html tags and JavaScript
    String firstnameValue = Jsoup.clean(request.getParameter("firstname-input"),Whitelist.none());
    String lastnameValue = Jsoup.clean(request.getParameter("lastname-input"),Whitelist.none());
    String emailValue = Jsoup.clean(request.getParameter("email"), Whitelist.none());
    String messageValue = Jsoup.clean(request.getParameter("message-input"),Whitelist.none());
    
  
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Message");
    FullEntity messageEntity =
    Entity.newBuilder(keyFactory.newKey())
        .set("firstname", firstnameValue)
        .set("lastname", lastnameValue)
        .set("email", emailValue)
        .set("message", messageValue)
        .build();
    
    datastore.put(messageEntity);

    response.sendRedirect("/index.html");
  }

  
}