package com.user.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TransferBean implements Serializable {
    private Integer idTransfer;
    private String ibanUser;
    private String nameSender;
    private String emailSender;
    private String ibanBeneficiary;
    private String nameBeneficiary;
    private String emailBeneficiary;
    private String refBank;
    private String description;
    private String transferType;
    private BigDecimal amount;
    private Boolean validate;
    private Date dateTransfer;
    private Date dateValidateTransfer;

    public TransferBean() {
    }

    public TransferBean(String ibanUser, String nameSender, String emailSender, String ibanBeneficiary, String nameBeneficiary, String emailBeneficiary, String refBank, String description, String transferType, BigDecimal amount, Boolean validate, Date dateTransfer) {
        this.ibanUser = ibanUser;
        this.nameSender = nameSender;
        this.emailSender = emailSender;
        this.ibanBeneficiary = ibanBeneficiary;
        this.nameBeneficiary = nameBeneficiary;
        this.emailBeneficiary = emailBeneficiary;
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

    public String getEmailSender() {
        return emailSender;
    }

    public void setEmailSender(String emailSender) {
        this.emailSender = emailSender;
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

    public String getEmailBeneficiary() {
        return emailBeneficiary;
    }

    public void setEmailBeneficiary(String emailBeneficiary) {
        this.emailBeneficiary = emailBeneficiary;
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

    public Date getDateValidateTransfer() {
        return dateValidateTransfer;
    }

    public void setDateValidateTransfer(Date dateValidateTransfer) {
        this.dateValidateTransfer = dateValidateTransfer;
    }
}
