package xyz.codevomit.combo.data.telegram;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Entity
@Table(name = "received_message")
@Data
public class ReceivedMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message_id")
    private Integer messageId;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "unix_time")
    private Integer unixTime;

    @Column(name = "server_time")
    private ZonedDateTime serverTime;

    @Column(name = "text", columnDefinition = "varchar(512)")
    private String text;
}
