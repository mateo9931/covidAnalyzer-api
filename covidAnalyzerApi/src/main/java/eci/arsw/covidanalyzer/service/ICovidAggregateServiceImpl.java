package eci.arsw.covidanalyzer.service;


import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;
import eci.arsw.covidanalyzer.persistence.InMemory;
import eci.arsw.covidanalyzer.service.ICovidAggregateService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Component()
@Qualifier("aggregate")
public class ICovidAggregateServiceImpl implements ICovidAggregateService {
    private InMemory truePositive = new InMemory();
    private InMemory falsePositive = new InMemory();
    private InMemory trueNegative = new InMemory();
    private InMemory falseNegative = new InMemory();
    @Override
    public boolean aggregateResult(Result result, ResultType type) {
        boolean value=false;
        if(type == ResultType.TRUE_POSITIVE){
            truePositive.add(result);
            value=true;
        }
        else if(type == ResultType.TRUE_NEGATIVE){
            falsePositive.add(result);
            value=true;
        }
        else if(type == ResultType.FALSE_POSITIVE){
            trueNegative.add(result);
            value=true;
        }
        else {
            falseNegative.add(result);
            value=true;
        }
        return value;
    }

    @Override
    public Collection<Result> getResult(ResultType type) {
        Collection<Result> colect = null;
        if(type == ResultType.TRUE_POSITIVE){
            colect = truePositive.getAll().values();

        }
        else if(type == ResultType.TRUE_NEGATIVE){
             colect = falsePositive.getAll().values();

        }
        else if(type == ResultType.FALSE_POSITIVE){
             colect = trueNegative.getAll().values();

        }
        else {
             colect = falseNegative.getAll().values();

        }
        return colect;
    }

    @Override
    public void upsertPersonWithMultipleTests(UUID id) {
        truePositive.update(id);
        falsePositive.update(id);
        trueNegative.update(id);
        falseNegative.update(id);
    }
}
