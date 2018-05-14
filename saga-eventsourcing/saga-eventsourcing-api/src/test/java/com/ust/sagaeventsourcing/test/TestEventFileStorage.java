package com.ust.sagaeventsourcing.test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

import com.ust.sagaeventsourcing.event.Eventide;
import com.ust.sagaeventsourcing.saga.Storage;

public final class TestEventFileStorage extends Storage<Eventide<TestSimpleData>> {

	private FileWriter fileWriter;
	
	public TestEventFileStorage() {
		super("FILE-"+UUID.randomUUID(), -1);
		try {
			fileWriter=new FileWriter("Events.log", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void store(Eventide<TestSimpleData> data) throws Exception {
		fileWriter.write(data.toStorageFormat()+"\n");
		fileWriter.flush();
	}

}
