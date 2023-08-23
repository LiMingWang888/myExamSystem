package com.wlm.exam.form;

import lombok.Data;

/**
 * @author 11208
 */
@Data
public class ModifyUserFormVO {
    private String id;

    private String nickName;

    private String oldPassword;

    private String newPassword;

    private String renewPassword;
}
