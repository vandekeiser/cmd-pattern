package cla.domain.customer;

import java.util.List;

import cla.command.undo.memento.Memento;
import cla.command.undo.memento.MementoableCommand;
import cla.dao.CustomerDao;

public class CustomerCommands {

	public static MementoableCommand createCustomer(String name, CustomerDao dao) {
		return new MementoableCommand() {
			@Override public void execute() {
				dao.persist(new Customer(name));				
			}
			
			@Override public Memento takeSnapshot() {
				List<Customer> allCustomers = dao.findAll();
				return () -> {
					//To restore, 
					
					//1. Delete all
					dao.deleteAll();
					
					//2. Recreate the snapshot list of customers
					allCustomers.stream().forEach(p -> dao.merge(p));
					//if persist instead of merge: "PersistentObjectException: detached entity passed to persist"
				};
			}
		};
	}

}
