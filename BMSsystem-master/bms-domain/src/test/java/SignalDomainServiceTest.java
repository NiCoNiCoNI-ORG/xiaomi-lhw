import com.bench.bms.MybatisConfig;
import com.bench.bms.common.exception.DomainException;
import com.bench.bms.common.exception.exceptionsenum.DomainExceptionEnum;
import com.bench.bms.domain.model.RuleDo;
import com.bench.bms.domain.model.SignalDo;
import com.bench.bms.infra.redisservice.RedisService;
import com.bench.bms.infra.repository.SignalRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author bench
 * @Date 2024/06/24 16:55
 **/
@Slf4j
@EnableAutoConfiguration
@SpringBootTest(classes = MybatisConfig.class)
public class SignalDomainServiceTest {

    @Resource
    private SignalRepository signalRepository;

    @Resource
    private RedisService<RuleDo> ruleRedisService;

    private static final long CACHE_TIMEOUT = 18000L; // 定义缓存过期时间为18000秒

    @Test
    public void testTetRuleBySignal(){

        Map<String, Double> signal = new HashMap<String, Double>();
        signal.put("Mx", 12.0);
        signal.put("Mi", 11.9);

        SignalDo signalDo = new SignalDo(1L, 1, signal);
        String redisKey = "carId:" + signalDo.getCarId() + ":warnId:" + signalDo.getWarnId();
        RuleDo ruleDo = ruleRedisService.get(redisKey);

        if (ruleDo == null){
            log.info("数据库查询rule" + redisKey);
            ruleDo = signalRepository.getRule(signalDo);
            if (ruleDo == null){
                throw new DomainException(DomainExceptionEnum.RULE_NOT_FOUND);
            }
            ruleRedisService.set(redisKey, ruleDo, CACHE_TIMEOUT);
        }
    }

}
