package hatecode._0001_0999;
public class StudentAttendanceRecordI {
/*
551. Student Attendance Record I
You are given a string representing an attendance record for a student. The record only contains the following three characters:
'A' : Absent.
'L' : Late.
'P' : Present.
A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

You need to return whether the student could be rewarded according to his attendance record.

Example 1:
Input: "PPALLP"
Output: True
*/
    public boolean checkRecord(String s) {
        if (s == null || s.length() < 1 || s.indexOf("LLL") >=0) return false;
        int first = s.indexOf("A");
        if (first >= 0) {
            if (s.indexOf("A", first + 1) >=0) return false;
        }
        
        return true;
    }
}