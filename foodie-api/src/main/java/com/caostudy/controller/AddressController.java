package com.caostudy.controller;

import com.caostudy.pojo.UserAddress;
import com.caostudy.pojo.bo.AddressBO;
import com.caostudy.pojo.vo.CategoryVO;
import com.caostudy.service.AddressService;
import com.caostudy.utils.CaoJSONResult;
import com.caostudy.utils.MobileEmailUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 曹学习
 * @description AddressController
 * @date 2020/11/9 9:08
 */
@Api(value = "地址相关", tags = {"地址相关的api接口"})
@RestController
@RequestMapping("address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "根据用户id查询收货地址列表", notes = "根据用户id查询收货地址列表", httpMethod = "POST")
    @PostMapping("/list")
    public CaoJSONResult list(
            @ApiParam(name = "UserId",value = "用户id",example = "201103C35YK6BXP0",required = true)
            @RequestParam String userId) {
        if(StringUtils.isBlank(userId)){
            return CaoJSONResult.errorMsg("用户id不能为空");
        }
        List<UserAddress> list = addressService.queryAll(userId);
        return CaoJSONResult.ok(list);
    }

    @ApiOperation(value = "用户新增地址", notes = "用户新增地址", httpMethod = "POST")
    @PostMapping("/add")
    public CaoJSONResult add(
            @ApiParam(name = "addressBO",value = "地址对象",required = true)
            @RequestBody AddressBO addressBO) {
        CaoJSONResult checkRes=checkAddress(addressBO);
        if(checkRes.getStatus()!=200){
            return checkRes;
        }
        addressService.addNewUserAddress(addressBO);
        return CaoJSONResult.ok();
    }

    //验证参数是否合法
    private CaoJSONResult checkAddress(AddressBO addressBO) {
        String receiver = addressBO.getReceiver();
        if (StringUtils.isBlank(receiver)) {
            return CaoJSONResult.errorMsg("收货人不能为空");
        }
        if (receiver.length() > 12) {
            return CaoJSONResult.errorMsg("收货人姓名不能太长");
        }

        String mobile = addressBO.getMobile();
        if (StringUtils.isBlank(mobile)) {
            return CaoJSONResult.errorMsg("收货人手机号不能为空");
        }
        if (mobile.length() != 11) {
            return CaoJSONResult.errorMsg("收货人手机号长度不正确");
        }
        boolean isMobileOk = MobileEmailUtils.checkMobileIsOk(mobile);
        if (!isMobileOk) {
            return CaoJSONResult.errorMsg("收货人手机号格式不正确");
        }

        String province = addressBO.getProvince();
        String city = addressBO.getCity();
        String district = addressBO.getDistrict();
        String detail = addressBO.getDetail();
        if (StringUtils.isBlank(province) ||
                StringUtils.isBlank(city) ||
                StringUtils.isBlank(district) ||
                StringUtils.isBlank(detail)) {
            return CaoJSONResult.errorMsg("收货地址信息不能为空");
        }
        return CaoJSONResult.ok();
    }
}
