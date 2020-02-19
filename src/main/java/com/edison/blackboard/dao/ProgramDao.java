package com.edison.blackboard.dao;

import com.edison.blackboard.model.Program;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("ProgramDao")
public class ProgramDao {
    private static List<Program> programList = new ArrayList<>();
    static {
//        programList.add(new Program(UUID.randomUUID(), "Information Systems"));
//        programList.add(new Program(UUID.randomUUID(), "Computer Science"));
//        programList.add(new Program(UUID.randomUUID(), "Computer Science Align"));
    }

    public boolean addProgram(Program program) {
        UUID id = UUID.randomUUID();
        return insertProgram(id, program);
    }

    public static Program getProgramByIndex(int index) {
        if(programList.size() <= index) return null;
        return programList.get(index);
    }

    private boolean insertProgram(UUID id, Program program) {
//        programList.add(new Program(id, program));
        return true;
    }

    public List<Program> selectAllPrograms() {
        return programList;
    }

    public static Optional<Program> selectProgramById(UUID id) {
        return programList.stream().filter(program -> program.getId().equals(id)).findFirst();
    }

    public boolean deleteProgramById(UUID id) {
//        Optional<Program> toBeDeleted = selectProgramById(id);
//        if(toBeDeleted.isEmpty()) return false;
//        programList.remove(toBeDeleted.get());
        return true;
    }

    public boolean updateProgramById(UUID id, Program program) {
//        Program newProgram = new Program(id, program.getName());
//        return selectProgramById(id).map(program1 -> {
//            int index = programList.indexOf(program1);
//            if(index >= 0) {
//                programList.set(index, newProgram);
//                return true;
//            }
//            return false;
//        }).orElse(false);
        return true;
    }
}
