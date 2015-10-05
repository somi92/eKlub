/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.entities;

import java.util.Objects;

/**
 *
 * @author milos
 */
public class Attendance {
    
    private Member member;
    private Training training;
    private boolean isAttendant;
    private int lateMin;

    public Attendance() {
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public boolean isIsAttendant() {
        return isAttendant;
    }

    public void setIsAttendant(boolean isAttendant) {
        this.isAttendant = isAttendant;
    }

    public int getLateMin() {
        return lateMin;
    }

    public void setLateMin(int lateMin) {
        this.lateMin = lateMin;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.member);
        hash = 43 * hash + Objects.hashCode(this.training);
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
        final Attendance other = (Attendance) obj;
        if (!Objects.equals(this.member, other.member)) {
            return false;
        }
        if (!Objects.equals(this.training, other.training)) {
            return false;
        }
        return true;
    }
    
    
}
