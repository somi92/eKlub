/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.entities;

import java.util.Date;
import java.util.List;

/**
 *
 * @author milos
 */
public class MembershipFee {
    
    private long id;
    private Date dateFrom;
    private Date dateTo;
    private String remark;
    private List<Payment> payments;

    public MembershipFee() {
    }

    public MembershipFee(long id, Date dateFrom, Date dateTo, String remark) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.remark = remark;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final MembershipFee other = (MembershipFee) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
