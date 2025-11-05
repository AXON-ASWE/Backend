package com.swe.project.entities.admins;

import com.swe.project.entities.users.Users;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "T_ADMINS")
@Data
public class Admins extends Users {
    public Admins() {
        super();
    }
}
