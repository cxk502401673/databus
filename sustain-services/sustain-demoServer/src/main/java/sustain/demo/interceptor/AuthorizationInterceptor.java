//package sustain.demo.interceptor;
//
//import com.zjydt.sustain.common.constants.CommonConstants;
//import com.zjydt.sustain.common.util.IPUtil;
//import com.zjydt.sustain.common.util.JwtUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//@Slf4j
//public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
//
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        boolean flag = false;
//
//        String token  = request.getHeader("token");
//        String ip= IPUtil.getIpAddress(request);
//        if(StringUtils.isBlank(token)){
//
//            log.error("token 为空 ，IP："+ip);
//            return false;
//        }
//        System.out.println(token);
//        System.out.println(ip);
//        // 校验 TOKEN
//        flag = StringUtils.isNotBlank(token) ? JwtUtil.checkJWTByKey(token, CommonConstants.Key) : false;
//        // 如果校验未通过，返回 401 状态
//        if (!flag){
//            log.error("  token 验证失败");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        }
//        return flag;
//    }
//
//
//
//}
