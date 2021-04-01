package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.gson.Gson;
import com.google.sps.data.Message;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet responsible for loading messages. */
@WebServlet("/load-messages")
public class ListMessagesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    //Create Query instance
        Query<Entity> query =
            Query.newEntityQueryBuilder().setKind("Message").setOrderBy(OrderBy.desc("timestamp")).build();
        QueryResults<Entity> results = datastore.run(query);
        
        List<Message> messages = new ArrayList<>();
        while (results.hasNext()) {
            Entity entity = results.next();

            long id = entity.getKey().getId();
            String firstname = entity.getString("firstname");
            String lastname = entity.getString("lastname");
            String email = entity.getString("email");
            String message = entity.getString("message");
            long timestamp = entity.getLong("timestamp");
            
            Message msg= new Message(id, firstname, lastname, email, message, timestamp);
            messages.add(msg); 
        }

        Gson gson = new Gson();

        response.setContentType("application/json;");
        response.getWriter().println(gson.toJson(messages));
    }
}