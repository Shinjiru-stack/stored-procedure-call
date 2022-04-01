package org.springframework.guru.repository;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.guru.domain.UserSPDetail;
import org.springframework.guru.response.AddUserResponse;
import org.springframework.guru.response.AddUserSPResponse;
import org.springframework.stereotype.Repository;



@Repository
public class AddUserRepositoryImpl {

    private static Logger logger = LoggerFactory.getLogger(AddUserRepositoryImpl.class);

    private final static String USERS_PROC = ".INSERT_JP_USERS";

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private Environment env;

    public AddUserResponse addUsers(UserSPDetail userSPDetail) {
        String dbName = env.getProperty("spring.jpa.properties.hibernate.default_schema");
        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery(dbName + USERS_PROC);

        query.registerStoredProcedureParameter("Email_Param", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("First_Name_Param", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Middle_Name_Param", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Last_Name_Param", String.class, ParameterMode.IN);

        query.registerStoredProcedureParameter("Gender_Param", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Phone_Number_Param", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Summary_Param", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Experience_Param", Integer.class, ParameterMode.IN);

        query.registerStoredProcedureParameter("Profile_Img_Param", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("Result_Param", Integer.class, ParameterMode.OUT);

        query.setParameter("Email_Param", userSPDetail.getEmail());
        query.setParameter("First_Name_Param", userSPDetail.getFirstName());
        query.setParameter("Middle_Name_Param", userSPDetail.getMiddleName());

        query.setParameter("Last_Name_Param", userSPDetail.getLastName());
        query.setParameter("Gender_Param", userSPDetail.getGender());
        query.setParameter("Phone_Number_Param", userSPDetail.getPhoneNumber());

        query.setParameter("Summary_Param", userSPDetail.getSummary());
        query.setParameter("Experience_Param", userSPDetail.getExperience());
        query.setParameter("Profile_Img_Param", userSPDetail.getProfileImg());

        int count = ((Number) query.getOutputParameterValue("Result_Param")).intValue();
        logger.info("SP Response " + count);

        if (count == 1) {
            AddUserResponse usrResp = new AddUserResponse("true", "success");
            usrResp.setData(new AddUserSPResponse("User added successfully."));
            return usrResp;
        } else {
            AddUserResponse usrResp = new AddUserResponse("true", "success");
            usrResp.setData(new AddUserSPResponse("User updated successfully."));
            return usrResp;
        }
    }

}