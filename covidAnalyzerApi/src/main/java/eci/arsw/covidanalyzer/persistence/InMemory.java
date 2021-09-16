package eci.arsw.covidanalyzer.persistence;

import eci.arsw.covidanalyzer.model.Result;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemory {
    Map<UUID, Result> map = new HashMap<UUID, Result>();

    public void add(Result res){
        map.put(res.getId(),res);
    }

    public Result getById(String id){
        return map.get(id);
    }

    public Map<UUID, Result> getAll(){
        return map;
    }

    public void update(UUID id){
        if(map.containsKey(id)){
            Result res =map.get(id);
            res.setVeces(res.getVeces()+1);
        }
    }

}
