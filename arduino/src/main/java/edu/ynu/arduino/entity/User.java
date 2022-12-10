package edu.ynu.arduino.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class User extends AbstractDomainEntity implements UserDetails {

    @Size(max = 20)
    @NotNull
    @Column(name = "password", nullable = false, length = 20)
    @Schema(description = "用户密码")
    private String password;

    @Size(max = 20)
    @NotNull
    @Column(name = "username", nullable = false, length = 20)
    @Schema(description = "用户名")
    private String username;

    @NotNull
    @Column(name = "sex", nullable = false)
    @Schema(description = "用户性别")
    private Integer sex;

    @NotNull
    @Column(name = "delTag", nullable = false)
    @Schema(description = "删除标志")
    private Integer delTag;

    @Schema(description = "用户是否没有过期")
    private boolean accountNonExpired;

    @Schema(description = "用户是否没有被锁定")
    private boolean accountNonLocked;

    @Schema(description = "用户凭证是否没有过期")
    private boolean credentialsNonExpired;

    @Schema(description = "用户是否可用")
    private boolean enabled;

    @Transient
    @JsonIgnore
    private List<Role> roles;

    /**
     * 获取用户权限
     * 使用stream流将roles中的role转换为SimpleGrantedAuthority
     * 使用改函数时需要主义，多表连接只在service中完成，当service中为本对象传输了roles，该函数才不会返回空值
     * @return
     */
    /**
     * 当前属性不存入数据库
     */
    @Transient
    @Override
    /**客户端调用的时候，这个会在json中忽略*/
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        TODO:完成查询
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        for (Role role : getRoles()) {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        return authorities;
        /**用map函数完成List<roles>向List<SimpleCrantedAuthority>的转换*/
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
