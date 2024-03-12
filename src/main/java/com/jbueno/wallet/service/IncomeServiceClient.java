package com.jbueno.wallet.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbueno.wallet.config.IncomeClient;
import com.jbueno.wallet.dto.Income;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class IncomeServiceClient implements IncomeService {

    @Autowired
    private IncomeClient incomeClient;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public void addIncome(Long walletId, Income income) {
        // TODO Auto-generated method stub
    }

    @Override
    public void removeIncome(Long walletId, Long incomeId) {
        // TODO Auto-generated method stub
    }

    @Override
    public void updateIncome(Long walletId, Income income) {
        // TODO Auto-generated method stub
    }

    @Override
    public List<Income> getIncomes(Long walletId) {

        //TODO: Change this once IncomesService save the WalletID and it's in docker

        WebClient build = webClientBuilder.clientConnector(new ReactorClientHttpConnector(this.incomeClient.getClient()))
                .baseUrl("http://localhost:8011/incomes")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8011/incomes"))
                .build();
        JsonNode block = build.method(HttpMethod.GET).uri("/" )
                .retrieve().bodyToMono(JsonNode.class).block();

        ArrayList<Income> incomes = new ObjectMapper().convertValue(block, ArrayList.class);

        return incomes;
    }

    @Override
    public Income getIncome(Long walletId, Long incomeId) {
        // TODO Auto-generated method stub
        return null;
    }
}
