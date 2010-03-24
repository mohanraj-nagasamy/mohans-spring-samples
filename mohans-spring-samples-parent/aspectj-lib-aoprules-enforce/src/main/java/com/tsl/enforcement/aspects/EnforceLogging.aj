package com.tsl.enforcement.aspects;

import junit.framework.*;

public aspect EnforceLogging {

	pointcut scope():
		within(com.tsl..*) && !within(TestCase+);

	pointcut printing(): 
		get(* System.out) || get(* System.err) || call(* printStackTrace());

	declare warning
		: scope() && printing()
		: "Don't print to Console, use logger";

}