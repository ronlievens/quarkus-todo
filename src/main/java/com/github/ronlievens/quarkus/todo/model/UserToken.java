package com.github.ronlievens.quarkus.todo.model;

import com.github.ronlievens.quarkus.todo.model.enumeration.UserTokenType;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "GEBRUIKER_TOKENS")
public class UserToken extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(nullable = false)
    public Long userId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public UserTokenType type;

    @Column(nullable = false)
    public String value;

    @Column(nullable = false)
    public OffsetDateTime validUntil;

    @Column(updatable = false)
    public OffsetDateTime createdOn;

    @Column(insertable = false)
    public OffsetDateTime disabledOn;
}
