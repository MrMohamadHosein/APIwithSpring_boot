package ir.MrMohamadHosein.spring_boot.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Student(

        @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
        val id:Long,

        var name: String,
        var course: String,
        var score: Int

) {
        constructor() :this(-1,"" , "" , -1)
}