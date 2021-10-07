package com.transaction.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTransaction;
    private Integer idUser;
    private String nameSender;
    private String emailSender;
    private String nameBeneficiary;
    private String emailBeneficiary;
    private String transactionType;
    private String description;
    private BigDecimal amount;
    private long transactionNumber;
    private Date transactionDate;

    public Transaction() {
    }

    public Transaction(Integer idUser, String nameSender, String nameBeneficiary, String transactionType, String description, BigDecimal amount, long transactionNumber, Date transactionDate) {
        this.idUser = idUser;
        this.nameSender = nameSender;
        this.nameBeneficiary = nameBeneficiary;
        this.transactionType = transactionType;
        this.description = description;
        this.amount = amount;
        this.transactionNumber = transactionNumber;
        this.transactionDate = transactionDate;
    }

    public Integer getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Integer idTransaction) {
        this.idTransaction = idTransaction;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNameSender() {
        return nameSender;
    }

    public void setNameSender(String nameSender) {
        this.nameSender = nameSender;
    }

    public String getNameBeneficiary() {
        return nameBeneficiary;
    }

    public void setNameBeneficiary(String nameBeneficiary) {
        this.nameBeneficiary = nameBeneficiary;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getEmailSender() {
        return emailSender;
    }

    public void setEmailSender(String emailSender) {
        this.emailSender = emailSender;
    }

    public String getEmailBeneficiary() {
        return emailBeneficiary;
    }

    public void setEmailBeneficiary(String emailBeneficiary) {
        this.emailBeneficiary = emailBeneficiary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public long getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(long transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
