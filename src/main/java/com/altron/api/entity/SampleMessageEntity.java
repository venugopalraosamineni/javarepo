package com.altron.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sample_message_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SampleMessageEntity {

	@Column(name = "variable_value", nullable = false)
	@Id
	private Integer variableValue;

}
