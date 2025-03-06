package com.enzulode.stats.util;

import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Spliterator;
import java.util.function.Consumer;

@Slf4j
public class ResultSetSpliterator implements Spliterator<ResultSet> {

  private ResultSet rs;

  public ResultSetSpliterator(ResultSet rs) {
    this.rs = rs;
  }

  @Override
  public boolean tryAdvance(Consumer<? super ResultSet> action) {
    try {
      if (rs.next()) {
        action.accept(rs);
        return true;
      }
    } catch (SQLException e) {
      log.error(e.getMessage(), e);
    }
    return false;
  }

  @Override
  public Spliterator<ResultSet> trySplit() {
    return null;
  }

  @Override
  public long estimateSize() {
    return Long.MAX_VALUE;
  }

  @Override
  public int characteristics() {
    return 0;
  }
}
