package dev.teerayut.pml.api;


import dev.teerayut.pml.api.base.BaseService;

/**
 * Created by TheKhaeng on 9/15/2016 AD.
 */

public class Service extends BaseService<ServiceAPI> {

    public static Service newInstance( String baseUrl ){
        Service service = new Service();
        service.setBaseUrl( baseUrl );
        return service;
    }

    private Service(){
    }

    @Override
    protected Class<ServiceAPI> getApiClassType(){
        return ServiceAPI.class;
    }
}
