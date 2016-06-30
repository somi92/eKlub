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
    
    private long id;
    private MembershipFee fee;
    private double amount;
    private Date dateOfPayment;
    private long memberId;

    public Payment() {
    }

    public Payment(long id, MembershipFee fee, double amount, Date dateOfPayment, long memberId) {
        this.id = id;
        this.fee = fee;
        this.amount = amount;
        this.dateOfPayment = dateOfPayment;
        this.memberId = memberId;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
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
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
