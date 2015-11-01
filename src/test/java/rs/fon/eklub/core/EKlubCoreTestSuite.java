/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core;
        
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

/**
 *
 * @author milos
 */
@RunWith(org.junit.runners.Suite.class)
@SuiteClasses({
    rs.fon.eklub.core.interactors.GroupInteractorTest.class,
    rs.fon.eklub.core.interactors.MemberInteractorTest.class,
    rs.fon.eklub.core.interactors.PaymentInteractorTest.class,
    rs.fon.eklub.core.interactors.TrainingInteractorTest.class,
    rs.fon.eklub.core.interactors.CategoryInteractorTest.class,
    rs.fon.eklub.core.interactors.AdminInteractorTest.class
})
public class EKlubCoreTestSuite {
    
}
