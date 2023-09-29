package AuthPract2.practice2.Security


import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.Date


@Component
class JwtHelper(private val constant: SecurityConstant) {

    private val  key = Keys.hmacShaKeyFor(constant.secretKey.toByteArray())
    fun generateToken(authentication: Authentication):String{
//        println("From token generator")
        val userName = authentication.name
//        println("username : $userName   authentication: $authentication"  )
        val currentDate = Date()
//        println("current-date : $currentDate")
        val expiryDate = Date(currentDate.time + constant.jwtExpiration)
        println("current time: ${currentDate}  expiry : ${expiryDate}")


     val token  = Jwts.builder()
         .setSubject(userName)
         .setIssuedAt(currentDate)
         .setExpiration(expiryDate)
         .signWith(key,SignatureAlgorithm.HS256)
         .compact()
//        println("token : $token")
        return token
    }

    fun getUserNameFromJwt(token:String):String{
//        println("from get username from jwt")
        val claims : Claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
//        println("username from token : ${claims.subject}")
        return claims.subject

    }

    fun validateToken(token:String):Boolean{
//        println("From validation token")
        return try {
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
            println("valid token")
            true
        }catch (e:Exception){
            println("invalid token")
            false
        }

    }
}