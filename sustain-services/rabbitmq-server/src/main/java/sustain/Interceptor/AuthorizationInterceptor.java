package sustain.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import sustain.util.RedisUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
@Slf4j
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private RedisUtil redisUtil;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private JdbcTemplate jdbcTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;
//        String username = request.getHeader("username");
//        Object o = redisUtil.get(username);
//        if (o == null) {
//            List<rules> results = jdbcTemplate.query("select * from t_rules  where valid = 1 and username= /'"+username+"/'", new
//                    BeanPropertyRowMapper<>(rules.class));
//        }else{
//
//        }

//        // 获取 HTTP HEAD 中的 TOKEN
//        String token  = request.getHeader("token");
//        System.out.println(token);
//        // 校验 TOKEN
//        flag = StringUtils.isNotBlank(token) ? JwtUtil.checkJWTByKey(token,keys) : false;
//        // 如果校验未通过，返回 401 状态
//        if (!flag){
//            //log.error("用户"+username+" token 验证失败");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        }
        return flag;
    }


    public static class rules{
        private String id;
        private String username;
        private String serverName;
        private String valid;
        private Date create_time;
        private Date update_time;
    }
}
