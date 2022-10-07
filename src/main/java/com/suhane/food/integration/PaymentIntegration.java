package com.suhane.food.integration;

import com.suhane.food.dto.Account;
import com.suhane.food.exception.FoodServiceException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class PaymentIntegration {

    private static final Logger log = LogManager.getLogger(PaymentIntegration.class);
    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "paymentService",fallbackMethod = "getBalanceServiceFallBackMethod")
    public Integer getAmountDetails(String accNum) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Account> entity = new HttpEntity<Account>(headers);
        try {
            return restTemplate.exchange("http://BANK-SERVICE/bank/account/balance?accnumber=" + accNum
                    , HttpMethod.GET, entity, Integer.class).getBody();
        } catch (Exception e) {
            log.info("failed to call bank service by getAmountDetails method: {}",e.getMessage());
            throw new FoodServiceException(e.getMessage());
        }

    }

    @CircuitBreaker(name = "paymentService",fallbackMethod = "paymentServiceFallBackMethod")
    public Account getPaymentStatus(String accNum, Integer amount) {
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Account> entity = new HttpEntity<Account>(headers);
        try {

            return restTemplate.exchange("http://BANK-SERVICE/bank/account/debit?accnumber=" + accNum + "&"
                    + "money=" + amount, HttpMethod.GET, entity, Account.class).getBody();
        } catch (Exception e) {
            log.info("failed to call bank service by getPaymentStatus: {}",e.getMessage());
            throw new FoodServiceException(e.getMessage());
        }
    }

    public Integer getBalanceServiceFallBackMethod(Exception e) {
        log.info("getBalanceServiceFallBackMethod ..");
        return 10 ;
    }

    public Account paymentServiceFallBackMethod(Exception e) {
        log.info("paymentServiceFallBackMethod ..");
        return new Account() ;
    }
}
