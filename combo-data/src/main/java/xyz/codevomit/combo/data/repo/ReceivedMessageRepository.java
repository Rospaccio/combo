package xyz.codevomit.combo.data.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.codevomit.combo.data.telegram.ReceivedMessage;

@Repository
public interface ReceivedMessageRepository extends JpaRepository<ReceivedMessage, Long> {
}
