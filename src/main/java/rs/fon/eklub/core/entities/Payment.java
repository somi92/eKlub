/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.entities;

import java.util.Date;
import java.util.Objects;

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

    public Payment(MembershipFee fee, double amount, Date dateOfPayment, Member member) {
        this.fee = fee;
        this.amount = amount;
        this.dateOfPayment = dateOfPayment;
        this.member = member;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.fee);
        hash = 29 * hash + Objects.hashCode(this.member);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Payment other = (Payment) obj;
        if (!Objects.equals(this.fee, other.fee)) {
            return false;
        }
        if (!Objects.equals(this.member, other.member)) {
            return false;
        }
        return true;
    }
    
    
}
