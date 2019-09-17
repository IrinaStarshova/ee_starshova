package com.accenture.flowershop.fe.ws;

import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.jws.WebService;

@WebService(endpointInterface = "com.accenture.flowershop.fe.ws.FlowersStockWebService")
public class FlowersStockWebServiceImpl implements FlowersStockWebService {

    @Autowired
    private FlowerBusinessService flowerBusinessService;

    public void increaseFlowersStockSize (int count){
        flowerBusinessService.increaseQuantityOfFlowers(count);
    }
}
