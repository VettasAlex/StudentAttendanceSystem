package com.example.storage;

import java.util.HashMap;
import java.util.Map;

import com.example.model.AttendanceRecord;
import com.example.model.Course;
import com.example.model.Student;

public class DataStorage {
   public static Map<Integer, Student> students = new HashMap<>();
   public static Map<Integer, Course> courses = new HashMap<>();
   public static Map<Integer, AttendanceRecord> attendanceRecords = new HashMap<>();
   
   public static int studentIdCounter = 1;
   public static int courseIdCounter = 1;
   public static int attendanceIdCounter =1;
}
