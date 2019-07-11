package sustain.token.Interceptor;

import com.zjydt.sustain.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    private  String keys="yidiantong.mima1";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;
        // 获取 HTTP HEAD 中的 TOKEN
        String token  = request.getHeader("token");
        System.out.println(token);
        Claims claims = JwtUtil.getClaims(keys, token);
        String username  = request.getHeader("username")==null?"":request.getHeader("username");
        // 校验 TOKEN
        flag = StringUtils.isNotBlank(token) ? JwtUtil.checkJWTByKey(token,keys) : false;
        // 如果校验未通过，返回 401 状态
        if (!flag){
            log.error("用户"+username+" token 验证失败");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }


        return flag;
    }
}
