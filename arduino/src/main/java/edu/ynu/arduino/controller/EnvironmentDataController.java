package edu.ynu.arduino.controller;


import edu.ynu.arduino.entity.EnvironmentData;
import edu.ynu.arduino.service.EnvironmentDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.math.BigDecimal;

/**
 * 控制层
 *
 * @author shiwenbo
 * @since 2022-11-29 11:55:23
 */
@Slf4j
@RestController
@Tag(name = "EnvironmentDataController")
@RequestMapping("/environmentData")
public class EnvironmentDataController extends AbstractTypedController<EnvironmentData, String> {

    @Resource
    private EnvironmentDataService environmentDataService;

    EnvironmentDataController(EnvironmentDataService service) {
        this.svcContext = service;
        this.environmentDataService = service;
    }

    @Operation(summary = "新建数据记录")
    @PostMapping("/create")
    public EnvironmentData create(@PathParam("airHumidity") Double airHumidity,
                                  @PathParam("light_intensity") Integer lightIntensity,
                                  @PathParam("soil_moisture") Double soilMoisture,
                                  @PathParam("temperature") Double temperature) {
        EnvironmentData environmentData = new EnvironmentData();
        environmentData.setAirHumidity(BigDecimal.valueOf(airHumidity));
        environmentData.setLightIntensity(lightIntensity);
        environmentData.setSoilMoisture(BigDecimal.valueOf(soilMoisture));
        environmentData.setTemperature(BigDecimal.valueOf(temperature));
        return environmentDataService.create(environmentData);
    }
}

