package xyz.codevomit.combo.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.codevomit.combo.data.telegram.TelegramChat;

@Repository
public interface TelegramChatRepository extends JpaRepository<TelegramChat, Long> {
}
