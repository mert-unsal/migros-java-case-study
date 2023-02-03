package com.migros.casestudy.factory;

import java.io.IOException;
import java.util.List;

public interface Factory {
    <T> List<T> readResource() throws IOException;
}
