package org.b2bsalesportal.userservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @Column(name = "user_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "first_name" ,length=100, nullable = false)
    private String firstName;

    @Column(name = "last_name" ,length=100, nullable = false)
    private String lastName;

    @Column(name = "email" , nullable = false, unique = true)
    private String email;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "address" ,length=150)
    private String address;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "active_state")
    private boolean activeState = true;

    public User(String firstName, String lastName, String email, String contactNumber, String address, String password, boolean activeState) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.address = address;
        this.password = password;
        this.activeState = activeState;
    }
}
