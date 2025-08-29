package ru.aidar.service;

import org.springframework.kafka.core.KafkaTemplate;
import ru.aidar.dto.UserRequestDto;
import ru.aidar.dto.UserResponseDto;
import ru.aidar.exception.ResourceNotFoundException;
import ru.aidar.model.User;
import ru.aidar.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private KafkaTemplate<String, String> kafkaTemplate;

    public UserService(UserRepository userRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.userRepository = userRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    // Приватный метод-маппер из Entity в безопасный DTO
    private UserResponseDto toUserResponseDto(User user) {
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt());
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toUserResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::toUserResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        // Проверяем, не заняты ли username или email
        if (userRepository.existsByUsername(userRequestDto.getUsername())) {
            throw new IllegalStateException("Username is already taken");
        }
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new IllegalStateException("Email is already in use");
        }

        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());

        // ВАЖНО: В реальном проекте здесь будет хеширование пароля!
        // String hashedPassword = passwordEncoder.encode(userRequestDto.getPassword());
        // user.setPassword(hashedPassword);
        user.setPassword(userRequestDto.getPassword());

        User savedUser = userRepository.save(user);
        publishUserCreated(user.getEmail());

        return toUserResponseDto(savedUser);
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        // Здесь можно добавить более сложную логику проверки на уникальность, если email/username меняются

        user.setUsername(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword()); // Опять же, здесь должно быть хеширование

        User updatedUser = userRepository.save(user);
        return toUserResponseDto(updatedUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        User user = userRepository.findById(id).get();
        userRepository.deleteById(id);
        publishUserDeleted(user.getEmail());
    }

    public void publishUserCreated(String email) {
        System.out.println("Письмо о создании направлено на email: " + email);
        String message = "Поздравляю, вы были зарегистрированы на нашем сервисе";
        kafkaTemplate.send("user_topic", message);
    }

    public void publishUserDeleted(String email) {
        System.out.println("Письмо о удалении направлено на email: " + email);
        String message = "Не поздравляем, вы были удалены на нашем сервисе";
        kafkaTemplate.send("user_topic", message);
    }
}