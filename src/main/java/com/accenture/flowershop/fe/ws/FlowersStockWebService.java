package com.accenture.flowershop.fe.ws;

import org.springframework.dao.OptimisticLockingFailureException;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface FlowersStockWebService {
    void increaseFlowersStockSize (@WebParam(name = "count") int count)
            throws OptimisticLockingFailureException;
}
