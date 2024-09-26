package com.example.springbootbasic.util;
// 역할
public enum Roles {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN"), EDITOR("ROLE_EDITOR");
    private String role;
    private Roles(String role){
         this.role=role;
    }
    public String getRole(){ // -> Roles.USER.getRole() : ROLE_USER
        return role;
    }
}
