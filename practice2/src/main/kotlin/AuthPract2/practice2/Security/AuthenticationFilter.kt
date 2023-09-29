package AuthPract2.practice2.Security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthenticationFilter(
    val jwtHelper: JwtHelper,
    val customerUserDetails: CustomUserDetails
) : OncePerRequestFilter() {


    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

            val token:String? = getJwtFromRequest(request)
//            println("from JwtAuthenticationFilter of oncePerRequestFilter token : $token")
//        println(request.getHeader("Authorization"))
            if ( (token != null) && jwtHelper.validateToken(token) ){
//                println("from auth filter")
                val username:String = jwtHelper.getUserNameFromJwt(token)
//                println("username: $username ....from auth filter")
                val userDetails: UserDetails = customerUserDetails.loadUserByUsername(username)
//                println("userDetails: $userDetails    ....from auth filter")
                val authenticationToken = UsernamePasswordAuthenticationToken(userDetails.username,userDetails.password,userDetails.authorities,)
                authenticationToken.details= WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authenticationToken

            }
            filterChain.doFilter(request,response)
    }

    private fun getJwtFromRequest(request: HttpServletRequest): String? {

        val bearertoken:String? = request.getHeader("Authorization")
        println("BearerToken : $bearertoken")
        if(bearertoken !=null && bearertoken.startsWith("Bearer")){
            println("Token : ${bearertoken.substring(7)}")
            return bearertoken.substring(7)
        }
        return  null

    }
}