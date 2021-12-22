package com.jungdam.participant.dto.response;

public class ReadParticipantRoleResponse {

    private final Long id;
    private final String role;

    public ReadParticipantRoleResponse(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}
