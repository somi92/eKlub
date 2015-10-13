/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.entities;

/**
 *
 * @author milos
 */
public class Attendance {
    
    private long id;
    private Member member;
    private Training training;
    private boolean isAttendant;
    private int lateMin;

    public Attendance() {
    }

    public Attendance(long id, Member member, Training training, boolean isAttendant, int lateMin) {
        this.id = id;
        this.member = member;
        this.training = training;
        this.isAttendant = isAttendant;
        this.lateMin = lateMin;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        int hash = 7;
        hash = 23 * hash + (int) (this.id ^ (this.id >>> 32));
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
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
