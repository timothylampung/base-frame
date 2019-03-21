package my.spotit.asset.identity.business.service;

import my.spotit.AbstractTest;
import my.spotit.asset.helper.IdentityServiceHelper;
import my.spotit.asset.identity.domain.dao.RecursiveGroupException;
import my.spotit.asset.identity.domain.model.DexActorType;
import my.spotit.asset.identity.domain.model.DexGroup;
import my.spotit.asset.identity.domain.model.DexPrincipal;
import my.spotit.asset.identity.domain.model.DexPrincipalRole;
import my.spotit.asset.identity.domain.model.DexPrincipalRoleImpl;
import my.spotit.asset.identity.domain.model.DexPrincipalType;
import my.spotit.asset.identity.domain.model.DexRoleType;
import my.spotit.asset.identity.domain.model.DexUser;
import my.spotit.asset.identity.domain.model.DexUserImpl;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

public class IdentityServiceImplTest extends AbstractTest {
    private static final Logger LOG = LoggerFactory.getLogger(IdentityServiceImplTest.class);

    @Autowired
    private IdentityService identityService;
    @Autowired
    private IdentityServiceHelper identityServiceHelper;

    @Test
    @Rollback(true)//TODO change to true
    public void pool_user() {
        identityServiceHelper.changeUser("root");

        List<UserAccount> accounts = new ArrayList<>();
        accounts.add(new UserAccount("timothy.lampung", "Timothy Lampung", "timothy.lampung@gmail.com", "950808136453", DexActorType.FACILITY_MANAGER));
        accounts.add(new UserAccount("amirul.yunik", "Amirul Yunik", "amirul.yunik@gmail.com", "238912399923", DexActorType.STAFF));
        accounts.add(new UserAccount("zamir.zaharul", "Hafidz Zakaria", "zamir.zaharul@gmail.com", "348938398934", DexActorType.SUPERVISOR));
        accounts.add(new UserAccount("zamir.zaharul", "Hafidz Zakaria", "zamir.zaharul@gmail.com", "348938398934", DexActorType.TECHNICIAN));

//        for (UserAccount account : accounts) {
//            if (account.type.equals(DexActorType.STAFF)) {
//                save_technician(account);
//            } else if (account.type.equals(DexActorType.FACILITY_MANAGER)) {
//                save_instructor(account);
//            } else if (account.type.equals(DexActorType.SUPERVISOR)) {
//                save_instructor(account);
//            } else if (account.type.equals(DexActorType.TECHNICIAN)) {
//                save_instructor(account);
//            }
//        }
    }


    public void save_technician(UserAccount account) {

//        Create technician User Account
        DexUser user = new DexUserImpl();
        user.setEmail(account.getEmail());
        user.setPassword("abc123");
        user.setRealName(account.getRealname());
        user.setUsername(account.getName());
        user.setPrincipalType(DexPrincipalType.USER);
        identityService.saveUser(user);

//        Check technician user account user exist
        DexUser savedUser = identityService.findUserByUsername(account.getName());
        Assert.assertNotNull(savedUser);

//        give role to technician user account
        DexPrincipalRole role = new DexPrincipalRoleImpl();
        role.setRole(DexRoleType.ROLE_USER);
        identityService.addPrincipalRole(savedUser, role);

////        Role should not be empty
//        DexUser assigenedRoleUser = identityService.findUserByUsername(username);
//        Assert.assertTrue(!assigenedRoleUser.getRoles().isEmpty());

//      Create technician Actor
        String code = "TECH_" + System.currentTimeMillis();
    }

    private void addToUserGroup(DexPrincipal principal) {
        DexGroup grp_usr = identityService.findGroupByName("GRP_USR");
        try {
            identityService.addGroupMember(grp_usr, principal);
        } catch (RecursiveGroupException e) {
            e.printStackTrace();
        }
    }


//    public void save_instructor(UserAccount account) {
//        //        Create Instructor User Account
//        DexUser user = new DexUserImpl();
//        user.setEmail(account.getEmail());
//        user.setPassword("abc123");
//        user.setRealName(account.getRealname());
//        user.setUsername(account.getName());
//        user.setPrincipalType(DexPrincipalType.USER);
//        identityService.saveUser(user);
//
////        Check Instructor user account user exist
//        DexUser savedUser = identityService.findUserByUsername(account.getName());
//        Assert.assertNotNull(savedUser);
//
//
////        give role to Instructor user account
//        DexPrincipalRole role = new DexPrincipalRoleImpl();
//        role.setRole(DexRoleType.ROLE_USER);
//        identityService.addPrincipalRole(savedUser, role);
//
//
//        String code = "INSTR_" + System.currentTimeMillis();
//        DexInstructor instructor = new DexInstructorImpl();
//        instructor.setName(account.getRealname());
//        instructor.setAddress1(generateString(10));
//        instructor.setAddress2(generateString(10));
//        instructor.setAddress3(generateString(10));
//        instructor.setCode(code);
//        instructor.setEmail(account.getEmail());
//        instructor.setFax(generateString(5));
//        instructor.setMobile(generateString(12));
//        instructor.setIdentityNo(account.getIdNo());
//        instructor.setPostcode(generateString(5));
//        instructor.setPhone(getRandomId());
//        LOG.debug("instructor --- {}", instructor);
//        identityService.saveInstructor(instructor);
//
//        //        Check technician actor exist
//        DexInstructor savedInstructor = identityService.findInstructorByCode(code);
//        Assert.assertNotNull(savedInstructor);
//
//        savedUser.setActor(savedInstructor);
//        identityService.updateUser(savedUser);
//
////        Check technician is assigned to user account
//        DexUser updatedUser = identityService.findUserByUsername(account.getName());
//        Assert.assertNotNull(updatedUser.getActor());
//
//        addToUserGroup(updatedUser);
//
//
//    }

    class UserAccount {
        private String name;
        private String realname;
        private String email;
        private String idNo;
        private DexActorType type;

        public UserAccount(String name, String realname, String email, String idNo, DexActorType type) {
            this.name = name;
            this.realname = realname;
            this.email = email;
            this.idNo = idNo;
            this.type = type;
        }

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public DexActorType getType() {
            return type;
        }

        public void setType(DexActorType type) {
            this.type = type;
        }
    }
}