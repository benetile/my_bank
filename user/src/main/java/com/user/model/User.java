package com.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.user.beans.AccountBean;
import com.user.beans.BankBean;
import com.user.beans.BeneficiaryBean;
import com.user.beans.SenderBean;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;
    private String firstname;
    private String lastname;
    private String username;
    private Date birthdate;
    private String gender;
    private String email;
    private String phone;
    private String address;
    private Integer zipCode;
    private String city;
    private String password;
    private String role;
    private Date registrationDate;
    private Boolean isNotLocked;
    private Boolean isActive;

    //association bank

    @ManyToMany(fetch = FetchType.LAZY,targetEntity = SenderBean.class,cascade = CascadeType.ALL)
    @JoinTable(name = "user_sender",
                joinColumns = @JoinColumn(name = "idUser"),
                inverseJoinColumns = @JoinColumn(name = "idSender"))
    @JsonIgnoreProperties("users")
    private List<SenderBean> senders = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY,targetEntity = BeneficiaryBean.class, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_beneficiary",
                joinColumns = @JoinColumn(name = "idUser"),
                inverseJoinColumns = @JoinColumn(name = "idBeneficiary"))
    @JsonIgnoreProperties("users")
    private List<BeneficiaryBean> beneficiaries = new ArrayList<>();

    @ManyToOne//(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "idBank")
    @JsonIgnoreProperties("users")
    private BankBean bank;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "idAccount")
    private AccountBean account;

    public User() {
    }

    public User(String firstname, String lastname, String username, Date birthdate, String gender, String email, String phone, String address, Integer zipCode, String city, String password, String role, Date registrationDate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.birthdate = birthdate;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.password = password;
        this.role = role;
        this.registrationDate = registrationDate;
    }
    public User(BankBean bank,String firstname, String lastname, String username, Date birthdate, String gender, String email, String phone, String address, Integer zipCode, String city, String password, String role, Date registrationDate) {
        super();
        this.setBank(bank);
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.birthdate = birthdate;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.password = password;
        this.role = role;
        this.registrationDate = registrationDate;
    }


    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Boolean getNotLocked() {
        return isNotLocked;
    }

    public void setNotLocked(Boolean notLocked) {
        isNotLocked = notLocked;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<SenderBean> getSenders() {
        return senders;
    }

    public void setSenders(List<SenderBean> senders) {
        this.senders = senders;
    }

    public List<BeneficiaryBean> getBeneficiaries() {
        return beneficiaries;
    }

    public void setBeneficiaries(List<BeneficiaryBean> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

    public BankBean getBank() {
        return bank;
    }

    public void setBank(BankBean bank) {
        this.bank = bank;
    }

    public AccountBean getAccount() {
        return account;
    }

    public void setAccount(AccountBean account) {
        this.account = account;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(idUser, user.idUser) && Objects.equals(firstname, user.firstname) && Objects.equals(lastname, user.lastname) && Objects.equals(username, user.username) && Objects.equals(birthdate, user.birthdate) && Objects.equals(gender, user.gender) && Objects.equals(email, user.email) && Objects.equals(phone, user.phone) && Objects.equals(address, user.address) && Objects.equals(zipCode, user.zipCode) && Objects.equals(city, user.city) && Objects.equals(password, user.password) && Objects.equals(role, user.role) && Objects.equals(registrationDate, user.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, firstname, lastname, username, birthdate, gender, email, phone, address, zipCode, city, password, role, registrationDate);
    }
}
