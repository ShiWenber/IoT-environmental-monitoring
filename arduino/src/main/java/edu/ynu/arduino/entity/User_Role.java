package edu.ynu.arduino.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class User_Role extends AbstractDomainEntity {

    @NotNull
    @Size(max = 32)
    @Column(name = "user$id", nullable = false, length = 32)
    private String user$id;

    @NotNull
    @Size(max = 32)
    @Column(name = "role$id", nullable = false, length = 32)
    private String role$id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User_Role user_role = (User_Role) o;
        return id != null && Objects.equals(id, user_role.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
