package ikubinfo.hospital.management.system.department.controller;

import ikubinfo.hospital.management.system.department.entity.Department;
import ikubinfo.hospital.management.system.department.entity.DepartmentDto;
import ikubinfo.hospital.management.system.department.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/department")
public class DepartmentController {

  private final DepartmentService service;

  @PostMapping("/addDepartment")
  public DepartmentDto addDepartment(@RequestBody DepartmentDto department) {
    return service.addDepartment(department);
  }

  @GetMapping("/findById/{id}")
  public DepartmentDto findById(@PathVariable Long id) {
    return service.findById(id);
  }

  @DeleteMapping("/deleteById/{id}")
  public Integer deleteById(@PathVariable Long id) {
    return service.deleteById(id);
  }
}
