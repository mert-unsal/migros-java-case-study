package com.migros.casestudy.factory;

import java.util.List;

public interface Factory {
    <T> List<T> readResource();
}
