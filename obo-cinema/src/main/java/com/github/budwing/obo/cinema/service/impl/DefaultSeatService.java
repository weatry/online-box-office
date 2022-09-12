package com.github.budwing.obo.cinema.service.impl;

import com.github.budwing.obo.cinema.dto.SeatDTO;
import com.github.budwing.obo.cinema.entity.Seat;
import com.github.budwing.obo.cinema.mapper.SeatMapper;
import com.github.budwing.obo.cinema.service.SeatService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.transaction.annotation.ShardingSphereTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DefaultSeatService implements SeatService {
    @Autowired
    private SeatMapper seatMapper;

    @Transactional
    @ShardingSphereTransactionType(TransactionType.BASE)
    @Override
    public List<SeatDTO> getAllAvailableSeat(Integer cinemaId, Integer hallId) {
        List<Seat> seatList = seatMapper.selectAvailableSeatByCinemaHall(cinemaId, hallId);
        return seatList.stream().map(seat -> SeatDTO.of(seat))
                .collect(Collectors.toList());
    }
}
