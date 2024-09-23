package com.gogo.model.common.data.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * TODO, move to common-data
 * */
@Getter
@Setter
@MappedSuperclass
@ConditionalOnProperty(name = "spring.shopping.table", havingValue = "true")
public abstract class AbstractEntity implements Serializable {

	/**
	 *  Serializable = enable object to convert to byte stream and later reconstruct it like session attributes.
	 *  If you do not explicitly declare a serialVersionUID, the Java compiler will automatically generate one
	 *  based on various aspects of the class (e.g., fields, methods).
	 *  This can lead to unexpected InvalidClassException if the class structure changes, as the automatically generated serialVersionUID may change.
	 * */
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(name = "updated_at")
	private Date updatedAt;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private Date createdAt;

	@PreUpdate
	protected void onUpdate() {
		updatedAt = new Date();
	}
}
