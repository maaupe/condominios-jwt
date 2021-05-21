package br.com.condominio.app.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditoria<T> {
	
	
	@CreatedBy
	@Column(name="created_by")
	protected T createdBy;

	@LastModifiedBy
	@Column(name="modified_by")
	protected T modifiedBy;

	@CreatedDate
	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	protected LocalDateTime createdDate;

	@LastModifiedDate
	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	protected LocalDateTime modifiedDate;
	
  

}
