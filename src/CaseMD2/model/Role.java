package CaseMD2.model;

import javax.management.StringValueExp;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER");
    private String value;
    Role(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
    // value:  " ADMIN"
    public static Role fromValue(String value){
        Role[] values = values();
        for (Role role :values){
            if(role.value.equals(value))
                return role;
        }
        throw new IllegalArgumentException("invalid");
    }
}
