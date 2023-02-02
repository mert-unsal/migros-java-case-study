package com.migros.casestudy.factory;

import com.migros.casestudy.domain.event.CourierEvent;

import java.util.List;

public interface Factory {
    <T> List<T> readResource();
}
