package com.activation.activation_api.model;

import com.activation.activation_api.enumeration.Role;
import com.activation.activation_api.enumeration.Status;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CORE_USERS")
@Data public class User {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "core_users_seq_gen")
    @SequenceGenerator(name = "core_users_seq_gen", sequenceName = "core_users_seq", allocationSize = 1)
    private long id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ROLE")
    private Role role;
    @Column(name = "STATUS")
    private Status status;
}
