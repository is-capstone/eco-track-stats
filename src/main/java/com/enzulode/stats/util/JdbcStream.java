package com.enzulode.stats.util;

import java.sql.ResultSet;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JdbcStream {

  public static Stream<ResultSet> stream(ResultSet rs) {
    ResultSetSpliterator spliterator = new ResultSetSpliterator(rs);
    return StreamSupport.stream(spliterator, false);
  }

  public static <T> Stream<T> stream(ResultSet rs, Mapper<T> mapper) {
    ResultSetSpliterator spliterator = new ResultSetSpliterator(rs);
    return StreamSupport.stream(spliterator, false).map(mapper::map);
  }

  private JdbcStream() {}
}
