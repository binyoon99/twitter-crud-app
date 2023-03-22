package com.binyoon.apps.twitter.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;


  @JsonInclude(JsonInclude.Include.NON_NULL)
  public class Dividend {

    private String exDate;
    private String paymentDate;
    private String recordDate;
    private String declaredDate;
    private Double amount;

    public String getExDate() {
      return exDate;
    }

    public void setExDate(String exDate) {
      this.exDate = exDate;
    }

    public String getPaymentDate() {
      return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
      this.paymentDate = paymentDate;
    }

    public String getRecordDate() {
      return recordDate;
    }

    public void setRecordDate(String recordDate) {
      this.recordDate = recordDate;
    }

    public String getDeclaredDate() {
      return declaredDate;
    }

    public void setDeclaredDate(String declaredDate) {
      this.declaredDate = declaredDate;
    }

    public Double getAmount() {
      return amount;
    }

    public void setAmount(Double amount) {
      this.amount = amount;
    }
  }
