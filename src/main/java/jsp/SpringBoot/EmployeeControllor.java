package jsp.SpringBoot;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(@RequestBody Employee e){
		er.save(e);
		ResponseStructure<Employee> str=new ResponseStructure<Employee>();
		str.setStatusCode(HttpStatus.CREATED.value()); //201 created
		str.setMessage("Success");
		str.setData(e);
		return new ResponseEntity<ResponseStructure<Employee>>(str, HttpStatus.CREATED);
	}
	
//	get all the info
	@GetMapping("/employee")
	public ResponseEntity<ResponseStructure<List<Employee>>> getAllEmployees(){
		List<Employee> e = er.findAll();
		ResponseStructure<List<Employee>> str= new ResponseStructure<List<Employee>>();
		str.setStatusCode(HttpStatus.OK.value());
		str.setMessage("Success");
		str.setData(e);
		return new ResponseEntity<ResponseStructure<List<Employee>>>(str, HttpStatus.OK);
	}
	
//	get info by employee id
	@GetMapping("/employee/{id}")
	public ResponseEntity<ResponseStructure<Employee>> getEmployeeById(@PathVariable int id) {
		Optional<Employee> opt = er.findById(id);
		ResponseStructure<Employee> str = new ResponseStructure<Employee>();
		
		if(opt.isPresent()) {
			str.setStatusCode(HttpStatus.OK.value());
			str.setMessage("Success");
			str.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Employee>>(str, HttpStatus.OK);
		}else {
			throw new IdNotFoundException();
		}
 	}

//	update the info
	@PutMapping("/employee")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(@RequestBody Employee e) {
		er.save(e);
		ResponseStructure<Employee> str = new ResponseStructure<Employee>();
		
		str.setStatusCode(HttpStatus.OK.value());
		str.setMessage("Success");
		str.setData(e);
		return new ResponseEntity<ResponseStructure<Employee>>(str, HttpStatus.OK);
	}
	
//	delete the info by id
	@DeleteMapping("/employee/{eid}")
	public ResponseEntity<ResponseStructure<Employee>> deleteEmployeeById(@PathVariable int id) {
		Optional<Employee> opt = er.findById(id);
		ResponseStructure<Employee> str = new ResponseStructure<Employee>();
		
		if(opt.isPresent()) {
			Employee e = opt.get();
			er.delete(e);
			str.setStatusCode(HttpStatus.OK.value());
			str.setMessage("Deleted");
			str.setData(null);
			return new ResponseEntity<ResponseStructure<Employee>>(str, HttpStatus.OK);
			
		}else {
			throw new IdNotFoundException();
		}
	}
		
//		find by name of the employee
		@GetMapping("/employees/{ename}")
		public ResponseEntity<ResponseStructure<List<Employee>>> getEmployeeByName(@PathVariable String ename){
			ResponseStructure<List<Employee>> str = new ResponseStructure<List<Employee>>();
			List<Employee> emp = er.findByEname(ename);
			
			if(emp.isEmpty()) {
				str.setStatusCode(HttpStatus.NOT_FOUND.value());
				str.setMessage("Name not Found");
				str.setData(null);
				return new ResponseEntity<ResponseStructure<List<Employee>>>(str, HttpStatus.NOT_FOUND);
		    }else {
			    str.setStatusCode(HttpStatus.OK.value());
			    str.setMessage("Success");
			    str.setData(emp);
			    return new ResponseEntity<ResponseStructure<List<Employee>>>(str, HttpStatus.OK);
		}
		
	}
	@GetMapping("/employees")
	public ResponseEntity<ResponseStructure<List<Employee>>> getEmployeeByEsalary() {
		ResponseStructure<List<Employee>> str = new ResponseStructure<List<Employee>>();
		List<Employee> emp = er.getEmployeeByEsalary();
			
		if(!emp.isEmpty()) {
			str.setStatusCode(HttpStatus.OK.value());
			str.setMessage("Success");
			str.setData(emp);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(str, HttpStatus.OK);
		} else {
			str.setStatusCode(HttpStatus.NOT_FOUND.value());
		    str.setMessage("salary not find");
		    str.setData(null);
		    return new ResponseEntity<ResponseStructure<List<Employee>>>(str, HttpStatus.NOT_FOUND);
			}
	 	}
		
	@GetMapping("/employees/{eid}/{ename}")
	public ResponseEntity<ResponseStructure<List<Employee>>> getEmployeeByIdAndName(@PathVariable int eid,@PathVariable String ename) {
		ResponseStructure<List<Employee>> str = new ResponseStructure<List<Employee>>();
		List<Employee> emp = er.getEmployeeByIdAndName(eid,ename);
			
		if(!emp.isEmpty()) {
			str.setStatusCode(HttpStatus.OK.value());
			str.setMessage("Success");
			str.setData(emp);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(str, HttpStatus.OK);
		} else {
			str.setStatusCode(HttpStatus.NOT_FOUND.value());
		    str.setMessage("not find");
		    str.setData(null);
		    return new ResponseEntity<ResponseStructure<List<Employee>>>(str, HttpStatus.NOT_FOUND);
			}
	 	}
	
	@GetMapping("/Employees/{ename}")
	public ResponseEntity<ResponseStructure<List<Employee>>> getEmployeeEname(@PathVariable String ename){
		ResponseStructure<List<Employee>> str = new ResponseStructure<List<Employee>>();
		List<Employee> e = er.getEmployeeByEname(ename);
		
		if(!e.isEmpty()) {
			str.setStatusCode(HttpStatus.OK.value());
			str.setMessage("Success");
			str.setData(e);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(str,HttpStatus.OK);
		}
		else {
			str.setStatusCode(HttpStatus.NOT_FOUND.value());
			str.setMessage("Not found");
			str.setData(null);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(str,HttpStatus.NOT_FOUND);
		}
	}
}
