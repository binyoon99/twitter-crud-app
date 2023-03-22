package com.binyoon.apps.twitter.example.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigInteger;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Financial {

  private String reportDate;
  private BigInteger grossProfit;
  private BigInteger costOfRevenue;
  private BigInteger operatingRevenue;
  private BigInteger totalRevenue;
  private BigInteger operatingIncome;
  private BigInteger netIncome;

  public String getReportDate() {
    return reportDate;
  }

  public void setReportDate(String reportDate) {
    this.reportDate = reportDate;
  }

  public BigInteger getGrossProfit() {
    return grossProfit;
  }

  public void setGrossProfit(BigInteger grossProfit) {
    this.grossProfit = grossProfit;
  }

  public BigInteger getCostOfRevenue() {
    return costOfRevenue;
  }

  public void setCostOfRevenue(BigInteger costOfRevenue) {
    this.costOfRevenue = costOfRevenue;
  }

  public BigInteger getOperatingRevenue() {
    return operatingRevenue;
  }

  public void setOperatingRevenue(BigInteger operatingRevenue) {
    this.operatingRevenue = operatingRevenue;
  }

  public BigInteger getTotalRevenue() {
    return totalRevenue;
  }

  public void setTotalRevenue(BigInteger totalRevenue) {
    this.totalRevenue = totalRevenue;
  }

  public BigInteger getOperatingIncome() {
    return operatingIncome;
  }

  public void setOperatingIncome(BigInteger operatingIncome) {
    this.operatingIncome = operatingIncome;
  }

  public BigInteger getNetIncome() {
    return netIncome;
  }

  public void setNetIncome(BigInteger netIncome) {
    this.netIncome = netIncome;
  }

}