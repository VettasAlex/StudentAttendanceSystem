package com.example.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.example.model.Course;
import com.example.storage.DataStorage;

// @WebServlet("/courses/*")

public class CourseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            String json = new Gson().toJson(DataStorage.courses.values());
            resp.getWriter().write(json);

        } else { 
            try {
                int courseId = Integer.parseInt(pathInfo.substring(1));
                Course course = DataStorage.courses.get(courseId);
                if(course != null) {
                    resp.getWriter().write(new Gson().toJson(course));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }

            } catch (NumberFormatException e){
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Course course = new Gson().fromJson(req.getReader(), Course.class);
        int courseId = DataStorage.courseIdCounter++;
        course.setCourseId(courseId);
        DataStorage.courses.put(courseId, course);
        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.getWriter().write("Course " + courseId + " was succesfully created");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int courseId = Integer.parseInt(pathInfo.substring(1));
        Course updatedCourse = new Gson().fromJson(req.getReader(), Course.class);
        if (DataStorage.courses.containsKey(courseId)) {
            updatedCourse.setCourseId(courseId);
            DataStorage.courses.put(courseId, updatedCourse);
            resp.getWriter().write("Course Updated Succesfully");
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
        int courseId = Integer.parseInt(pathInfo.substring(1));
        if (DataStorage.courses.remove(courseId) != null) {
            resp.getWriter().write("Course Deleted Succesfully");
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
 }




