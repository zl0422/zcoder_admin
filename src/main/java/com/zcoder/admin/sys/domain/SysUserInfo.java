package com.zcoder.admin.sys.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户基本信息
 * Created by lin on 2016-05-24.
 */
@Entity
@Data
public class SysUserInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7713644849492389584L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserId;
    @Column
    private String realName;
    @Column
    private String phone;
    @Column
    private String email;
    @Column
    private SysUser createBy;
    @Column
    private Date createDate;
    @Column
    private String status;

}
