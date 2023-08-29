package com.wlm.exam.config;

import com.wlm.exam.vo.UserFormVO;
import com.wlm.exam.pojo.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wlm
 * @date 2023/8/23 - 10:58
 */

@Mapper
public interface MsMapper {

    MsMapper INSTANCE = Mappers.getMapper(MsMapper.class);

    User userFormToUser(UserFormVO userForm);

}
