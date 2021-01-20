package com.example.hotel.service;

import com.example.hotel.entity.*;
import com.example.hotel.model.*;
import com.example.hotel.repository.UserRepository;
import com.example.hotel.repositoryMo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MigrationService {
    @Autowired
    private HotelMoRepository hotelMoRepository;
    @Autowired
    private CustomerMoRepository customerMoRepository;
    @Autowired
    private ReservationMoRepository reservationMoRepository;
    @Autowired
    private CleaningServiceEmplMoRepository cleaningServiceEmplMoRepository;
    @Autowired
    private UserMoRepository userMoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired CustomerServiceEmplMoRepository customerServiceEmplMoRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private CleaningServiceEmployeeService cleaningServiceEmployeeService;
    @Autowired
    private CustomerServiceEmployeeService customerServiceEmployeeService;

    /**
     * Start migration script
     * @throws Exception
     */
    public void startMigration() throws Exception{
        addHotelsToMongo();
        addCustomersToMongo();
        addReservationsToMongo();
        addCleaningServiceEmplToMongo();
        addUserToMongo();
        addCustomerServiceEmplToMongo();
    }

    /**
     * Migrate hotels in MongoDB
     */
    private void addHotelsToMongo(){
        List<Hotel> hotels = hotelService.getHotels();
        List<HotelMo> hotelsMo = new ArrayList<>();
        for (Hotel hotel: hotels) {
            List<Room> rooms = hotel.getRooms();
            List<RoomMo> roomsMo = new ArrayList<>();
            for (Room room: rooms) {
                roomsMo.add(new RoomMo(room.getRoomID(),room.isAvailable(),room.getType()));
            }
            hotelsMo.add(new HotelMo(sequenceGeneratorService.getSequenceNumber(HotelMo.SEQUENCE_NAME),hotel.getAddress(),roomsMo));
        }
        for (HotelMo hotelMo: hotelsMo) {
            hotelMoRepository.save(hotelMo);
        }
    }

    /**
     * Migrate customers in MongoDB
     */
    private void addCustomersToMongo(){
       List<Customer> customers = customerService.getCustomers();
       List<CustomerMo> customersMo = new ArrayList<>();
        for (Customer customer: customers) {
            customersMo.add(new CustomerMo(sequenceGeneratorService.getSequenceNumber(CustomerMo.SEQUENCE_NAME),customer.getFirstName(),customer.getLastName(),customer.getIdCard()));
        }
       for (CustomerMo customerMo: customersMo) {
           customerMoRepository.save(customerMo);
       }
    }

    /**
     * Migrate reservations to MongoDB
     */
    private  void addReservationsToMongo(){
        List<Reservation> reservations = reservationService.getReservations();
        List<ReservationMo> reservationsMo = new ArrayList<>();
        for (Reservation reservation: reservations) {
            RoomMo roomMo= getRoomMoById(reservation.getRoom().getRoomID());
            reservationsMo.add(new ReservationMo(sequenceGeneratorService.getSequenceNumber(ReservationMo.SEQUENCE_NAME),reservation.getPrice(),
                    reservation.getDate(),reservation.getDuration(),customerMoRepository.findAll().get(0),roomMo));
        }
        for (ReservationMo reservationMo: reservationsMo) {
            reservationMoRepository.save(reservationMo);
        }
    }

    /**
     * Mograte User to MongoDB
     */
    private void addUserToMongo(){
        List<User> users = userRepository.findAll();
        List<UserMo> usersMo = new ArrayList<>();
        for(User user: users){
            usersMo.add(new UserMo(sequenceGeneratorService.getSequenceNumber(UserMo.SEQUENCE_NAME),user.getUsername(),user.getPassword(),user.isLoggedIn()));
        }
        for(UserMo userMo: usersMo){
            userMoRepository.save(userMo);
        }
    }

    /**
     * Get room by Id by reservation
     * @param id
     * @return
     */
    private RoomMo getRoomMoById(int id){
        List<RoomMo> roomsMo = hotelMoRepository.findAll().get(0).getRoomsMo();
        return roomsMo.get(id);
    }

    /**
     * Add cleaning service employees to Mongo
     */
    private void addCleaningServiceEmplToMongo(){
        List<CleaningServiceEmployee> cleaningServiceEmployees = cleaningServiceEmployeeService.getCleaningServiceEmployees();
        List<CleaningServiceEmplMo> cleaningServiceEmplsMo = new ArrayList<>();
        for(CleaningServiceEmployee employee: cleaningServiceEmployees){
            HotelMo hotelMo= getHotelMo(employee.getHotel().getHotelId());
            cleaningServiceEmplsMo.add(new CleaningServiceEmplMo(sequenceGeneratorService.getSequenceNumber(CleaningServiceEmplMo.SEQUENCE_NAME),employee.getFirstName(),
                    employee.getLastName(),employee.getSocialId(),hotelMo,employee.getWorkingHours(),employee.getResponsibility()));
        }
        for (CleaningServiceEmplMo employee: cleaningServiceEmplsMo) {
            cleaningServiceEmplMoRepository.save(employee);
        }

    }
    /**
     * Add customer service employees to Mongo
     */
    private void  addCustomerServiceEmplToMongo() {
        List<CustomerServiceEmployee> customerServiceEmployees = customerServiceEmployeeService.getCustomerServiceEmployees();
        List<CustomerServiceEmplMo> customerServiceEmplsMo = new ArrayList<>();
        for (CustomerServiceEmployee employee : customerServiceEmployees) {
            HotelMo hotelMo= getHotelMo(employee.getHotel().getHotelId());
            UserMo userMo = userMoRepository.findAll().get(0);
            customerServiceEmplsMo.add(new CustomerServiceEmplMo(sequenceGeneratorService.getSequenceNumber(CustomerServiceEmplMo.SEQUENCE_NAME),employee.getFirstName(),
                    employee.getLastName(),employee.getSocialId(),hotelMo,employee.getPhoneNumber(),employee.getEmail(),userMo));
        }
        for(CustomerServiceEmplMo employee: customerServiceEmplsMo){
            customerServiceEmplMoRepository.save(employee);
        }
    }
    /**
     * Get Hotel by Id
     * @param id
     * @return
     */
    private HotelMo getHotelMo(int id){
        Optional<HotelMo> hotelMoOptional = hotelMoRepository.findById(id);
        if (hotelMoOptional.isPresent()) {
            return (hotelMoOptional.get());
        }
        else {
            return null;
        }
    }

}
