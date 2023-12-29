package com.designxplay.arcadextest2.service;

import com.designxplay.arcadextest2.dto.ItemDto;
import com.designxplay.arcadextest2.entity.Item;
import com.designxplay.arcadextest2.entity.Player;
import com.designxplay.arcadextest2.entity.PlayerItem;
import com.designxplay.arcadextest2.repository.PlayerItemRepository;
import com.designxplay.arcadextest2.repository.PlayerRepository;
import com.designxplay.arcadextest2.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerItemRepository playerItemRepository;
    @Autowired
    private ItemRepository itemRepository;

    // 특정 플레이어의 아이템 목록 가져오기
    public List<PlayerItem> getPlayerItems(Long playerId) {
        return playerItemRepository.findByPlayerId(playerId);
    }

    // 기타 필요한 비즈니스 로직 메소드 추가

    @Transactional
    public PlayerItem addOrUpdateItem(Long playerId, ItemDto itemDto) {
        // 플레이어 조회
        Optional<Player> player = playerRepository.findById(playerId);
        // 아이템 조회 (이 예시에서는 이름으로 조회)
        Optional<Item> item = itemRepository.findByName(itemDto.getName());

        if (player.isPresent() && item.isPresent()) {
            // 이미 해당 아이템을 가지고 있는지 확인
            Optional<PlayerItem> existingPlayerItem = playerItemRepository.findByPlayerAndItem(player.get(), item.get());

            if (existingPlayerItem.isPresent()) {
                // 아이템 개수 업데이트
                PlayerItem playerItem = existingPlayerItem.get();
                playerItem.setCount(playerItem.getCount() + itemDto.getCount()); // 기존 개수에 추가
                return playerItemRepository.save(playerItem); // 변경 사항 저장
            } else {
                // 새로운 PlayerItem 생성
                PlayerItem newPlayerItem = new PlayerItem();
                newPlayerItem.setPlayer(player.get());
                newPlayerItem.setItem(item.get());
                newPlayerItem.setCount(itemDto.getCount());
                return playerItemRepository.save(newPlayerItem); // DB에 저장
            }
        } else {
            // 플레이어 또는 아이템을 찾을 수 없는 경우
            return null;
        }
    }
}
