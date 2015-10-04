/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.entities;

import java.util.Date;

/**
 *
 * @author milos
 */
public class Payment {
    
    private MembershipFee fee;
    private double amount;
    private Date dateOfPayment;
    private Member member;

    public Payment() {
    }

    public MembershipFee getFee() {
        return fee;
    }

    public void setFee(MembershipFee fee) {
        this.fee = fee;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(Date dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
    
    
}
