package com.example.aspectexample.service;

import com.example.aspectexample.aspect.LogExecutionTime;
import com.example.aspectexample.dto.UserRequest;
import com.example.aspectexample.exception.UserEmailAlreadyExistsException;
import com.example.aspectexample.exception.UserIdAlreadyExistsException;
import com.example.aspectexample.exception.UserNameAlreadyExistsException;
import com.example.aspectexample.exception.UserNotFoundException;
import com.example.aspectexample.model.UserEntity;
import com.example.aspectexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
   // private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    // ModelMapper: Bir nesne modelinin Dto adındaki nesne modeliyle eşlenmesi için kullanılır.
    //private final ModelMapper modelMapper;

    private final UserMapperService userMapperService;


    @Override
    public UserEntity getUser(int id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public UserEntity createUser(UserRequest userRequest) {

        // Optional: NullPointerException hatasını önlemek için kullanılır.
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userRequest.getId());

        // .isPresent : Mevcut bir deger varsa true yoksa false döndürür
        if (optionalUserEntity.isPresent()) {
            throw new UserNotFoundException();
        }
        if (userRepository.getByEmail(userRequest.getEmail()).isPresent()) {
            throw new UserEmailAlreadyExistsException();
        }

        if (userRepository.getByUserName(userRequest.getUserName()).isPresent()) {
            throw new UserEmailAlreadyExistsException();
        }

        //UserEntity userEntity = modelMapper.map(userRequest, UserEntity.class);
        UserEntity userEntity1 = userMapperService.toDto(userRequest);

        return userRepository.save(userEntity1);
    }

    @Override
    public UserEntity updateUser(int id, UserRequest userRequest) {

        UserEntity newUser = userRepository.findById(id).orElseThrow(UserIdAlreadyExistsException::new);

        newUser.setFirstName(userRequest.getFirstName());
        newUser.setLastName(userRequest.getLastName());
        newUser.setYearOfBirth(userRequest.getYearOfBirth());

        Optional<UserEntity> optinalUser=userRepository.getByEmail(userRequest.getEmail());
        if(newUser.getEmail() != userRequest.getEmail() && optinalUser.isPresent() ) {
            throw new UserEmailAlreadyExistsException();
        }

        optinalUser=userRepository.getByUserName(userRequest.getUserName());
        if(newUser.getUserName() != userRequest.getUserName() && optinalUser.isPresent() ) {
            throw new UserNameAlreadyExistsException();
        }

        return userRepository.save(newUser);
    }

    @Override
    @LogExecutionTime
    public void deleteUser(int id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.delete(userEntity);
    }
}
