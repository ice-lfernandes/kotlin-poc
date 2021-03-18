package br.com.iteris.kotlinpoc.controller

import br.com.iteris.kotlinpoc.model.Employee
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/employee")
class EmployeeController {

    @GetMapping("/{id}")
    fun getEmployeeById(@PathVariable id: String): ResponseEntity<Employee> {
        return ResponseEntity.notFound().build()
    }

    @GetMapping
    fun getEmployee(): ResponseEntity<List<Employee>> {
        return ResponseEntity.ok(listOf())
    }

}

