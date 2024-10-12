package com.insurance_app.insurance_server.entity.lookup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "lk_user_role")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRoleLookupEntity implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "key", nullable = false)
    private String key;

    @Column(name = "value", nullable = false)
    private String value;

    @Override
    public boolean equals(Object obj) {
        if( obj != null && obj instanceof UserRoleLookupEntity ) {
            UserRoleLookupEntity entity = (UserRoleLookupEntity)obj;
            return this.getId() == entity.getId();
        }
        return super.equals(obj);
    }

    public static final UserRoleLookupEntity INSURER = new UserRoleLookupEntity(1,"insurer","Insurer");
    public static final UserRoleLookupEntity REINSURER = new UserRoleLookupEntity(2,"reinsurer","Reinsurer");
    public static final UserRoleLookupEntity ADMIN = new UserRoleLookupEntity(3,"admin","Admin");
    public static final UserRoleLookupEntity GUEST = new UserRoleLookupEntity(4,"guest","Guest");

    public static UserRoleLookupEntity getFromId(int id) {
        switch(id) {
            case 1:
                return INSURER;
            case 2:
                return REINSURER;
            case 3:
                return ADMIN;
            case 4:
                return GUEST;
            default:
                return null;
        }
    }
}
