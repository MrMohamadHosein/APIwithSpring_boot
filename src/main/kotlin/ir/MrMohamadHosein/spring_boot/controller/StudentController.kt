package ir.MrMohamadHosein.spring_boot.controller

import com.google.gson.Gson
import ir.MrMohamadHosein.spring_boot.model.Student
import ir.MrMohamadHosein.spring_boot.repository.StudentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

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
    fun insertStudent(@RequestBody data: String): ResponseEntity<String> {

        val gson = Gson()
        val newStudent = gson.fromJson<Student>(data, Student::class.java)

        studentRepository.save(newStudent)

        return ResponseEntity.ok("success")
    }


    @PutMapping("/student/updating{name}")
    fun updateStudent(
            @PathVariable("name") name: String,
            @RequestBody data: String
    ): ResponseEntity<String> {

        val gson = Gson()
        val newStudent: Student = gson.fromJson(data, Student::class.java)

        studentRepository.save(newStudent)

        System.out.println(name)

        return ResponseEntity.ok("success")
    }


    @DeleteMapping("/student/deleting{name}")
    fun deleteStudent( @PathVariable("name") name:String ) : ResponseEntity<String> {

        studentRepository.deleteById(name)
        return ResponseEntity.ok(name)

    }


}