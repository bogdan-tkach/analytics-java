package com.segment.analytics;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.UUID;

public abstract class PayloadBuilder<T extends Payload, V extends PayloadBuilder> {
  Map<String, Object> context;
  UUID anonymousId;
  String userId;

  PayloadBuilder() {
    // Hidden from Public API
  }

  public V context(Map<String, Object> context) {
    if (context == null) {
      throw new NullPointerException("Null context");
    }
    this.context = ImmutableMap.copyOf(context);
    return self();
  }

  public V anonymousId(UUID anonymousId) {
    if (anonymousId == null) {
      throw new NullPointerException("Null anonymousId");
    }
    this.anonymousId = anonymousId;
    return self();
  }

  public V userId(String userId) {
    if (userId == null) {
      // todo validate length?
      throw new NullPointerException("Null userId");
    }
    this.userId = userId;
    return self();
  }

  abstract T realBuild();

  abstract V self();

  public T build() {
    if (anonymousId == null && userId == null) {
      throw new IllegalStateException("Either anonymousId or userId must be provided.");
    }
    return realBuild();
  }
}
