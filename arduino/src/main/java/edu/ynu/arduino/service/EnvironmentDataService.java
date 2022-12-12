package edu.ynu.arduino.service;


import edu.ynu.arduino.dao.EnvironmentDataDao;
import edu.ynu.arduino.entity.EnvironmentData;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    public Page<EnvironmentData> queryEnvironmentDataPage(Pageable page) {
        Specification<EnvironmentData> spec = (root, query, criteriaBuilder) ->
                query.orderBy(
                        criteriaBuilder.desc(root.get("id"))
                ).getRestriction();
        return environmentDataDao.queryPage(page, spec);
    }

    @Operation(summary = "按照时间排序分页查询")
    public Page<EnvironmentData> queryEnvironmentDataPageByTime(Pageable page) {
        Specification<EnvironmentData> spec = (root, query, criteriaBuilder) ->
                query.orderBy(
                        criteriaBuilder.desc(root.get("time"))
                ).getRestriction();
        return environmentDataDao.queryPage(page, spec);
    }
}

