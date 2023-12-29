package com.designxplay.arcadextest2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer avatarNumber;
    private Integer totalPoint;
    // 현재 게임 모드
    private Integer currentGameMode;
    // 현재 게임 모드 시작 시간
    private Date gameStartTime;
    // 갖고 있는 아이템 리스트
    private Short itemCount1;
    private Short itemCount2;
    private Short itemCount3;
    private Short itemCount4;
    private Short itemCount5;
    private Short itemCount6;
    private Short itemCount7;
    private Short itemCount8;
    private Short itemCount9;
    private Short itemCount10;
    private Short itemCount11;
    private Short itemCount12;
    private Short itemCount13;
    private Short itemCount14;
    private Short itemCount15;
    // 취득해야 하는 아이템 리스트
    private String itemNeedToGet;
    // 현재 게임 포인트
    private Integer currentModeGamePoint;






    // 기타 필드와 메소드
}
