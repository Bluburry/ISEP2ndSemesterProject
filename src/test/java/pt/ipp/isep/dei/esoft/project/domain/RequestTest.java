package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.RequestRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("unused")
class RequestTest {

	Person personTest1 = new Person("johndoe@gmail.com", "John Doe", 333444555, 444333555, 555444333);
	Person personTest2 = new Person("janedoe@gmail.com", "Jane Doe", 111222333, 222111333, 333222111);
	Employee employeeTest1 = new Employee("janesmith@gmail.com", "Jane Smith", 999000888, 888000999, 999888000,
			"holderplace",
			"67890", "Arkansas", "Little Rock", "onnud", "agent");
	Employee employeeTest2 = new Employee("johnsmith@gmail.com", "John Smith", 666777888, 777666888, 888777666,
			"placeholder",
			"12345", "Kansas", "Wichita", "dunno", "agent");

	List<String> photosTest = new ArrayList<String>(Arrays.asList("asd.jpg", "abc.jpg", "qwe.png", "def.png"));
	Property landTest = new Land(PropertyType.LAND, 123, 321, photosTest, "placeholder", "12345", "Kansas", "Wichita",
			"dunno");
	Property apartmentTest = new Apartment(PropertyType.APARTMENT, 456, 654, photosTest, "holderplace",
			"67890", "Arkansas", "Little Rock", "onnud", 2, 3, 4, "fridge; AC; central heating;");
	Property houseTest = new House(PropertyType.HOUSE, 789, 987, photosTest, "plaholderce",
			"13579", "Washington", "Small Stone", "something", 3, 5, 2,
			"fridge; AC; central heating; garage; lawn; garden", true, false, SunExposure.NORTH);

	@Test
	void ensureRequestCreation() {
		RequestLease testLease = new RequestLease(apartmentTest, BusinessType.LEASE, personTest1, employeeTest1, 122.32,
				24);
		RequestSale testSale = new RequestSale(landTest, BusinessType.SALE, personTest2, employeeTest2, 1000.59);
		assertNotNull(testLease);
		assertNotNull(testSale);
	}

	@Test
	void testLeaseGets() {
		RequestLease test = new RequestLease(apartmentTest, BusinessType.LEASE, personTest1, employeeTest1, 122.32,
				24);
		assertEquals(apartmentTest, test.getProperty());
		assertEquals(BusinessType.LEASE.getBusinessType(), test.getBusinessType().getBusinessType());
		assertEquals(personTest1, test.getOwner());
		assertEquals(employeeTest1, test.getAgent());
		assertEquals(122.32, test.getRentPrice());
		assertEquals(24, test.getContractDuration());
	}

	@Test
	void testSaleGets() {
		RequestSale test = new RequestSale(landTest, BusinessType.SALE, personTest2, employeeTest2, 1000.59);
		assertEquals(landTest, test.getProperty());
		assertEquals(BusinessType.SALE.getBusinessType(), test.getBusinessType().getBusinessType());
		assertEquals(personTest2, test.getOwner());
		assertEquals(employeeTest2, test.getAgent());
		assertEquals(1000.59, test.getSalePrice());
	}

	@Test
	void testingleaseCompareTo() {
		RequestLease testLease1 = new RequestLease(landTest, BusinessType.LEASE, personTest1, employeeTest1, 122.32,
				24);
		RequestSale testSale1 = new RequestSale(landTest, BusinessType.SALE, personTest2, employeeTest2, 1000.59);
		assertEquals(0, testLease1.compareTo(testSale1));
	}

	@Test
	void testingSaleCompareTo() {
		RequestLease testLease1 = new RequestLease(landTest, BusinessType.LEASE, personTest1, employeeTest1, 122.32,
				24);
		RequestSale testSale1 = new RequestSale(landTest, BusinessType.SALE, personTest2, employeeTest2, 1000.59);
		assertEquals(0, testSale1.compareTo(testLease1));
	}

	@Test
	void testingSaleToString() {
		RequestSale testSale1 = new RequestSale(landTest, BusinessType.SALE, personTest2, employeeTest2, 1000.59);
		String test = String.format("%s - %s\nBusiness Type: %s\nPrice: %.2f$\nProperty information:\n%s",
				personTest2.getName(),
				personTest2.getEmail().toString(), BusinessType.SALE.getBusinessType(), 1000.59, landTest.toString());
		assertEquals(test, testSale1.toString());
	}

	@Test
	void testingRequestToString() {
		RequestSale testSale1 = new RequestSale(landTest, BusinessType.SALE, personTest2, employeeTest2, 1000.59);
		String test = String.format("%s - %s\nBusiness Type: %s\nPrice: %.2f$\nProperty information:\n%s",
				personTest2.getName(),
				personTest2.getEmail().toString(), BusinessType.SALE.getBusinessType(), 1000.59, landTest.toString());
		assertEquals(test, testSale1.toString());
	}

	// Testing Argument Exceptions

