package com.github.budwing.obo.schedule.sal;

import com.github.budwing.obo.schedule.vo.Seat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("obo-cinema")
public interface CinemaClient {
    @GetMapping("/obo/cinema/{cinemaId}/hall/{hallId}/seat")
    List<Seat> getSeatOf(@PathVariable Integer cinemaId, @PathVariable Integer hallId);
}
