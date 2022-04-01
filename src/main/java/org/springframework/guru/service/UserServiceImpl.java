package org.springframework.guru.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.guru.domain.UserSPDetail;
import org.springframework.guru.repository.AddUserRepositoryImpl;
import org.springframework.guru.request.AddUserRequest;
import org.springframework.guru.response.AddUserResponse;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl{

    @Autowired
    private AddUserRepositoryImpl addUserRepository;

    public AddUserResponse addUsers(AddUserRequest addUserRequest) {
        return addUsers(addUserRequest.getEmail(), addUserRequest.getFirstName(), addUserRequest.getMiddleName(),
                addUserRequest.getLastName(), addUserRequest.getGender(), addUserRequest.getPhoneNumber(),
                addUserRequest.getSummary(), addUserRequest.getProfileImg(), addUserRequest.getExperience());
    }

    private AddUserResponse addUsers(String email, String firstName, String middleName, String lastName, String gender,
            String phoneNumber, String summary, String profileImg, int experience) {

        AddUserResponse spResp = null;
        UserSPDetail userSPDetail = new UserSPDetail();
        userSPDetail.setEmail(email);
        userSPDetail.setFirstName(firstName);
        userSPDetail.setMiddleName(middleName);
        userSPDetail.setLastName(lastName);
        userSPDetail.setGender(gender);
        userSPDetail.setPhoneNumber(phoneNumber);
        userSPDetail.setSummary(summary);
        userSPDetail.setProfileImg(profileImg);
        userSPDetail.setExperience(experience);
        spResp = addUserRepository.addUsers(userSPDetail);
        return spResp;
    }
}