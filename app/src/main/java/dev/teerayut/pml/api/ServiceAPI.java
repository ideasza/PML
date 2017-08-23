package dev.teerayut.pml.api;


import dev.teerayut.pml.api.result.match.MatchItemResultGroup;
import dev.teerayut.pml.api.result.standing.StandingItemResultGroup;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static dev.teerayut.pml.api.ServiceUrl.FIXTURES_URL;
import static dev.teerayut.pml.api.ServiceUrl.TABLE_URL;


/**
 * Created by teerayut.k on 7/24/2017.
 */

public interface ServiceAPI {

    @GET( TABLE_URL )
    Call<StandingItemResultGroup> getStanding();

    @GET( FIXTURES_URL )
    Call<MatchItemResultGroup> getFixtures();
}
