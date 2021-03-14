package com.trade.store.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@ToString
public class Trade{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String tradeId;

    @Column
    private int version;

    @Column
    private String counterPartyId;

    @Column
    private String bookId;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate maturityDate;

    @Column
    private LocalDate createdDate;

    @Column
    private String expiredFlag;

}
