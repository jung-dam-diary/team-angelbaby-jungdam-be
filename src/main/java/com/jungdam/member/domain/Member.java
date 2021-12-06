package com.jungdam.member.domain;

import com.jungdam.common.domain.BaseEntity;
import com.jungdam.member.domain.vo.Role;
import com.jungdam.member.domain.vo.Status;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_email")
    private String email;

    @Column(name = "member_nickname")
    private String nickname;

    @Column(name = "member_avatar")
    private String avatar;

    @Column(name = "member_role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "member_status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    protected Member() {

    }
}