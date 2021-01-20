package com.example.hotel.service;

import com.example.hotel.entity.*;
import com.example.hotel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DBFeedingService {
    @Autowired
    private RoomService roomService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private CleaningServiceEmployeeRepository cleaningServiceEmployeeRepository;
    @Autowired
    private CustomerServiceEmployeeRepository customerServiceEmployeeRepository;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private List<Room> roomsDB = new ArrayList<>();
    private List<CleaningServiceEmployee> cleaningEmployeesDB = new ArrayList<>();
    private List<CustomerServiceEmployee> customerServiceEmployeesDB = new ArrayList<>();
    private List<Reservation> reservationsDB = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    String[] roomTypes= {"SINGLE_ROOM","DOUBLE_ROOM","APARTMENT","HONEYMOON_SWEET"};
    String[] names = {"Smith", "John", "Cosmic", "Gate", "Mike", "Ross", "August", "Spring"};
    int[] workingHours ={10,15,20,25,30,35,40};
    String[] responsibility ={"Changing sheets", "Cleaning bathroom","Cleaning floor"};
    String[] emails = {"supermen@gmx.at","batman@gmx.at", "atom@gmx.at", "supermen@gmx.at","crystal@gmx.at","brainiac@gmx.at"};
    double[] price ={210,540,90,220,110};
    String[] dates = {"2021-02-02","2021-03-02","2021-10-10","2021-08-11","2021-03-11"};
    /**
     * Get random 6 digit number
     * @return
     */
    public String getRandomNumber(){
        Random r = new Random();
        return String.format("%06d", Integer.valueOf(r.nextInt(1001)));
    }

    /**
     * Create 100 random Rooms
     */
    public void createRooms(){
        for(int i=0; i<=99; i++){
            roomsDB.add( new Room(i,true, roomTypes[new Random().nextInt(roomTypes.length)]));
        }
    }

    /**
     * Create a new Hotel
     */
    Hotel hotel = new Hotel(1,"Wien,Opernring 1",roomsDB);

    /**
     * Create 30 random cleaning service employees
     */
    public void createCleaningServiseEmployees(){
        for(int i=1; i<=30; i++){
            cleaningEmployeesDB.add( new CleaningServiceEmployee(i,names[new Random().nextInt(names.length)],names[new Random().nextInt(names.length)],
                    getRandomNumber(), hotel,workingHours[new Random().nextInt(workingHours.length)],
                    responsibility[new Random().nextInt(responsibility.length)]));
        }
    }
    /**
     * Create Login admin user
     */
    User user = new User("admin", "pass");

    /**
     * Create 20 random customer service employees
     */
    public void createCustomerServiseEmployees(){
        for(int i=1; i<=20; i++){
            customerServiceEmployeesDB.add( new CustomerServiceEmployee(i,names[new Random().nextInt(names.length)],names[new Random().nextInt(names.length)],
                    getRandomNumber(), hotel,"+43"+getRandomNumber(),
                    emails[new Random().nextInt(emails.length)],user));
        }
    }

    /**
     * Create first Customer
     */
    Customer customer = new Customer(1,"Max", "Musterman", "2835778");

    /**
     * create 5 reservations with 5 new customers
     */
    public void createReservations(){
        for (int i=0; i<=4;i++){
            reservationsDB.add(new Reservation(i,price[new Random().nextInt(price.length)],dates[new Random().nextInt(dates.length)],
                    i, customers.get(0),roomsDB.get(i)));
        }
    }


    /**
     * Feeding Service
     */
    public void startFeedingDB() throws Exception{
        createRooms();
        addHotelToDB(hotel);
        createCleaningServiseEmployees();
        addCleaningEmployeesToDB();
        addUserToDB(user);
        createCustomerServiseEmployees();
        addCustomerEmployeesToDB();
        addCustomerToDb();
        createReservations();
        addReservationsToDB();
    }

    /**
     * Add Hotel to DB
     * @param hotel
     */
    private void addHotelToDB(Hotel hotel){
        hotelService.addHotel(hotel);
    }

    /**
     * Add user to DB
     * @param user
     */
    private void addUserToDB(User user){
        userRepository.save(user);
    }

    /**
     * Add cleaning service employees to DB
     */
    private void addCleaningEmployeesToDB(){
        for (CleaningServiceEmployee employee: cleaningEmployeesDB ) {
            cleaningServiceEmployeeRepository.save(employee);
        }
    }

    /**
     * Add customer service employees to DB
     */
    private void addCustomerEmployeesToDB(){
        for (CustomerServiceEmployee employee: customerServiceEmployeesDB ) {
            customerServiceEmployeeRepository.save(employee);
        }
    }

    /**
     * Add Customer to DB
     */
    private void addCustomerToDb(){
        customerRepository.save(customer);
        customers = customerRepository.findAll();
    }



    /**
     * Add customer service employees to DB
     */
    private void addReservationsToDB(){
        for (Reservation reservation: reservationsDB ) {
            reservationService.saveReservation(reservation);
        }
    }
}
