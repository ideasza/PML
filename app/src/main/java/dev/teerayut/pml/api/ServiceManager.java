package dev.teerayut.pml.api;

import android.util.Log;


import dev.teerayut.pml.api.result.match.MatchItemResultGroup;
import dev.teerayut.pml.api.result.standing.StandingItemResultGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static dev.teerayut.pml.api.ServiceUrl.BASE_URL;


/**
 * Created by teerayut.k on 7/24/2017.
 */

public class ServiceManager {

    private static ServiceManager instance;
    private static ServiceAPI api;

    public interface ServiceManagerCallback<T>{
        void onSuccess(T result);

        void onFailure(Throwable t);
    }

    public static ServiceManager getInstance(){
        if( instance == null ){
            instance = new ServiceManager();
        }
        return instance;
    }

    private ServiceManager(){
    }

    public static void setApi( ServiceAPI mockApi ){
        api = mockApi;
    }

    public void requestStandingTable(final ServiceManagerCallback<StandingItemResultGroup> callback) {
        requestTableCall().enqueue(new Callback<StandingItemResultGroup>() {
            @Override
            public void onResponse(Call<StandingItemResultGroup> call, Response<StandingItemResultGroup> response) {
                Log.e("requestStanding", response + "");
                if( callback != null ){
                    /*if( tableChecker( response ) ){
                        callback.onSuccess( response.body() );
                    }else{
                        callback.onFailure( new Throwable( "Response invalid." ) );
                    }*/
                    callback.onSuccess( response.body() );
                }
            }

            @Override
            public void onFailure(Call<StandingItemResultGroup> call, Throwable t) {
                if( callback != null ){
                    callback.onFailure( t );
                }
            }
        });
    }

    public void requestFixtures(final ServiceManagerCallback<MatchItemResultGroup> callback) {
        requestFixturesCall().enqueue(new Callback<MatchItemResultGroup>() {
            @Override
            public void onResponse(Call<MatchItemResultGroup> call, Response<MatchItemResultGroup> response) {
                Log.e("requestFixtures", response + "");
                if( callback != null ){
                    /*if( tableChecker( response ) ){
                        callback.onSuccess( response.body() );
                    }else{
                        callback.onFailure( new Throwable( "Response invalid." ) );
                    }*/
                    callback.onSuccess( response.body() );
                } else{
                    callback.onFailure( new Throwable( "Response invalid." ) );
                }
            }

            @Override
            public void onFailure(Call<MatchItemResultGroup> call, Throwable t) {
                if( callback != null ){
                    callback.onFailure( t );
                }
            }
        });
    }

    public Call<StandingItemResultGroup> requestTableCall(){
        return Service.newInstance( BASE_URL )
                .getApi( api )
                .getStanding();
    }

    public Call<MatchItemResultGroup> requestFixturesCall() {
        return Service.newInstance( BASE_URL )
                .getApi( api )
                .getFixtures();
    }
}
