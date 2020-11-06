package com.caostudy.mapper;

import com.caostudy.pojo.vo.ItemCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsMapperCostume {
    public List<ItemCommentVO> queryItemComments(
            @Param("paramsMap") Map<String, Object> map);
}