package com.example.servlet;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.example.model.AttendanceRecord;
import com.example.storage.DataStorage;

// @WebServlet("/attendanceRecords/*")

public class AttendanceRecordServlet extends HttpServlet {

    
    
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("application/json");
    Gson gson = new Gson();
    String pathInfo = req.getPathInfo(); // e.g. /date/2025-07-28 or /student/1

    if (pathInfo != null && !pathInfo.equals("/")) {
        String[] parts = pathInfo.substring(1).split("/");

        if (parts.length == 2) {
            String filterType = parts[0];
            String value = parts[1];

            if (filterType.equals("date")) {
                List<AttendanceRecord> records = DataStorage.getAttendanceRecordsByDate(value);
                resp.getWriter().write(gson.toJson(records));
                return;
            } else if (filterType.equals("student")) {
                List<AttendanceRecord> records = DataStorage.getAttendanceRecordsByStudentId(value);
                resp.getWriter().write(gson.toJson(records));
                return;
            }
        }
    }

    String json = gson.toJson(DataStorage.attendanceRecords.values());
    resp.getWriter().write(json);
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




