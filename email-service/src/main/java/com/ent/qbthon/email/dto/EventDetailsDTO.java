package com.ent.qbthon.email.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDetailsDTO {
	@NotNull(message="date must not be null")
	String date;
	@NotNull(message="slot must not be null")
	String slot;
	@NotNull(message="skills must not be null")
	List<String> skills;
	@NotNull(message="users must not be null")
	List<String> users;
}
