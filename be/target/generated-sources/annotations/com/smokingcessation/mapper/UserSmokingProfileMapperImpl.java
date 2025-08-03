package com.smokingcessation.mapper;

import com.smokingcessation.dto.UserSmokingProfileRequest;
import com.smokingcessation.model.UserSmokingProfile;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-03T13:52:44+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class UserSmokingProfileMapperImpl implements UserSmokingProfileMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserSmokingProfileRequest toDto(UserSmokingProfile entity) {
        if ( entity == null ) {
            return null;
        }

        UserSmokingProfileRequest userSmokingProfileRequest = new UserSmokingProfileRequest();

        userSmokingProfileRequest.setUser( userMapper.toDto( entity.getUser() ) );
        userSmokingProfileRequest.setProfileId( entity.getProfileId() );
        userSmokingProfileRequest.setCigarettesPerDay( entity.getCigarettesPerDay() );
        userSmokingProfileRequest.setCigarettesPerPack( entity.getCigarettesPerPack() );
        userSmokingProfileRequest.setCigarettePackCost( entity.getCigarettePackCost() );
        userSmokingProfileRequest.setQuitDate( entity.getQuitDate() );
        userSmokingProfileRequest.setEndDate( entity.getEndDate() );
        userSmokingProfileRequest.setStatus( entity.getStatus() );

        return userSmokingProfileRequest;
    }

    @Override
    public UserSmokingProfile toEntity(UserSmokingProfileRequest dto) {
        if ( dto == null ) {
            return null;
        }

        UserSmokingProfile.UserSmokingProfileBuilder userSmokingProfile = UserSmokingProfile.builder();

        userSmokingProfile.user( userMapper.toEntity( dto.getUser() ) );
        userSmokingProfile.profileId( dto.getProfileId() );
        userSmokingProfile.cigarettesPerDay( dto.getCigarettesPerDay() );
        userSmokingProfile.cigarettesPerPack( dto.getCigarettesPerPack() );
        userSmokingProfile.cigarettePackCost( dto.getCigarettePackCost() );
        userSmokingProfile.quitDate( dto.getQuitDate() );
        userSmokingProfile.endDate( dto.getEndDate() );
        userSmokingProfile.status( dto.getStatus() );

        return userSmokingProfile.build();
    }
}
