package com.watchbe.watchbedemo.model.account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER(1234, Collections.emptySet()),
    ADMIN(9012,
            Set.of(
                    Permission.ADMIN_READ,
                    Permission.ADMIN_UPDATE,
                    Permission.ADMIN_CREATE,
                    Permission.ADMIN_DELETE,
                    Permission.MANAGER_READ,
                    Permission.MANAGER_UPDATE,
                    Permission.MANAGER_CREATE,
                    Permission.MANAGER_DELETE
            )
    ),
    MANAGER(5678,
            Set.of(
                    Permission.MANAGER_READ,
                    Permission.MANAGER_UPDATE,
                    Permission.MANAGER_CREATE,
                    Permission.MANAGER_DELETE
            )
    );
    private final int value;

    private final Set<Permission> permissions;

    //use for spring security
    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities =  getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        System.out.println("autho="+authorities);
        return authorities;
    }

}
