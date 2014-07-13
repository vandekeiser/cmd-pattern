package cla;

import java.util.List;

import cla.command.undo.memento.Memento;
import cla.command.undo.memento.MementoableCommand;
import cla.dao.CustomerDao;
import cla.domain.customer.Customer;

public class CustomerCommands {

	public static MementoableCommand createCustomer(String name, CustomerDao dao) {
		return new MementoableCommand() {
			@Override public void execute() {
				dao.persist(new Customer(name));				
			}
			
			@Override public Memento takeSnapshot() {
				List<Customer> allCustomers = dao.findAll();
				return () -> {
					dao.deleteAll();
					allCustomers.stream().forEach(p -> dao.merge(p));
					//allCustomers.stream().forEach(p -> dao.persist(p));//KO: PersistentObjectException: detached entity passed to persist
				};
			}
		};
	}

}
