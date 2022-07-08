package com.hamitmizrak.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

//Lombok
@Data

//audit
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"created_date,update_date"})

//super class oldugu icin
@MappedSuperclass
abstract  public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employee_id",updatable = false)
    private Long employeeId;

    @CreatedBy
    @Column(name="created_by")
    private String createdBy;

    @CreatedDate
    @Column(name="created_date")
    private Date creationDate;

    @LastModifiedBy
    @Column(name="update_by")
    private String updateBy;

    @LastModifiedDate
    @Column(name="update_date")
    private Date updateDate;
}
