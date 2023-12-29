package com.designxplay.arcadextest2.repository;

import com.designxplay.arcadextest2.entity.Item;
import com.designxplay.arcadextest2.entity.Player;
import com.designxplay.arcadextest2.entity.PlayerItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerItemRepository extends JpaRepository<PlayerItem, Long> {
    // 특정 플레이어의 아이템 목록을 찾는 메소드
    List<PlayerItem> findByPlayerId(Long playerId);
    Optional<PlayerItem> findByPlayerAndItem(Player player, Item item);
}
