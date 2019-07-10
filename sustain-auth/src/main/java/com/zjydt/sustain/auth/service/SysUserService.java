package com.zjydt.sustain.auth.service;

import com.zjydt.sustain.auth.service.fallback.SysUserServiceFallback;
import com.zjydt.sustain.common.entity.SysUser;
import com.zjydt.sustain.common.vo.SysUserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * feign 调用服务
 * </p>
 *
 * @author lee_bw
 * @since 2018-10-08
 */
@FeignClient(name = "sustain-user-service", fallback = SysUserServiceFallback.class)

public interface SysUserService {

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    @GetMapping("/user/loadUserByUsername/{username}")
    SysUserVo loadUserByUsername(@PathVariable(value = "username") String username);

    /**
     * 通过mobile查找用户
     * @param mobile
     * @return
     */
    @GetMapping("/user/loadUserByMobile/{mobile}")
    SysUserVo loadUserByMobile(@PathVariable(value = "mobile") String mobile);

}
