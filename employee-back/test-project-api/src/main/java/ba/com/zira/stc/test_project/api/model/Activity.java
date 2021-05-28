package ba.com.zira.stc.test_project.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String description;
    private String name;
    private String shortName;
    private String modifiedBy;
    private String status;
    private LocalDateTime modified;
    private String createdBy;
    private String code;
    private LocalDateTime created;
    private String type;
}
