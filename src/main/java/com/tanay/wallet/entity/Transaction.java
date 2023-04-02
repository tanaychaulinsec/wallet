package com.tanay.wallet.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Double amount;
    private String description;
    @Min(1)
    @Max(3)
    private int type;//1 for add, 2 for expense, 3 for transfer
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date transactionDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet_id",nullable = false)
    private Wallet wallet;

    @PrePersist
    public void setTransactionDate(){ this.transactionDate = new Date(); }
}
