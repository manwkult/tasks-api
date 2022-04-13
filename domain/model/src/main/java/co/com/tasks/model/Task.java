package co.com.tasks.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Task {

    private Long id;
    private String name;
    private String description;
    private Date executionDate;
    private Integer status;
    private Integer employeeId;
}
