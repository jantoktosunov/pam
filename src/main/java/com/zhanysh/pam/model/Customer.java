package com.zhanysh.pam.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
@XmlRootElement
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "FIRSTNAME")
    private String firstName;

    @NotBlank
    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "ADDRESS")
    private String address;

    @Email
    @Column(name = "EMAIL")
    private String email;

    public Customer(String firstName, String lastName, String address, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
    }
}
