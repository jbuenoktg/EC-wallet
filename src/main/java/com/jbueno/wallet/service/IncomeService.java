package com.jbueno.wallet.service;

import com.jbueno.wallet.dto.Income;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IncomeService {

    void addIncome(Long walletId, Income income);
    void removeIncome(Long walletId, Long incomeId);
    void updateIncome(Long walletId, Income income);
    List<Income> getIncomes(Long walletId);
    Income getIncome(Long walletId, Long incomeId);

}
