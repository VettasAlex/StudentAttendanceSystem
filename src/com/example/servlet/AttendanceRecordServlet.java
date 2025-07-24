package com.example.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.example.model.AttendanceRecord;
import com.example.model.Course;
import com.example.model.Student;
import com.example.storage.DataStorage;

@WebServlet("/attendanceRecords/*")

public class AttendanceRecordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pathInfo = req.getPathInfo();
        resp.setContentType("application/json"); //Fix For Sending JSON

        if (pathInfo == null || pathInfo.equals("/")) {
            String json = new Gson().toJson(DataStorage.attendanceRecords.values());
            resp.getWriter().write(json);

        } else { 
            try {
                int id = Integer.parseInt(pathInfo.substring(1));
                AttendanceRecord attendanceRecord = DataStorage.attendanceRecords.get(id);
                if(attendanceRecord != null) {
                    resp.getWriter().write(new Gson().toJson(attendanceRecord));
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
        AttendanceRecord attendanceRecord = new Gson().fromJson(req.getReader(), AttendanceRecord.class);
        int id = DataStorage.attendanceRecordIdCounter++;
        attendanceRecord.setId(id);
        DataStorage.attendanceRecords.put(id, attendanceRecord);
        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.getWriter().write("Attendance Record " + id + " was succesfully registered");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int id = Integer.parseInt(pathInfo.substring(1));
        AttendanceRecord updatedAttendanceRecord = new Gson().fromJson(req.getReader(), AttendanceRecord.class);
        if (DataStorage.attendanceRecords.containsKey(id)) {
            updatedAttendanceRecord.setId(id);
            DataStorage.attendanceRecords.put(id, updatedAttendanceRecord);
            resp.getWriter().write("Attendance Record Updated Succesfully");
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
        if (DataStorage.attendanceRecords.remove(id) != null) {
            resp.getWriter().write("Attendance Record Removed from System Succesfully");
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
 }




