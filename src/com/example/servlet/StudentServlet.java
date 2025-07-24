package com.example.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import com.example.model.Student;
import com.example.storage.DataStorage;

@WebServlet("/students/*")

public class StudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            String json = new Gson().toJson(DataStorage.students.values());
            resp.getWriter().write(json);

        } else {
            try {
                int id = Integer.parseInt(pathInfo.substring(1));
                Student student = DataStorage.students.get(id);
                if (student != null) {
                    resp.getWriter().write(new Gson().toJson(student));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }

            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = new Gson().fromJson(req.getReader(), Student.class);
        int id = DataStorage.studentIdCounter++;
        student.setId(id);
        DataStorage.students.put(id, student);
        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.getWriter().write("Welcome Student with ID: " + id);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int id = Integer.parseInt(pathInfo.substring(1));
        Student updatedStudent = new Gson().fromJson(req.getReader(), Student.class);
        if (DataStorage.students.containsKey(id)) {
            updatedStudent.setId(id);
            DataStorage.students.put(id, updatedStudent);
            resp.getWriter().write("Updated Student");
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        int id = Integer.parseInt(pathInfo.substring(1));
        if (DataStorage.students.remove(id) != null) {
            resp.getWriter().write("Student Deleted Succesfully");
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
