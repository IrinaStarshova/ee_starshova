package com.accenture.flowershop.fe.ws.rest;

import com.accenture.flowershop.be.business.order.OrderBusinessService;
import com.accenture.flowershop.be.entity.order.Order;
import com.accenture.flowershop.fe.dto.OrderDTO;
import com.accenture.flowershop.fe.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/order")
public class OrderRestService {
    @Autowired
    private OrderBusinessService orderBusinessService;
    @Autowired
    private Mapper mapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{pageNumber}/{quantity}")
    public Response getOrders(@PathParam("pageNumber") int pageNumber,
                              @PathParam("quantity") int quantity) {
        Page<Order> orders = orderBusinessService.
                getOrders(pageNumber, quantity);
        return Response.ok(orders.map(order -> mapper.map(order, OrderDTO.class))).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/close")
    public Response closeOrder(Long orderId) {
        try {
            orderBusinessService.closeOrder(orderId);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
