package CaseMD2.services;

import CaseMD2.model.Tech;

import java.util.List;

public interface ITechService {
    List<Tech> getTechs();
    void add(Tech newTech);
    void update();
    Tech getTechById(int id);
    boolean exist(int id);
    boolean checkDuplicateName(String Name);
    Boolean checkDuplicateId(int id);
    void remove(Tech tech);
}
