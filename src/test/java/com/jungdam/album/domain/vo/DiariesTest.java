package com.jungdam.album.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;

import com.jungdam.diary.domain.Diary;
import com.jungdam.diary.domain.vo.Content;
import com.jungdam.diary.domain.vo.RecordedAt;
import com.jungdam.diary.domain.vo.Title;
import com.jungdam.participant.domain.Participant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;

class DiariesTest {

    private List<Diary> diaries = new ArrayList<>();

    private LocalDate localDate = LocalDate.now();

    @Mock
    private Participant participant;

    @BeforeEach
    void 일급_컬렉션_초기화() {
        String[] data = {"제목111, 본문222", "제목222, 본문333", "제목333, 본문444"};

        for (String d : data) {
            String[] source = d.split(",");
            Diary diary = new Diary(
                new Title(source[0]),
                new Content(source[1]),
                new RecordedAt(localDate),
                participant
            );
            diaries.add(diary);
        }
    }

    @ParameterizedTest
    @CsvSource({
        "제목111, 본문222",
        "제목222, 본문333",
        "제목333, 본문444"
    })
    void 일급_컬렉션_데이터_추가(String title, String content) {

        Diary diary = new Diary(
            new Title(title),
            new Content(content),
            new RecordedAt(localDate),
            participant
        );
        diaries.add(diary);

        assertThat(diaries.size()).isEqualTo(4);
    }


}