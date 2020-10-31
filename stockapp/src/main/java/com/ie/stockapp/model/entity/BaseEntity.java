package com.ie.stockapp.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@ToString
public abstract class BaseEntity {

    @Column(name = "CREATEDATE")
    private LocalDateTime createDate;

    @Column(name = "CREATEDBY")
    private String createdBy;

    @Column(name = "LASTCHANGEDATE")
    private LocalDateTime lastChangeDate;

    @Column(name = "LASTCHANGEDBY")
    private String lastChangedBy;
}
