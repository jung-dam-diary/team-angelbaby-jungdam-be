package com.jungdam.album.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FamilyMotto {

    @Column(name = "album_family_motto", length = 30)
    private String familyMotto;

    protected FamilyMotto() {
    }

    public FamilyMotto(String familyMotto) {
        this.familyMotto = familyMotto;
    }
}