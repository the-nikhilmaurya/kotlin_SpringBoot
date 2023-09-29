package AuthPract2.practice2.Security

import org.springframework.stereotype.Component

@Component
data class SecurityConstant (
    val jwtExpiration:Int = 200000,
    val secretKey:String = "secretkey-ytrbvrfdrvergergerveegvddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
)