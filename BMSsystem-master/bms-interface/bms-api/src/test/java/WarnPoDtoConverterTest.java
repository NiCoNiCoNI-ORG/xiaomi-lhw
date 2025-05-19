import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bench.bms.api.BMSApplication;
import com.bench.bms.api.converter.SignalVoConverter;
import com.bench.bms.api.model.req.SignalReq;
import com.bench.bms.application.model.SignalDto;
import com.bench.bms.application.model.WarnDto;
import com.bench.bms.application.service.BmsWarnService;
import com.bench.bms.application.service.SignalListHandle;
import com.bench.bms.domain.model.RuleDo;
import com.bench.bms.domain.model.SignalDo;
import com.bench.bms.domain.model.WarnDo;
import com.bench.bms.infra.repository.RuleRepository;
import com.bench.bms.infra.repository.WarnRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

//@EnableAutoConfiguration
@SpringBootTest(classes = BMSApplication.class)
public class WarnPoDtoConverterTest {


    @Resource
    private SignalVoConverter converter;

    @Resource
    private BmsWarnService bmsWarnService;

    @Resource
    private RuleRepository ruleRepository;

    @Resource
    private WarnRepository warnRepository;

    @Resource
    private SignalListHandle signalListHandle;

    @Test
    public void testToDto() {
        SignalReq signalReq = new SignalReq();
        signalReq.setCarId(1);
//        signalReq.setWarnId(2);
        signalReq.setSignal("{\"Mx\":11.0,\"Mi\":9.6,\"Ix\":12.0,\"Ii\":11.7}");

        SignalDto signalDto = converter.toDto(signalReq);

        List<SignalDto> signalDtoList = new ArrayList<>();
        signalDtoList.add(signalDto);

        signalDtoList = signalListHandle.signalHandle(signalDtoList);

        System.out.println(signalDtoList.get(0));

        WarnDto warnDto = bmsWarnService.getWarn(signalDtoList.get(0));

        System.out.println(warnDto);

    }

//    @Test
//    public void testDo(){
//        SignalDo signalDo = new SignalDo(carId=1, warnId=1, signal={Mi=9.6, Mx=11.0});
//    }

    @Test
    public void test5(){
        SignalDo signalDo = new SignalDo();
        RuleDo ruleDo = new RuleDo();

        Map<String, Double> signal = new HashMap<String, Double>();
        signal.put("Mi" , 9.6);
        signal.put("Mx", 11.0);

        signalDo.setCarId(Long.valueOf(1));
        signalDo.setWarnId(1);
        signalDo.setSignal(signal);

        Map<Double, Integer> rule = new LinkedHashMap<Double, Integer>();
        rule.put(5.0, 0);
        rule.put(3.0, 1);
        rule.put(1.0, 2);
        rule.put(0.6, 3);
        rule.put(0.2, 4);

        ruleDo.setRule(rule);
        ruleDo.setWarnName("电压差报警");
        ruleDo.setBatteryType("三元电池");
        WarnDo warnDo = ruleRepository.getWarn(signalDo, ruleDo);

        System.out.println(warnDo);

        warnRepository.saveWarn(warnDo);



    }

}
