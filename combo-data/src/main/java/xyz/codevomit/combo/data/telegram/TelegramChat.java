package xyz.codevomit.combo.data.telegram;

import jakarta.persistence.*;

@Entity
@Table(name = "telegram_chat")
public class TelegramChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chat_id")
    private String chatId;

    @OneToOne
    private TelegramUser user;
}
