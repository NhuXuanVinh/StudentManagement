package vn.edu.hust.activityexamples

import java.io.Serializable

data class StudentModel(var studentName: String, var studentId: String) : Serializable {
    override fun toString(): String {
        return "$studentName - $studentId"
    }
}
//
//data class StudentModel(
//    val id: Int,
//    val name: String,
//    val mssv: String
//) : Serializable
