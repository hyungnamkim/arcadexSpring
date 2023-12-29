package com.designxplay.arcadextest2.controller;

import com.designxplay.arcadextest2.dto.ItemDto;
import com.designxplay.arcadextest2.entity.PlayerItem;
import com.designxplay.arcadextest2.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ItemRestfulController {
    @Autowired
    private PlayerService playerService;

    @GetMapping("/{playerId}/items")
    public ResponseEntity<?> getPlayerItems(@PathVariable Long playerId) {
        try {
            List<PlayerItem> playerItems = playerService.getPlayerItems(playerId);
            if (playerItems.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            // DTO 변환 로직을 추가하여 클라이언트에 필요한 데이터만 전송
            List<ItemDto> itemsDto = playerItems.stream()
                    .map(pi -> new ItemDto(pi.getItem().getName(), pi.getItem().getDescription(), pi.getCount()))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(itemsDto);
        } catch (Exception e) {
            // 예외 처리 로직
            return ResponseEntity.badRequest().body("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/{playerId}/addItem")
    public ResponseEntity<?> addOrUpdateItem(@PathVariable Long playerId, @RequestBody ItemDto itemDto) {
        try {
            PlayerItem updatedPlayerItem = playerService.addOrUpdateItem(playerId, itemDto);
            if (updatedPlayerItem != null) {
                return ResponseEntity.ok(updatedPlayerItem); // 성공 시 업데이트된 PlayerItem 반환
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player or Item not found");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("An error occurred: " + e.getMessage());
        }
    }

    // 기타 메소드 및 로직
}
