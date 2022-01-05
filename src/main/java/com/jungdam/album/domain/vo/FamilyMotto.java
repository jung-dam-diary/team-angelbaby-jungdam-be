package com.jungdam.album.domain.vo;

import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.common.InvalidArgumentException;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import org.springframework.util.StringUtils;

@Embeddable
public class FamilyMotto {

    @Transient
    private static final int FAMILY_MOTTO_LENGTH = 30;

    @Column(name = "album_family_motto", nullable = false, length = FAMILY_MOTTO_LENGTH)
    private String familyMotto;

    protected FamilyMotto() {
    }

    public FamilyMotto(String familyMotto) {
        validate(familyMotto);
        this.familyMotto = familyMotto;
    }

    private void validate(String familyMotto) {
        if (!StringUtils.hasText(familyMotto) || familyMotto.length() > FAMILY_MOTTO_LENGTH) {
            throw new InvalidArgumentException(ErrorMessage.INVALID_ALBUM_FAMILY_MOTTO);
        }
    }

    public String getFamilyMotto() {
        return familyMotto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FamilyMotto familyMotto1 = (FamilyMotto) o;

        return familyMotto.equals(familyMotto1.familyMotto);
    }

    @Override
    public int hashCode() {
        return familyMotto.hashCode();
    }
}