	@Test
	void testPropertyExceptions() {
		assertThrows(IllegalArgumentException.class, () -> {
			new RequestLease(null, BusinessType.LEASE, personTest1, employeeTest1,
					122.32,
					24);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new RequestSale(null, BusinessType.SALE, personTest2, employeeTest2, 1000.59);
		});
	}

	@Test
	void testBusinessTypeExceptions() {
		assertThrows(IllegalArgumentException.class, () -> {
			new RequestLease(apartmentTest, BusinessType.SALE, personTest1, employeeTest1,
					122.32,
					24);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new RequestSale(landTest, BusinessType.LEASE, personTest2, employeeTest2, 1000.59);
		});
	}

	@Test
	void testOwnerExceptions() {
		assertThrows(IllegalArgumentException.class, () -> {
			new RequestLease(apartmentTest, BusinessType.LEASE, null, employeeTest1,
					122.32,
					24);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new RequestSale(landTest, BusinessType.SALE, null, employeeTest2, 1000.59);
		});
	}

	@Test
	void testAgentExceptions() {
		assertThrows(IllegalArgumentException.class, () -> {
			new RequestLease(apartmentTest, BusinessType.LEASE, personTest1, null,
					122.32,
					24);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new RequestSale(landTest, BusinessType.SALE, personTest2, null, 1000.59);
		});
	}

	@Test
	void testPriceExceptions() {
		assertThrows(IllegalArgumentException.class, () -> {
			new RequestLease(apartmentTest, BusinessType.LEASE, personTest1, employeeTest1,
					-122.32,
					24);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new RequestSale(landTest, BusinessType.SALE, personTest2, employeeTest2, -1000.59);
		});
	}

	@Test
	void testDurationExceptions() {
		assertThrows(IllegalArgumentException.class, () -> {
			new RequestLease(apartmentTest, BusinessType.LEASE, personTest1, employeeTest1,
					122.32,
					-24);
		});
	}

	RequestLease testLease1 = new RequestLease(landTest, BusinessType.LEASE, personTest1, employeeTest1, 122.32,
			24);
	RequestLease testLease2 = new RequestLease(apartmentTest, BusinessType.LEASE, personTest2, employeeTest1, 244.64,
			12);
	RequestLease testLease3 = new RequestLease(houseTest, BusinessType.LEASE, personTest1, employeeTest2, 122.32,
			36);
	RequestSale testSale1 = new RequestSale(landTest, BusinessType.SALE, personTest2, employeeTest2, 1000.59);
	RequestSale testSale2 = new RequestSale(apartmentTest, BusinessType.SALE, personTest2, employeeTest1, 2000.33);
	RequestSale testSale3 = new RequestSale(houseTest, BusinessType.SALE, personTest1, employeeTest2, 100000.22);

	@Test
	void testEquals() {
		RequestSale test = new RequestSale(landTest, BusinessType.SALE, personTest2, employeeTest2, 1000.59);
		assertTrue(test.equals(testSale1));
	}

	// Utilizing repository

	RequestRepository rr = new RequestRepository();//Repositories.getInstance().getRequestRepository();

	@Test
	void ensureRequestRepositoryNotNull() {
		assertNotNull(rr);
	}

	@Test
	void ensureLeaseCreated() {
		Request test = rr.createRentRequest(landTest, BusinessType.LEASE, personTest1, employeeTest1, 122.32,
				24);
		assertNotNull(test);
		assertEquals(testLease1, test);
	}

	@Test
	void ensureSaleCreated() {
		Request test = rr.createSaleRequest(landTest, BusinessType.SALE, personTest2, employeeTest2, 1000.59);
		assertNotNull(test);
		assertEquals(testSale1, test);
	}

	@Test
	void testingRepositoryList() {
		List<Request> listTest = new ArrayList<Request>(
				Arrays.asList(testLease1, testLease2, testLease3, testSale1, testSale2, testSale3));
		RequestRepository repoTest = new RequestRepository();//Repositories.getInstance().getRequestRepository();

		Request leaseTest1 = repoTest.createRentRequest(landTest, BusinessType.LEASE, personTest1, employeeTest1,
				122.32,
				24);
		Request leaseTest2 = repoTest.createRentRequest(apartmentTest, BusinessType.LEASE, personTest2, employeeTest1,
				244.64,
				12);
		Request leaseTest3 = repoTest.createRentRequest(houseTest, BusinessType.LEASE, personTest1, employeeTest2,
				122.32,
				36);
		Request saleTest1 = repoTest.createSaleRequest(landTest, BusinessType.SALE, personTest2, employeeTest2,
				1000.59);
		Request saleTest2 = repoTest.createSaleRequest(apartmentTest, BusinessType.SALE, personTest2, employeeTest1,
				2000.33);
		Request saleTest3 = repoTest.createSaleRequest(houseTest, BusinessType.SALE, personTest1, employeeTest2,
				100000.22);

		assertEquals(listTest, repoTest.getRequestList());
	}

