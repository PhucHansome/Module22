package CaseMD2.services;

import CaseMD2.model.Tech;
import CaseMD2.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class TechService implements ITechService {
    List<Tech> teches = new ArrayList<>();
    public static String path = "data/techs.csv";
    private String name;

    @Override
    public List<Tech> getTechs() {
        List<Tech> newTechs = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            newTechs.add(new Tech(record));
        }
        return teches = newTechs;
    }

    @Override
    public void add(Tech newTech) {
        teches.add(newTech);
        CSVUtils.write(path, teches);
    }

    @Override
    public void update() {
        CSVUtils.write(path, teches);
    }

    @Override
    public Tech getTechById(int id) {
        for (Tech tech : teches) {
            if (tech.getId() == id)
                return tech;
        }

        return null;
    }

    @Override
    public boolean exist(int id) {

        return getTechById(id) != null ;
    }

    @Override
    public boolean checkDuplicateName(String Name) {
        for (Tech tech : teches){
            if (tech.getName().equals(name))
                return true;
        }
        return false;
    }

    @Override
    public Boolean checkDuplicateId(int id) {
        for (Tech tech : teches){
            if (tech.getId() == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(Tech tech) {
        teches.remove(tech);
        update();

    }
}
