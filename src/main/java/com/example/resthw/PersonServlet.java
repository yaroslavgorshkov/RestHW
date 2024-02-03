package com.example.resthw;

import java.io.*;
import java.util.List;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/persons/*")
public class PersonServlet extends HttpServlet {
    private final ObjectMapper mapper = new ObjectMapper();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String contextPath = request.getPathInfo();
        System.out.println(contextPath);

        if (contextPath != null && contextPath.length() > 1) {
            String[] pathSegments = contextPath.split("/");
            if (pathSegments.length == 2) {
                try {
                    int personId = Integer.parseInt(pathSegments[1]);
                    Person person = PersonService.get(personId);

                    if (person != null) {
                        response.setContentType("application/json");
                        String json = mapper.writeValueAsString(person);
                        response.getWriter().println(json);
                    }
                } catch (NumberFormatException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid person ID");
                }
            }
        } else {
            response.setContentType("application/json");
            List<Person> personList = PersonService.getAll();
            String json = mapper.writeValueAsString(personList);
            response.getWriter().println(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String json = sb.toString();
        Person person = mapper.readValue(json, Person.class);
        PersonService.save(person);
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        String contextPath = request.getPathInfo();
        System.out.println(contextPath);

        if (contextPath != null && contextPath.length() > 1) {
            String[] pathSegments = contextPath.split("/");
            if (pathSegments.length == 2) {
                int personId = Integer.parseInt(pathSegments[1]);
                PersonService.delete(personId);
            }
        } else {
            PersonService.deleteAll();
        }
    }

}