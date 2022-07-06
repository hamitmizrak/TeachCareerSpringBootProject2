package com.hamitmizrak.data.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class EmployeeEntity extends BaseEntity {
    private String employeeName;
    private String employeeSurname;
}
