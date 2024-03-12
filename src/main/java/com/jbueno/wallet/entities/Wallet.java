package com.jbueno.wallet.entities;

import com.jbueno.wallet.dto.Income;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ElementCollection
    @Transient
    private List<Income> incomes;

}
