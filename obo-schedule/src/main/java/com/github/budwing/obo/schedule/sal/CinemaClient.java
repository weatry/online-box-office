package com.github.budwing.obo.schedule.sal;

import com.github.budwing.obo.schedule.vo.Seat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "obo-cinema", fallbackFactory = CinemaClientFallbackFactory.class)
public interface CinemaClient {
    @GetMapping("/obo/cinema/{cinemaId}/hall/{hallId}/seat")
    List<Seat> getSeatOf(@PathVariable("cinemaId") Integer cinemaId, @PathVariable("hallId") Integer hallId);
}

@Component
@Slf4j
class CinemaClientFallbackFactory implements FallbackFactory<CinemaClient> {
    @Override
    public CinemaClient create(Throwable cause) {
        log.error("Cinema client fallback for exception: {}", cause.getClass());
        log.error("Cinema client exception messages: {}", cause.getMessage());
        return new CinemaClient() {
            @Override
            public List<Seat> getSeatOf(Integer cinemaId, Integer hallId) {
                return null;
            }
        };
    }
}
