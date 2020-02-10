package com.edison.blackboard.api;

import com.edison.blackboard.model.Program;
import com.edison.blackboard.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/blackboard/program")
@RestController
public class ProgramController {
    private final ProgramService programService;

    @Autowired
    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping
    public List<Program> getAllPrograms() {
        return programService.getAllPrograms();
    }

    @GetMapping(path = "{id}")
    public Program getProgramById(@PathVariable("id") UUID id) {
        return programService.getProgramById(id).orElse(null);
    }

    @PostMapping
    public void addProgram(@Valid @NotNull @RequestBody Program program) {
        programService.addProgram(program);
    }

    @PutMapping(path = "{id}")
    public boolean updateProgramById(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Program program) {
        return programService.updateProgramById(id, program);
    }

    @DeleteMapping(path = "{id}")
    public boolean deleteProgramById(@PathVariable("id") UUID id) {
        return programService.deleteProgramById(id);
    }

}
