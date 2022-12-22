package edu.ynu.arduino.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class EnvironmentData extends AbstractDomainEntity {

    @Column(name = "light_intensity")
    private Integer lightIntensity;

    @Column(name = "temperature", precision = 4, scale = 2)
    private BigDecimal temperature;

    @Column(name = "air_humidity", precision = 5, scale = 2)
    private BigDecimal airHumidity;

    @Column(name = "soil_moisture", precision = 5, scale = 2)
    private BigDecimal soilMoisture;

    @Column(name = "time")
    private Date time;

	@Size(max = 32)
	@NotNull
	@Column(name = "`device$id`", nullable = true, length = 32)
	@Schema(description = "数据所属设备id")
	private String device$id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EnvironmentData that = (EnvironmentData) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}