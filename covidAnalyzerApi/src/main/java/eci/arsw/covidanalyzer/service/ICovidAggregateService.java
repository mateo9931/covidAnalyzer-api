package eci.arsw.covidanalyzer.service;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public interface ICovidAggregateService {
    /**
     * Add a new result into the specified result type storage.
     *
     * @param result
     * @param type
     * @return
     */
    boolean aggregateResult(Result result, ResultType type);
    /**
     * Get all the results for the specified result type.
     *
     * @param type
     * @return
     */
    Collection<Result> getResult(ResultType type);
    /**
     * 
     * @param id
     */
    void upsertPersonWithMultipleTests(UUID id);
}
