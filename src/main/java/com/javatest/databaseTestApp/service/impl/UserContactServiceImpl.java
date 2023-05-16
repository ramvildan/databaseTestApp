package com.javatest.databaseTestApp.service.impl;

import com.javatest.databaseTestApp.converter.UserContactConverter;
import com.javatest.databaseTestApp.dto.UserContactCreateDto;
import com.javatest.databaseTestApp.dto.UserContactDto;
import com.javatest.databaseTestApp.dto.UserContactUpdateDto;
import com.javatest.databaseTestApp.entity.UserDetails;
import com.javatest.databaseTestApp.exception.AlreadyExistsException;
import com.javatest.databaseTestApp.exception.UserNotFoundException;
import com.javatest.databaseTestApp.repository.UserDetailsRepository;
import com.javatest.databaseTestApp.service.UserContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserContactServiceImpl implements UserContactService {

    private final UserDetailsRepository userDetailsRepository;

    private final UserContactConverter userContactConverter;

    @Override
    public UserContactDto create(Integer userId, UserContactCreateDto userContactCreateDto) {

        if(userDetailsRepository.findByUserIdAndIsDeletedIsFalse(userId).isEmpty()) {

            UserDetails newUserContact = UserDetails.builder()
                    .surname(userContactCreateDto.getSurname())
                    .name(userContactCreateDto.getName())
                    .patronymic(userContactCreateDto.getPatronymic())
                    .phoneNumber(userContactCreateDto.getPhoneNumber())
                    .createdAt(new Date())
                    .updatedAt(new Date())
                    .isDeleted(false)
                    .build();

            return userContactConverter.fromUserDetailsToUserContactDto(
                    userDetailsRepository.save(newUserContact)
            );
        }

        throw new AlreadyExistsException("This user already exists");
    }

    @Override
    public List<UserContactDto> readAll() {
        return userDetailsRepository.findAllByIsDeletedIsFalse().stream()
                .map(userContactConverter::fromUserDetailsToUserContactDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserContactDto update(Integer userId, UserContactUpdateDto userContactUpdateDto) {

        UserDetails userContactToUpdate = userDetailsRepository
                .findByUserIdAndIsDeletedIsFalse(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        userContactToUpdate.setSurname(userContactToUpdate.getSurname());
        userContactToUpdate.setName(userContactToUpdate.getName());
        userContactToUpdate.setPatronymic(userContactToUpdate.getPatronymic());
        userContactToUpdate.setPhoneNumber(userContactToUpdate.getPhoneNumber());
        userContactToUpdate.setUpdatedAt(new Date());

        return userContactConverter.fromUserDetailsToUserContactDto(
                userDetailsRepository.save(userContactToUpdate)
        );
    }

    @Override
    public void delete(Integer userId) {

        UserDetails userContactToDelete = userDetailsRepository
                .findByUserIdAndIsDeletedIsFalse(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        userContactToDelete.setIsDeleted(true);
        userContactToDelete.setUpdatedAt(new Date());

        userContactConverter.fromUserDetailsToUserContactDto(
                userDetailsRepository.save(userContactToDelete)
        );
    }
}
