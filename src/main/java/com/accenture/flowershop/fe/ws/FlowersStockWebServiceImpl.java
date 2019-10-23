package com.accenture.flowershop.fe.ws;

import com.accenture.flowershop.be.business.flower.FlowerBusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;

import javax.jws.WebService;

@WebService(endpointInterface = "com.accenture.flowershop.fe.ws.FlowersStockWebService")
public class FlowersStockWebServiceImpl implements FlowersStockWebService {

    @Autowired
    private FlowerBusinessService flowerBusinessService;
    private static final Logger LOG = 	LoggerFactory.getLogger(FlowersStockWebService.class);

    public void increaseFlowersStockSize (int count)
            throws OptimisticLockingFailureException {
        try {
            flowerBusinessService.increaseQuantityOfAllFlowers(count);
            LOG.info("Quantity of flowers increased by "+ count);
        }catch (OptimisticLockingFailureException e){
            LOG.info("Fail increase quantity Of flowers!");
            throw e;
        }
    }
}
