package jsp.SpringBoot;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	List<Employee> findByEname(String ename);
	
	@Query("select e from Employee e where e.esalary < 10000.00")
	List<Employee> getEmployeeByEsalary();
	
	@Query("select e from Employee e where e.eid=?1 and e.ename=?2")
	List<Employee> getEmployeeByIdAndName(int eid, String ename);
	
	@Query("select e from Employee e where e.ename = :e_ename")
	List<Employee> getEmployeeByEname(String e_ename);
	
	
	

	

}
