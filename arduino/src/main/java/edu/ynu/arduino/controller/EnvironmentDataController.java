package edu.ynu.arduino.controller;


import edu.ynu.arduino.entity.EnvironmentData;
import edu.ynu.arduino.service.EnvironmentDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

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
        environmentData.setTime(Instant.now());
        return environmentDataService.create(environmentData);
    }

    @Operation(summary = "数据记录分页查询")
    @GetMapping("/pageQuery")
    public Page<EnvironmentData> pageQuery(Pageable pageable) {
        return environmentDataService.queryEnvironmentDataPage(pageable);
    }

    @Operation(summary = "数据记录分页查询 按时间排序")
    @GetMapping("/pageQueryOrderByTime")
    public Page<EnvironmentData> pageQueryOrderByTime(Pageable pageable) {
        return environmentDataService.queryEnvironmentDataPageByTime(pageable);
    }
}

