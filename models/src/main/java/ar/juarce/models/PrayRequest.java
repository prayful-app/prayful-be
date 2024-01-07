package ar.juarce.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "pray_request")
@Getter
@Setter
public class PrayRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pray_request_id_seq")
    @SequenceGenerator(name = "pray_request_id_seq", sequenceName = "pray_request_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "requester_id", nullable = false)
    private User requester;

    @Column(nullable = false)
    private String request;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public PrayRequest() {
        // Needed for Hibernate
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrayRequest that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format(
                "PrayRequest[id=%d, requester='%s', request='%s', createdAt='%s']",
                id, requester, request, createdAt);
    }

    /*
        Builder for PrayRequest
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final PrayRequest prayRequest;

        public Builder() {
            prayRequest = new PrayRequest();
        }

        public Builder id(Long id) {
            prayRequest.setId(id);
            return this;
        }

        public Builder requester(User requester) {
            prayRequest.setRequester(requester);
            return this;
        }

        public Builder request(String request) {
            prayRequest.setRequest(request);
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            prayRequest.setCreatedAt(createdAt);
            return this;
        }

        public PrayRequest build() {
            prayRequest.setCreatedAt(LocalDateTime.now());
            return prayRequest;
        }
    }

}
