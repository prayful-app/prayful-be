package ar.juarce.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "prayer")
@Getter
@Setter
public class Prayer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prayer_id_seq")
    @SequenceGenerator(name = "prayer_id_seq", sequenceName = "prayer_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "prayer_request_id", nullable = false)
    private PrayerRequest prayerRequest;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "believer_id", nullable = false)
    private User believer;

    @Column(nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Prayer() {
        // Needed for Hibernate
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prayer that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format(
                "Prayer[id=%d, prayerRequest='%s', believer='%s', content='%s', createdAt='%s']",
                id, prayerRequest, believer, content, createdAt);
    }

    /*
        Builder for Prayer
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Prayer prayer;

        public Builder() {
            prayer = new Prayer();
        }

        public Builder id(Long id) {
            prayer.setId(id);
            return this;
        }

        public Builder prayerRequest(PrayerRequest prayerRequest) {
            prayer.setPrayerRequest(prayerRequest);
            return this;
        }

        public Builder believer(User believer) {
            prayer.setBeliever(believer);
            return this;
        }

        public Builder content(String content) {
            prayer.setContent(content);
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            prayer.setCreatedAt(createdAt);
            return this;
        }

        public Prayer build() {
            prayer.setCreatedAt(LocalDateTime.now());
            return prayer;
        }
    }

}
