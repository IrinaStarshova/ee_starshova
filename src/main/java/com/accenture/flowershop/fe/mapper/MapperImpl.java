package com.accenture.flowershop.fe.mapper;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class MapperImpl extends DozerBeanMapper implements Mapper  {

    @Override
    public <T> List<T> mapList(List<?> sources, Class<T> destinationClass) {
        ArrayList<T> targets = new ArrayList<T>();
        for (Object source : sources) {
            targets.add(super.map(source, destinationClass));
        }
        return targets;
    }
}