	@Test
	void testingRepositoryRentList() {
		List<Request> listTest = new ArrayList<Request>(
				Arrays.asList(testLease1, testLease2, testLease3));
		RequestRepository repoTest = new RequestRepository();//Repositories.getInstance().getRequestRepository();

		Request leaseTest1 = repoTest.createRentRequest(landTest, BusinessType.LEASE, personTest1, employeeTest1,
				122.32,
				24);
		Request leaseTest2 = repoTest.createRentRequest(apartmentTest, BusinessType.LEASE, personTest2, employeeTest1,
				244.64,
				12);
		Request leaseTest3 = repoTest.createRentRequest(houseTest, BusinessType.LEASE, personTest1, employeeTest2,
				122.32,
				36);
		Request saleTest1 = repoTest.createSaleRequest(landTest, BusinessType.SALE, personTest2, employeeTest2,
				1000.59);
		Request saleTest2 = repoTest.createSaleRequest(apartmentTest, BusinessType.SALE, personTest2, employeeTest1,
				2000.33);
		Request saleTest3 = repoTest.createSaleRequest(houseTest, BusinessType.SALE, personTest1, employeeTest2,
				100000.22);

		assertEquals(listTest, repoTest.getRentRequestList());
	}

	@Test
	void testingRepositorySaleList() {
		List<Request> listTest = new ArrayList<Request>(
				Arrays.asList(testSale1, testSale2, testSale3));
		RequestRepository repoTest = new RequestRepository();//Repositories.getInstance().getRequestRepository();

		Request leaseTest1 = repoTest.createRentRequest(landTest, BusinessType.LEASE, personTest1, employeeTest1,
				122.32,
				24);
		Request leaseTest2 = repoTest.createRentRequest(apartmentTest, BusinessType.LEASE, personTest2, employeeTest1,
				244.64,
				12);
		Request leaseTest3 = repoTest.createRentRequest(houseTest, BusinessType.LEASE, personTest1, employeeTest2,
				122.32,
				36);
		Request saleTest1 = repoTest.createSaleRequest(landTest, BusinessType.SALE, personTest2, employeeTest2,
				1000.59);
		Request saleTest2 = repoTest.createSaleRequest(apartmentTest, BusinessType.SALE, personTest2, employeeTest1,
				2000.33);
		Request saleTest3 = repoTest.createSaleRequest(houseTest, BusinessType.SALE, personTest1, employeeTest2,
				100000.22);

		assertEquals(listTest, repoTest.getSaleRequestList());
	}

	@Test
	void testingRemoveRequest() {
		List<Request> listTest = new ArrayList<Request>(
				Arrays.asList(testLease1, testLease2, testLease3, testSale1, testSale2, testSale3));
		RequestRepository repoTest = new RequestRepository();//Repositories.getInstance().getRequestRepository();

		Request leaseTest1 = repoTest.createRentRequest(landTest, BusinessType.LEASE, personTest1, employeeTest1,
				122.32,
				24);
		Request leaseTest2 = repoTest.createRentRequest(apartmentTest, BusinessType.LEASE, personTest2, employeeTest1,
				244.64,
				12);
		Request leaseTest3 = repoTest.createRentRequest(houseTest, BusinessType.LEASE, personTest1, employeeTest2,
				122.32,
				36);
		Request saleTest1 = repoTest.createSaleRequest(landTest, BusinessType.SALE, personTest2, employeeTest2,
				1000.59);
		Request saleTest2 = repoTest.createSaleRequest(apartmentTest, BusinessType.SALE, personTest2, employeeTest1,
				2000.33);
		Request saleTest3 = repoTest.createSaleRequest(houseTest, BusinessType.SALE, personTest1, employeeTest2,
				100000.22);

		repoTest.removeRequest(saleTest3);

		assertNotEquals(listTest, repoTest.getRequestList());
	}

	@Test
	void testingGetRequestByAgent() {
		List<Request> listTest = new ArrayList<Request>(
				Arrays.asList(testLease1, testLease2, testSale2));
		RequestRepository repoTest = new RequestRepository();//Repositories.getInstance().getRequestRepository();

		Request leaseTest1 = repoTest.createRentRequest(landTest, BusinessType.LEASE, personTest1, employeeTest1,
				122.32,
				24);
		Request leaseTest2 = repoTest.createRentRequest(apartmentTest, BusinessType.LEASE, personTest2, employeeTest1,
				244.64,
				12);
		Request leaseTest3 = repoTest.createRentRequest(houseTest, BusinessType.LEASE, personTest1, employeeTest2,
				122.32,
				36);
		Request saleTest1 = repoTest.createSaleRequest(landTest, BusinessType.SALE, personTest2, employeeTest2,
				1000.59);
		Request saleTest2 = repoTest.createSaleRequest(apartmentTest, BusinessType.SALE, personTest2, employeeTest1,
				2000.33);
		Request saleTest3 = repoTest.createSaleRequest(houseTest, BusinessType.SALE, personTest1, employeeTest2,
				100000.22);

		assertEquals(listTest, repoTest.getRequestsByAgent(employeeTest1));
		assertEquals(listTest, repoTest.getRequestsByAgentEmail(employeeTest1.getEmail().getEmail()));
	}
}
