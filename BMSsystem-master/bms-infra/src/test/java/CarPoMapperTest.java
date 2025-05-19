import com.bench.bms.infra.repository.MybatisConfig;
import com.bench.bms.infra.repository.entity.CarPo;
import com.bench.bms.infra.repository.entity.RulePo;
import com.bench.bms.infra.repository.mapper.CarPoMapper;
import com.bench.bms.infra.repository.mapper.RulePoMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author bench
 * @Date 2024/06/17 21:37
 **/

@EnableAutoConfiguration
@SpringBootTest(classes = MybatisConfig.class)
//@ContextConfiguration(classes = MybatisConfig.class)
public class CarPoMapperTest {

    @Resource
    private CarPoMapper carPoMapper;


    @Resource
    private RulePoMapper rulePoMapper;

    @Test
    public void test1(){
        CarPo carPo = carPoMapper.selectByCarId(1L);
        System.out.println(carPo);
//        carPoMapper.ListAllCar();
    }

    @Test
    public void test2(){
        List<CarPo> carPoList = carPoMapper.listAllCar();
//        System.out.println(carPo);
//        carPoMapper.ListAllCar();
        carPoList.forEach(System.out::println);
    }

    @Test
    public void test3(){
        List<RulePo> rulePoList = rulePoMapper.listRules();
//        System.out.println(carPo);
//        carPoMapper.ListAllCar();
        rulePoList.forEach(System.out::println);
    }
}
