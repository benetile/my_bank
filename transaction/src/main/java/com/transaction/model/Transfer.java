package com.transaction.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="transaction")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTransfer;
    private String ibanUser;
    private String nameSender;
    private String ibanBeneficiary;
    private String nameBeneficiary;
    private String refBank;
    private String description;
    private String transferType;
    private BigDecimal amount;
    private Boolean validate;
    private Date dateTransfer;

    public Transfer() {
    }

    public Transfer(String ibanUser, String nameSender, String ibanBeneficiary, String nameBeneficiary, String refBank, String description, String transferType, BigDecimal amount, Boolean validate, Date dateTransfer) {
        this.ibanUser = ibanUser;
        this.nameSender = nameSender;
        this.ibanBeneficiary = ibanBeneficiary;
        this.nameBeneficiary = nameBeneficiary;
        this.refBank = refBank;
        this.description = description;
        this.transferType = transferType;
        this.amount = amount;
        this.validate = validate;
        this.dateTransfer = dateTransfer;
    }

    public Integer getIdTransfer() {
        return idTransfer;
    }

    public void setIdTransfer(Integer idTransfer) {
        this.idTransfer = idTransfer;
    }

    public String getIbanUser() {
        return ibanUser;
    }

    public void setIbanUser(String ibanUser) {
        this.ibanUser = ibanUser;
    }

    public String getNameSender() {
        return nameSender;
    }

    public void setNameSender(String nameSender) {
        this.nameSender = nameSender;
    }

    public String getIbanBeneficiary() {
        return ibanBeneficiary;
    }

    public void setIbanBeneficiary(String ibanBeneficiary) {
        this.ibanBeneficiary = ibanBeneficiary;
    }

    public String getNameBeneficiary() {
        return nameBeneficiary;
    }

    public void setNameBeneficiary(String nameBeneficiary) {
        this.nameBeneficiary = nameBeneficiary;
    }

    public String getRefBank() {
        return refBank;
    }

    public void setRefBank(String refBank) {
        this.refBank = refBank;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getValidate() {
        return validate;
    }

    public void setValidate(Boolean validate) {
        this.validate = validate;
    }

    public Date getDateTransfer() {
        return dateTransfer;
    }

    public void setDateTransfer(Date dateTransfer) {
        this.dateTransfer = dateTransfer;
    }
}
