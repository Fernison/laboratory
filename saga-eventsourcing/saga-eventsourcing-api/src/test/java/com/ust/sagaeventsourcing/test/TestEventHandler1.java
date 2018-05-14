package com.ust.sagaeventsourcing.test;

import java.util.Random;
import com.ust.sagaeventsourcing.event.EventHandlerMethod;
import com.ust.sagaeventsourcing.event.EventSubscriber;
import com.ust.sagaeventsourcing.event.Eventide;

@EventSubscriber(application="mi app3333", saga="my saga1")
public class TestEventHandler1 {

	@EventHandlerMethod(event= "my event1_1", status="ALTA OK")
	public Eventide<TestSimpleData> operacion1_1(Eventide<TestSimpleData> event) {
		try {
		    long rd=new Random().nextInt(10)*1000;
		    System.out.println("MyEventHandlers veryLongMethod "+rd);
		    try {
		        Thread.sleep(rd);
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		    System.out.println("MyEventHandlers Terminado "+rd);
		    return event;
		} catch (Exception e) {
			return null;
		}		
	}

	@EventHandlerMethod(event= "my event1_2", status="ALTA KO")
	public Eventide<TestSimpleData> operacion1_2(Eventide<TestSimpleData> event) {
		return null;
	}
	
}
