package com.emredulger.entity;

import java.math.BigDecimal;

public class VehicleCredit extends BaseEntity {

    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    protected void criticalValidations() {
        System.out.println("it is doing critical validations for mortgage credit..");
    }
}
