/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author milos
 */
@RunWith(org.junit.runners.Suite.class)
@Suite.SuiteClasses({
    rs.fon.eklub.controllers.CategoryControllerTest.class,
    rs.fon.eklub.controllers.GroupControllerTest.class,
    rs.fon.eklub.controllers.MemberControllerTest.class,
    rs.fon.eklub.controllers.TrainingControllerTest.class,
    rs.fon.eklub.controllers.MembershipFeeControllerTest.class
})
public class EKlubControllersTestSuite {
    
}
