package com.gogo.model.common.data.jpa.entity.product;

import com.gogo.model.common.data.jpa.entity.AbstractEntity;
import com.gogo.model.common.data.jpa.entity.setting.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "country")
@ConditionalOnProperty(name = "spring.shopping.table", havingValue = "true")
public class Country extends AbstractEntity {

	@Column(nullable = false, length = 45)
	private String name;
	
	@Column(nullable = false, length = 5)
	private String code;
	
	@OneToMany(mappedBy = "country")
	private Set<State> states;

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + ", code=" + code + "]";
	}
}
