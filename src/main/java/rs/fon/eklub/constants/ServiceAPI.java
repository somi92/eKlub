/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.constants;

/**
 *
 * @author milos
 */
public class ServiceAPI {
    
    public class DefaultResponseMessages {
        public static final String RESOURCE_FOUND = "Resource found.";
        public static final String RESOURCE_NOT_FOUND = "Resource does not exist.";
        public static final String RESOURCE_SAVED = "Resource saved.";
        public static final String RESOURCE_DELETED = "Resource deleted.";
    }
    
    public class DefaultErrorMessages {
        public static final String DATABASE_ERROR = "Database error.";
        public static final String SERVICE_ERROR = "Service error.";
    }
    
    public class Headers {
        public static final String CONTENT_TYPE = "Content-type=application/json";
    }
    
    public class Group {
        public static final String GET_ALL_GROUPS = "/groups";
        public static final String POST_SAVE_GROUP = "/groups";
    }
    
    public class Member {
        public static final String GET_MEMBER_BY_ID = "/members/{id}";
        public static final String DELETE_MEMBER_BY_ID = "/members/{id}";
        public static final String GET_ALL_MEMBERS = "/members";
        public static final String POST_SEARCH_MEMBERS = "/members/search";
        public static final String POST_SAVE_MEMBER = "/members";
    }
    
    public class Category {
        public static final String GET_ALL_CATEGORIES = "/categories";
    }
    
    public class Training {
        public static final String POST_SAVE_TRAINING = "/trainings";
        public static final String GET_TRAINING_BY_ID = "/trainings/{id}";
        public static final String POST_SEARCH_TRAINING = "/trainings/search";
    }
    
    public class Payment {
        public static final String POST_SAVE_PAYMENTS = "/payments";
        public static final String GET_PAYMENTS = "/payments";
    }
    
    public class MembershipFee {
        public static final String GET_ALL_MEMBERSHIP_FEES = "/fees";
    }
}
