package com.sairam.cabBookingApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sairam.cabBookingApp.models.enums.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

@Entity
@Data
//@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class Customer extends User  implements UserDetails {

    public Customer(@NotNull String firstName, @NotNull String lastName, @Email @NotNull String email, @NotNull String password, @NotNull Role role, String token, String address, Long mobileNumber, String id) {
        super(id,firstName, lastName, email, password, role,  address, mobileNumber);
        this.token = token;
    }

//    @JsonIgnore
    private String token;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("CUSTOMER"));
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getFirstName();
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
//    private List<TripBook> trips;



}
