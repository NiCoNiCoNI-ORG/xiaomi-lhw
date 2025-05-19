import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bench.bms.api.converter.SignalVoConverter;
import com.bench.bms.api.model.req.SignalReq;
import com.bench.bms.application.model.SignalDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Map;

public class WarnInfoPoDtoConverterTest {

    private final SignalVoConverter converter = Mappers.getMapper(SignalVoConverter.class);

    @Test
    public void testToDto() {
        SignalReq signalReq = new SignalReq();
        signalReq.setCarId(1);
        signalReq.setWarnId(2);
        signalReq.setSignal("{\"Mx\":11.0,\"Mi\":9.6,\"Ix\":12.0,\"Ii\":11.7}");

        SignalDto signalDto = converter.toDto(signalReq);

        assertEquals(1, signalDto.getCarId());
        assertEquals(2, signalDto.getWarnId());
        Map<String, Double> expectedMap = Map.of("Mx", 11.0, "Mi", 9.6, "Ix", 12.0, "Ii", 11.7);
        assertEquals(expectedMap, signalDto.getSignal());
    }
}
