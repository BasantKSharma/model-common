package com.gogo.model.common.data.jpa.entity.locale;

import com.gogo.model.common.data.jpa.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "state")
@ConditionalOnProperty(name = "spring.shopping.table", havingValue = "true")
public class State extends AbstractEntity {

	@Column(nullable = false, length = 45)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;

	@Override
	public String toString() {
		return "State [id=" + id + ", name=" + name + "]";
	}
}
