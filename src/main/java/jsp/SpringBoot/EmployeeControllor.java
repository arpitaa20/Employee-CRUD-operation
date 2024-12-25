package jsp.SpringBoot;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jsp")
public class EmployeeControllor {
	
	@Autowired
	private EmployeeRepository er;
	
//	Insert all the info
	@PostMapping("/employee")
	public ResponseStructure<Employee> saveEmployee(@RequestBody Employee e){
		er.save(e);
		ResponseStructure<Employee> str=new ResponseStructure<Employee>();
		str.setStatusCode(HttpStatus.CREATED.value());
		str.setMessage("Success");
		str.setData(e);
		return str;
	}
	
//	get all the info
	@GetMapping("/employee")
	public ResponseStructure<List<Employee>> getAllEmployees(){
		List<Employee> e = er.findAll();
		ResponseStructure<List<Employee>> str= new ResponseStructure<List<Employee>>();
		str.setStatusCode(HttpStatus.OK.value());
		str.setMessage("Success");
		str.setData(e);
		return str;
	}
	
//	get info by employee id
	@GetMapping("/employee/{id}")
	public Employee getEmployeeById(@PathVariable int id) {
		Optional<Employee> opt = er.findById(id);
		
		if(opt.isPresent()) {
			return opt.get();
			
		}else {
			return null;
		}
 	}

//	update the info
	@PutMapping("/employee")
	public String updateEmployee(@RequestBody Employee e) {
		er.save(e);
		return "Employee record updated";
	}
	
//	delete the info by id
	@DeleteMapping("/employee/{id}")
	public String deleteEmployeeById(@PathVariable int id) {
		Optional<Employee> opt = er.findById(id);
		if(opt.isPresent()) {
			er.delete(opt.get());
			return "Employee record is delete";
		}else {
			return "No record delete";
		}
		
	}


}
