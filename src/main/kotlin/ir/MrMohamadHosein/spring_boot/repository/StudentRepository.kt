package ir.MrMohamadHosein.spring_boot.repository

import ir.MrMohamadHosein.spring_boot.model.Student
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : CrudRepository<Student , String> {


}