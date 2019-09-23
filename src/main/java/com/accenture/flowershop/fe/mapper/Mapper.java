package com.accenture.flowershop.fe.mapper;

import java.util.List;

public interface Mapper extends org.dozer.Mapper {
    <T> List<T> mapList(List<?> sources, Class<T> destinationClass);
}
