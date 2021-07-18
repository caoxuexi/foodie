package com.caostudy.service.center;

import com.caostudy.pojo.Users;
import com.caostudy.pojo.bo.center.CenterUserBO;

/**
 * @author Cao Study
 * @description CenterUserService
 * @date 2021/7/6 21:02
 */
public interface CenterUserService {
    /**
     * 根据用户id查询用户信息
     *
     * @param userId
     * @return
     */
    public Users queryUserInfo(String userId);

    /**
     * 修改用户信息
     *
     * @param userId
     * @param centerUserBO
     */
    public Users updateUserInfo(String userId, CenterUserBO centerUserBO);

    /**
     * 用户头像更新
     *
     * @param userId
     * @param faceUrl
     * @return
     */
    public Users updateUserFace(String userId, String faceUrl);
}
