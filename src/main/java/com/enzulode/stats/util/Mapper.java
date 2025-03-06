package com.enzulode.stats.util;

import java.sql.ResultSet;

public interface Mapper<T> {

  public T map(ResultSet rs);
}
