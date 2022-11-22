package ch.zli.m223.model;

import javax.persistence.*;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(readOnly = true)
    private Long id;

    @Column(nullable = false)
    private Long roomNr;

    @OneToOne(mappedBy = "bookings")
    @JsonIgnoreProperties("bookings")
    private Bookings bookings;

    // id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // roomNr
    public Long getRoomNr() {
        return roomNr;
    }

    public void setRoomNr(Long roomNr) {
        this.id = roomNr;
    }
}
