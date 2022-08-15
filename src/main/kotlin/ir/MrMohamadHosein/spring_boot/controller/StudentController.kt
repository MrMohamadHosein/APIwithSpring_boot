package ir.MrMohamadHosein.spring_boot.controller

import com.google.gson.Gson
import ir.MrMohamadHosein.spring_boot.model.Student
import ir.MrMohamadHosein.spring_boot.repository.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class StudentController {
    lateinit var studentRepository: StudentRepository

    @Autowired
    fun set_student_repository(studentRepo: StudentRepository) {
        studentRepository = studentRepo
    }

    @GetMapping("/student")
    fun getAllStudents(): ResponseEntity<MutableIterable<Student>> {

        val dataFromDatabase = studentRepository.findAll()
        return ResponseEntity.ok(dataFromDatabase)

    }

    @PostMapping("/student")
    fun insertStudent(@RequestBody data: String) {

        val gson = Gson()
        val newStudent = gson.fromJson<Student>(data, Student::class.java)

        studentRepository.save(newStudent)

    }


    @PutMapping("/student/updating{id}")
    fun updateStudent(
        @PathVariable("id") id: Long,
        @RequestBody data: String
    ) {

        val gson = Gson()
        val newStudent: Student = gson.fromJson(data, Student::class.java)

        val oldStudent: Student = studentRepository.findById(id).get()

        System.out.println("new Student :"+newStudent)
        System.out.println("old Student :" +oldStudent)

        oldStudent.name = newStudent.name
        oldStudent.course = newStudent.course
        oldStudent.score = newStudent.score

        studentRepository.save(oldStudent)


    }


    @DeleteMapping("/student/deleting{id}")
    fun deleteStudent(@PathVariable("id") id: Long) {
        studentRepository.deleteById(id)
    }


}