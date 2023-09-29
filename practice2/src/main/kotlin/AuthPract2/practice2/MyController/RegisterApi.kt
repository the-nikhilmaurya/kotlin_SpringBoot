package AuthPract2.practice2.MyController

import AuthPract2.practice2.Modal.AuthResponseDto
import AuthPract2.practice2.Modal.EmployeeTable
import AuthPract2.practice2.Modal.LoginDto
import AuthPract2.practice2.Security.JwtHelper
import AuthPract2.practice2.Services.EmployeeService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/public")
class RegisterApi(
    val service: EmployeeService,
    val authManager: AuthenticationManager,
    val passwordEncoder: PasswordEncoder,
    val jwtGenerator :JwtHelper
    ) {


    @GetMapping
    fun sayHello():String{
        return "`<h2>helloooo<h2>`"
    }

    @PostMapping("register")
    fun registerApi(@RequestBody createUser:EmployeeTable):ResponseEntity<String>{
        if(service.userExists(createUser.email))
        {
            return ResponseEntity.ok().body("already registered email:${createUser.email}")
        }
        val pass = createUser.password
        createUser.password = passwordEncoder.encode(createUser.password)
        println("new employee from register controller: $createUser")
        service.createEmployee(createUser)

        return ResponseEntity.ok().body("Employee created")
    }

    @PostMapping("login")
    fun loginApi(@RequestBody loginData:LoginDto):ResponseEntity<AuthResponseDto>{
//        println("logindata:$loginData")
        println(SecurityContextHolder.getContext())
        val   authentication:Authentication = authManager
            .authenticate(UsernamePasswordAuthenticationToken(loginData.email,loginData.password))

        SecurityContextHolder.getContext().authentication = authentication

        val token:String= jwtGenerator.generateToken(authentication)
//        println("from login api : ${jwtGenerator.getUserNameFromJwt(token)} email:${loginData.email}")

        return ResponseEntity.ok().body(AuthResponseDto(token,"Bearer"))
    }

}
