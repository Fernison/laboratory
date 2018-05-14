package com.ust.sagaeventsourcing.saga;

import org.springframework.stereotype.Component;

/**
 * Class that represents the beginning of a saga
 * @author fcabrero
 *
 */

@Component
public abstract class SagaInitiator<T extends SagaData,Y> {

	public abstract T initiateSaga(Y object);

}
