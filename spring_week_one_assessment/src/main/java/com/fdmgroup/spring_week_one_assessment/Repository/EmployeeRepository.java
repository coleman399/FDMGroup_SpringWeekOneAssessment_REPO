package com.fdmgroup.spring_week_one_assessment.Repository;

import java.util.List;

import com.fdmgroup.spring_week_one_assessment.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE e.address LIKE %?1%")
    public List<Employee> search(@Param("keyword") String keyword);

}
