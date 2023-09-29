package AuthPract2.practice2.Security


import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class AuthenticationEntryPoint : AuthenticationEntryPoint {


    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: org.springframework.security.core.AuthenticationException
    ) {

        println("from entryPoint response: $response \n request: $request")
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,authException.message)
    }
}