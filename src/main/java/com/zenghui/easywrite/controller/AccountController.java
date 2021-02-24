package com.zenghui.easywrite.controller;

import com.zenghui.easywrite.common.api.ApiResult;
import com.zenghui.easywrite.dto.LoginDTO;
import com.zenghui.easywrite.dto.RegisterDTO;
import com.zenghui.easywrite.entity.staff.ComStaff;
import com.zenghui.easywrite.service.ComStaffService;
import io.swagger.annotations.Api;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author HUT-zenghui
 */
@Api(tags = "账户接口")
@RestController
@RequestMapping("/account")
public class AccountController {
    @Resource
    private ComStaffService comStaffService;

    private static final String STAFF = "2";
    private static final String ADMIN = "1";

    @PostMapping("/register")
    public ApiResult<Map<String, Object>> register(@Valid @RequestBody RegisterDTO dto) {
        ComStaff user = comStaffService.executeRegister(dto);
        if (ObjectUtils.isEmpty(user)) {
            return ApiResult.failed("账号注册失败");
        }
        Map<String, Object> map = new HashMap<>(16);
        map.put("user", user);
        return ApiResult.success(map);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResult<Map<String, String>> login(@Valid @RequestBody LoginDTO dto) {
        String token = comStaffService.executeLogin(dto);
        if (ObjectUtils.isEmpty(token)) {
            return ApiResult.failed("账号密码错误");
        }
        Map<String, String> map = new HashMap<>(16);
        map.put("token", token);
        return ApiResult.success(map, "登录成功");
    }

    /**
     * 根据传过来的token获取用户信息
     * 这地方用了一个拦截器，将前端传递的Token 通过请求拦截器通过Token计算得到 USER_NAME,LEVEL
     * 并放入Header中
     * @return
     */
    @GetMapping("/info")
    public ApiResult<ComStaff> getUser(@RequestHeader(value = "USER_NAME") String username ){
        ComStaff data = comStaffService.getUserByUsername(username);
        return ApiResult.success(data);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ApiResult<Object> logOut() {
        return ApiResult.success(null, "注销成功");
    }


}
