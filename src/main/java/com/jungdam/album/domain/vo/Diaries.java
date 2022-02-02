package com.jungdam.album.domain.vo;

import com.jungdam.diary.domain.Diary;
import com.jungdam.diary.domain.vo.RecordedAt;
import com.jungdam.error.dto.ErrorMessage;
import com.jungdam.error.exception.common.NotExistException;
import com.jungdam.participant.domain.Participant;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Embeddable
public class Diaries {

    private final static Logger log = LoggerFactory.getLogger(Diaries.class);

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diary> diaries = new ArrayList<>();

    public void add(Diary diary) {
        diaries.add(diary);
    }

    public void delete(Long id, Participant participant) {
        Diary diary = find(id, participant);
        remove(diary);
    }

    public boolean isExists(RecordedAt recordedAt, Participant participant) {
        return diaries.stream()
            .anyMatch(d -> d.isWritten(recordedAt, participant));
    }

    public Diary find(Long id, Participant participant) {
        return diaries.stream()
            .filter(d -> d.isCreator(id, participant))
            .findFirst()
            .orElseThrow(() -> new NotExistException(ErrorMessage.NOT_EXIST_DIARY).error(log));
    }

    private void remove(Diary diary) {
        diaries.remove(diary);
    }

    public Diary findById(Long id) {
        return diaries.stream()
            .filter(d -> d.isEquals(id))
            .findFirst()
            .orElseThrow(() -> new NotExistException(ErrorMessage.NOT_EXIST_DIARY).error(log));
    }
}