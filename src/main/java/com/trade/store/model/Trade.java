package com.trade.store.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@ToString
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
