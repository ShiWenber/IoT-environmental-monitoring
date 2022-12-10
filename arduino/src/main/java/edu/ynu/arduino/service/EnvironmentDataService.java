package edu.ynu.arduino.service;
    
    


import edu.ynu.arduino.service.AbstractTypedService;
import java.lang.String;
import edu.ynu.arduino.entity.EnvironmentData;
import edu.ynu.arduino.service.EnvironmentDataService;
import edu.ynu.arduino.dao.EnvironmentDataDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
/**
 * 业务层
 *
 * @author shiwenbo
 * @since 2022-11-29 11:55:23
 */
@Slf4j
@Service
public class EnvironmentDataService extends AbstractTypedService<EnvironmentData, String> {

	@Resource
    private EnvironmentDataDao environmentDataDao;

    public EnvironmentDataService(EnvironmentDataDao dao) {
        this.dataContext = dao;
        this.environmentDataDao = dao;
    }

    public Page<EnvironmentData> queryEnvironmentDataByPage(Pageable page) {
        return environmentDataDao.queryPage(page, null);
    }

}

