package com.google.sps.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns HTML that contains a hardcoded string. */
@WebServlet("/mystring")
public class HardCodedStringServlet extends HttpServlet {
    
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    List<String> messages = new ArrayList<String>();
    //add messages to arraylist messages
    messages.add("I'm a golden state warriors fan!");
    messages.add("My favourite show is HTGAWM");
    messages.add("I play basketball");
    messages.add("I started playing piano in 2020");
    messages.add("God's grace keeps me going");

    //convert the messages to json
    String messageJson = convertToJson(messages);
    response.setContentType("application/json;");
    response.getWriter().println(messageJson);
    
  }
/**Convert List messages into Json string */
  private String convertToJson(List<String> messages){
        String json = "[";
        for(int i=0; i< messages.size(); i++){
            String currentMessage = messages.get(i);
                if(i == messages.size()-1){
                    return json += "{" + "\"message\": " + "\"" + currentMessage + "\"" + "}" + "] ";
            }   
        json += "{" + "\"message\": " + "\"" + currentMessage + "\"" + "}" + ", ";  
        }
        return json;
  }
}
