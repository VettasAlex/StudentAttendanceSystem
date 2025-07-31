package com.example.storage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.model.AttendanceRecord;
import com.example.model.Course;
import com.example.model.Student;

public class DataStorage {
   public static Map<Integer, Student> students = new HashMap<>();
   public static Map<Integer, Course> courses = new HashMap<>();
   public static Map<Integer, AttendanceRecord> attendanceRecords = new HashMap<>();

   public static int studentIdCounter = 1;
   public static int courseIdCounter = 1;
   public static int attendanceRecordIdCounter = 1;

   public static List<AttendanceRecord> getAttendanceRecordsByStudentId(String studentId) {
    int id = Integer.parseInt(studentId);
    return attendanceRecords.values().stream()
        .filter(r -> r.getStudentId() == id)
        .collect(Collectors.toList());
}


   public static List<AttendanceRecord> getAttendanceRecordsByDate(String date) {
      return attendanceRecords.values().stream()
            .filter(r -> r.getDate().equals(date))
            .collect(Collectors.toList());
   }
}

// {
// "fullName":"XX",
// "email":"xx@gmail.com",
// "attendanceRecords": "[1,2,3]"
// }

// {
// "courseName":"XX",
// "courseInstructor": "XX"
// }

// {
//   "studentId": "1",
//   "courseId": "1",
//   "date": "2025-07-28",
//   "present": true
// }
