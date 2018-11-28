package com.ly.common.collect;

import java.util.HashMap;

public final class Maps
{
	private Maps() {}
	
	/**
	   * Creates a <i>mutable</i>, empty {@code HashMap} instance.
	   *
	   * <p><b>Note:</b> if mutability is not required, use {@link
	   * ImmutableMap#of()} instead.
	   *
	   * <p><b>Note:</b> if {@code K} is an {@code enum} type, use {@link
	   * #newEnumMap} instead.
	   *
	   * <p><b>Note for Java 7 and later:</b> this method is now unnecessary and
	   * should be treated as deprecated. Instead, use the {@code HashMap}
	   * constructor directly, taking advantage of the new
	   * <a href="http://goo.gl/iz2Wi">"diamond" syntax</a>.
	   *
	   * @return a new, empty {@code HashMap}
	   */
	  public static <K, V> HashMap<K, V> newHashMap() {return new HashMap<K, V>();}
}
