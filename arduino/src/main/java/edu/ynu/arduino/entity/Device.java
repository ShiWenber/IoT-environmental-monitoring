package edu.ynu.arduino.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Device extends AbstractDomainEntity{

	@Size(max = 20)
	@Column(name = "customName", nullable = true, length = 20)
	@Schema(description = "设备自定义名称")
	private String customName;

	@Size(max = 20)
	@Column(name = "typeName", nullable = true, length = 20)
	@Schema(description = "设备型号名称")
	private String typeName;

	@Size(max = 32)
	@NotNull
	@Column(name = "`user$id`", nullable = false, length = 32)
	@Schema(description = "设备所属用户id")
	private String user$id;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Device device = (Device) o;
		return id != null && Objects.equals(id, device.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
