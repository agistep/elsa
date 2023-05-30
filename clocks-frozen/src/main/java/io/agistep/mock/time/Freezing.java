package io.agistep.mock.time;

@FunctionalInterface
public interface Freezing {

	void doProcess() throws Throwable;
}
