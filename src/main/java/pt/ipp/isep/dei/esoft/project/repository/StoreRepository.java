package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Serialization;
import pt.ipp.isep.dei.esoft.project.domain.Store;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class StoreRepository {

    private List<Store> stores = new ArrayList<>();
    private static String SERFILE = "StoreRepository.ser";

    public void saveStoreRepository() {
        Serialization.toWrite((List<Object>) (List<?>) stores, SERFILE);
    }

    public void loadStoreRepository() {
        this.stores = (List<Store>) (List<?>) Serialization.toRead(SERFILE);
    }

    /**
     * This method returns a defensive (immutable) copy of the list of stores.
     *
     * @return The list of stores.
     */
    public List<Store> getStoresList() {
        return List.copyOf(stores);
    }

    // GET AGENT LIST
    public List<Employee> getAgentList() {
        List<Employee> storeAgentList = new ArrayList<>();
        for (Store store : stores) {
            for (Employee employee : store.getEmployeeList()) {
                if (employee.getRole().compareTo("AGENT") == 0) {
                    storeAgentList.add(employee);
                }
            }
        }
        return storeAgentList;
    }

    public Store getStoreWithEmployee(Employee employee) {
        for (Store s : stores) {
            if (s.getEmployeeList().contains(employee)) {
                return s;
            }
        }
        return null;
    }

    public boolean addEmployeeToStore(Employee employee, String storeID) {
        Boolean userWasAdded = false;
        for (Store store : stores) {
            if (store.getIdStore().compareTo(storeID) == 0) {
                store.addEmployeeToStore(employee);
                userWasAdded = true;
            }
        }
        return userWasAdded;
    }

    public boolean addEmployeeToStores(Employee employee, List<String> storeIDs) {
        int i;
        int counter = 0;
        int storeListSize = storeIDs.size();
        for (Store store : stores) {
            i = 0;
            while (i < storeListSize) {
                if (store.getIdStore().compareTo(storeIDs.get(i)) == 0) {
                    store.addEmployeeToStore(employee);
                    counter++;
                    i = storeListSize;
                }
                i++;
            }
        }
        if (counter == storeListSize) {
            return true;
        } else {
            return false;
        }
    }

    public void createNewStore(String name, String emailAddress, double phoneNumber) {
        Store store = new Store(name, emailAddress, phoneNumber);
        for (Store s : stores) {
            if (s.equals(store))
                throw new IllegalArgumentException("This store already exists in the system");
        }
        stores.add(store);
    }

    public void addNewStore(Store store) {
        for (Store s : stores) {
            if (s.equals(store))
                throw new IllegalArgumentException("This store already exists in the system");
        }
        stores.add(store);
    }

    public Store addStore(String name, String location, String phoneNumber, String email) {
        String[] phone = phoneNumber.split("-");
        phoneNumber = phone[0] + phone[1] + phone[2];
        String[] locationData = location.split(",");
        Store storeExists = checkExistingStore(name, email, Double.parseDouble(phoneNumber), locationData);
        if (storeExists==null) {
            Store newStore = new Store(name, email, Double.parseDouble(phoneNumber), locationData[0],
                    locationData[locationData.length - 1], locationData[locationData.length - 2],
                    locationData[locationData.length - 3], legacyLocationDistrict(locationData));
            stores.add(newStore);
            return newStore;
        }
        return storeExists;
    }

    public Store checkExistingStore(String name,String email, Double phoneNumber, String[] locationData) {
        for (Store store : stores) {
            if (store.getName().equals(name) && store.getEmailAddress().equals(email) && store.getPhoneNumber()==phoneNumber && store.getCity().equals(locationData[locationData.length-3]) && store.getDistrict().equals(legacyLocationDistrict(locationData)) && store.getStreet().equals(locationData[0]) && store.getZip().equals(locationData[locationData.length-1]) && store.getState().equals(locationData[locationData.length-2])) {
                return store;
            }
        }
        return null;
    }

    private String legacyLocationDistrict(String[] location) {
        if (location.length == 4) {
            return "Legacy District";
        }
        return location[1];
    }
}
