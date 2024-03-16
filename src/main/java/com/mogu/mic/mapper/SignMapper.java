package com.mogu.mic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mogu.mic.entity.SignEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @BelongsProject: AutuSignMoguDing
 * @BelongsPackage: com.mogu.mic.mapper
 * @Author: mi
 * @CreateTime: 2023/8/27 20:03
 * @Description:
 * @Version: 1.0
 */


@Mapper
public interface SignMapper extends BaseMapper<SignEntity> {

    List<SignEntity> getAllUsers();

    SignEntity selectById(@Param("id") String id);

    Integer selectCount();

    List<String> listByEmailList();

    SignEntity getAllState(@Param("id") Integer id);
}
