package com.github.ronlievens.quarkus.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.ronlievens.quarkus.todo.model.enumeration.GenderType;
import com.github.ronlievens.quarkus.todo.model.enumeration.LanguageType;
import com.github.ronlievens.quarkus.todo.model.enumeration.UserRoleType;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Entity
@Table(name = "GEBRUIKERS")
public class User extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String firstName;
    public String infix;
    public String lastName;
    @Column(nullable = false)
    public String email;

    @JsonIgnore
    @Column(nullable = false)
    public String passHash;

    @JsonIgnore
    @Enumerated(STRING)
    public LanguageType languageCode;
    @JsonIgnore
    public LocalDate dateOfBirth;
    @JsonIgnore
    @Enumerated(value = STRING)
    public GenderType gender;

    @JsonIgnore
    @Column(insertable = false, updatable = false)
    public OffsetDateTime createdOn;
    @JsonIgnore
    @Column(insertable = false)
    public OffsetDateTime disabledOn;

    @JsonIgnore
    @ElementCollection(fetch = EAGER)
    @CollectionTable(name = "GEBRUIKER_ROLLEN", joinColumns = @JoinColumn(name = "USER_ID"))
    @Column(name = "AUTHORITY")
    @Enumerated(STRING)
    public Set<UserRoleType> roles;

    @JsonIgnore
    @Transient
    public String password;

    // --[ QUERIES ]----------------------------------------------------------------------------------------------------
    public static User findByEmail(@NonNull String email) {
        return (User) find("email", email);
    }

    // --[ CUSTOM GETTERS AND SETTERS ]---------------------------------------------------------------------------------
    @JsonIgnore
    public String fullName() {
        var fullName = new StringBuilder();
        if (isNotBlank(firstName)) {
            fullName.append(firstName);
            fullName.append(" ");
        }
        if (isNotBlank(infix)) {
            fullName.append(infix);
            fullName.append(" ");
        }
        if (isNotBlank(lastName)) {
            fullName.append(lastName);
        }
        return fullName.toString();
    }
}
