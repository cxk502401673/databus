package sustain.token.controller;

import com.zjydt.sustain.common.entity.ApplyJwtData;
import com.zjydt.sustain.common.entity.DetailRes;
import com.zjydt.sustain.common.entity.TokenData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sustain.token.util.JwtUtil;
@Slf4j
@RestController
public class TokenController {

    @RequestMapping(value = "/getToken", method = RequestMethod.POST, consumes = "application/json")
    public DetailRes<String> getToken(@RequestBody ApplyJwtData applyJwtData){
        System.out.println("idea");
        String token=JwtUtil.buildJWT(applyJwtData.getClaim(),applyJwtData.getSub(),
                applyJwtData.getDuration());
        return new DetailRes(true, "操作成功 ",token);

    }


    @RequestMapping(value = "/checkToken", method = RequestMethod.POST, consumes = "application/json")
    public DetailRes<String> checkToken(@RequestBody TokenData tokenData){
        // 校验 TOKEN
       Boolean  flag = StringUtils.isNotBlank(tokenData.getClaimsJws()) ? JwtUtil.checkJWT(tokenData.getClaimsJws(),tokenData.getSub()) : false;
        // 如果校验未通过，返回 401 状态
        if (!flag){
            log.error("用户"+tokenData.getSub()+" token 验证失败");
            return new DetailRes(false, "验证失败 ");
        }else{
            return new DetailRes(true, "验证成功 ");
        }
    }
}
