package com.chuanze.tools.unit;

import com.chuanze.tools.Service.CaculateService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description: @Test throws & timeout
 * @Author chuanze
 * @Date 2020-4-2 10:41
 * @Version V1.0
 **/
@Slf4j
class CaculateServiceTest {
    private CaculateService caculateService = new CaculateService();

    @Test
    public void plus() {
        //check for equality
        assertEquals(new Integer(3), caculateService.plus(1, 2));
    }

    @Test()
    public void subtractException() {
        //check for equality
        assertThrows(RuntimeException.class, () -> {
            assertEquals(new Integer(2), caculateService.subtract("4", 2));
        });
    }

    /**
     * 测试过期时间，默认为秒
     */
    @Test
    @Timeout(value = 12, unit = TimeUnit.SECONDS)
    public void plusTimeout1() {
        try {
            // 时间单位代表一秒
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            log.error(e.getMessage(),e);
        }
        //check for equality
        assertEquals(new Integer(3), caculateService.plus(1, 2));
    }

    /**
     * 测试过期时间，默认为秒
     */
    @Test
    public void plusTimeout2() {
        assertTimeout(Duration.ofSeconds(11), () -> {
            try {
                // 时间单位代表一秒
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                log.error(e.getMessage(),e);
            }
            //check for equality
            assertEquals(new Integer(3), caculateService.plus(1, 2));
        });
    }

}