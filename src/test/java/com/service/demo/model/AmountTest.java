package com.service.demo.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AmountTest {

    private Amount amount;

    @Before
    public void setUp() throws Exception {
        this.amount = new Amount();
    }

    @Test
    public void givenStringAmountWithFractionConvertToDouble(){
        String newAmount = " ¼";
        amount.setAmount(newAmount);

        assertEquals(0.25 , amount.getAmount(), 0);
        assertEquals("-", amount.getUnits());
    }

    @Test
    public void setItemAmountgivenStringAmount(){
        String newAmount = "500g";
        amount.setAmount(newAmount);

        assertEquals(500, amount.getAmount(), 0);
        assertEquals("g", amount.getUnits());

    }
}