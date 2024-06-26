package com.sairam.cabBookingApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sairam.cabBookingApp.models.enums.Role;
import jakarta.persistence.*;
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
public class Customer extends User   {

    public Customer(@NotNull String firstName, @NotNull String lastName, @Email @NotNull String email, @NotNull String password, @NotNull String role,  String address, Long mobileNumber) {
        super(firstName, lastName, email, password, role,  address, mobileNumber,null);

    }

    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<TripBook> trips;

}
