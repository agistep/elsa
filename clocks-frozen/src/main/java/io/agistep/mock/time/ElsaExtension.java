package io.agistep.mock.time;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;

import java.lang.reflect.Method;
import java.time.ZoneId;
import java.util.Optional;

import static org.junit.platform.commons.support.AnnotationSupport.findAnnotation;

public class ElsaExtension implements InvocationInterceptor {

	//private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(Freeze.class);
	@Override
	public void interceptTestMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
		Optional<Freeze> annotation = findAnnotation(extensionContext.getElement(), Freeze.class);
		if(!annotation.isPresent()) {
			invocation.proceed();
			return;
		}
		Elsa.freeze(annotation.get().value(), invocation::proceed);

	}

}
