package com.tanay.wallet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import nonapi.io.github.classgraph.json.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
public class Wallet {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Balance")
    private  Double balance;
    @OneToMany(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY,mappedBy = "wallet",orphanRemoval = true)
    private List<Transaction> transactions;
    @PrePersist
    public void setBalance(){ this.balance = (double) 0; }
//    private List<Transaction> transactions;

//    @OneToMany(cascade =CascadeType.ALL, mappedBy ="Wallet")
//    @JoinColumn(name = "Transaction")
//    private List<Transaction> transactions;


}
