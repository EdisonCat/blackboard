package com.edison.blackboard.service;

import com.edison.blackboard.dao.ProgramDao;
import com.edison.blackboard.model.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProgramService {
    private final ProgramDao programDao;

    @Autowired
    public ProgramService(@Qualifier("ProgramDao") ProgramDao programDao) {
        this.programDao = programDao;
    }

    public boolean addProgram(Program program) {
        return programDao.addProgram(program);
    }

    public Program getProgramByIndex(int index) {
        return programDao.getProgramByIndex(index);
    }

    public List<Program> getAllPrograms() {
        return programDao.selectAllPrograms();
    }

    public Optional<Program> getProgramById(UUID id) { return programDao.selectProgramById(id); }

    public boolean deleteProgramById(UUID id) {
        return programDao.deleteProgramById(id);
    }

    public boolean updateProgramById(UUID id, Program program) {
        return programDao.updateProgramById(id, program);
    }
}
