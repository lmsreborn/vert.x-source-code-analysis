package io.vertx.test.core;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import org.junit.Test;

/**
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class CreateVertxTest extends AsyncTestBase {

  @Test
  public void testCreateSimpleVertx() {
    Vertx vertx = Vertx.vertx();
    assertNotNull(vertx);
  }

  @Test
  public void testCreateVertxWithOptions() {
    VertxOptions options = new VertxOptions();
    Vertx vertx = Vertx.vertx(options);
    assertNotNull(vertx);
  }

  @Test
  public void testFailCreateClusteredVertxSynchronously() {
    VertxOptions options = new VertxOptions();
    options.setClustered(true);
    try {
      Vertx.vertx(options);
      fail("Should throw exception");
    } catch (IllegalArgumentException e) {
      // OK
    }
  }

  @Test
  public void testCreateClusteredVertxAsync() {
    VertxOptions options = new VertxOptions();
    options.setClustered(true);
    Vertx.vertxAsync(options, ar -> {
      assertTrue(ar.succeeded());
      assertNotNull(ar.result());
      testComplete();
    });
    await();
  }

  @Test
  public void testCreateNonClusteredVertxAsync() {
    VertxOptions options = new VertxOptions();
    Vertx.vertxAsync(options, ar -> {
      assertTrue(ar.succeeded());
      assertNotNull(ar.result());
      testComplete();
    });
    await();
  }
}
