package com.zcoder.admin.core.common;

import com.zcoder.admin.sys.domain.SysUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lin on 2016-05-24.
 */
public class BaseEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5818466185323226074L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected Date createDate;

    @Column(nullable = false)
    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="user_id")
    protected SysUser createBy;



    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public SysUser getCreateBy() {
        return createBy;
    }

    public void setCreateBy(SysUser createBy) {
        this.createBy = createBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




}
