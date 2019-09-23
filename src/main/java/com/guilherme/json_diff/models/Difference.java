package com.guilherme.json_diff.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Table(name = "differences", schema = "PUBLIC")
@Entity
public class Difference {

    @EmbeddedId
    private Key key;

    private String file;

    @Enumerated(EnumType.STRING)
    private DiffFileType fileType;

    @Builder
    public Difference(Long id, Side side, String file, DiffFileType type) {
        this.key = new Key(side, id);
        this.file = file;
        this.fileType = type;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Key implements Serializable {

        @Enumerated(EnumType.STRING)
        private Side side;

        private Long id;
    }

    public enum Side {
        LEFT,
        RIGHT
    }

}
// VALIDATE JSON TODO