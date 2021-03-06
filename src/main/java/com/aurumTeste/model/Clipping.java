package com.aurumTeste.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Clipping implements Serializable{
	
	
	private static final long serialVersionUID = -7546319100353285248L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	@NotNull(message = "The field clippingDate is required. Please fill it.")
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate clippingDate;
	
	@Column(columnDefinition = "TEXT")
	@NotNull(message = "The field clippingMatter is required. Please fill it.")
	private String clippingMatter;
	
	@Column
	@JsonFormat(pattern="yyyy-MM-dd")
	private ClassificationTypeEnum classificationType;
	
	@Column
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate classifiedDate;
	
	@Column
	private String classifiedTime;
	
	@Column
	private Boolean important;
	
	@Column
	private Boolean viewed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getClippingDate() {
		return clippingDate;
	}

	public void setClippingDate(LocalDate clippingDate) {
		this.clippingDate = clippingDate;
	}

	public String getClippingMatter() {
		return clippingMatter;
	}

	public void setClippingMatter(String clippingMatter) {
		this.clippingMatter = clippingMatter;
	}

	public ClassificationTypeEnum getClassificationType() {
		return classificationType;
	}

	public void setClassificationType(ClassificationTypeEnum classificationType) {
		this.classificationType = classificationType;
	}

	public LocalDate getClassifiedDate() {
		return classifiedDate;
	}

	public void setClassifiedDate(LocalDate classifiedDate) {
		this.classifiedDate = classifiedDate;
	}

	public String getClassifiedTime() {
		return classifiedTime;
	}

	public void setClassifiedTime(String classifiedTime) {
		this.classifiedTime = classifiedTime;
	}

	public Boolean getImportant() {
		return important;
	}

	public void setImportant(Boolean important) {
		this.important = important;
	}

	public Boolean getViewed() {
		return viewed;
	}

	public void setViewed(Boolean viewed) {
		this.viewed = viewed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classificationType == null) ? 0 : classificationType.hashCode());
		result = prime * result + ((classifiedDate == null) ? 0 : classifiedDate.hashCode());
		result = prime * result + ((classifiedTime == null) ? 0 : classifiedTime.hashCode());
		result = prime * result + ((clippingDate == null) ? 0 : clippingDate.hashCode());
		result = prime * result + ((clippingMatter == null) ? 0 : clippingMatter.hashCode());
		result = prime * result + ((important == null) ? 0 : important.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clipping other = (Clipping) obj;
		if (clippingMatter == null) {
			if (other.clippingMatter != null)
				return false;
		} else if (!clippingMatter.equals(other.clippingMatter))
			return false;
		return true;
	}

	
}
