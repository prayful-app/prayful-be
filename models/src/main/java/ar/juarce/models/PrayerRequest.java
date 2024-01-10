package ar.juarce.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "prayer_request")
@Getter
@Setter
public class PrayerRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prayer_request_id_seq")
    @SequenceGenerator(name = "prayer_request_id_seq", sequenceName = "prayer_request_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "requester_id", nullable = false)
    private User requester;

    @Column(nullable = false)
    private String request;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public PrayerRequest() {
        // Needed for Hibernate
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrayerRequest that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format(
                "PrayerRequest[id=%d, requester='%s', request='%s', createdAt='%s']",
                id, requester, request, createdAt);
    }

    /*
        Builder for PrayerRequest
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final PrayerRequest prayerRequest;

        public Builder() {
            prayerRequest = new PrayerRequest();
        }

        public Builder id(Long id) {
            prayerRequest.setId(id);
            return this;
        }

        public Builder requester(User requester) {
            prayerRequest.setRequester(requester);
            return this;
        }

        public Builder request(String request) {
            prayerRequest.setRequest(request);
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            prayerRequest.setCreatedAt(createdAt);
            return this;
        }

        public PrayerRequest build() {
            prayerRequest.setCreatedAt(LocalDateTime.now());
            return prayerRequest;
        }
    }

}
