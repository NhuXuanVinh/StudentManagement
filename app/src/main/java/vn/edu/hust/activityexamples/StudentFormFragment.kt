package vn.edu.hust.activityexamples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import android.widget.Toast

class StudentFormFragment : Fragment() {

    private val args: StudentFormFragmentArgs by navArgs() // Lấy tham số truyền vào Fragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_form, container, false)

        // Liên kết các View từ XML
        val editName: EditText = view.findViewById(R.id.editName)
        val editMSSV: EditText = view.findViewById(R.id.editMSSV)
        val buttonOk: Button = view.findViewById(R.id.button_ok)
        val buttonCancel: Button = view.findViewById(R.id.button_cancel)

        // Hiển thị thông tin nếu là chế độ chỉnh sửa
        args.student?.let { student ->
            editName.setText(student.studentName)
            editMSSV.setText(student.studentId)
        }

        // Nút OK để lưu thông tin sinh viên
        buttonOk.setOnClickListener {
            val name = editName.text.toString().trim()
            val mssv = editMSSV.text.toString().trim()

            if (name.isEmpty() || mssv.isEmpty()) {
                Toast.makeText(requireContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
            } else {
                val updatedStudent = StudentModel(name, mssv)

                // Gửi dữ liệu trở về Fragment danh sách
                findNavController().previousBackStackEntry?.savedStateHandle?.set(
                    "updatedStudent", updatedStudent
                )

                // Quay lại màn hình trước
                findNavController().navigateUp()
            }
        }

        // Nút Cancel để hủy và quay lại
        buttonCancel.setOnClickListener {
            findNavController().navigateUp()
        }

        return view
    }
}


