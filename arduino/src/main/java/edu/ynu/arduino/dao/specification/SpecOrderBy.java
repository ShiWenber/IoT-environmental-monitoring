package edu.ynu.arduino.dao.specification;

import com.sun.xml.bind.v2.TODO;
import edu.ynu.arduino.entity.EnvironmentData;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public class SpecOrderBy extends AbstractQueryCondition {
    // 根据新建时间排序
    Specification<EnvironmentData> spec;

    SpecOrderBy(Sort sort) {
        // TODO
    }

}
