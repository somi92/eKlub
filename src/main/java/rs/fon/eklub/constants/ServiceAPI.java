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
        public static final String GET_SEARCH_MEMBERS = "/members";
        public static final String POST_SAVE_MEMBER = "/members";
    }
    
    public class Category {
        public static final String GET_ALL_CATEGORIES = "/categories";
    }
    
}
