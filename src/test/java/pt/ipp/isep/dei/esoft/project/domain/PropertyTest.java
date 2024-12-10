package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.PropertyRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

    @Test
    void ensureLandCreation() {
        List<String> photos = new ArrayList<String>();
        photos.add("asd.jpg");
        photos.add("abc.jpg");
        photos.add("qwe.png");
        photos.add("def.png");
        Land test = new Land(PropertyType.LAND, 123, 321, photos, "placeholder", "12345", "Kansas", "Wichita", "dunno");
        assertNotNull(test, "land is null");
    }

    @Test
    void ensureApartmentCreation() {
        List<String> photos = new ArrayList<String>();
        photos.add("asd.jpg");
        photos.add("abc.jpg");
        photos.add("qwe.png");
        photos.add("def.png");
        Apartment test = new Apartment(PropertyType.APARTMENT, 456, 654, photos, "holderplace",
                "67890", "Arkansas", "Little Rock", "onnud", 2, 2, 2, "fridge; AC; central heating;");
        assertNotNull(test, "apartment is null");
    }

    @Test
    void ensureHouseCreation() {
        List<String> photos = new ArrayList<String>();
        photos.add("asd.jpg");
        photos.add("abc.jpg");
        photos.add("qwe.png");
        photos.add("def.png");
        House test = new House(PropertyType.HOUSE, 789, 987, photos, "plaholderce",
                "13579", "Washington", "Small Stone", "something", 3, 5,
                "fridge; AC; central heating; garage; lawn; garden", true, false, SunExposure.NORTH);
        assertNotNull(test, "apartment is null");
    }

    @Test
    void landGets() {
        List<String> photos = new ArrayList<String>();
        photos.add("asd.jpg");
        photos.add("abc.jpg");
        photos.add("qwe.png");
        photos.add("def.png");
        Land test = new Land(PropertyType.LAND, 123, 321, photos, "placeholder", "12345", "Kansas", "Wichita", "dunno");
        assertEquals("LAND", test.getPropertyType());
        assertEquals(123, test.getArea());
        assertEquals(321, test.getDistance());
        assertEquals(photos, test.getPhotographs());
        assertEquals("placeholder", test.getStreet());
        assertEquals("12345", test.getZip());
        assertEquals("Kansas", test.getState());
        assertEquals("Wichita", test.getCity());
        assertEquals("dunno", test.getDistrict());
    }

    @Test
    void ApartmentGets() {
        List<String> photos = new ArrayList<String>();
        photos.add("asd.jpg");
        photos.add("abc.jpg");
        photos.add("qwe.png");
        photos.add("def.png");
        Apartment test = new Apartment(PropertyType.APARTMENT, 456, 654, photos, "holderplace",
                "67890", "Arkansas", "Little Rock", "onnud", 2, 3, 4, "fridge; AC; central heating;");
        assertEquals("APARTMENT", test.getPropertyType());
        assertEquals(456, test.getArea());
        assertEquals(654, test.getDistance());
        assertEquals(photos, test.getPhotographs());
        assertEquals("holderplace", test.getStreet());
        assertEquals("67890", test.getZip());
        assertEquals("Arkansas", test.getState());
        assertEquals("Little Rock", test.getCity());
        assertEquals("onnud", test.getDistrict());
        assertEquals(2, test.getNumberBedrooms());
        assertEquals(3, test.getNumberParking());
        assertEquals(4, test.getNumberBathrooms());
        assertEquals("fridge; AC; central heating;", test.getAvailableEquipment());
    }

    @Test
    void HouseGets() {
        List<String> photos = new ArrayList<String>();
        photos.add("asd.jpg");
        photos.add("abc.jpg");
        photos.add("qwe.png");
        photos.add("def.png");
        House test = new House(PropertyType.HOUSE, 789, 987, photos, "plaholderce",
                "13579", "Washington", "Small Stone", "something", 3, 5, 2,
                "fridge; AC; central heating; garage; lawn; garden", true, false, SunExposure.NORTH);
        assertEquals("HOUSE", test.getPropertyType());
        assertEquals(789, test.getArea());
        assertEquals(987, test.getDistance());
        assertEquals(photos, test.getPhotographs());
        assertEquals("plaholderce", test.getStreet());
        assertEquals("13579", test.getZip());
        assertEquals("Washington", test.getState());
        assertEquals("Small Stone", test.getCity());
        assertEquals("something", test.getDistrict());
        assertEquals(3, test.getNumberBedrooms());
        assertEquals(5, test.getNumberParking());
        assertEquals(2, test.getNumberBathrooms());
        assertEquals("fridge; AC; central heating; garage; lawn; garden", test.getAvailableEquipment());
        assertEquals(true, test.getBasement());
        assertEquals(false, test.getLoft());
        assertEquals(SunExposure.NORTH.getSunExposure(), test.getSunExposure());
    }

    // Using repositoryequals
    PropertyRepository propertyRepository = new PropertyRepository();//Repositories.getInstance().getPropertyRepository();
    List<String> photosTest = new ArrayList<String>(Arrays.asList("asd.jpg", "abc.jpg", "qwe.png", "def.png"));
    Land landTest = new Land(PropertyType.LAND, 123, 321, photosTest, "placeholder", "12345", "Kansas", "Wichita",
            "dunno");
    Apartment apartmentTest = new Apartment(PropertyType.APARTMENT, 456, 654, photosTest, "holderplace",
            "67890", "Arkansas", "Little Rock", "onnud", 2, 3, 4, "fridge; AC; central heating;");
    House houseTest = new House(PropertyType.HOUSE, 789, 987, photosTest, "plaholderce",
            "13579", "Washington", "Small Stone", "something", 3, 5, 2,
            "fridge; AC; central heating; garage; lawn; garden", true, false, SunExposure.NORTH);

    @Test
    void testToString() {
        String landString = "Kansas, Wichita, dunno, placeholder, 12345\nProperty type: LAND\nArea in square meters: 123.00 | Distance from city center: 321.00";
        assertEquals(landString, landTest.toString());
        String apartmentString = "Arkansas, Little Rock, onnud, holderplace, 67890\nProperty type: APARTMENT\nArea in square meters: 456.00 | Distance from city center: 654.00";
        assertEquals(apartmentString, apartmentTest.toString());
        String houseString = "Washington, Small Stone, something, plaholderce, 13579\nProperty type: HOUSE\nArea in square meters: 789.00 | Distance from city center: 987.00";
        assertEquals(houseString, houseTest.toString());
    }

    @Test
    void createLand() {
        List<String> photos = new ArrayList<String>();
        photos.add("asd.jpg");
        photos.add("abc.jpg");
        photos.add("qwe.png");
        photos.add("def.png");
        Property test = propertyRepository.createNewProperty(PropertyType.LAND, 123, 321, photos, "placeholder",
                "12345", "Kansas", "Wichita", "dunno");
        assertNotNull(test);
    }

    @Test
    void createApartment() {
        List<String> photos = new ArrayList<String>();
        photos.add("asd.jpg");
        photos.add("abc.jpg");
        photos.add("qwe.png");
        photos.add("def.png");
        Property test = propertyRepository.createNewApartmentProperty(PropertyType.APARTMENT, 456, 654, photos, 2, 3, 4,
                "fridge; AC; central heating;", "holderplace",
                "67890", "Arkansas", "Little Rock", "onnud");
        assertNotNull(test);
    }

    @Test
    void createHouse() {
        List<String> photos = new ArrayList<String>();
        photos.add("asd.jpg");
        photos.add("abc.jpg");
        photos.add("qwe.png");
        photos.add("def.png");
        Property test = propertyRepository.createNewHouseProperty(PropertyType.HOUSE, 789, 987, photos, 3, 5, 2,
                "fridge; AC; central heating; garage; lawn; garden", true, false, SunExposure.NORTH, "plaholderce",
                "13579", "Washington", "Small Stone", "something");
        assertNotNull(test);
    }

    @Test
    void testLandEquals() {
        List<String> photos = new ArrayList<String>();
        photos.add("asd.jpg");
        photos.add("abc.jpg");
        photos.add("qwe.png");
        photos.add("def.png");
        Property test = propertyRepository.createNewProperty(PropertyType.LAND, 123, 321, photos, "placeholder",
                "12345", "Kansas", "Wichita", "dunno");
        assertTrue(test.equals(landTest));
    }

    @Test
    void testApartmentEquals() {
        List<String> photos = new ArrayList<String>();
        photos.add("asd.jpg");
        photos.add("abc.jpg");
        photos.add("qwe.png");
        photos.add("def.png");
        Property test = propertyRepository.createNewApartmentProperty(PropertyType.APARTMENT, 456, 654, photos, 2, 3, 4,
                "fridge; AC; central heating;", "holderplace",
                "67890", "Arkansas", "Little Rock", "onnud");
        assertTrue(test.equals(apartmentTest));
    }

    @Test
    void testHouseEquals() {
        List<String> photos = new ArrayList<String>();
        photos.add("asd.jpg");
        photos.add("abc.jpg");
        photos.add("qwe.png");
        photos.add("def.png");
        Property test = propertyRepository.createNewHouseProperty(PropertyType.HOUSE, 789, 987, photos, 3, 5, 2,
                "fridge; AC; central heating; garage; lawn; garden", true, false, SunExposure.NORTH, "plaholderce",
                "13579", "Washington", "Small Stone", "something");
        assertTrue(test.equals(houseTest));
    }

    @Test
    void testExceptionOnInvalidPropertyType() {
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewProperty(PropertyType.APARTMENT, 123, 321, photosTest, "placeholder",
                    "12345", "Kansas", "Wichita", "dunno");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewApartmentProperty(PropertyType.HOUSE, 456, 654, photosTest, 2, 3, 4,
                    "fridge; AC; central heating;", "holderplace",
                    "67890", "Arkansas", "Little Rock", "onnud");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewHouseProperty(PropertyType.LAND, 789, 987, photosTest, 3, 5, 2,
                    "fridge; AC; central heating; garage; lawn; garden", true, false, SunExposure.NORTH, "plaholderce",
                    "13579", "Washington", "Small Stone", "something");
        });
    }

    @Test
    void testExceptionOnInvalidArea() {
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewProperty(PropertyType.LAND, -123, 321, photosTest, "placeholder",
                    "12345", "Kansas", "Wichita", "dunno");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewApartmentProperty(PropertyType.APARTMENT, -456, 654, photosTest, 2, 3, 4,
                    "fridge; AC; central heating;", "holderplace",
                    "67890", "Arkansas", "Little Rock", "onnud");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewHouseProperty(PropertyType.HOUSE, -789, 987, photosTest, 3, 5, 2,
                    "fridge; AC; central heating; garage; lawn; garden", true, false, SunExposure.NORTH, "plaholderce",
                    "13579", "Washington", "Small Stone", "something");
        });
    }

    @Test
    void testExceptionOnInvalidDistance() {
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewProperty(PropertyType.LAND, 123, -321, photosTest, "placeholder",
                    "12345", "Kansas", "Wichita", "dunno");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewApartmentProperty(PropertyType.APARTMENT, 456, -654, photosTest, 2, 3, 4,
                    "fridge; AC; central heating;", "holderplace",
                    "67890", "Arkansas", "Little Rock", "onnud");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewHouseProperty(PropertyType.HOUSE, 789, -987, photosTest, 3, 5, 2,
                    "fridge; AC; central heating; garage; lawn; garden", true, false, SunExposure.NORTH, "plaholderce",
                    "13579", "Washington", "Small Stone", "something");
        });
    }

    @Test
    void testExceptionOnInvalidPhotos() {
        List<String> photos = new ArrayList<String>();
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewProperty(PropertyType.LAND, 123, 321, photos, "placeholder",
                    "12345", "Kansas", "Wichita", "dunno");
        });
        photos.add("asd.jpg");
        photos.add("abc.jpg");
        photos.add("qwe.png");
        photos.add("def");
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewApartmentProperty(PropertyType.APARTMENT, 456, 654, photos, 2, 3, 4,
                    "fridge; AC; central heating;", "holderplace",
                    "67890", "Arkansas", "Little Rock", "onnud");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewHouseProperty(PropertyType.HOUSE, 789, 987, photos, 3, 5, 2,
                    "fridge; AC; central heating; garage; lawn; garden", true, false, SunExposure.NORTH, "plaholderce",
                    "13579", "Washington", "Small Stone", "something");
        });
    }

    @Test
    void testExceptionOnInvalidLocation() {
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewProperty(PropertyType.LAND, 123, 321, photosTest, "",
                    "12345", "Kansas", "Wichita", "dunno");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewProperty(PropertyType.LAND, 123, 321, photosTest, "",
                    "12345", "Kansas", "Wichita", "");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewApartmentProperty(PropertyType.APARTMENT, 456, 654, photosTest, 2, 3, 4,
                    "fridge; AC; central heating;", "holderplace",
                    "67890", "", "Little Rock", "onnud");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewHouseProperty(PropertyType.HOUSE, 789, 987, photosTest, 3, 5, 2,
                    "fridge; AC; central heating; garage; lawn; garden", true, false, SunExposure.NORTH, "plaholderce",
                    "13579", "Washington", "", "something");
        });
    }

    @Test
    void testExceptionOnInvalidZip() {
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewProperty(PropertyType.LAND, 123, 321, photosTest, "placeholder",
                    "123", "Kansas", "Wichita", "dunno");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewApartmentProperty(PropertyType.APARTMENT, 456, 654, photosTest, 2, 3, 4,
                    "fridge; AC; central heating;", "holderplace",
                    "6780", "Arkansas", "Little Rock", "onnud");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewHouseProperty(PropertyType.HOUSE, 789, 987, photosTest, 3, 5, 2,
                    "fridge; AC; central heating; garage; lawn; garden", true, false, SunExposure.NORTH, "plaholderce",
                    "1357", "Washington", "Small Stone", "something");
        });
    }

    @Test
    void testExceptionOnInvalidNumbers() {
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewApartmentProperty(PropertyType.APARTMENT, 456, 654, photosTest, -2, 3, 4,
                    "fridge; AC; central heating;", "holderplace",
                    "67890", "Arkansas", "Little Rock", "onnud");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewApartmentProperty(PropertyType.APARTMENT, 456, 654, photosTest, 2, -3, 4,
                    "fridge; AC; central heating;", "holderplace",
                    "67890", "Arkansas", "Little Rock", "onnud");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewHouseProperty(PropertyType.HOUSE, 789, 987, photosTest, 3, 5, -2,
                    "fridge; AC; central heating; garage; lawn; garden", true, false, SunExposure.NORTH, "plaholderce",
                    "13579", "Washington", "Small Stone", "something");
        });
    }

    @Test
    void testExceptionOnInvalidEquipment() {
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewApartmentProperty(PropertyType.APARTMENT, 456, 654, photosTest, 2, 3, 4,
                    null, "holderplace",
                    "67890", "Arkansas", "Little Rock", "onnud");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewHouseProperty(PropertyType.HOUSE, 789, 987, photosTest, 3, 5, 2,
                    null, true, false, SunExposure.NORTH, "plaholderce",
                    "13579", "Washington", "Small Stone", "something");
        });
    }

    @Test
    void testExceptionOnInvalidSunExposure() {
        assertThrows(IllegalArgumentException.class, () -> {
            propertyRepository.createNewHouseProperty(PropertyType.HOUSE, 789, 987, photosTest, 3, 5, 2,
                    "fridge; AC; central heating; garage; lawn; garden", true, false, null, "plaholderce",
                    "13579", "Washington", "Small Stone", "something");
        });
    }

    PropertyRepository propertyTest = new PropertyRepository();

    Property p1 = propertyTest.createNewProperty(PropertyType.LAND, 123, 321, photosTest, "placeholder", "12345",
            "Kansas", "Wichita", "dunno");
    Property p2 = propertyTest.createNewApartmentProperty(PropertyType.APARTMENT, 456, 654, photosTest, 2, 3, 4,
            "fridge; AC; central heating;", "holderplace", "67890", "Arkansas", "Little Rock", "onnud");
    Property p3 = propertyTest.createNewHouseProperty(PropertyType.HOUSE, 789, 987, photosTest, 3, 5, 2,
            "fridge; AC; central heating; garage; lawn; garden", true, false, SunExposure.NORTH, "plaholderce", "13579",
            "Washington", "Small Stone", "something");

    List<Property> testProperties = new ArrayList<Property>(Arrays.asList(p1, p2, p3));
    List<Land> testLand = new ArrayList<Land>(Arrays.asList((Land) p1));
    List<Apartment> testApartment = new ArrayList<Apartment>(Arrays.asList((Apartment) p2));
    List<House> testHouse = new ArrayList<House>(Arrays.asList((House) p3));

    @Test
    void testPropertyList() {
        assertEquals(testProperties, propertyTest.getPropertyList());
    }

    @Test
    void testPropertyListByType() {
        assertEquals(testLand, propertyTest.getPropertyListByPropertyType(PropertyType.LAND));
        assertEquals(testApartment, propertyTest.getPropertyListByPropertyType(PropertyType.APARTMENT));
        assertEquals(testHouse, propertyTest.getPropertyListByPropertyType(PropertyType.HOUSE));
    }
}