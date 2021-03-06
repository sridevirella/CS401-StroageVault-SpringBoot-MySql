package com.cs401.storagevault.services;

import com.cs401.storagevault.model.repository.*;
import com.cs401.storagevault.model.tables.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class DBService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private PricingRepository pricingRepository;
    @Autowired
    private DeviceRegistrationRepo deviceRegistrationRepo;
    @Autowired
    private SpaceRequestRepository spaceRequestRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;

    public List<User> listAllUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<String> getBrands() {
        return brandRepository.findAllBrands();
    }

    public Map<String, Set<String>> getModels(String brand) {
        Map<String, Set<String>> models = new HashMap<>();

       Set<String> modelSet = new HashSet<>();
       modelSet.add("Nothing Selected");
       modelSet.addAll(modelRepository.findByBrandName(brand));
       models.put(brand, modelSet);
        return models;
    }

    public double getPricingModel(char customerType, String subscriptionType) {

        if(subscriptionType.equals("Y"))
            return pricingRepository.yearlySubscriptionPrice(customerType);
        else
            return pricingRepository.findByCustomerType(customerType);
    }

    public void saveDeviceRegistrationDetails(DeviceRegistration deviceRegistration) {
        deviceRegistrationRepo.save(deviceRegistration);
    }

    public List<DeviceRegistration> getUserLentDeviceDetails(String email) {
        return deviceRegistrationRepo.findByUserEmail(email);
    }

    public void saveConsumerSpaceRequestDetails(SpaceRequest spaceRequest) {
        spaceRequestRepository.save(spaceRequest);
    }

    public void updateUsageSpace(String email, Long usageBytes) {
        int usedSpace = spaceRequestRepository.getRequestsByEmail(email).getUsedSpace();
        spaceRequestRepository.updateUsage(email, (usedSpace + usageBytes.intValue()));
    }

    public SpaceRequest getConsumerSpaceRequests(String email) {
        return spaceRequestRepository.getRequestsByEmail(email);
    }

    public String getConsumersPriceDetails(String email) {
        return spaceRequestRepository.getPrice(email);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    public void saveTotalPriceToCart(Cart cart) {
        cartRepository.save(cart);
    }

    public Cart getTotalPrice(String email) {
        return cartRepository.findById(email).get();
    }


}
