/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core.interactors;

import java.util.List;
import rs.fon.eklub.core.data.StatAccessService;
import rs.fon.eklub.core.entities.Stat;
import rs.fon.eklub.core.exceptions.ServiceException;
import rs.fon.eklub.core.services.StatService;

/**
 *
 * @author milos
 */
public class StatInteractor implements StatService {

    private StatAccessService<Stat> dao;

    public StatInteractor() {
    }

    public StatInteractor(StatAccessService<Stat> dao) {
        this.dao = dao;
    }
    
    public StatAccessService<Stat> getDao() {
        return dao;
    }

    public void setDao(StatAccessService<Stat> dao) {
        this.dao = dao;
    }
    
    @Override
    public List<Stat> getEntityStats() throws ServiceException {
        return dao.getEntityStats();
    }

    @Override
    public List<Stat> getMembersByCategoryStats() throws ServiceException {
        return dao.getMembersByCategoryStats();
    }

    @Override
    public List<Stat> getAttendanceStats() throws ServiceException {
        return dao.getAttendanceStats();
    }
}
