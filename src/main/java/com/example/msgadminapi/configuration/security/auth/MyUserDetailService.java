package com.example.msgadminapi.configuration.security.auth;

import com.example.msgadminapi.domain.entity.teacher.Teacher;
import com.example.msgadminapi.domain.repository.TeacherRepository;
import com.example.msgadminapi.domain.repository.UserRepository;
import com.example.msgadminapi.exception.exception.TeacherNotFoundException;
import com.example.msgadminapi.exception.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws TeacherNotFoundException {
        return teacherRepository.findByUserId(userId)
                .map(MyUserDetails::new)
                .orElseThrow(() -> new TeacherNotFoundException());
    }
}
