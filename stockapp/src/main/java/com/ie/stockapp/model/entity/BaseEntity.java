package com.ie.stockapp.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@ToString
public abstract class BaseEntity {

    @Column(name="createDate")
    private LocalDateTime createDate;

    @Column(name="createdBy")
    private String createdBy;

    @Column(name="lastChangeDate")
    private LocalDateTime lastChangeDate;

    @Column(name="lastChangedBy")
    private Timestamp lastChangedBy;
}
