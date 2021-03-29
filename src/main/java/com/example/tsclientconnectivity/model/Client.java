package com.example.tsclientconnectivity.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;


@Data
@Table(name = "clients",
        uniqueConstraints = {@UniqueConstraint(columnNames = "email") })
@Entity
@NoArgsConstructor
@Getter @Setter
public class Client implements UserDetails {

    @NotNull
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String fname;
    private String lname;
    private String email;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    private String password;
    private boolean isAdmin;
//    @Column(name = "portfolioId")
//    private long portfolioId;


    public Client(String fname,String lname,String email,
                  String phonenumber, String password, boolean isAdmin){
        this.lname=lname;
        this.fname=fname;
        this.password=password;
        this.phoneNumber=phonenumber;
        this.isAdmin=isAdmin;
        this.email=email;
       // accountBalance=10000;

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grantedAuthority=new SimpleGrantedAuthority("client");
        return Collections.singletonList(grantedAuthority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}
