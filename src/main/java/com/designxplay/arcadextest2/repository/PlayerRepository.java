package com.designxplay.arcadextest2.repository;

import com.designxplay.arcadextest2.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    // 커스텀 메소드가 필요할 경우 여기에 추가
}
