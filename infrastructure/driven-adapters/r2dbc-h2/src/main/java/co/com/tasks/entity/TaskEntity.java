package co.com.tasks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

@Table("task")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(length = 120, nullable = false, unique = true)
    private String name;

    @Column(length = 250, nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime executionDate;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false)
    private Integer employeeId;
}
